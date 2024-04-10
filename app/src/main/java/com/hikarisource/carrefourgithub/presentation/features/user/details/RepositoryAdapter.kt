package com.hikarisource.carrefourgithub.presentation.features.user.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hikarisource.carrefourgithub.databinding.ItemRepositoryBinding
import com.hikarisource.carrefourgithub.presentation.model.RepositoryPresentation

class RepositoryAdapter(
    private val clickListener: (RepositoryPresentation) -> Unit,
) : ListAdapter<RepositoryPresentation, RepositoryAdapter.RepositoryViewHolder>(RepositoryDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(currentList[position], clickListener)
    }

    class RepositoryViewHolder private constructor(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            repository: RepositoryPresentation,
            clickListener: (RepositoryPresentation) -> Unit
        ) {
            binding.userNameText.text = repository.name
            binding.userContainer.setOnClickListener { clickListener.invoke(repository) }
        }

        companion object {
            fun from(parent: ViewGroup): RepositoryViewHolder {
                val userItemBinding =
                    ItemRepositoryBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return RepositoryViewHolder(userItemBinding)
            }
        }
    }
}