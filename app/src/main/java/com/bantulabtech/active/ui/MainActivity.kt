package com.bantulabtech.active.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.bantulabtech.active.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var drawerNavigationView: NavigationView
    lateinit var drawerLayout: DrawerLayout
    lateinit var appBarConfiguration: AppBarConfiguration
//    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.nav_view)
        drawerNavigationView = findViewById(R.id.drawer_nav_view)
        drawerLayout = findViewById(R.id.container)
//        toolbar =  findViewById(R.id.toolbar)

//        setSupportActionBar(toolbar)


        navController = findNavController(this, R.id.home_nav_host_fragment)
        //note appBarConfig can also be setup with below settings
        // appBarConfiguration = AppBarConfiguration(navController.graph)
         appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.loginFragment, R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )

        setupActionBarWithNavController(this, navController, appBarConfiguration)
        setupWithNavController(bottomNavigationView, navController)
        drawerNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{_,destination,_ ->
            hideBottomNavBar(destination)
            hideDrawerNavBar(destination)
//            hideToolBar(destination)
        }
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
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

//    fun hideToolBar(destination: NavDestination){
//        if(destination.id == R.id.loginFragment){
//            toolbar.visibility = View.GONE
//        }
//        else{
//            toolbar.visibility = View.VISIBLE
//        }
//    }
}