package com.github.septagrammer.testplugin.model.implementation

import com.github.septagrammer.testplugin.model.interfaces.AbstractNode
import com.github.septagrammer.testplugin.utils.Tags

class NodeRefImpl(val src: String?, val id: String?): AbstractNode(Tags.NODEREF) {
    override fun buildStringToShow(): String {
        return "This should not be there"
    }
}