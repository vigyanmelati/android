package id.maskology.ui.main.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import id.maskology.R
import id.maskology.databinding.FragmentProfileBinding
import id.maskology.ui.login.LoginActivity


class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        val name : String?
        val photo : Uri?
        auth = Firebase.auth
        val firebaseUser = auth.currentUser
        if (firebaseUser == null) {
            // Not signed in, launch the Login activity
            startActivity(Intent(activity, LoginActivity::class.java))
            return
        }
        firebaseUser.let {
            name = firebaseUser.displayName
            photo = firebaseUser.photoUrl
        }

        binding.apply {
            btnLogout.setOnClickListener{
                showPopup()
            }
            nameUser.text = name
            Glide.with(this@ProfileFragment)
                .load(photo)
                .into(profileUser)
        }
    }

    private fun showPopup() {
        val alert: AlertDialog.Builder = AlertDialog.Builder(context)
        alert.setMessage("Are you sure?")
            .setPositiveButton("Logout", DialogInterface.OnClickListener { dialog, which ->
                signOut() // Last step. Logout function
            }).setNegativeButton("Cancel", null)
        val alert1: AlertDialog = alert.create()
        alert1.show()
    }

    private fun signOut() {
        auth.signOut()
        startActivity(Intent(activity, LoginActivity::class.java))
    }

}