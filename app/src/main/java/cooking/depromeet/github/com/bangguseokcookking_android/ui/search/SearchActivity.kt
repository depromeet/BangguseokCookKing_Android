package cooking.depromeet.github.com.bangguseokcookking_android.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cooking.depromeet.github.com.bangguseokcookking_android.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        search_tab.apply {
            addTab(this.newTab().setText("최근 검색어"))
            addTab(this.newTab().setText("인기 검색"))
        }
    }
}