package com.billy.whatislunchtoday.activity

import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.FrameLayout
import com.billy.whatislunchtoday.R

/**
 * Created by billylu on 2017/10/30.
 */

open class BaseActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName

    private lateinit var drawerLayout : DrawerLayout
    private lateinit var navigationView : NavigationView
    private lateinit var frameLayout : FrameLayout
    private lateinit var toolbar : Toolbar


    override fun setContentView(layoutResID: Int) {
        drawerLayout = layoutInflater.inflate(R.layout.navigation_drawer, null) as DrawerLayout
        navigationView = drawerLayout.findViewById(R.id.navigationView)
        frameLayout = drawerLayout.findViewById(R.id.frameLayout)

        layoutInflater.inflate(layoutResID, frameLayout, true)
        super.setContentView(drawerLayout)

        toolbar = findViewById(R.id.toolbar)

        setNavigationListener()

    }

    private fun setNavigationListener() {
        navigationView.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.lunch_list -> {
                        var intent = Intent(this@BaseActivity, MainActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(0,0)
                        finish()
                    }
                    R.id.today_menu -> {
                        var intent = Intent(this@BaseActivity, TodayMenuActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(0,0)
                        finish()
                    }
                    R.id.food_list -> {
                        var intent = Intent(this@BaseActivity, FoodListActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(0,0)
                        finish()
                    }
                    R.id.drink_list -> {
                        var intent = Intent(this@BaseActivity, DrinkActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(0,0)
                        finish()
                    }
                }
                return true
            }
        })
    }

    fun setUpToolbar() {
        setSupportActionBar(toolbar)
        var toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.DrawerOpen, R.string.DrawerClose)
        drawerLayout.setDrawerListener(toggle)
        toggle.syncState()
    }
}