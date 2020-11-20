package com.example.submission8.database.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "todo")
@Parcelize
data class Todo (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int? = null,

    @ColumnInfo(name = "title")
    var title : String? = null,

    @ColumnInfo(name = "deskription")
    var deskription : String? = null,

    @ColumnInfo(name = "date")
    var date : String? = null
    ) : Parcelable