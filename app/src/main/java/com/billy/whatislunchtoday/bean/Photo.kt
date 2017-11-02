package com.billy.whatislunchtoday.bean

/**
 * Created by billylu on 2017/10/31.
 */

class Photo {

    private lateinit var storeName : String
    private lateinit var imgUri : String

    fun setStoreName(storeName : String){
        this.storeName = storeName
    }

    fun getStoreName() : String{
        return this.storeName
    }

    fun setImgUri(imgUri: String) {
        this.imgUri = imgUri
    }

    fun getImgUri() : String {
        return this.imgUri
    }



}