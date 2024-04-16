package com.hikarisource.carrefourgithub.presentation.features.user.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.hikarisource.carrefourgithub.core.extensions.VerticalItemDecoration
import com.hikarisource.carrefourgithub.core.extensions.launchWhenCreated
import com.hikarisource.carrefourgithub.core.extensions.navigate
import com.hikarisource.carrefourgithub.databinding.FragmentUserListBinding
import com.hikarisource.carrefourgithub.presentation.model.UserPresentation
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : Fragment() {

    private val viewModel by viewModel<UserListViewModel>()

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private val userAdapter: UserAdapter = UserAdapter(::onUserClicked)

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
        setupViews()
        observeViewModel()
        viewModel.fetchAllUsers()
    }

    private fun onUserClicked(user: UserPresentation) {
        navigate(UserListFragmentDirections.toUserDetailFragment(user))
    }

    private fun setupViews() {
        setupListeners()
        setupRecycler()
    }

    private fun setupListeners() = binding.run {
        fetchUsersTryAgainButton.setOnClickListener { onTryAgainClicked() }
    }

    private fun onTryAgainClicked() {
        displayUserListLoading()
        viewModel.fetchAllUsers()
    }

    private fun setupRecycler() = binding.run {
        userRecycler.apply {
            adapter = userAdapter
            addItemDecoration(VerticalItemDecoration())
            setHasFixedSize(true)
        }
    }

    private fun observeViewModel() {
        observerFetchUsersState()
    }

    private fun observerFetchUsersState() = launchWhenCreated {
        viewModel.fetchUsersState.collectLatest(::onFetchUserStateChanged)
    }

    private fun onFetchUserStateChanged(fetchUserState: FetchUserState) {
        when (fetchUserState) {
            Fetching -> displayUserListLoading()
            is Fetched -> displayUsers(fetchUserState.users)
            is Error -> displayUserListError()
        }
    }

    private fun displayUserListLoading() = binding.run {
        fetchUsersTryAgainButton.isVisible = false
        userListErrorText.isVisible = false
        usersProgress.isVisible = true
        userRecycler.isVisible = false
    }

    private fun displayUsers(users: List<UserPresentation>) = binding.run {
        fetchUsersTryAgainButton.isVisible = false
        userListErrorText.isVisible = false
        usersProgress.isVisible = false
        userRecycler.isVisible = true
        userAdapter.submitList(users)
    }

    private fun displayUserListError() = binding.run {
        fetchUsersTryAgainButton.isVisible = true
        userListErrorText.isVisible = true
        usersProgress.isVisible = false
        userRecycler.isVisible = false
        val a = 5
    }
}
