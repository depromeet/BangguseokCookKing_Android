package cooking.depromeet.github.com.bangguseokcookking_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import cooking.depromeet.github.com.bangguseokcookking_android.write.WriteActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity() {


    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // bottom sheet
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById<ConstraintLayout>(R.id.main_bottom))

        // 마이페이지
        main_ib_mypage.setOnClickListener {

        }

        // 글쓰기
        main_ib_write.setOnClickListener {
            startActivity(intentFor<WriteActivity>())
        }
    }
}
