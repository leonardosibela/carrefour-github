package com.hikarisource.carrefourgithub.presentation.features.user.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.hikarisource.carrefourgithub.databinding.ItemUserBinding
import com.hikarisource.carrefourgithub.presentation.model.UserPresentation

class UserAdapter(
    private val clickListener: (UserPresentation) -> Unit,
) : ListAdapter<UserPresentation, UserAdapter.UserViewHolder>(UserDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(currentList[position], clickListener)
    }

    class UserViewHolder private constructor(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserPresentation, clickListener: (UserPresentation) -> Unit) {
            Glide.with(binding.root.context)
                .load(user.avatarUrl)
                .apply(RequestOptions().transform(CircleCrop()))
                .into(binding.userAvatarImage)

            binding.userNameText.text = user.login
            binding.userContainer.setOnClickListener { clickListener.invoke(user) }
        }

        companion object {
            fun from(parent: ViewGroup): UserViewHolder {
                val userItemBinding =
                    ItemUserBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return UserViewHolder(userItemBinding)
            }
        }
    }
}