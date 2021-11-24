package com.etsija.jefugames.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.etsija.jefugames.R
import com.etsija.jefugames.model.db.JEFUdatabase
import com.etsija.jefugames.viewmodel.SharedViewModel

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // This shared ViewModel is scoped to this activity, so will stay alive for the
        // duration of this activity/app (since it only has one activity)
        val viewModel: SharedViewModel by viewModels()
        viewModel.init(JEFUdatabase.getInstance(this))

        // Controller for navigating between components (fragments)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        // Define top-level fragments
        appBarConfiguration = AppBarConfiguration(setOf(R.id.teamFragment))

        // Setup top app bar
        setupActionBarWithNavController(navController, appBarConfiguration)



    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // Hide keyboard
    fun hideKeyboard(view: View) {
        val imm: InputMethodManager =
            application.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    // Show keyboard
    fun showKeyboard() {
        val imm: InputMethodManager =
            application.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }
}