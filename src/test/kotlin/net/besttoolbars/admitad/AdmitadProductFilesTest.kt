package net.besttoolbars.admitad

import net.besttoolbars.admitad.response.product.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.net.URL
import java.time.Instant

internal class AdmitadProductFilesTest {

    fun fileURL(file: String): URL {
        return this.javaClass.classLoader.getResource(file)!!.toURI().toURL()
    }

    @Test
    fun testAeroResponse() {
        val admitadProductFiles = AdmitadProductFiles()
        val fileUrl = fileURL("testdata/products/simplified_AERO.xml")
        val parseCtxList = admitadProductFiles.productAsSeq(fileUrl.toString())

        val verticalSetting = AdmitadProductOfferParam(
            name = "Регулировка высоты",
            value = "Без регулировки"
        )
        val size = AdmitadProductOfferParam(
            name = "РАЗМЕР: Высота до сидения",
            value = "47"
        )
        val weight =
            AdmitadProductOfferParam(name = "Вес (кг)", value = "7.625")
        val colorLatte =
            AdmitadProductOfferParam(name = "Цвет", value = "латте")
        val colorCarcus =
            AdmitadProductOfferParam(name = "Цвет каркаса", value = "Черный")
        val colorAbc =
            AdmitadProductOfferParam(name = "Цвет обивки", value = "Бежевый")
        val sizeLength = AdmitadProductOfferParam(
            name = "РАЗМЕР: Длина (глубина)",
            value = "50"
        )

        val merch = AdmitadProductMerchant(
            name = "AERO: столы и стулья",
            company = "AERO: столы и стулья",
            url = "https://www.mebelaero.ru",
            currencies = listOf(
                AdmitadProductCurrency(
                    id = "RUB",
                    rate = "1"
                )
            ),
            categories = listOf(
                AdmitadProductCategory(
                    id = "22",
                    name = "Товары",
                    parentId = null
                ),
                AdmitadProductCategory(
                    id = "22195",
                    parentId = "22",
                    name = "Столы"
                ),
                AdmitadProductCategory(
                    id = "22190",
                    parentId = "22195",
                    name = "Кухонные столы"
                ),
                AdmitadProductCategory(
                    id = "2255",
                    parentId = "22195",
                    name = "Столы деревянные"
                ),
                AdmitadProductCategory(
                    id = "22188",
                    parentId = "22195",
                    name = "Столы лакированные"
                ),
                AdmitadProductCategory(
                    id = "22108",
                    parentId = "22195",
                    name = "Столы стеклянные"
                )
            ),
            localDeliveryCost = "500",
            cpa = "0"
        )

        val ctxList = listOf(
            ProductParseContext(
                merchant = merch,
                product = AdmitadProductOffer(
                    available = true,
                    id = 8035,
                    bid = null,
                    categoryId = "2265",
                    currencyId = "RUB",
                    delivery = true,
                    description = "табак",
                    manufacturerWarranty = true,
                    model = "B70",
                    modifiedTime = Instant.ofEpochSecond(1539113811),
                    name = "Стул для гостиной металлический B70 – табак",
                    params = listOf(verticalSetting, size, weight),
                    picture = listOf(
                        "https://www.mebelaero.ru/upload/iblock/c51/c51d6c56da31a792b613538f86d67d31.jpg",
                        "https://www.mebelaero.ru/upload/iblock/0d1/0d165d4a5b93fc585eb24c8de7b78d9a.jpg"
                    ),
                    price = 6990.0,
                    notes = "Доставка осуществляется после 100% предоплаты!",
                    url =
                    "https://ad.admitad.com/g/fj77fd2gew162aae1f4e9bc0ba5f2e/?i=5&ulp=https%3A%2F%2Fwww.mebelaero.ru%2Fcatalog%2Ftovar%2Fstul-b70-tob%2F",
                    vendor = "AERO",
                    vendorCode = "8035"
                )
            ),
            ProductParseContext(
                merchant = merch,
                product = AdmitadProductOffer(
                    available = true,
                    id = 8362,
                    bid = null,
                    categoryId = "2265",
                    currencyId = "RUB",
                    delivery = true,
                    description = "белый",
                    manufacturerWarranty = true,
                    model = "B70",
                    modifiedTime = Instant.ofEpochSecond(1541111177),
                    name = "Стул кухонный металлический B70 – белый",
                    params = listOf(verticalSetting, size, weight),
                    picture = listOf(
                        "https://www.mebelaero.ru/upload/iblock/f8e/f8ea9da04a44336ff097b26434fbdd2e.jpg",
                        "https://www.mebelaero.ru/upload/iblock/df0/df06e9661edfd6ac5c881cb5cdd1d9e7.jpg"
                    ),
                    price = 6990.0,
                    notes = "Доставка осуществляется после 100% предоплаты!",
                    url =
                    "https://ad.admitad.com/g/fj77fd2gew162aae1f4e9bc0ba5f2e/?i=5&ulp=https%3A%2F%2Fwww.mebelaero.ru%2Fcatalog%2Ftovar%2Fstul-b70-w%2F",
                    vendor = "AERO",
                    vendorCode = "8362"
                )
            ),
            ProductParseContext(
                merchant = merch,
                product = AdmitadProductOffer(
                    available = true,
                    id = 43523,
                    bid = null,
                    categoryId = "2265",
                    currencyId = "RUB",
                    delivery = true,
                    description = "латте",
                    manufacturerWarranty = true,
                    model = "B805",
                    modifiedTime = Instant.ofEpochSecond(1548866473),
                    name = "Стул для гостиной металлический B805 – латте",
                    params = listOf(colorLatte, colorCarcus, colorAbc, sizeLength),
                    picture = listOf(
                        "https://www.mebelaero.ru/upload/iblock/65f/65f68ce2664c5aa6f592f9595e5f4d4a.jpg",
                        "https://www.mebelaero.ru/upload/iblock/b4e/b4ee49e98d9e855bd7c5a76ba0a6af6a.jpg"
                    ),
                    price = 9290.0,
                    notes = "Доставка осуществляется после 100% предоплаты!",
                    url =
                    "https://ad.admitad.com/g/fj77fd2gew162aae1f4e9bc0ba5f2e/?i=5&ulp=https%3A%2F%2Fwww.mebelaero.ru%2Fcatalog%2Ftovar%2Fstul-b805-cow-latte%2F",
                    vendor = "AERO",
                    vendorCode = "43523"
                )
            )
        )

        Assertions.assertEquals(ctxList, parseCtxList.toList())
    }

//    @Test
//    fun richeIsParsed() {
//        val admitadProductFiles = AdmitadProductFiles()
//        val fileUrl = fileURL("testdata/products/Riche.xml")
//        val product = admitadProductFiles.product(fileUrl.toString())
//        assertNotNull(product)
//        assertNotNull(product.shop)
//    }
//
//    @Test
//    fun allsoftIsParsed() {
//        val admitadProductFiles = AdmitadProductFiles()
//        val fileUrl = fileURL("testdata/products/osnovnoi_products_20190307_082026.xml")
//        val product = admitadProductFiles.product(fileUrl.toString())
//        assertNotNull(product)
//        assertNotNull(product.shop)
//    }

//    @Test
//    fun testStreamReader() {
//        val admitadProductFiles = AdmitadProductFiles()
//        val fileUrl = fileURL("testdata/products/simplified_AERO.xml")
//        val product = admitadProductFiles.productAsSeq(fileUrl.toString(), { c -> println("callcabck cat $c") })
//    }
}
