package com.github.septagrammer.testplugin.model

interface Node {
    fun getChildren(): List<Node>

    val id: String

    val title: String?

    val value: String?
}