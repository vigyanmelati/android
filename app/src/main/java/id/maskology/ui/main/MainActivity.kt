package id.maskology.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import id.maskology.R
import id.maskology.databinding.ActivityMainBinding
import id.maskology.ui.camera.CameraActivity
import id.maskology.ui.detailEcommerce.DetailEcommerceActivity
import id.maskology.ui.detailProduct.DetailProductActivity
import id.maskology.ui.main.fragment.EducationFragment
import id.maskology.ui.main.fragment.FavoriteFragment
import id.maskology.ui.main.fragment.HomeFragment
import id.maskology.ui.main.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeFragment = HomeFragment()
    private val educationFragment = EducationFragment()
    private val profileFragment = ProfileFragment()
    private val favoriteFragment = FavoriteFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        if (!allPermissionsGranted()) {
//            ActivityCompat.requestPermissions(
//                this,
//                REQUIRED_PERMISSIONS,
//                REQUEST_CODE_PERMISSIONS
//            )
//        }

        setFragment(homeFragment)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId){
                R.id.nav_home -> setFragment(homeFragment)
                R.id.nav_education -> setFragment(educationFragment)
                R.id.nav_favorite -> setFragment(favoriteFragment)
                R.id.nav_profile -> setFragment(profileFragment)
            }
            true
        }

        binding.fabCamera.setOnClickListener { toCameraActivity() }
    }

    private fun testActivity() {
        startActivity(Intent(this@MainActivity, DetailEcommerceActivity::class.java))
    }


    private fun toCameraActivity() {
        startActivity(Intent(this@MainActivity, CameraActivity::class.java))
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == REQUEST_CODE_PERMISSIONS) {
//            if (!allPermissionsGranted()) {
//                Toast.makeText(
//                    this,
//                    "Tidak mendapatkan permission.",
//                    Toast.LENGTH_SHORT
//                ).show()
//                finish()
//            }
//        }
//    }
//    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
//        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
//    }

    override fun onResume() {
        super.onResume()
        when (binding.bottomNavigation.selectedItemId){
            R.id.nav_home -> setFragment(homeFragment)
            R.id.nav_education -> setFragment(educationFragment)
            R.id.nav_favorite -> setFragment(favoriteFragment)
            R.id.nav_profile -> setFragment(profileFragment)
        }
    }


    companion object {
        const val CAMERA_X_RESULT = 200
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}