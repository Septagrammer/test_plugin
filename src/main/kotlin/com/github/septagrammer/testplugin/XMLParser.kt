package com.github.septagrammer.testplugin

import com.fasterxml.jackson.xml.XmlMapper
import com.github.septagrammer.testplugin.model.Root
import java.io.File
import java.io.FileInputStream


object XMLParser {

    fun parse(data: String): Root{
        val xmlMapper = XmlMapper()
        val value: Root = xmlMapper.readValue(data, Root::class.java)
        return value
    }


}