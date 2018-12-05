package com.example.myapplication.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChorePojo(val id: String = "", val name: String = "", val date: String = "",
                     val assignedTo: String = "", val about: String = "", val feedback: String = "",
                     var completed: Boolean = false, val time_to_complete: Int = 0) : Parcelable {

    fun toMap(): Map<String, Any?> {
        return mapOf(
                "id" to id,
                "name" to name,
                "date" to date,
                "assignedTo" to assignedTo,
                "about" to about,
                "feedback" to feedback,
                "completed" to completed,
                "time_to_complete" to time_to_complete
        )
    }
}