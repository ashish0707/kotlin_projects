package com.example.myapplication.pojo

data class ChorePojo(val name: String, val date: String, val assignedTo: String,
                     val about: String) {

    fun toMap(): Map<String, Any?> {
        return mapOf(
                "name" to name,
                "date" to date,
                "assignedTo" to assignedTo,
                "about" to about
        )
    }
}