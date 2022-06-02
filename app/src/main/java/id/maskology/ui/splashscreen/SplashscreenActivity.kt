package id.maskology.ui.splashscreen

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import id.maskology.R
import id.maskology.databinding.ActivitySplashscreenBinding
import id.maskology.ui.main.MainActivity
import kotlin.system.exitProcess

class SplashscreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashscreenBinding
    private val delay = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        playAnimation()
        splashDelay()
    }

    private fun playAnimation() {
        val animateLogo = ObjectAnimator.ofFloat(binding.imgLogo, View.ALPHA, 1f).setDuration(1000)
        val animateTextOwner = ObjectAnimator.ofFloat(binding.tvOwner, View.ALPHA, 1f).setDuration(1000)

        AnimatorSet().apply {
            playSequentially(animateLogo, animateTextOwner)
            start()
        }
    }

    private fun splashDelay() {
        Handler(Looper.getMainLooper()).postDelayed({toNextActivity()}, delay)
    }

    private fun toNextActivity() {
        startActivity(Intent(this@SplashscreenActivity, MainActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        exitProcess(0)
    }
}