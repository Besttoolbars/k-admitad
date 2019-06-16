package net.besttoolbars.admitad

import com.fasterxml.aalto.stax.InputFactoryImpl
import net.besttoolbars.admitad.response.product.*
import org.apache.commons.io.FileUtils
import java.io.File
import java.net.URL
import java.nio.file.Files
import java.time.Instant
import java.util.*
import java.util.UUID.randomUUID
import java.util.stream.Stream
import java.util.stream.StreamSupport
import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamReader
import javax.xml.stream.events.XMLEvent.*

class AdmitadProductFiles {
    fun productAsSeq(url: String): Sequence<ProductParseContext> {
        var file: File? = null
        val deleteFile = {
            file?.runCatching {
                if (exists()) {
                    FileUtils.forceDelete(this)
                }
            }
        }
        try {
            file = loadProduct(url)
            val xmlInputFactory = InputFactoryImpl()
            xmlInputFactory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false)
            xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false)

            val r = xmlInputFactory.createXMLStreamReader(Files.newInputStream(file.toPath()))
            return parseProductXmlAsSeq(r) {
                r.close()
                deleteFile()
            }
        } catch (e: Exception) {
            deleteFile()
            throw RuntimeException(e)
        }
    }

    fun productAsJavaStream(url: String): Stream<ProductParseContext> {
        val sequence = productAsSeq(url)
        return StreamSupport.stream(
            Spliterators.spliteratorUnknownSize(sequence.iterator(), Spliterator.ORDERED),
            false
        )
    }

    private fun loadProduct(url: String): File {
        val website = URL(url)
        val filePath = ".admitad/products/${randomUUID()}.xml"
        val file = File(filePath)
        if (!file.parentFile.exists()) {
            file.parentFile.mkdirs()
        }
        FileUtils.copyURLToFile(website, file)
        return file
    }

    private fun parseProductXmlAsSeq(r: XMLStreamReader, clearCallback: () -> Unit): Sequence<ProductParseContext> {
        var type: Int
        var path = ""
        lateinit var productBuilder: AdmitadProductOffer.Builder
        lateinit var paramBuilder: AdmitadProductOfferParam.Builder
        lateinit var categoryBuilder: AdmitadProductCategory.Builder
        lateinit var currencyBuilder: AdmitadProductCurrency.Builder
        var productCtx: ProductParseContext? = null
        val merchantBuilder = AdmitadProductMerchant.Builder()

        return sequence {
            try {
                do {
                    type = r.next()
                    when (type) {
                        START_ELEMENT -> {
                            path += "/${r.name}"
                            when (path) {
                                "$shopPath/currencies/currency" -> {
                                    currencyBuilder = AdmitadProductCurrency.Builder()
                                    for (i in 0 until r.attributeCount) {
                                        when (r.getAttributeName(i).localPart) {
                                            "id" -> currencyBuilder.id = r.getAttributeValue(i)
                                            "rate" -> currencyBuilder.rate = r.getAttributeValue(i)
                                        }
                                    }
                                }
                                "$shopPath/categories/category" -> {
                                    categoryBuilder = AdmitadProductCategory.Builder()
                                    for (i in 0 until r.attributeCount) {
                                        when (r.getAttributeName(i).localPart) {
                                            "id" -> categoryBuilder.id = r.getAttributeValue(i)
                                            "parentId" -> categoryBuilder.parentId = r.getAttributeValue(i)
                                        }
                                    }
                                }
                                offerPath -> {
                                    productBuilder = AdmitadProductOffer.Builder()
                                    for (i in 0 until r.attributeCount) {
                                        when (r.getAttributeName(i).localPart) {
                                            "id" -> productBuilder.id = r.getAttributeValue(i).tryToLong()
                                            "available" -> productBuilder.available =
                                                r.getAttributeValue(i).tryToBoolean()
                                        }
                                    }
                                }
                                "$offerPath/param" -> {
                                    paramBuilder = AdmitadProductOfferParam.Builder()
                                    for (i in 0 until r.attributeCount) {
                                        when (r.getAttributeName(i).localPart) {
                                            "name" -> paramBuilder.name = r.getAttributeValue(i)
                                        }
                                    }
                                }
                            }
                        }
                        CHARACTERS -> {
                            when (path) {
                                "$shopPath/url" -> merchantBuilder.url = r.text
                                "$shopPath/cpa" -> merchantBuilder.cpa = r.text
                                "$shopPath/name" -> merchantBuilder.name = r.text
                                "$shopPath/company" -> merchantBuilder.company = r.text
                                "$shopPath/local_delivery_cost" -> merchantBuilder.localDeliveryCost = r.text
                                "$shopPath/categories/category" -> categoryBuilder.name = r.text
                                "$offerPath/categoryId" -> productBuilder.categoryId = r.text
                                "$offerPath/currencyId" -> productBuilder.currencyId = r.text
                                "$offerPath/delivery" -> productBuilder.delivery = r.text.tryToBoolean()
                                "$offerPath/description" -> productBuilder.description = r.text
                                "$offerPath/manufacturer_warranty" -> productBuilder.manufacturerWarranty =
                                    r.text.tryToBoolean()
                                "$offerPath/modified_time" -> productBuilder.modifiedTime = r.text.tryToInstant()
                                "$offerPath/picture" -> productBuilder.picture.add(r.text)
                                "$offerPath/price" -> productBuilder.price = r.text.tryToDouble()
                                "$offerPath/sales_notes" -> productBuilder.notes = r.text
                                "$offerPath/url" -> productBuilder.url = r.text
                                "$offerPath/vendor" -> productBuilder.vendor = r.text
                                "$offerPath/vendorCode" -> productBuilder.vendorCode = r.text
                                "$offerPath/name" -> productBuilder.name = r.text
                                "$offerPath/model" -> productBuilder.model = r.text
                                "$offerPath/param" -> paramBuilder.value = r.text
                            }
                        }
                        END_ELEMENT -> {
                            when (path) {
                                offerPath -> {
                                    productCtx = productCtx?.copy(product = productBuilder.build())
                                        ?: ProductParseContext(
                                            product = productBuilder.build(),
                                            merchant = merchantBuilder.build()
                                        )
                                    productCtx?.let {
                                        yield(it)
                                    }
                                }
                                "$offerPath/param" -> productBuilder.params.add(paramBuilder.build())
                                "$shopPath/currencies/currency" -> {
                                    merchantBuilder.currencies.add(currencyBuilder.build())
                                }
                                "$shopPath/categories/category" -> {
                                    merchantBuilder.categories.add(categoryBuilder.build())
                                }
                            }
                            path = path.removeSuffix("/${r.name}")
                        }
                    }

                } while (type != END_DOCUMENT)
            } finally {
                clearCallback()
            }
        }
    }

    private fun String?.tryToLong() = try {
        this?.toLong()
    } catch (e: Exception) {
        null
    }

    private fun String?.tryToBoolean() = try {
        this?.toBoolean()
    } catch (e: Exception) {
        null
    }

    private fun String?.tryToDouble() = try {
        this?.toDouble()
    } catch (e: Exception) {
        null
    }

    private fun String?.tryToInstant() = try {
        this?.toLong()?.let { Instant.ofEpochSecond(it) }
    } catch (e: Exception) {
        null
    }

    companion object {
        const val shopPath = "/yml_catalog/shop"
        const val offerPath = "$shopPath/offers/offer"
    }
}
