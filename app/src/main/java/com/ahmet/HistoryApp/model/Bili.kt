package com.ahmet.HistoryApp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "user2")
@Parcelize
data class Bili(
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "details")
    val details: String,
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "location")
    val location: String,
    @ColumnInfo(name = "name")
    val name: String
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id3: Int = 0
}