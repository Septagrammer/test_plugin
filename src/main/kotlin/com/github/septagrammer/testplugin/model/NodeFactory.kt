package com.github.septagrammer.testplugin.model

import com.github.septagrammer.testplugin.model.interfaces.AbstractNode
import com.github.septagrammer.testplugin.model.implementation.BasicNodeImpl
import com.github.septagrammer.testplugin.model.implementation.NodeRefImpl
import com.github.septagrammer.testplugin.model.implementation.RootNodeImpl
import com.github.septagrammer.testplugin.utils.Tags
import org.xml.sax.Attributes

object NodeFactory {
    fun createNode(tag: String, attributes: Attributes?): AbstractNode? {
        return when (tag) {
            Tags.ROOT.tagName -> createRootNode()
            Tags.NODEREF.tagName -> createNodeRef(attributes)
            Tags.NODEA.tagName -> createBasicNode(Tags.NODEA, attributes)
            Tags.NODEB.tagName -> createBasicNode(Tags.NODEB, attributes)
            else -> null
        }
    }

    private fun createRootNode(): AbstractNode {
        val node = RootNodeImpl()
        node.tag = Tags.ROOT

        return node
    }

    private fun createBasicNode(tag: Tags, attributes: Attributes?): AbstractNode {
        val id: String? = attributes?.getValue("id")
        val node = BasicNodeImpl()
        node.id = id
        node.title = attributes?.getValue("title")
        node.tag = tag

        return node
    }

    private fun createNodeRef(attributes: Attributes?): AbstractNode {
        val node = NodeRefImpl()
        node.id = attributes?.getValue("id")
        node.src = attributes?.getValue("src")
        node.tag = Tags.NODEREF

        return node
    }
}