package cooking.depromeet.github.com.bangguseokcookking_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { MainAdapter() }

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
            adapter = this@MainActivity.adapter
        }

        // dummy

        val items: MutableList<MainRecipe> = mutableListOf()

        for (i in 0..10) {
            items.add(MainRecipe("김치찌게$i", "https://t1.daumcdn.net/cfile/tistory/196D084A4EB222222D", 998, 143))
        }

        adapter.addItemAll(items)

        //

        main_tv_content.text = "안녕하세요 'ㅎㅇ'\n오늘은 어떤 요리를 해볼까요?"

    }
}
