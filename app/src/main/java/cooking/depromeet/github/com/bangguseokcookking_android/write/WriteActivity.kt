package cooking.depromeet.github.com.bangguseokcookking_android.write

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cooking.depromeet.github.com.bangguseokcookking_android.R
import kotlinx.android.synthetic.main.activity_write.*
import org.jetbrains.anko.sdk25.coroutines.onRatingBarChange

class WriteActivity : AppCompatActivity() {

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

        }

        // rating bar


    }
}