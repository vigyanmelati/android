package id.maskology.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Banner(
    val image: Int
) : Parcelable
