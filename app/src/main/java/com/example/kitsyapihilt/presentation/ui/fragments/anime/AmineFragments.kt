package com.example.kitsyapihilt.presentation.ui.fragments.anime

import android.util.Log
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kitsyapihilt.R
import com.example.kitsyapihilt.databinding.FragmentAmineBinding
import com.example.kitsyapihilt.databinding.ItemFilterBinding
import com.example.kitsyapihilt.presentation.base.BaseFragment
import com.example.kitsyapihilt.presentation.extensions.showText
import com.example.kitsyapihilt.presentation.models.anime.toUI
import com.example.kitsyapihilt.presentation.models.categories.DataItemCtUI
import com.example.kitsyapihilt.presentation.ui.adapters.AnimeAdapter
import com.example.kitsyapihilt.presentation.ui.adapters.CategoriesAdapter
import com.example.kitsyapihilt.presentation.ui.adapters.DefaultLoadStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AmineFragments : BaseFragment<FragmentAmineBinding>(R.layout.fragment_amine) {

    override val binding by viewBinding(FragmentAmineBinding::bind)
    private val viewModel by viewModels<AnimeViewModel>()
    private val animeAdapter = AnimeAdapter()
    private val categoriesList = arrayListOf<DataItemCtUI>()
    private val categoriesAdapter: CategoriesAdapter by lazy {
        CategoriesAdapter(categoriesList)
    }

    override fun initialize() {
        setupRecycler()

        animeAdapter.addLoadStateListener { state ->
            binding.progressBar.isVisible = state.source.refresh is LoadState.Loading
        }
    }

    override fun setupListener() {
        binding.btnFilter.setOnClickListener {
            showBottomSheet()
        }
    }

    override fun setupObserves() {
        subscribeToAnime()
        subscribeToCategories()
        binding.etSearch.addTextChangedListener {
            viewModel.search(it.toString())
        }
    }

    private fun subscribeToAnime() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.animeFlow.collectLatest { pagingData ->
                animeAdapter.submitData(pagingData.map { it.toUI() })
            }
        }
    }

    private fun subscribeToCategories() {
        viewModel.getCategoriesState.spectateUiState(
            success = { data -> categoriesAdapter.submitData(data) },
            error = {
                showText(it)
                Log.e("ERROR", it)
            }

        )
    }

    private fun setupRecycler() {
        binding.recyclerView.apply {
            adapter = animeAdapter.withLoadStateFooter(DefaultLoadStateAdapter())
        }
    }

    private fun showBottomSheet() {
        val filerBinding = ItemFilterBinding.inflate(layoutInflater)
        val bottomSheet = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)

        filerBinding.rvCategoriesAnime.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter
        }

        filerBinding.btnApply.setOnClickListener {
            viewModel.filter(categoriesAdapter.getSelectedItems())
            bottomSheet.dismiss()
        }

        filerBinding.btnReset.setOnClickListener {
            viewModel.filter(emptyList())
            categoriesAdapter.clearSelectedItems()
            bottomSheet.dismiss()
        }

        bottomSheet.setContentView(filerBinding.root)
        bottomSheet.show()
    }
}
