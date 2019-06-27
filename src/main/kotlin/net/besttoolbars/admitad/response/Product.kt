package net.besttoolbars.admitad.response

import java.time.Instant

data class AdmitadProductCategory(
    val id: String?,
    val parentId: String?,
    val name: String?
) {
    class Builder {
        var id: String? = null
        var parentId: String? = null
        var name: String? = null

        fun build() = AdmitadProductCategory(id, parentId, name)
    }
}

data class AdmitadProductCurrency(
    val id: String?,
    val rate: String?
) {
    class Builder {
        var id: String? = null
        var rate: String? = null

        fun build() = AdmitadProductCurrency(id, rate)
    }
}

data class AdmitadProductMerchant(
    val name: String?,

    val company: String?,

    val url: String?,

    val currencies: List<AdmitadProductCurrency>,

    val categories: List<AdmitadProductCategory>,

    val localDeliveryCost: String?,

    val cpa: String?

//    @field:XmlElementWrapper(name = "offers")
//    @field:XmlElement(name = "offer")
//    val offers: List<AdmitadProductOffer> = mutableListOf()
) {
//    fun populateOfferCategories() {
//        if (categories.isEmpty()) {
//            return
//        }
//        val map = ProductCategoryTree.initNodeTree(categories)
//        if (offers.isEmpty()) {
//            return
//        }
//        for (offer in offers) {
//            val ids = ProductCategoryTree.categoriesIds(offer.categoryId, map)
//            offer.categoryIds = ids
//        }
//    }

    class Builder {
        var name: String? = null
        var company: String? = null
        var url: String? = null
        var currencies: MutableList<AdmitadProductCurrency> = mutableListOf()
        var categories: MutableList<AdmitadProductCategory> = mutableListOf()
        var localDeliveryCost: String? = null
        var cpa: String? = null

        fun build() = AdmitadProductMerchant(
            name = name,
            company = company,
            url = url,
            currencies = currencies,
            categories = categories,
            localDeliveryCost = localDeliveryCost,
            cpa = cpa
        )
    }
}

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

data class AdmitadProductOfferParam(
    val name: String? = null,
    val value: String? = null
) {
    class Builder {
        var name: String? = null
        var value: String? = null

        fun build() = AdmitadProductOfferParam(name, value)
    }
}

data class AdmitadProductResponse(
    val date: String? = null,

    val shop: AdmitadProductMerchant? = null
)
