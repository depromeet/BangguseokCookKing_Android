package cooking.depromeet.github.com.bangguseokcookking_android.ui.write

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fxn.pix.Pix
import com.fxn.utility.PermUtil
import com.google.gson.Gson
import cooking.depromeet.github.com.bangguseokcookking_android.R
import cooking.depromeet.github.com.bangguseokcookking_android.api.RetrofitService
import cooking.depromeet.github.com.bangguseokcookking_android.domain.SubRecipe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_write.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import java.io.File


class WriteActivity : AppCompatActivity(), AnkoLogger,
    WriteRecipeEventListener {

    companion object {
        private const val IMAGE_PICKER = 6666
    }

    private lateinit var adapter: WriteAdapter

    private var recipeCount: Int = 0

    private var currentRecipePosition = -1

    private val recipeImages: MutableList<String> = mutableListOf()
    private val recipeContents: MutableList<String> = mutableListOf()

    private var hasUpload = false

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        // cancel button
        write_tv_cancel.setOnClickListener { finish() }

        // submit btn
        write_tv_submit.setOnClickListener { if (!hasUpload) hasValidUpload() }

        // plus btn
        write_ib_plus.setOnClickListener { isValidRecipeCount() }

        // rating bar
        write_rating_bar.setListener {
            when (it.rating) {
                1, 2 -> ratingDifficult("쉬움")
                3 -> ratingDifficult("보통")
                4, 5 -> ratingDifficult("어려움")
            }
        }

        // bottom recyclerview
        adapter = WriteAdapter(this)

        write_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@WriteActivity).apply {
                stackFromEnd = true
            }
            adapter = this@WriteActivity.adapter
        }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun ratingDifficult(text: String) {
        write_tv_difficult.text = text
    }

    private fun addRecipe(url: String) {
        recipeCount = adapter.itemCount
        if (recipeCount < 6) {

            if (recipeImages.size == recipeCount) {
                addImage(url)
                addContent("")
                adapter.addItem(
                    WriteRecipe(
                        url,
                        "",
                        recipeCount
                    )
                )
                if (adapter.itemCount == 6) write_ib_plus.visibility = View.GONE
            } else toast("이전 단계 작성을 완료해 주세요.")

        } else {
            write_ib_plus.visibility = View.GONE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_PICKER -> {
                if (resultCode == Activity.RESULT_OK) {
                    val image = data?.getStringArrayListExtra(Pix.IMAGE_RESULTS)
                    addRecipe(image!![0])
                    scrollToLastPosition()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PermUtil.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openImagePicker()
                } else toast("권한을 확인 해 주세요.")
        }
    }

    override fun onImageSelected(position: Int) {
        currentRecipePosition = position
        if (recipeImages.size == 0 || position < 6) openImagePicker()
        else toast("최대 6개까지 가능합니다.")
    }

    override fun onTextWatcher(text: String, position: Int) {
        recipeContents[position] = text
    }

    private fun isValidRecipeCount() {
        if (adapter.itemCount == 0 || adapter.itemCount < 7) {

            if (recipeContents.isNotEmpty() && recipeContents[adapter.itemCount - 1].isEmpty()) {
                toast("글을 작성해 주세요.")
                return
            }
            openImagePicker()
        }
    }

    private fun openImagePicker() = Pix.start(this, IMAGE_PICKER, 1)

    private fun addImage(url: String) = recipeImages.add(url)

    private fun addContent(content: String) = recipeContents.add(content)

    private fun scrollToLastPosition() = write_scroll_view.viewTreeObserver.addOnGlobalLayoutListener {
        write_scroll_view.post {
            write_scroll_view.fullScroll(View.FOCUS_DOWN)
        }
    }

    private fun hasValidUpload() {

        // 제목이 없을경우
        if (hasStringEmpty(write_et_title)) {
            toast("제목을 입력해 주세요.")
            return
        }

        if (hasStringEmpty(write_et_menu)) {
            toast("재료를 입력해 주세요.")
            return
        }

        if (recipeImages.isEmpty() || recipeImages.size < 2) {
            toast("조리방법을 2가지 이상 추가해주세요.")
            return
        }

        val thumbnail = prepareFilePart(recipeImages[0], "recipe")
        val otherImage: MutableList<MultipartBody.Part> = mutableListOf()

        recipeImages.forEachIndexed { i, _ ->
            if (i != 0) {
                otherImage.add(prepareFilePart(recipeImages[i], "subRecipe"))
            }
        }
        val subRecipeList = mutableListOf<SubRecipe>()

        recipeContents.forEachIndexed { i, s ->
            subRecipeList.add(SubRecipe(i, s))
        }

        val params = recipeMap(
            toRequestBody(write_et_title.text.toString()),
            toRequestBody(write_rating_bar.rating.toString()),
            toRequestBody(Gson().toJson(splitIngredientList(write_et_menu.text.toString()))),
            toRequestBody(Gson().toJson(subRecipeList))
        )

        hasUpload = true
        compositeDisposable.add(uploadRecipe(thumbnail, otherImage, params))
    }

    private fun uploadRecipe(
        thumbnail: MultipartBody.Part,
        otherImage: MutableList<MultipartBody.Part>,
        params: HashMap<String, RequestBody>
    ) = RetrofitService.api()
        .uploadRecipe(thumbnail, otherImage, params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess {
            hasUpload = false
            finish()
        }
        .doOnError { hasUpload = false }
        .subscribe()

    private fun hasStringEmpty(et: EditText): Boolean = et.text.toString().isEmpty()

    private fun prepareFilePart(uri: String, param: String): MultipartBody.Part {
        val file = File(uri)
        val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)

        return MultipartBody.Part.createFormData(param, file.name, requestBody)
    }

    private fun splitIngredientList(text: String): MutableList<String> {
        val arr = text.split(",")

        arr.forEach {
            it.trim()
        }
        return arr.toMutableList()
    }

    private fun toRequestBody(value: String): RequestBody =
        RequestBody.create(MediaType.parse("multipart/form-data"), value)

    private fun recipeMap(
        title: RequestBody,
        level: RequestBody,
        ingredientList: RequestBody,
        subRecipeList: RequestBody
    ): HashMap<String, RequestBody> =
        HashMap<String, RequestBody>().apply {
            this["title"] = title
            this["level"] = level
            this["ingredientList"] = ingredientList
            this["subRecipeList"] = subRecipeList
        }
}