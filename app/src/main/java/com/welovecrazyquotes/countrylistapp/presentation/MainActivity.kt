package com.welovecrazyquotes.countrylistapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
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

    companion object {
        private val TAG = this::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFrag =
            supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        navController = navHostFrag.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        settingsDataStore = SettingsDataStore(this)

        enableDarkMode()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ui_mode -> {
                enableDarkMode = !enableDarkMode
                viewModel.enableDarkMode(enableDarkMode)
                lifecycleScope.launchWhenCreated {
                    viewModel.isDarkModeEnabled.collect {
                        settingsDataStore.saveDarkModeToPreferences(it, this@MainActivity)
                    }
                }
                invalidateOptionsMenu()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val menuItem = menu?.findItem(R.id.ui_mode)
        lifecycleScope.launchWhenCreated {
            viewModel.isDarkModeEnabled.collect { isEnabled ->
                menuItem?.let {
                    if (isEnabled) {
                        menuItem.icon = AppCompatResources.getDrawable(this@MainActivity, R.drawable.sun)
                    } else {
                        menuItem.icon = AppCompatResources.getDrawable(this@MainActivity, R.drawable.moon)
                    }
                }
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    private fun enableDarkMode() {
        lifecycleScope.launchWhenCreated {
            settingsDataStore.dataStore.collect { isDarkModeEnabled ->
                if (isDarkModeEnabled) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
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