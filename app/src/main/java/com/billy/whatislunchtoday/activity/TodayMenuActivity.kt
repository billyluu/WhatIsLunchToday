package com.billy.whatislunchtoday.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.billy.whatislunchtoday.R

/**
 * Created by JUDY on 2017/11/6.
 */
class TodayMenuActivity : BaseActivity() {

    private lateinit var food_img : ImageView
    private lateinit var food_name : TextView

    private lateinit var drink_img : ImageView
    private lateinit var drink_name : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_menu)

        setUpToolbar()



    }

    override fun onResume() {
        super.onResume()





    }
}