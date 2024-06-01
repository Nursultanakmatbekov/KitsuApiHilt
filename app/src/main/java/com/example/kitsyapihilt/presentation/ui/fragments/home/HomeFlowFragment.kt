package com.example.kitsyapihilt.presentation.ui.fragments.home

import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kitsyapihilt.R
import com.example.kitsyapihilt.databinding.FragmentHomeFlowBinding
import com.example.kitsyapihilt.presentation.base.BaseFlowFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFlowFragment : BaseFlowFragment(R.layout.fragment_home_flow, R.id.home_host_fragment) {

    private val binding by viewBinding(FragmentHomeFlowBinding::bind)

    override fun setupNavigation(navController: NavController) {
        binding.bottomNavigation.setupWithNavController(navController)
    }
}