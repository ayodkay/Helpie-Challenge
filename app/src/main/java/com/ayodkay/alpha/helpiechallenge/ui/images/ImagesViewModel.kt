package com.ayodkay.alpha.helpiechallenge.ui.images

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayodkay.alpha.helpiechallenge.App.Companion.context
import com.kymjs.rxvolley.RxVolley
import com.kymjs.rxvolley.client.HttpCallback
import org.json.JSONArray


class ImagesViewModel : ViewModel() {
    private var _images: MutableLiveData<JSONArray>? = null


    fun getImages(): LiveData<JSONArray>
    {
        if (_images == null) {
            _images = MutableLiveData()
            loadImages()
        }

        return _images!!
    }

    // Do an asynchronous operation to fetch images.
    private fun loadImages()
    {
        val url = "https://jsonplaceholder.typicode.com/photos"

        val callBack = object:HttpCallback(){
            override fun onSuccess(response: String?) {
                val jsonArray = JSONArray(response)
                _images?.value = jsonArray
            }

            override fun onFailure(errorNo: Int, strMsg: String?) {
                Toast.makeText(context,strMsg,Toast.LENGTH_LONG).show()
            }
        }

        RxVolley.Builder()
            .url(url)
            .httpMethod(RxVolley.Method.GET) //default GET or POST/PUT/DELETE/HEAD/OPTIONS/TRACE/PATCH
            .cacheTime(6) //default: get 5min, post 0min
            .contentType(RxVolley.ContentType.JSON) //default FORM or JSON
            .shouldCache(true) //default: get true, post false
            .callback(callBack)
            .encoding("UTF-8") //default
            .doTask()
    }

}