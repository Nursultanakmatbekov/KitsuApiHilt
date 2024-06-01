package com.example.kitsyapihilt.presentation.ui.fragments.createpost

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.kitsyapihilt.R
import com.example.kitsyapihilt.databinding.FragmentCreatePostBinding
import com.example.kitsyapihilt.presentation.base.BaseFragment
import com.example.kitsyapihilt.presentation.extensions.showText
import com.example.kitsyapihilt.presentation.models.user.UserUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePostFragment : BaseFragment<FragmentCreatePostBinding>(R.layout.fragment_create_post) {

    override val binding by viewBinding(FragmentCreatePostBinding::bind)
    private val viewModel by viewModels<CreatePostViewModel>()
    private var user: UserUI? = null

    override fun setupListener() {
        binding.tvPost.setOnClickListener {
            createPost()
        }
        binding.tvCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun setupObserves() {
        viewModel.getUser("nurs")
        subscribeToCreatePostState()
        subscribeToUser()
    }

    private fun subscribeToUser() {
        viewModel.userFlow.spectateUiState(
            loading = { binding.progressBar.visibility = View.VISIBLE },
            success = { data ->
                binding.progressBar.visibility = View.GONE
                user = data.first { user ->
                    user.attributes.name == "nurs"
                }
                user?.attributes?.name?.let { binding.tvUserName.text = it }
                if (user?.attributes?.avatar?.original != null) {
                    Glide.with(binding.ivUserImage.context)
                        .load(user?.attributes?.avatar?.original)
                        .into(binding.ivUserImage)
                } else {
                    binding.ivUserImage.setImageResource(R.drawable.ava)
                }
            },
            error = { showText(it) }
        )
    }

    private fun subscribeToCreatePostState() {
        viewModel.fetchCreatePostState.spectateUiState(
            loading = {
                binding.progressBar.visibility = View.VISIBLE
            },
            success = {
                binding.progressBar.visibility = View.GONE
                showText("Success")
                findNavController().navigateUp()
            },
            error = {
                binding.progressBar.visibility = View.GONE
                showText(it)
                Log.e("nurs", it.toString())
            }
        )
    }

    private fun createPost() {
        if (user?.id == null) {
            showText(getString(R.string.unknown_error))
        } else if (binding.etContent.text.isNullOrBlank()) {
            binding.etContent.error = getString(R.string.input_text)
        } else {
            viewModel.createPost(
                userId = user!!.id,
                content = binding.etContent.text.toString(),
                nsfw = binding.cbNsfw.isChecked,
                spoiler = binding.cbSpoiler.isChecked
            )
        }
    }
}