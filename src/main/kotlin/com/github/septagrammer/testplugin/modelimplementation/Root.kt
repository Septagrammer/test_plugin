package com.github.septagrammer.testplugin.modelimplementation

import com.github.septagrammer.testplugin.model.ChildOwner
import com.github.septagrammer.testplugin.model.Node

open class Root: ChildOwner {
    override fun getChildren(): List<Node> {
        TODO("Not yet implemented")
    }
}