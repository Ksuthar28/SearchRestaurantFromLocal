package com.sample.searchrestaurant.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sample.searchrestaurant.R
import com.sample.searchrestaurant.adapters.RestaurantAdapter
import com.sample.searchrestaurant.databinding.HomeFragmentBinding
import com.sample.searchrestaurant.utils.MyExtension.dismissKeyboard
import com.sample.searchrestaurant.utils.Resource
import com.sample.searchrestaurant.viewmodel.RestaurantViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    /*
    Initialize the adapter
     */
    @Inject
    lateinit var rAdapter: RestaurantAdapter

    /*
   Initialize data binding
    */
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    var isLoading = false

    /*
   Initialize the view model
    */
    private val viewModel: RestaurantViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchRestaurant()
        setupRecyclerView()
        showSearchHint()
    }

    /*
   Handle search view text changes
    */
    private fun searchRestaurant() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.dismissKeyboard(requireContext())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null)
                    if (newText.isNotEmpty()) loadData() else showSearchHint()
                return false
            }
        })
    }

    /*
  Set the initial hit for search criteria
   */
    fun showSearchHint() {
        showError(getString(R.string.search_res_hint))
    }

    /*
  Set the recycler view
   */
    private fun setupRecyclerView() {
        with(binding.list) {
            adapter = rAdapter
        }
        initViewModelObservers()
    }

    /**
     * Observer & updating data to the adapter.
     * */
    private fun initViewModelObservers() {
        viewModel.restaurants.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        rAdapter.differ.submitList(it)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        showError(it)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                else -> Unit
            }
        }
    }

    /*
  Fetch data
   */
    private fun loadData() {
        viewModel.getRestaurants(binding.searchView.query.toString(), requireContext())
    }

    /*
  Show error/info text
   */
    private fun showError(error: String) {
        binding.progressBar.visibility = View.GONE
        binding.emptyText.text = error
        binding.emptyText.visibility = View.VISIBLE
        rAdapter.differ.submitList(null)
    }

    /*
  Hide progress bar
   */
    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.emptyText.visibility = View.GONE
        isLoading = false
    }

    /*
  Show progress bar
   */
    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.emptyText.visibility = View.GONE
        isLoading = true
    }

    /*
  remove adapter and data binding
   */
    override fun onDestroy() {
        super.onDestroy()
        binding.list.adapter = null
        _binding = null
    }
}
