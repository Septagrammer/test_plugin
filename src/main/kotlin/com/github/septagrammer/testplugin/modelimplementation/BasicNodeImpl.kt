package com.github.septagrammer.testplugin.modelimplementation

import com.github.septagrammer.testplugin.model.AbstractNode
import com.github.septagrammer.testplugin.model.BasicNode

class BasicNodeImpl: AbstractNode(), BasicNode {

    override var id: String? = null
    override var title: String? = null
    override var value: String? = null

    /*override fun addChild(child: AbstractNode) {
        child.parent = this
        children!!.add(child)
    }*/
}