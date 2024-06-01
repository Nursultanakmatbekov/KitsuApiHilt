package com.example.kitsyapihilt.presentation.ui.fragments.post

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.map
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kitsyapihilt.R
import com.example.kitsyapihilt.databinding.FragmentPostBinding
import com.example.kitsyapihilt.presentation.base.BaseFragment
import com.example.kitsyapihilt.presentation.extensions.showText
import com.example.kitsyapihilt.presentation.models.post.toUI
import com.example.kitsyapihilt.presentation.ui.adapters.PostsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostFragment : BaseFragment<FragmentPostBinding>(R.layout.fragment_post) {

    override val binding by viewBinding(FragmentPostBinding::bind)
    private val viewModel by viewModels<PostViewModel>()
    private val postsAdapter: PostsAdapter by lazy {
        PostsAdapter(this::onItemClick)
    }

    override fun initialize() {
        binding.rvPosts.adapter = postsAdapter
        postsAdapter.addLoadStateListener {
            binding.progressBar.isVisible = it.source.refresh is LoadState.Loading
        }
    }

    override fun setupObserves() {
        subscribeToPosts()
    }

    private fun subscribeToPosts() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.postsFlow.collectLatest {
                postsAdapter.submitData(it.map { dataItem ->
                    val user = viewModel.fetchUser(dataItem.id)
                    dataItem.relationships.user = user
                    dataItem.toUI()
                })
            }
        }
    }

    private fun onItemClick(id: String) {
        showText(id)
    }
}