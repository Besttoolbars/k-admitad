package net.besttoolbars.admitad.response.product

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
