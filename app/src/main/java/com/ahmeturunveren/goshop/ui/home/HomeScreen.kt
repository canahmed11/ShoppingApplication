package com.ahmeturunveren.goshop.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahmeturunveren.goshop.R
import com.ahmeturunveren.goshop.databinding.FragmentHomeScreenBinding
import com.ahmeturunveren.goshop.ui.home.adapter.HomeAdapter
import com.ahmeturunveren.goshop.util.extensions.gone
import com.ahmeturunveren.goshop.util.extensions.toastMessage
import com.ahmeturunveren.goshop.util.extensions.visible
import com.ahmeturunveren.goshop.util.resource.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private val homeAdapter by lazy { HomeAdapter(onCategoryClick = ::onCategoryClick) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeToolbar.apply {
            favButton.gone()
            customToolbar.navigationIcon = null
        }
        initObserver()
    }

    private fun initObserver() {
        homeViewModel.getCategoryList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    binding.apply {
                        homeTextView.text = getString(R.string.categories)
                        homeProgressBar.gone()
                    }
                    response.data?.let {
                        initAdapter(it)
                    }
                }
                is Resource.Error -> {
                    binding.apply {
                        homeTextView.gone()
                        homeProgressBar.visible()
                    }
                    requireContext().toastMessage(getString(R.string.somethings_wrong))
                }
                is Resource.Loading -> {
                    binding.apply {
                        homeProgressBar.visible()
                    }
                }
                else -> {}
            }
        }
    }

    private fun initAdapter(list: List<String>) {
        binding.homeRecyclerView.adapter = homeAdapter
        homeAdapter.submitList(list)
    }

    private fun onCategoryClick(category: String) {
        val action = HomeScreenDirections.actionHomeScreenToProductsScreen(category)
        findNavController().navigate(action)
    }
}