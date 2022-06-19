package com.ds.norris.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ds.norris.R
import com.ds.norris.adapter.CategoryAdapter
import com.ds.norris.databinding.FragmentCategoryBinding
import com.ds.norris.ui.BaseFragment
import com.ds.norris.ui.ViewDialog
import com.ds.norris.viewmodel.category.CategoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment : BaseFragment(), CategoryAdapter.ItemClickListener {

    private var _binding: FragmentCategoryBinding? = null
    private val categoryViewModel: CategoryViewModel by viewModel()
    private var adapter: CategoryAdapter? = null
    private var recyclerView: RecyclerView? = null
    private var categories = ArrayList<String>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = root.findViewById(R.id.categoryList)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        showProgress()
        categoryViewModel.getCategories()

        categoryViewModel.categoryList.observe {
            cancelProgress()
            it?.let {
                setList(it)
                categories = it as ArrayList<String>
            }

        }

        categoryViewModel.norris.observe {
            displayDialog(it.value, it.iconUrl)
        }
        categoryViewModel.errorMessage.observe {
            cancelProgress()
            Toast.makeText(requireContext().applicationContext, it, Toast.LENGTH_SHORT).show()
        }

    }

    private fun setList(list: List<String>) {
        adapter = CategoryAdapter(list, this)
        recyclerView?.adapter = adapter
    }

    private fun displayDialog(title: String, imageUrl: String) {
        ViewDialog().showDialog(requireContext(), title, imageUrl)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        categoryViewModel.searchNorrisByCategory(categories[position])
    }
}