package net.besttoolbars.admitad.response

data class AdmitadMeta(
    val count: Int? = 0,
    val limit: Int? = 0,
    val offset: Int? = 0
) {
    fun hasNextPage(): Boolean {
        if (count == null || limit == null || offset == null) {
            return false
        }
        return count > offset + limit
    }

    fun nextQuery(): Map<String, Any> {
        if (count == null || limit == null || offset == null) {
            throw RuntimeException("Invalid meta data")
        }
        return mapOf("limit" to limit, "offset" to (offset + limit))
    }
}
