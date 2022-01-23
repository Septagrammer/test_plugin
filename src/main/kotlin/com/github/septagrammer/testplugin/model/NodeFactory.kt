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
        return RootNodeImpl()
    }

    private fun createBasicNode(tag: Tags, attributes: Attributes?): AbstractNode {
        val id: String? = attributes?.getValue("id")

        return BasicNodeImpl(id, attributes?.getValue("title"), tag)
    }

    private fun createNodeRef(attributes: Attributes?): AbstractNode {
        return NodeRefImpl(attributes?.getValue("src"), attributes?.getValue("id"))
    }
}