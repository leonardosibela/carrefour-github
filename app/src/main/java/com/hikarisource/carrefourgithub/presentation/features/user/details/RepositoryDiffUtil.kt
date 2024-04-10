package com.hikarisource.carrefourgithub.presentation.features.user.details

import androidx.recyclerview.widget.DiffUtil
import com.hikarisource.carrefourgithub.presentation.model.RepositoryPresentation
import com.hikarisource.carrefourgithub.presentation.model.UserPresentation

class RepositoryDiffUtil : DiffUtil.ItemCallback<RepositoryPresentation>() {

    override fun areItemsTheSame(
        oldItem: RepositoryPresentation,
        newItem: RepositoryPresentation
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: RepositoryPresentation,
        newItem: RepositoryPresentation
    ) = oldItem.name == newItem.name
            && oldItem.url == newItem.url
}
