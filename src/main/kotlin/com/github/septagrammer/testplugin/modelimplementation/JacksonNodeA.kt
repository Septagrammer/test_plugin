package com.github.septagrammer.testplugin.modelimplementation

import com.fasterxml.jackson.annotation.JsonTypeName
import com.github.septagrammer.testplugin.model.Node
import com.github.septagrammer.testplugin.model.NodeA

@JsonTypeName("nodeA")
class JacksonNodeA(override val id: String, override val title: String?, override val value: String?) : NodeA {
    override fun getChildren(): List<Node> {
        TODO("Not yet implemented")
    }

}