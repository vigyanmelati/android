package id.maskology.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PredictResponse(

	@field:SerializedName("result")
	val result: List<ResultItem>
)

@Parcelize
data class ResultItem(

	@field:SerializedName("label")
	val label: String,

	@field:SerializedName("value")
	val value: Int
) : Parcelable
