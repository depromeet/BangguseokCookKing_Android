package cooking.depromeet.github.com.bangguseokcookking_android.api

import com.google.gson.annotations.SerializedName
import cooking.depromeet.github.com.bangguseokcookking_android.domain.MainRecipe
import cooking.depromeet.github.com.bangguseokcookking_android.util.base_url
import cooking.depromeet.github.com.bangguseokcookking_android.util.main_list_param
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET(main_list_param)
    fun getMainRecipeList(@Query("search") ingredient: String): Single<MainListItems>

    data class MainListItems(
        @SerializedName("success") val response: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String,
        @SerializedName("data") val recipeList: MutableList<MainRecipe>
    )

    companion object {

        // 이거 땡기면되
        fun api(): RetrofitService {
            return Retrofit.Builder()
                .baseUrl(base_url)
                .client(OkHttpClient(provideLoggingInterceptor()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitService::class.java)
        }

        private fun OkHttpClient(
            interceptor: HttpLoggingInterceptor
        ): OkHttpClient = OkHttpClient.Builder()
            .run {
                addInterceptor(interceptor)
                build()
            }

        private fun provideLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    }

}