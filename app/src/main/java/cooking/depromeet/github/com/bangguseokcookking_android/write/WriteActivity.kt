package cooking.depromeet.github.com.bangguseokcookking_android.write

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fxn.pix.Pix
import com.fxn.utility.PermUtil
import cooking.depromeet.github.com.bangguseokcookking_android.R
import kotlinx.android.synthetic.main.activity_write.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jetbrains.anko.toast
import android.opengl.ETC1.getHeight
import android.view.ViewTreeObserver


class WriteActivity : AppCompatActivity(), AnkoLogger, WriteRecipeEventListener {

    companion object {
        private const val IMAGE_PICKER = 6666
    }

    private lateinit var adapter: WriteAdapter

    private var recipeCount: Int = 0

    private var currentRecipePosition = -1

    private val recipeImages: MutableList<String> = mutableListOf()
    private val recipeContents: MutableList<String> = mutableListOf()

    private var isCameraOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        // cancel button
        write_tv_cancel.setOnClickListener { finish() }

        // submit btn
        write_tv_submit.setOnClickListener {
        }

        // plus btn
        write_ib_plus.setOnClickListener {
            addRecipe()
            error { adapter.itemCount }
            scrollToLastPosition()
        }

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

    private fun ratingDifficult(text: String) {
        write_tv_difficult.text = text
    }

    private fun addRecipe() {
        recipeCount = adapter.itemCount
        if (recipeCount < 6) {

            if (recipeImages.size == recipeCount) {
                adapter.addItem(WriteRecipe("", "", recipeCount))
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
                    addImage(image!![0])
                    updateImage()
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
        if (recipeContents.isEmpty()) {
            recipeContents.add("text")
            error { "empty" }
        }
        if (recipeContents.size == position - 1) {
            recipeContents.add(text)
            error { "add" }
        }

        if (recipeContents.size - 1 == position) {
            recipeContents[position] = text
            error { "set" }
        }

        error { "$text $position" }
    }

    private fun openImagePicker() = Pix.start(this, IMAGE_PICKER, 1)

    private fun addImage(url: String) {
        recipeImages.add(url)
    }

    private fun updateImage() {
        adapter.changeImage(recipeImages[currentRecipePosition], currentRecipePosition)
    }

    /*private fun scrollToLastPosition() = write_scroll_view.post {
        write_scroll_view.fullScroll(View.FOCUS_DOWN)
    }*/
    private fun scrollToLastPosition() = write_scroll_view.viewTreeObserver.addOnGlobalLayoutListener {
        write_scroll_view.post {
            write_scroll_view.fullScroll(View.FOCUS_DOWN)
        }
    }
}