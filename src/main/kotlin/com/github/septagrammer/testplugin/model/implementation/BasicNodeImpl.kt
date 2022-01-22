package com.github.septagrammer.testplugin.model.implementation

import com.github.septagrammer.testplugin.model.interfaces.AbstractNode
import com.github.septagrammer.testplugin.model.interfaces.BasicNode

class BasicNodeImpl: AbstractNode(), BasicNode {
    override var id: String? = null
    override var title: String? = null
    override var value: String? = null
}