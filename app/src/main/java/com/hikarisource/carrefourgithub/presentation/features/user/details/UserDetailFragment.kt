package com.hikarisource.carrefourgithub.presentation.features.user.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.hikarisource.carrefourgithub.core.extensions.HorizontalLineDrawable
import com.hikarisource.carrefourgithub.core.extensions.VerticalItemDecoration
import com.hikarisource.carrefourgithub.core.extensions.displayToastShort
import com.hikarisource.carrefourgithub.core.extensions.launchWhenCreated
import com.hikarisource.carrefourgithub.databinding.FragmentUserDetailBinding
import com.hikarisource.carrefourgithub.presentation.model.RepositoryPresentation
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailFragment : Fragment() {

    private val viewModel: UserDetailViewModel by viewModel()

    private val repositoryAdapter = RepositoryAdapter(::onRepositoryClicked)

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
        setupRecycler()
        displayUserData()
        observeViewModel()
        viewModel.fetchRepositoriesFromUser(args.user)
    }

    private fun onRepositoryClicked(repository: RepositoryPresentation) {
        displayToastShort(repository.name)
    }

    private fun setupRecycler() = binding.run {
        Glide.with(requireContext())
            .load(HorizontalLineDrawable())
            .into(repositoryRecyclerDivider)

        repositoryRecycler.apply {
            setHasFixedSize(true)
            adapter = repositoryAdapter
            addItemDecoration(VerticalItemDecoration())
        }
    }

    private fun displayUserData() = binding.run {
        userDetailNameText.text = args.user.login
        Glide.with(binding.root.context)
            .load(args.user.avatarUrl)
            .apply(RequestOptions().transform(CircleCrop()))
            .into(binding.userDetailAvatarImage)
    }

    private fun observeViewModel() {
        observerFetchRepositoriesState()
    }

    private fun observerFetchRepositoriesState() = launchWhenCreated {
        viewModel.fetchRepositoriesState.collectLatest(::onFetchRepositoriesStateChanged)
    }

    private fun onFetchRepositoriesStateChanged(fetchRepositoriesState: FetchRepositoriesState) {
        when (fetchRepositoriesState) {
            Fetching -> displayLoading()
            EmptyList -> displayEmptyListMessage()
            is Fetched -> displayRepositories(fetchRepositoriesState)
        }
    }

    private fun displayLoading() = binding.run {
        repositoryRecycler.isVisible = false
        repositoriesProgress.isVisible = true
        userRepositoryEmptyListText.isVisible = false
    }

    private fun displayEmptyListMessage() = binding.run {
        repositoryRecycler.isVisible = false
        repositoriesProgress.isVisible = false
        userRepositoryEmptyListText.isVisible = true
    }

    private fun displayRepositories(fetchUserState: Fetched) = binding.run {
        repositoryRecycler.isVisible = true
        repositoriesProgress.isVisible = false
        userRepositoryEmptyListText.isVisible = false
        repositoryAdapter.submitList(fetchUserState.repositories)
    }
}