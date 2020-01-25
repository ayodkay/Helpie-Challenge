package com.ayodkay.alpha.helpiechallenge.ui.users

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayodkay.alpha.helpiechallenge.App.Companion.context
import com.kymjs.rxvolley.RxVolley
import com.kymjs.rxvolley.client.HttpCallback
import org.json.JSONArray

class UsersViewModel : ViewModel() {
    private var _users:MutableLiveData<JSONArray>? = null
    private var _usersPost:MutableLiveData<JSONArray>? = null

    // get all the loaded users
    fun getUsers():MutableLiveData<JSONArray> {
        if(_users ==null){
            _users = MutableLiveData()
            loadUsers()
        }
        return _users!!
    }

    //load all users Asynchronously
    private fun loadUsers(){

        val url = "https://jsonplaceholder.typicode.com/users"

        val callback = object : HttpCallback(){
            override fun onSuccess(response: String?) {
                val jsonArray = JSONArray(response)

                _users?.value = jsonArray
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
            .callback(callback)
            .encoding("UTF-8") //default
            .doTask()
    }


    // get all the loaded users post
    fun getUsersPost(id:Int):MutableLiveData<JSONArray> {
        if(_usersPost ==null){
            _usersPost = MutableLiveData()
            loadUsersPost(id)
        }
        return _usersPost!!
    }
    //load all users post Asynchronously
    private fun loadUsersPost(id: Int){

        val url = "https://jsonplaceholder.typicode.com/posts?userId=$id"

        val callback = object : HttpCallback(){
            override fun onSuccess(response: String?) {
                val jsonArray = JSONArray(response)
                _usersPost?.value = jsonArray
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
            .callback(callback)
            .encoding("UTF-8") //default
            .doTask()
    }
}