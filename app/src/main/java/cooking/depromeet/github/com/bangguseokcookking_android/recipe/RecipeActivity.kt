package cooking.depromeet.github.com.bangguseokcookking_android.recipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import cooking.depromeet.github.com.bangguseokcookking_android.R
import kotlinx.android.synthetic.main.activity_recipe.*
import kotlinx.android.synthetic.main.activity_recipe_content.*

class RecipeActivity : AppCompatActivity() {
    private val sequenceAdapter = SequenceAdapter()
    private val sequenceItems: MutableList<SequenceDataModel> = mutableListOf(
            SequenceDataModel(
                    "http://blogfiles.naver.net/20151014_201/cider99_1444814017014EYFi4_JPEG/%B9%E9%C1%BE%BF%F8%B0%E8%B6%F5%C2%F2.jpg",
                    "",
                    "1",
                    "볼에 달걀 6개와 물 1/3컵, 소금 반 아빠숫갈, 설탕 반 아빠숫갈을 넣고 잘 저어준다."
            ),
            SequenceDataModel(
                    "http://blogfiles.naver.net/20151014_201/cider99_1444814017014EYFi4_JPEG/%B9%E9%C1%BE%BF%F8%B0%E8%B6%F5%C2%F2.jpg",
                    "",
                    "2",
                    "뚝배기에 계란물이 80% 정도 차도록 넣어준다."
            ),
            SequenceDataModel(
                    "http://blogfiles.naver.net/20151014_201/cider99_1444814017014EYFi4_JPEG/%B9%E9%C1%BE%BF%F8%B0%E8%B6%F5%C2%F2.jpg",
                    "",
                    "3",
                    "볼에 달걀 6개와 물 1/3컵, 소금 반 아빠숫갈, 설탕 반 아빠숫갈을 넣고 잘 저어준다."
            ),
            SequenceDataModel(
                    "http://blogfiles.naver.net/20151014_201/cider99_1444814017014EYFi4_JPEG/%B9%E9%C1%BE%BF%F8%B0%E8%B6%F5%C2%F2.jpg",
                    "",
                    "4",
                    "뚝배기에 계란물이 80% 정도 차도록 넣어준다."
            )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            toolbar_title.text = getString(R.string.recipe_title)
            elevation = 0F
        }

        Glide.with(this)
                .load("http://blogfiles.naver.net/20151014_201/cider99_1444814017014EYFi4_JPEG/%B9%E9%C1%BE%BF%F8%B0%E8%B6%F5%C2%F2.jpg")
                .into(recipe_img)
        recipe_name.text = "활화산 계란찜"
        like_count.text = "397"
        comment_count.text = "397"
        level.text = "매우 쉬움"
        ingredient.text = "뚝배기, 달걀 6개, 물 1/3컵, 소금, 설탕, 다진대파, 참기름 약간"

        sequence_list.run {
            adapter = sequenceAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
        }
        sequenceAdapter.addItemAll(sequenceItems)
    }
}
