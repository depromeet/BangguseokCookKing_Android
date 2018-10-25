package cooking.depromeet.github.com.bangguseokcookking_android.recipe

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import cooking.depromeet.github.com.bangguseokcookking_android.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_recipe.*
import kotlinx.android.synthetic.main.activity_recipe_content.*
import kotlinx.android.synthetic.main.item_ad.view.*
import kotlinx.android.synthetic.main.item_comment.view.*

class RecipeActivity : AppCompatActivity() {
    private val sequenceAdapter = SequenceAdapter()
    private val sequenceItems: MutableList<SequenceDataModel> = mutableListOf()
    private val commentItems: MutableList<CommentDataModel> = mutableListOf()
    private val adItems: MutableList<ADDataModel> = mutableListOf()
    private var commentCount: Long = 0
    private var adCount: Long = 0

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

        for (i in 1..5) {
            sequenceItems.add(
                    SequenceDataModel(
                        "http://blogfiles.naver.net/20151014_201/cider99_1444814017014EYFi4_JPEG/%B9%E9%C1%BE%BF%F8%B0%E8%B6%F5%C2%F2.jpg",
                        "",
                        "1",
                        "볼에 달걀 6개와 물 1/3컵, 소금 반 아빠숫갈, 설탕 반 아빠숫갈을 넣고 잘 저어준다."
                    )
            )
        }

        sequence_list.run {
            adapter = sequenceAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
        }

        sequenceAdapter.addItemAll(sequenceItems)

        for (i in 1..10) {
            commentItems.add(
                    CommentDataModel(
                            "http://blogfiles.naver.net/20151014_201/cider99_1444814017014EYFi4_JPEG/%B9%E9%C1%BE%BF%F8%B0%E8%B6%F5%C2%F2.jpg",
                            "규나나낭",
                            "1",
                            "정말정말 쉽고 JMT, 시간도 오래 안걸려서 바로 해먹기 참 좋아요!"
                    )
            )
        }

        getMoreComment()
        comment_more.setOnClickListener {
            getMoreComment()
        }

        ad_title.text = "'계란' 관련 검색결과"

        for (i in 1..10) {
            adItems.add(
                    ADDataModel(
                            "http://blogfiles.naver.net/20151014_201/cider99_1444814017014EYFi4_JPEG/%B9%E9%C1%BE%BF%F8%B0%E8%B6%F5%C2%F2.jpg",
                            "계란말이 쉽게 만드는 법",
                            "잠실 맛집 생각나서 잠못이루는곳 잠실 맛집 등촌샤브칼국수 완벽한 첫끼~ 존맛탱 눈누난나 샤르링가 덩기덕쿵.."
                    )
            )
        }

        getMoreAD()
        ad_more.setOnClickListener {
            getMoreAD()
        }
    }

    private fun getMoreComment() {
        Observable.fromIterable(commentItems)
                .skip(commentCount)
                .take(5)
                .doOnComplete {
                    commentCount += 5
                    when {
                        commentItems.size - commentCount >= 5 -> comment_more_text.text = "5개 댓글 더보기"
                        commentItems.size - commentCount > 0 -> comment_more_text.text = "${commentItems.size - commentCount}개 댓글 더보기"
                        else -> comment_more.visibility = View.GONE
                    }
                }
                .subscribe({
                    val view = layoutInflater.inflate(R.layout.item_comment, comment_list, false)
                    Glide.with(applicationContext)
                            .load(it.profile)
                            .apply(RequestOptions().circleCrop())
                            .into(view.profile)
                    view.author.text = it.author
                    view.count.text = "${it.count}개 작성글"
                    view.comment_contents.text = it.contents
                    comment_list.addView(view)
                }){
                    it.printStackTrace()
                }
    }

    private fun getMoreAD() {
        Observable.fromIterable(adItems)
                .skip(adCount)
                .take(if(ad_list.childCount > 0) 5 else 3)
                .doOnComplete {
                    adCount += 5
                    when {
                        adItems.size - adCount >= 5 -> ad_more_text.text = "5개 검색결과 더보기"
                        adItems.size - adCount > 0 -> ad_more_text.text = "${adItems.size - adCount}개 검색결과 더보기"
                        else -> ad_more.visibility = View.GONE
                    }
                }
                .subscribe({
                    val view = layoutInflater.inflate(R.layout.item_ad, ad_list, false)
                    Glide.with(applicationContext)
                            .load(it.post_img)
                            .apply(RequestOptions().centerCrop())
                            .into(view.post_img)
                    view.title.text = it.title
                    view.ad_contents.text = it.contents
                    ad_list.addView(view)
                }){
                    it.printStackTrace()
                }
    }
}
