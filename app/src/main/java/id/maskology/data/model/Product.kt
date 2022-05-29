package id.maskology.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "product")
@Parcelize
data class Product(
    @PrimaryKey
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("storeId")
    val storeId: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("contact")
    val contact: String,

    @field:SerializedName("desc")
    val desc: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("imageUrl")
    val imageUrl: String,

    @field:SerializedName("backgroundUrl")
    val backgroundUrl: String,

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("updatedAt")
    val updatedAt: String,
) : Parcelable
