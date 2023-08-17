package com.ahmeturunveren.goshop.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahmeturunveren.goshop.databinding.FragmentProfileScreenBinding
import com.ahmeturunveren.goshop.util.extensions.click
import com.ahmeturunveren.goshop.util.extensions.gone

class ProfileScreen : Fragment() {

    private lateinit var binding: FragmentProfileScreenBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileToolbar.apply {
            favButton.gone()
            toolbarImageView.gone()
            toolbarTitle.text = null
            customToolbar.navigationIcon = null
        }
        clickSignOut()
    }

    private fun clickSignOut(){
        binding.signOutButton.click {
            requireActivity().finish()
        }
    }
}