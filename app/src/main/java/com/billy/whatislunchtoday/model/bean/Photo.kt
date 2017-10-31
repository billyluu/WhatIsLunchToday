package com.billy.whatislunchtoday.model.bean

import android.net.Uri

/**
 * Created by billylu on 2017/10/31.
 */

class Photo {

    private lateinit var storeName : String
    private lateinit var imgUri : Uri

    fun setStoreName(storeName : String){
        this.storeName = storeName
    }

    fun getStoreName() : String{
        return this.storeName
    }

    fun setImgUri(imgUri: Uri) {
        this.imgUri = imgUri
    }

    fun getImgUri() : Uri {
        return this.imgUri
    }



}