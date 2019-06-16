package net.besttoolbars.admitad.response.product

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
