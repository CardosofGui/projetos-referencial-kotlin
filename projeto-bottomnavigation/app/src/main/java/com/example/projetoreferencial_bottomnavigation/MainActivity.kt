package com.example.projetoreferencial_bottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottom_navigation)

        //switchFragment(item1())
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.item1,
            R.id.item2,
            R.id.item3
        ))

        setupActionBarWithNavController(findNavController(R.id.fragment_container), appBarConfiguration)
        bottomNav.setupWithNavController(findNavController(R.id.fragment_container))

        //initBottomNavigation()
    }

    private fun initBottomNavigation() {
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.item1 -> {
                    switchFragment(item1())
                    true
                }
                R.id.item2 -> {
                    switchFragment(item2())
                    true
                }
                R.id.item3 -> {
                    switchFragment(item3())
                    true
                }
                else->false
            }
        }
    }

    private fun switchFragment(item: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, item)
            .commit()
    }
}