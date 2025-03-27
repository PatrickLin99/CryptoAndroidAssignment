package com.example.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "currencies")
@Parcelize
data class CurrencyInfo(
    @PrimaryKey @SerializedName("id") val id: String,
    @SerializedName("name") private val _name: String?,
    @SerializedName("symbol") private val _symbol: String?,
    @SerializedName("code") val code: String?,
): Parcelable {

    val name: String
        get() = _name ?: ""

    val symbol: String
        get() = _symbol ?: ""

    companion object {
        val defaultInstance = CurrencyInfo("", "","","")
    }
}
