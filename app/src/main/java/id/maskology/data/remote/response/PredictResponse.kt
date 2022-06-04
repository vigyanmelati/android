package id.maskology.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import id.maskology.data.model.Category
import kotlinx.parcelize.Parcelize

data class PredictResponse(

	@field:SerializedName("result")
	val result: Category
)


