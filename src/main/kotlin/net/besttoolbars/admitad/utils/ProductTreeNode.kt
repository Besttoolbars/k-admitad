package net.besttoolbars.admitad.utils

data class ProductTreeNode(
    val id: String,

    val parentId: String? = null,

    val name: String? = null,

    val children: MutableList<ProductTreeNode> = mutableListOf(),

    var parent: ProductTreeNode? = null
)

