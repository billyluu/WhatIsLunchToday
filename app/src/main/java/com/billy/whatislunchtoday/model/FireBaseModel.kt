package com.billy.whatislunchtoday.model

import android.util.Log
import com.billy.whatislunchtoday.bean.Lunch
import com.billy.whatislunchtoday.bean.Photo

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*
import kotlin.collections.HashMap


/**
 * Created by billylu on 2017/7/12.
 */

class FireBaseModel {
    private val TAG = FireBaseModel::class.java.simpleName

    private val database: FirebaseDatabase
    private val myRef: DatabaseReference

    init {
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("storage")
    }

    fun saveData(sort: String, key: String, value: String) {
        myRef.child(sort).push().child(key).setValue(value)

    }

    fun saveMapData(sort : String, map : HashMap<String, String>){
        var key = myRef.child(sort).push().key

        var post = HashMap<String, HashMap<String, String>>()
        post.put("/" + sort + "/" + key, map)

        myRef.updateChildren(post as Map<String, Any>?)
    }

    fun readData(sort: String, fireBaseCallBack: FireBaseCallBack) {
        myRef.child(sort).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.value == null){
                    fireBaseCallBack.onNullData("目前尚無資訊")
                } else {
                    var map = dataSnapshot.value as HashMap<String, String>
                    var photoList = ArrayList<Photo>()
                    for (i in map.keys){
                        var photo = Photo()
                        var photoMap = map.get(i) as HashMap<String, String>
                        photo.setStoreName(photoMap.get("storeName")!!)
                        photo.setImgUri(photoMap.get("imgUri")!!)
                        photoList.add(photo)
                    }
                    fireBaseCallBack.onGetData(photoList)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

    fun readLunchData(sort: String, fireBaseCallBack: FireBaseCallBack2) {
        myRef.child(sort).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.value == null){
                    fireBaseCallBack.onNullData("目前尚無資訊")
                } else {
                    var map = dataSnapshot.value as HashMap<String, String>
                    var LunchList = ArrayList<Lunch>()
                    for (i in map.keys){
                        var lunch = Lunch()
                        var lunchMap = map.get(i) as HashMap<String, String>
                        lunch.setNickName(lunchMap.get("nickname")!!)
                        lunch.setFood(lunchMap.get("food")!!)
                        lunch.setDrink(lunchMap.get("drink")!!)
                        LunchList.add(lunch)
                    }
                    fireBaseCallBack.onGetData(LunchList)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }


    fun deleteData(key: String) {
        myRef.child(key).removeValue()
    }


    fun deleteData(date: String, child: String) {
        Log.i(TAG, date)
        Log.i(TAG, child)
        myRef.child(date).child(child).removeValue()
    }

    interface FireBaseCallBack {
        fun onGetData(list: List<Photo>)
        fun onNullData(msg : String)
    }

    interface FireBaseCallBack2 {
        fun onGetData(list: List<Lunch>)
        fun onNullData(msg : String)
    }
}
