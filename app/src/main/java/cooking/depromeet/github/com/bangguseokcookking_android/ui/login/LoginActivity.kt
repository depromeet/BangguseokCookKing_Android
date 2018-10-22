package cooking.depromeet.github.com.bangguseokcookking_android.ui.login

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import cooking.depromeet.github.com.bangguseokcookking_android.R
import cooking.depromeet.github.com.bangguseokcookking_android.ui.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.intentFor

class LoginActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_login)

        login_btn_kakao.setOnClickListener {
            startActivity(intentFor<MainActivity>())
        }

        login_btn_facebook.setOnClickListener {
            startActivity(intentFor<MainActivity>())
        }

        login_btn_naver.setOnClickListener {
            startActivity(intentFor<MainActivity>())
        }
    }
}