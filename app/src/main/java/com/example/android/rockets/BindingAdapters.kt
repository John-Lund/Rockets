package com.example.android.rockets

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.rockets.network.Rocket
import com.example.android.rockets.resultspage.ResultsAdapter
import com.example.android.rockets.resultspage.RocketApiServiceStatus


@BindingAdapter("setRecyclerData")
fun setRecyclerViewData(recyclerView: RecyclerView, data: List<Rocket>?) {
    val adapter = recyclerView.adapter as ResultsAdapter
    adapter.submitList(data)
}

@BindingAdapter("loadImage")
fun bindImage(view: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(view.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_icon_animation)
                    .error(R.drawable.broken_image)
            )
            .into(view)
    }
}

@BindingAdapter("setWifiStatus")
fun setWifiStatus(wifiLostImageView: ImageView, status: RocketApiServiceStatus?) {
    when (status) {
        RocketApiServiceStatus.ERROR
        -> {
            wifiLostImageView.visibility = View.VISIBLE
        }
        else -> {
            wifiLostImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("setLoadingStatus")
fun setLoadingStatusImage(loadingStatusImageView: ImageView, status: RocketApiServiceStatus?) {
    when (status) {
        RocketApiServiceStatus.LOADING
        -> {
            loadingStatusImageView.visibility = View.VISIBLE
        }
        else -> {
            loadingStatusImageView.visibility = View.GONE
        }
    }
}