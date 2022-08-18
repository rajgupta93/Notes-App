package com.example.newapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Notes")
class notes(

    @PrimaryKey(autoGenerate = true)
    var id: Int?= null,
    var title: String,
    var subtitle: String,
    var notes: String,
    var priority: Int

    ) : Parcelable