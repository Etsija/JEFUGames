package com.etsija.jefugames.view.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import com.etsija.jefugames.model.db.JEFUdatabase
import com.etsija.jefugames.view.MainActivity
import com.etsija.jefugames.viewmodel.SharedViewModel

abstract class BaseFragment: Fragment() {

    protected val mainActivity: MainActivity
        get() = activity as MainActivity

    protected val appDatabase: JEFUdatabase
        get() = JEFUdatabase.getInstance(requireActivity())

    protected val sharedViewModel: SharedViewModel by activityViewModels()

    // region Navigation helper methods

    // Navigate a level up in the Nav Graph
    protected fun navigateUp() {
        mainActivity.navController.navigateUp()
    }

    // Use the Nav Graph for navigating from fragment A to fragment B
    protected fun navigateViaNavGraph(actionId: Int) {
        mainActivity.navController.navigate(actionId)
    }

    protected fun navigateViaNavGraph(navDirections: NavDirections) {
        mainActivity.navController.navigate(navDirections)
    }

    // endregion Navigation helper methods
}