package net.besttoolbars.admitad.response.product

import java.time.Instant

data class AdmitadProductOffer(
    val available: Boolean?,

    val id: Long?,

    val bid: Long?,

    val categoryId: String?,

    val currencyId: String?,

    val delivery: Boolean?,

    val description: String?,

    val manufacturerWarranty: Boolean?,

    val model: String?,

    val modifiedTime: Instant?,

    val name: String?,

    val params: List<AdmitadProductOfferParam>,

    val picture: List<String>,

    val price: Double?,

    val notes: String?,

    val url: String?,

    val vendor: String?,

    val vendorCode: String?
) {
    var categoryIds: List<String> = mutableListOf()

    class Builder {
        var available: Boolean? = null
        var id: Long? = null
        var bid: Long? = null
        var categoryId: String? = null
        var currencyId: String? = null
        var delivery: Boolean? = null
        var description: String? = null
        var manufacturerWarranty: Boolean? = null
        var model: String? = null
        var modifiedTime: Instant? = null
        var name: String? = null
        var params: MutableList<AdmitadProductOfferParam> = mutableListOf()
        var picture: MutableList<String> = mutableListOf()
        var price: Double? = null
        var notes: String? = null
        var url: String? = null
        var vendor: String? = null
        var vendorCode: String? = null


        fun build(): AdmitadProductOffer {
            return AdmitadProductOffer(
                available = available,
                id = id,
                bid = bid,
                categoryId = categoryId,
                currencyId = currencyId,
                delivery = delivery,
                description = description,
                manufacturerWarranty = manufacturerWarranty,
                model = model,
                modifiedTime = modifiedTime,
                name = name,
                params = params,
                picture = picture,
                price = price,
                notes = notes,
                url = url,
                vendor = vendor,
                vendorCode = vendorCode
            )
        }
    }
}
