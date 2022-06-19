package com.ds.norris.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ds.norris.R
import com.ds.norris.adapter.NorrisListAdapter
import com.ds.norris.databinding.FragmentSearchBinding
import com.ds.norris.model.CustomNorrisList
import com.ds.norris.ui.BaseFragment
import com.ds.norris.viewmodel.search.SearchJokesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchJokesFragment : BaseFragment() {

    private var _binding: FragmentSearchBinding? = null
    private val searchJokesViewModel: SearchJokesViewModel by viewModel()
    private var adapter: NorrisListAdapter? = null
    private var recyclerView: RecyclerView? = null
    private var searchButton: Button? = null
    private var searchText: TextView? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        recyclerView = root.findViewById(R.id.norrisList)
        searchButton = root.findViewById(R.id.searchButton)
        searchText = root.findViewById(R.id.searchText)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.HORIZONTAL
            )
        )

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {


        searchButton?.setOnClickListener {
            searchText?.text?.isNotEmpty().apply {
                showProgress()
                searchJokesViewModel.searchNorris(searchText?.text.toString())
            }

        }
        searchJokesViewModel.errorMessage.observe {
            cancelProgress()
            Toast.makeText(requireContext().applicationContext, it, Toast.LENGTH_SHORT).show()
        }

        searchJokesViewModel.norrisList.observe {
            it?.let {
                setList(it)
            } ?: Toast.makeText(
                requireContext().applicationContext,
                "Network error",
                Toast.LENGTH_SHORT
            ).show()

            cancelProgress()
        }

    }

    private fun setList(list: CustomNorrisList) {
        adapter = NorrisListAdapter(list, requireContext())
        recyclerView?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}