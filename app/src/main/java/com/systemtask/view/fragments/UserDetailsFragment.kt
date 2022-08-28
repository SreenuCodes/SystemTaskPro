package com.systemtask.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.systemtask.R
import com.systemtask.databinding.FragmentUsersDetailsBinding

class UserDetailsFragment : Fragment(R.layout.fragment_users_details) {
    private lateinit var binding: FragmentUsersDetailsBinding
    val args: UserDetailsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUsersDetailsBinding.bind(view)
        val userData = args.userData
        userData?.let {
            binding.apply {
                tvName.text = it.name
                tvGender.text = it.gender
                tvId.text = it.id.toString()
                tvStatus.text = it.status
                tvEmail.text = it.email
            }
        }


    }
}