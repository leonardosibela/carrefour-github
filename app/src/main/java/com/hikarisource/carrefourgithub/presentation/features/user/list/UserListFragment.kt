package com.hikarisource.carrefourgithub.presentation.features.user.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hikarisource.carrefourgithub.databinding.FragmentUserListBinding
import com.hikarisource.carrefourgithub.presentation.model.UserPresentation

class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    val binding get() = _binding!!

    private val userAdapter: UserAdapter = UserAdapter {
        Toast.makeText(requireContext(), it.login, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userRecycler.apply {
            adapter = userAdapter
            setHasFixedSize(true)
            userAdapter.submitList(listOf(FIRST_USER, SECOND_USER))
        }
    }
}

val FIRST_USER = UserPresentation(
    id = 1,
    login = "mojombo",
    avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
    url = "https://api.github.com/users/mojombo"
)

val SECOND_USER = UserPresentation(
    id = 2,
    login = "defunkt",
    avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
    url = "https://api.github.com/users/defunkt"
)