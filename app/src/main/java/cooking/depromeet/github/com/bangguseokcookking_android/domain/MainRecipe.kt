package cooking.depromeet.github.com.bangguseokcookking_android.domain

import com.google.gson.annotations.SerializedName

/*
*   use MainBottomFragment Recyclerview
*   ingredientList <- 이거 일부로 안뽑음 ( 리스트에서 안씀)
*
 *  */
data class MainRecipe(
    @SerializedName("like") val like: Int,
    @SerializedName("comment") val comment: Int,
    @SerializedName("thumbnail") val image: String,
    @SerializedName("_id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("author") val user: String
)