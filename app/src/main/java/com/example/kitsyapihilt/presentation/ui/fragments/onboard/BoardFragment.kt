package com.example.kitsyapihilt.presentation.ui.fragments.onboard

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.data.local.prefs.TokenPreferenceHelper
import com.example.kitsyapihilt.R
import com.example.kitsyapihilt.databinding.FragmentBoardBinding
import com.example.kitsyapihilt.presentation.base.BaseFragment
import com.example.kitsyapihilt.presentation.extensions.navigateSafely
import com.example.kitsyapihilt.presentation.ui.adapters.OnBoardAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoardFragment : BaseFragment<FragmentBoardBinding>(R.layout.fragment_board) {

    override val binding by viewBinding(FragmentBoardBinding::bind)
    private val tokenPreferenceHelper: TokenPreferenceHelper by lazy {
        TokenPreferenceHelper(requireContext())
    }
    override fun initialize() {
        binding.viewPager.adapter = OnBoardAdapter(requireContext())
        binding.dotsIndicator.attachTo(binding.viewPager)
    }

    override fun setupListener() {
        binding.btmNext.setOnClickListener {
            binding.viewPager.currentItem++
        }

        binding.tvSkip.setOnClickListener {
            tokenPreferenceHelper.onBoardIsShown = true
            findNavController().navigateSafely(R.id.action_boardFragment_to_singInFragment)
        }

        binding.btnStart.setOnClickListener {
            tokenPreferenceHelper.onBoardIsShown = true
            findNavController().navigateSafely(R.id.action_boardFragment_to_singInFragment)
        }

        binding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 2) {
                    binding.btnStart.visibility = View.VISIBLE
                    binding.btmNext.visibility = View.GONE
                } else {
                    binding.btnStart.visibility = View.GONE
                    binding.btmNext.visibility = View.VISIBLE
                }
            }
        })
    }
}