package com.example.android.rockets.detailspage


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.rockets.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentDetailsBinding.inflate(inflater)

        binding.lifecycleOwner = this

        val rocket = DetailsFragmentArgs.fromBundle(arguments!!).selectedRocket
        val viewModelFactory = DetailsViewModelFactory(rocket, application)
        val viewModel = ViewModelProviders.of(
            this, viewModelFactory
        )
            .get(DetailsViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.wikiUrlString.observe(this, Observer {
            it?.let {
                val uri = Uri.parse(it)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                if (null != intent.resolveActivity(activity!!.packageManager)) {
                    startActivity(intent)
                }
                viewModel.wikiNavigationDone()
            }
        })
        return binding.root
    }
}
