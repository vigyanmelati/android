package id.maskology.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import id.maskology.R
import id.maskology.databinding.ActivityMainBinding
import id.maskology.ui.camera.CameraActivity
import id.maskology.ui.detailProduct.DetailProductActivity
import id.maskology.ui.main.fragment.EducationFragment
import id.maskology.ui.main.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeFragment = HomeFragment()
        val educationFragment = EducationFragment()

        setFragment(homeFragment)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId){
                R.id.nav_home -> setFragment(homeFragment)
                R.id.nav_camera -> toCameraActivity()
                R.id.nav_education -> setFragment(educationFragment)
            }
            true
        }
    }

    private fun toCameraActivity() {
        startActivity(Intent(this@MainActivity, DetailProductActivity::class.java))
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }
}