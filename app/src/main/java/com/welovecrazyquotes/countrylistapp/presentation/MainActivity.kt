package com.welovecrazyquotes.countrylistapp.presentation

import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.welovecrazyquotes.countrylistapp.presentation.adapter.CountryAdapter
import com.welovecrazyquotes.countrylistapp.R
import com.welovecrazyquotes.countrylistapp.common.SettingsDataStore
import com.welovecrazyquotes.countrylistapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var settingsDataStore: SettingsDataStore
    private var enableDarkMode = false
    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFrag = supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        navController = navHostFrag.navController

//        setupActionBarWithNavController(navController)
        settingsDataStore = SettingsDataStore(this)
        darkMode()
    }

    private fun darkMode() {
        enableDarkMode()

        binding.uiMode.setOnClickListener {
            enableDarkMode = !enableDarkMode
            viewModel.enableDarkMode(enableDarkMode)
            lifecycleScope.launchWhenCreated {
                settingsDataStore.saveDarkModeToPreferences(enableDarkMode, this@MainActivity)
            }
        }
    }

    private fun enableDarkMode() {
        lifecycleScope.launchWhenCreated {
            settingsDataStore.dataStore.collect { isDarkModeEnabled ->
                if (isDarkModeEnabled) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    binding.uiMode.setImageResource(R.drawable.sun)
                    binding.title.setTextColor(Color.WHITE)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    binding.uiMode.setImageResource(R.drawable.moon)
                    binding.title.setTextColor(Color.BLACK)
                }
            }
            /**
             *  persists the darkMode value past configuration changes
             */
            viewModel.isDarkModeEnabled.collect {
                enableDarkMode = it
            }
        }
    }
}