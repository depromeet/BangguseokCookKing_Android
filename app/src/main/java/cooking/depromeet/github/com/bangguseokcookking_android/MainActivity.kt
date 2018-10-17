package cooking.depromeet.github.com.bangguseokcookking_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 마이페이지
        main_ib_mypage.setOnClickListener {

        }

        // 글쓰기
        main_ib_write.setOnClickListener {

        }

        // 전체보기
        main_tv_view_all.setOnClickListener {

        }

        main_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        main_tv_content.text = "안녕하세요 'ㅎㅇ'\n 오늘은 어떤 요리를 해볼까요?"

    }
}
