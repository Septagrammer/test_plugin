package com.github.septagrammer.xml.model.implementation

import com.github.septagrammer.xml.model.interfaces.AbstractNode
import com.github.septagrammer.xml.utils.Tags

class NodeRefImpl(val src: String?, val id: String?): AbstractNode(Tags.NODEREF) {
    override fun buildStringToShow(): String {
        return "This should not be there"
    }
}