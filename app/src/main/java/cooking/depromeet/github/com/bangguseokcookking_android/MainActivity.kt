package cooking.depromeet.github.com.bangguseokcookking_android

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import cooking.depromeet.github.com.bangguseokcookking_android.search.SearchActivity
import cooking.depromeet.github.com.bangguseokcookking_android.write.WriteActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_drawer.*
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // bottom sheet
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById<ConstraintLayout>(R.id.main_bottom))

        // navigation drawer
        navigationDrawer()

        // search
        main_et_search.setOnClickListener {
            Log.e("d", "d")
            startActivity(intentFor<SearchActivity>())
        }

        // 마이페이지
        main_ib_mypage.setOnClickListener {

        }

        // 글쓰기
        main_ib_write.setOnClickListener {
            startActivity(intentFor<WriteActivity>())
        }
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

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)

        if (main_drawer.isDrawerOpen(GravityCompat.START)) {
            Log.e("test", "r")
            return false
        }
        Log.e("test", "zzr")
        return true

    }

}
