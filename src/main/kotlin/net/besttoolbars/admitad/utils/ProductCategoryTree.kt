package net.besttoolbars.admitad.utils

import net.besttoolbars.admitad.response.product.AdmitadProductCategory
import java.util.*
import java.util.stream.Stream
import kotlin.streams.toList

object ProductCategoryTree {
    fun initNodeTree(categories: List<AdmitadProductCategory>): Map<String, ProductTreeNode> {
        val map = categories.map(ProductCategoryTree::categoryToNode)
            .map { it.id to it }
            .toMap()

        for (node in map.values) {
            if (node.parentId == null) {
                continue
            }
            val productTreeNode = map[node.parentId] ?: continue
            productTreeNode.children.add(node)
            node.parent = productTreeNode
        }

        return map
    }

    fun categoryToNode(category: AdmitadProductCategory): ProductTreeNode {
        val id = category.id ?: "-1"
        val parentId = if (category.parentId == id) null else category.parentId
        return ProductTreeNode(id = id, parentId = parentId, name = category.name)
    }

    fun categoriesIds(id: String?, map: Map<String, ProductTreeNode>): List<String> {
        val node = map[id] ?: return listOf()
        return Stream.concat(
            childCategoryIds(node),
            parentIds(node)
        )
            .filter(Objects::nonNull)
            .filter { v -> !v.isBlank() }
            .distinct()
            .sorted()
            .toList()
    }

    fun childCategoryIds(node: ProductTreeNode?): Stream<String> {
        if (node == null) {
            return Stream.empty()
        }
        if (node.children.isEmpty()) {
            return Stream.of(node.id)
        }
        val childIdsStream = node.children
            .stream()
            .flatMap(ProductCategoryTree::childCategoryIds)
        return Stream.concat(Stream.of<String>(node.id), childIdsStream)
    }

    fun parentIds(node: ProductTreeNode?, depth: Int = 1): Stream<String> {
        if (node == node?.parent) {
            return Stream.empty()
        }
        if (depth > 20) {
            return Stream.empty()
        }
        val ids = node?.parent?.let {
            Stream.concat(
                Stream.of<String>(it.id),
                parentIds(it, depth + 1)
            )
        }
        return ids ?: Stream.empty()
    }
}
