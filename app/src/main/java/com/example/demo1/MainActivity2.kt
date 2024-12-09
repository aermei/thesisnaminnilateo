package com.example.demo1


import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.example.glbviewer.R

class MainActivity2 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    // Declare variables for drawer layout, navigation view, and toolbar

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Initialize the drawer layout
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        // Set up the toolbar and connect it to this activity
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Initialize the navigation view and set listener for item selection
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        // Set up drawer toggle for opening and closing the navigation drawer
        val toggle = ActionBarDrawerToggle(this,drawerLayout, toolbar,R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Load the default fragment (ProfileFragment) on first launch
        if (savedInstanceState == null) {
            replaceFragment(MapsFragment())
            navigationView.setCheckedItem(R.id.nav_maps) // Highlight the profile item by default
        }
    }

    // Handles item selection in the navigation drawer
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_maps -> replaceFragment(MapsFragment()) // Load ProfileFragment
            R.id.nav_profile -> replaceFragment(ProfileFragment())  // Load MapsFragment
            R.id.nav_logout -> { // Logout action
                Toast.makeText(this,"logout",Toast.LENGTH_SHORT).show()

                // Navigate back to MainActivity (Login screen)
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish() // Close MainActivity2 so it's removed from the activity stack
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START) // Close the drawer after selecting an item
        return true
    }


    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction() // Replace the fragment container with the new fragment
        transaction.replace(R.id.fragment_container, fragment) // Commit the transaction
        transaction.commit()
    }

    // Handles the back button press
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START) // Close drawer if open
        }else {
            onBackPressedDispatcher.onBackPressed() // Use dispatcher for consistent back navigation
        }
            super.onBackPressed() // Call the default back button behavior
        }
    }

