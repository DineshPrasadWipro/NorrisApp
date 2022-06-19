package com.ds.norris.ui.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ds.norris.databinding.FragmentRandomBinding
import com.ds.norris.ui.BaseFragment
import com.ds.norris.ui.ViewDialog
import com.ds.norris.viewmodel.random.RandomJokeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RandomJokeFragment : BaseFragment() {

    private var _binding: FragmentRandomBinding? = null
    private val randomJokeViewModel: RandomJokeViewModel by viewModel()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRandomBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        showProgress()
        randomJokeViewModel.getRandomNorris()

        randomJokeViewModel.randomNorris.observe {
            cancelProgress()
            displayDialog(it.value, it.url)

        }

        randomJokeViewModel.errorMessage.observe {
            cancelProgress()
            Toast.makeText(requireContext().applicationContext, it, Toast.LENGTH_SHORT).show()
        }


    }

    private fun displayDialog(title: String, imageUrl: String) {
        ViewDialog().showDialog(requireContext(), title, imageUrl)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}