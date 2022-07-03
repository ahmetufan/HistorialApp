package com.ahmet.HistoryApp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

data class KategoriModel(
    val history_kategori: List<Kategori>
)
data class Kategori(
    val id: String,
    val name: String
)

data class DetailModel(
    val history: List<Detail>
)
data class Detail(
    val date: String,
    val details: String,
    val id: String,
    val image: String,
    val kategori_id: String,
    val loaction: String,
    val name: String
)




@Parcelize
data class Post(
    val name: String,
    val country: String,
    val age: String,
    val image: Int,
    val history: String,
) : Parcelable {
    var count=0
}
        // name - image - date - location - details
@Parcelize
data class War(
    val image: Int,
    val name: String,
    val time: String,
    val konum: String,
    val detay: String
) : Parcelable {
    var count=0
}


//Not Model
@Parcelize
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
) : Parcelable

//Firebase Model
data class UserModel(val uid: String="", val username: String="", val imageUrl: String="")