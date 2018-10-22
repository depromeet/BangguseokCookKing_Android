package cooking.depromeet.github.com.bangguseokcookking_android.splash

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import cooking.depromeet.github.com.bangguseokcookking_android.MainActivity
import cooking.depromeet.github.com.bangguseokcookking_android.R
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.intentFor
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    private val composite = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)

        timer()
    }

    private fun timer() {
        composite.add(Completable.timer(3000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.computation())
            .doOnComplete {
                startActivity(intentFor<MainActivity>()).apply { finish() }
            }
            .subscribe())
    }

    override fun onPause() {
        super.onPause()
        composite.clear()
    }
}