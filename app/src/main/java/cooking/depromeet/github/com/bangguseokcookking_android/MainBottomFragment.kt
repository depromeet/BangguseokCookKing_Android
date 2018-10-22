package cooking.depromeet.github.com.bangguseokcookking_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cooking.depromeet.github.com.bangguseokcookking_android.api.RetrofitService
import cooking.depromeet.github.com.bangguseokcookking_android.api.domain.MainRecipe
import cooking.depromeet.github.com.bangguseokcookking_android.recipe.RecipeActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.main_bottom.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.intentFor

class MainBottomFragment : Fragment(), MainBottomEventListener, AnkoLogger {

    private val adapter by lazy { MainAdapter(this) }
    private lateinit var menuAdapter: MainMenuAdapter

    private val api by lazy { RetrofitService.api() }
    private val composite = CompositeDisposable()

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

        // 메뉴 클릭시 데이터 불러오기
        menuAdapter = MainMenuAdapter {
            callList(it)
        }

        main_menu_recycler_view.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = this@MainBottomFragment.menuAdapter
        }

        val menuItems: MutableList<String> = mutableListOf()

        // 가로 리사이클러뷰 데이터 json 없어서 임시로 테스트 db 있는거만 만들어놈
        menuItems.add("계란")
        menuItems.add("새우")

        menuAdapter.addItems(menuItems)

        main_tv_content.text = "안녕하세요 '민호'님\n오늘은 어떤 요리를 해볼까요?"

        callList("계란")

    }

    // 세로 리사이클러뷰 아이템 클릭 id 값 넘김
    override fun onItemClick(recipe: MainRecipe) {
        context?.startActivity(context?.intentFor<RecipeActivity>("id" to recipe.id))
    }

    private fun callList(query: String) =
        composite.add(api.getMainRecipeList(query)
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it.response }
            .map { it.recipeList }
            .doOnSuccess {
                adapter.addItemAll(it)
            }
            .subscribe())

}

interface MainBottomEventListener {

    fun onItemClick(recipe: MainRecipe)
}