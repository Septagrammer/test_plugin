package com.github.septagrammer.testplugin.utils

import com.github.septagrammer.testplugin.model.interfaces.AbstractNode
import com.github.septagrammer.testplugin.model.interfaces.Tags
import com.github.septagrammer.testplugin.model.implementation.BasicNodeImpl
import com.github.septagrammer.testplugin.model.implementation.NodeRefImpl
import com.github.septagrammer.testplugin.model.implementation.RootNodeImpl
import com.github.septagrammer.testplugin.model.interfaces.BasicNode
import com.github.septagrammer.testplugin.utils.data.DataLoader
import com.github.septagrammer.testplugin.utils.data.DataParser
import org.jetbrains.builtInWebServer.findIndexFile
import org.xml.sax.Attributes
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.*

object NodeFactory {
    fun createNode(tag: String, attributes: Attributes?): AbstractNode? {
        return when (tag) {
            Tags.ROOT.tagName -> createRootNode()
            Tags.NODEREF.tagName -> createNodeRef(Tags.NODEREF, attributes)
            Tags.NODEA.tagName -> createBasicNode(Tags.NODEA, attributes)
            Tags.NODEB.tagName -> createBasicNode(Tags.NODEB, attributes)
            else -> createRootNode()//todo
        }
    }

    private fun createRootNode(): AbstractNode {
        val node = RootNodeImpl()
        node.tag = Tags.ROOT
        return node
    }

    private fun createBasicNode(tag: Tags, attributes: Attributes?): AbstractNode? {
        val id: String? = attributes?.getValue("id")
        val node: BasicNodeImpl = BasicNodeImpl()
        node.id = id
        node.title = attributes?.getValue("title")
        node.tag = tag

        return node
    }

    private fun createNodeRef(tag: Tags, attributes: Attributes?): AbstractNode {
        val node: NodeRefImpl = NodeRefImpl()
        node.id = attributes?.getValue("id")
        node.src = attributes?.getValue("src")
        node.tag = tag
        return node
    }
}