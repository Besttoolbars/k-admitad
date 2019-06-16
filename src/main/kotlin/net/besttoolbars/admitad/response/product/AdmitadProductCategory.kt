package net.besttoolbars.admitad.response.product

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
