package net.besttoolbars.admitad.response.product

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
