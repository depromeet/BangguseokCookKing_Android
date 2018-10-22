package cooking.depromeet.github.com.bangguseokcookking_android.ui.mypage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import cooking.depromeet.github.com.bangguseokcookking_android.R
import cooking.depromeet.github.com.bangguseokcookking_android.domain.BookMark
import cooking.depromeet.github.com.bangguseokcookking_android.domain.MyLike
import cooking.depromeet.github.com.bangguseokcookking_android.domain.MyRecipe
import kotlinx.android.synthetic.main.activity_mypage.*

class MypageActivity : AppCompatActivity() {

    private val roundOptions = RequestOptions()
        .circleCrop()
        .diskCacheStrategy(DiskCacheStrategy.NONE)

    private val myRecipeAdapter by lazy { MyRecipeAdapter() }

    private val myBookmarkAdapter by lazy { MyBookMarkAdapter() }

    private val likeAdapter by lazy { MyLikeAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        // profile image
        Glide.with(this)
            .load("https://file.namu.moe/file/f5d57681a95d89748f18b9ad3cbebeaf19392aa2f6bcb6820192301d611f3cc3")
            .apply(roundOptions)
            .into(mypage_iv_thumbnail)

        // user name
        mypage_tv_name.text = "요리왕"

        // my recipe recyclerview
        mypage_recipe_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MypageActivity)
            adapter = myRecipeAdapter
        }
        myRecipeItems()

        // book mark recyclerview
        mypage_bookmark_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MypageActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = this@MypageActivity.myBookmarkAdapter
        }
        bookmarkList()

        // my like recyclerview
        mypage_like_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MypageActivity)
            adapter = this@MypageActivity.likeAdapter
        }
        likeList()

    }

    private fun myRecipeItems() {
        val items: MutableList<MyRecipe> = mutableListOf()

        for (i in 0..2) {
            items.add(MyRecipe("활화산 계란찜", "2018.10.20"))
        }
        myRecipeAdapter.addItems(items)
    }

    private fun bookmarkList() {
        val items: MutableList<BookMark> = mutableListOf()

        for (i in 0..4) {
            items.add(BookMark(i + 1, "https://t1.daumcdn.net/cfile/tistory/253A85485679554632"))
        }
        myBookmarkAdapter.addItems(items)
    }

    private fun likeList() {
        val items: MutableList<MyLike> = mutableListOf()

        for (i in 0..6) {
            items.add(
                MyLike(
                    "http://recipe1.ezmember.co.kr/cache/recipe/2016/09/06/9d34c73f4252f7aa26d5ee5616205e601.jpg",
                    "봉골레 파스타"
                )
            )
        }
        likeAdapter.addItems(items)
    }
}