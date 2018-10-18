package cooking.depromeet.github.com.bangguseokcookking_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cooking.depromeet.github.com.bangguseokcookking_android.recipe.RecipeActivity
import kotlinx.android.synthetic.main.main_bottom.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.intentFor

class MainBottomFragment : Fragment(), MainBottomEventListener, AnkoLogger {

    private val adapter by lazy { MainAdapter(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_bottom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 전체보기
        main_tv_view_all.setOnClickListener {

        }

        main_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MainBottomFragment.adapter
        }

        // dummy

        val items: MutableList<MainRecipe> = mutableListOf()

        for (i in 0..10) {
            items.add(MainRecipe("김치찌게$i", "https://t1.daumcdn.net/cfile/tistory/196D084A4EB222222D", 998, 143, "쉬움"))
        }

        adapter.addItemAll(items)

        main_tv_content.text = "안녕하세요 'ㅎㅇ'\n오늘은 어떤 요리를 해볼까요?"
    }

    override fun onItemClick(recipe: MainRecipe) {
        context?.startActivity(context?.intentFor<RecipeActivity>())
    }

}

interface MainBottomEventListener {

    fun onItemClick(recipe: MainRecipe)
}