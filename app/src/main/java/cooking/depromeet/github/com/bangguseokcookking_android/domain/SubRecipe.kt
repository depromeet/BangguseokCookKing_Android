package cooking.depromeet.github.com.bangguseokcookking_android.domain

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class SubRecipe(
    @SerializedName("order") val order: Int,
    @SerializedName("explain") var explain: String
)