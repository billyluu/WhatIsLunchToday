package com.billy.whatislunchtoday.activity

import android.os.Bundle
import com.billy.whatislunchtoday.R

/**
 * Created by billylu on 2017/10/30.
 */
class FoodListActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        setUpToolbar()


    }
}