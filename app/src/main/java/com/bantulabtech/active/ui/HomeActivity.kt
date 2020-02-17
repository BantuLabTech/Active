package com.bantulabtech.active.ui

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bantulabtech.active.R
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var drawerNavigationView: NavigationView
    lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)
        bottomNavigationView = findViewById(R.id.nav_view)
        drawerNavigationView = findViewById(R.id.drawer_nav_view)
        drawerLayout = findViewById(R.id.container)

        navController = findNavController(R.id.home_nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.loginFragment, R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
        drawerNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{_,destination,_ ->
            hideBottomNavBar(destination)
            hideDrawerNavBar(destination)
        }
    }

    fun hideBottomNavBar(destination: NavDestination){
        if(destination.id == R.id.loginFragment){
            bottomNavigationView.visibility = View.GONE
        }
        else{
            bottomNavigationView.visibility = View.VISIBLE
        }
    }

    fun hideDrawerNavBar(destination: NavDestination){
        if(destination.id == R.id.loginFragment){
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }
        else{
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
    }
}
