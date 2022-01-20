package com.github.septagrammer.testplugin.modelimplementation

import com.fasterxml.jackson.annotation.JsonTypeName
import com.github.septagrammer.testplugin.model.Node
import com.github.septagrammer.testplugin.model.NodeB

@JsonTypeName("nodeB")
class JacksonNodeB(override val id: String, override val title: String?, override val value: String?) : NodeB {
    override fun getChildren(): List<Node> {
        TODO("Not yet implemented")
    }
}