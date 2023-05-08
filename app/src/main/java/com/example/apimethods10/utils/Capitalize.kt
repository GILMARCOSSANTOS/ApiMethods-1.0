package com.example.apiregisteruser_10.utils

import java.util.*

class Capitalize {

    fun capitalize(str: String): String {
        return str.trim().split("\\s+".toRegex())
            .map { it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() } }.joinToString(" ")
    }

}