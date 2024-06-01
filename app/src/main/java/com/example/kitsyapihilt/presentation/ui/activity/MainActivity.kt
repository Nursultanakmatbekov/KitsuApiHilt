package com.example.kitsyapihilt.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.example.data.local.prefs.TokenPreferenceHelper
import com.example.kitsyapihilt.R
import com.example.kitsyapihilt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.migration.CustomInjection.inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }
    private val tokenPreferenceHelper: TokenPreferenceHelper by lazy {
        TokenPreferenceHelper(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation() {

        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        when {
            tokenPreferenceHelper.accessToken == null -> {
                navGraph.setStartDestination(R.id.singInFlowFragment)
            }

            tokenPreferenceHelper.accessToken != null -> {
                navGraph.setStartDestination(R.id.homeFlowFragment)
            }
        }
        navController.graph = navGraph
    }
}