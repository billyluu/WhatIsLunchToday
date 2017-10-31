package com.billy.whatislunchtoday.model

import android.net.Uri
import android.util.Log
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.sql.Timestamp
import java.util.*

/**
 * Created by billylu on 2017/10/31.
 */
class FireBaseStorageModel {
    private val TAG = javaClass.simpleName

    private var storageRef : StorageReference

    init {
        storageRef = FirebaseStorage.getInstance().reference
    }

    fun uploadImg(sort : String, uri : Uri, callBack: UploadCallBack) {
        var stamp = Timestamp(Date().time)
        var imgRef = storageRef.child(sort + "/" + stamp)
        var upload = imgRef.putFile(uri)
        upload.addOnSuccessListener(object : OnSuccessListener<UploadTask.TaskSnapshot>{
            override fun onSuccess(p0: UploadTask.TaskSnapshot?) {
                imgRef.downloadUrl.addOnSuccessListener { uri ->
                    callBack.onSuccess(uri)
                }
            }
        })
    }

    private fun getUri() {

    }

//    fun fetchAllImg(sort: String, callBack: CallBack) {
//        var imgRef = storageRef.child(sort)
//        imgRef.downloadUrl.addOnSuccessListener { uri: Uri? ->
//            Log.i(TAG, "" + uri)
//        }
//    }



    interface UploadCallBack {
        fun onSuccess(uri: Uri)
    }

}