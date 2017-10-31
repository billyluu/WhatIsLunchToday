package com.billy.whatislunchtoday.activity

import android.os.Bundle
import com.billy.whatislunchtoday.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setUpToolbar()

    }
}
