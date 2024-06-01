package com.example.kitsyapihilt.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kitsyapihilt.presentation.ui.fragments.anime.AmineFragments
import com.example.kitsyapihilt.presentation.ui.fragments.manga.MangaFragment
import com.example.kitsyapihilt.presentation.ui.fragments.post.PostFragment
import com.example.kitsyapihilt.presentation.ui.fragments.user.UserFragment

class HomeAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                AmineFragments()
            }

            1 -> {
                MangaFragment()
            }

            2 -> {
                UserFragment()
            }

            else -> PostFragment()
        }
    }
}