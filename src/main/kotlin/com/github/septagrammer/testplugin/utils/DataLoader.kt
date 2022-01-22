package com.github.septagrammer.testplugin.utils

class DataLoader {
   fun load(filename: String): String? {
       return kotlin.runCatching {
           this.javaClass.classLoader.getResource(filename).readText()
       }.getOrNull()
   }
}