package com.example.android.rockets.resultspage

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.android.rockets.databinding.FragmentResultsBinding

class ResultsFragment : Fragment() {

    private val viewModel: ResultsViewModel by lazy {
        ViewModelProviders.of(this).get(ResultsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentResultsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.resultsRecyclerView.adapter = ResultsAdapter(ResultsAdapter.OnClickListener {
            viewModel.displayDetailsfragment(it)
        })
        viewModel.navigateToSelectedRocket.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(ResultsFragmentDirections.actionResultsFragmentToDetailsFragment(it))
                viewModel.navigateToDetailsFragmentComplete()
            }
        })
        return binding.root
    }
}
