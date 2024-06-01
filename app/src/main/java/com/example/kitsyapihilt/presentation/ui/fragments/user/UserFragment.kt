package com.example.kitsyapihilt.presentation.ui.fragments.user

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kitsyapihilt.R
import com.example.kitsyapihilt.databinding.FragmentUserBinding
import com.example.kitsyapihilt.presentation.base.BaseFragment
import com.example.kitsyapihilt.presentation.models.user.toUI
import com.example.kitsyapihilt.presentation.ui.adapters.UserAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>(R.layout.fragment_user) {

    override val binding by viewBinding(FragmentUserBinding::bind)
    private val viewModel by viewModels<UserViewModel>()
    private val userAdapter = UserAdapter()

    override fun initialize() {
        setupRecycler()
    }

    override fun setupObserves() {
        setupRequest()
    }

    override fun setupRequest() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.usersFlow.collectLatest { userData ->
                userAdapter.submitData(userData.map { it.toUI() })
            }
        }
    }

    private fun setupRecycler() {
        binding.recyclerView.adapter = userAdapter
    }
}