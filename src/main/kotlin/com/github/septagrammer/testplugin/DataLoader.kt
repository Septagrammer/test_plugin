package com.github.septagrammer.testplugin

class DataLoader {
   fun load(filename: String): String? {
       return kotlin.runCatching {
           this.javaClass.classLoader.getResource(filename).readText()
       }.getOrNull()
   }
}