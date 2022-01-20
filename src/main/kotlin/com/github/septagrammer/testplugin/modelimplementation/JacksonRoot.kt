package com.github.septagrammer.testplugin.modelimplementation

import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.xml.annotate.JacksonXmlElementWrapper
import com.fasterxml.jackson.xml.annotate.JacksonXmlProperty
import com.fasterxml.jackson.xml.annotate.JacksonXmlRootElement
import com.github.septagrammer.testplugin.model.Node
import com.github.septagrammer.testplugin.model.Root


@JacksonXmlRootElement(localName = "root")
class JacksonRoot: Root {
    override fun getChildren(): List<Node> {
        return nodes
    }

    @JacksonXmlElementWrapper(localName = "node")
    private lateinit var nodes: List<Node>

    fun getEntries(): List<Node> {
        return nodes
    }

    fun setEntries(nodes: List<Node>) {
        this.nodes = nodes
    }
}