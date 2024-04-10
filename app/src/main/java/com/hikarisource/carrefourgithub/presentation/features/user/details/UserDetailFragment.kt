package com.hikarisource.carrefourgithub.presentation.features.user.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.hikarisource.carrefourgithub.core.extensions.displayToast
import com.hikarisource.carrefourgithub.databinding.FragmentUserDetailBinding

class UserDetailFragment : Fragment() {

    private val args by navArgs<UserDetailFragmentArgs>()

    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayToast(args.user.login)
    }
}