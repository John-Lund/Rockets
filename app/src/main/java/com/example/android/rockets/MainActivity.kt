package com.example.android.rockets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View

import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.android.rockets.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupNavigation()
    }

    // navigation setup. Handlers are to allow smooth animation of
    // entry and exit of title text. Appearance of the up button is
    // changed to a chevron here.
    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.title = ""

            when (destination.id) {
                R.id.detailsFragment -> {
                    binding.toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp)

                    val handler = Handler()
                    handler.postDelayed({
                        binding.toolBarTitleTextView.visibility = View.GONE
                    }, 400)
                }
                else -> {
                    val handler = Handler()
                    handler.postDelayed({
                        binding.toolBarTitleTextView.visibility = View.VISIBLE
                    }, 400)
                }
            }
        }
    }
}
