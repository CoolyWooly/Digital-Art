package kz.digitalart.app.ui

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*
import kz.digitalart.app.R

class MainActivity : DaggerAppCompatActivity() {

    private val TAG = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main_activity)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = Navigation.findNavController(this, R.id.container)
        NavigationUI.setupActionBarWithNavController(this, navController)
        NavigationUI.setupWithNavController(nav_view, navController)
    }

    fun setToolbarTitle(title: String) {
        tv_toolbar.text = title
    }

    override fun onSupportNavigateUp() = Navigation.findNavController(this, R.id.container).navigateUp()
}
