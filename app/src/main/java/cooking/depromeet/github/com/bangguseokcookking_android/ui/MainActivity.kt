package cooking.depromeet.github.com.bangguseokcookking_android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import cooking.depromeet.github.com.bangguseokcookking_android.R
import cooking.depromeet.github.com.bangguseokcookking_android.ui.mypage.MypageActivity
import cooking.depromeet.github.com.bangguseokcookking_android.ui.search.SearchActivity
import cooking.depromeet.github.com.bangguseokcookking_android.ui.write.WriteActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_drawer.*
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

    private val roundOptions = RequestOptions()
        .circleCrop()
        .diskCacheStrategy(DiskCacheStrategy.NONE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // bottom sheet
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById<ConstraintLayout>(R.id.main_bottom))

        // navigation drawer
        navigationDrawer()

        val nav_header = main_nav.getHeaderView(0)
        val header_thumbnail = nav_header.findViewById<AppCompatImageView>(R.id.header_iv_profile)
        val header_mypage = nav_header.findViewById<AppCompatTextView>(R.id.header_tv_mypage)
        header_mypage.setOnClickListener { startActivity(intentFor<MypageActivity>()) }

        Glide.with(this)
            .load("https://file.namu.moe/file/f5d57681a95d89748f18b9ad3cbebeaf19392aa2f6bcb6820192301d611f3cc3")
            .apply(roundOptions)
            .into(header_thumbnail)

        // search
        main_et_search.setOnClickListener { startActivity(intentFor<SearchActivity>()) }

        // 마이페이지
        main_ib_mypage.setOnClickListener {
            startActivity(intentFor<MypageActivity>())
        }

        // 글쓰기
        main_ib_write.setOnClickListener { startActivity(intentFor<WriteActivity>()) }
    }

    private fun navigationDrawer() = main_nav.setNavigationItemSelectedListener {
        main_drawer.closeDrawers()
        when (it.itemId) {
            R.id.menu_recipe -> {
            }
            R.id.menu_book_mark -> {
            }
            R.id.menu_like -> {
            }
        }

        true
    }
}
