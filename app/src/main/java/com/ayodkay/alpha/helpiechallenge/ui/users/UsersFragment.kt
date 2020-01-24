package com.ayodkay.alpha.helpiechallenge.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayodkay.alpha.helpiechallenge.R
import com.ayodkay.alpha.helpiechallenge.adapters.UsersAdapters
import com.ayodkay.alpha.helpiechallenge.models.Address
import com.ayodkay.alpha.helpiechallenge.models.Company
import com.ayodkay.alpha.helpiechallenge.models.Geo
import com.ayodkay.alpha.helpiechallenge.models.Users
import kotlinx.android.synthetic.main.fragment_users.*
import kotlinx.android.synthetic.main.users_card.*
import org.json.JSONArray
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class UsersFragment : Fragment() {

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        usersViewModel =
            ViewModelProvider(this).get(UsersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_users, container, false)

        usersViewModel.getUsers().observe(this, Observer {

            users_recycler.apply {
                layoutManager = LinearLayoutManager(context)
                hasFixedSize()
                adapter = UsersAdapters(handleArray(it))
            }

            user_progress.visibility = View.GONE
        })

        return root
    }

    private fun handleArray(jsonArray: JSONArray):ArrayList<Users>{
        val usersList:ArrayList<Users> = ArrayList()

        for(i in 0 until jsonArray.length()){

            val jsObj = jsonArray.getJSONObject(i)
            val id = jsObj.getInt("id")
            val name= jsObj.getString("name")
            val username= jsObj.getString("username")
            val email= jsObj.getString("email")
            val phone= jsObj.getString("phone")
            val website= jsObj.getString("website")

            val companyObj= jsObj.getJSONObject("company")
            val companyName = companyObj.getString("name")
            val catchPhrase = companyObj.getString("catchPhrase")
            val bs = companyObj.getString("bs")
            val company = Company(companyName,catchPhrase,bs)

            val addressObj= jsObj.getJSONObject("address")
            val street = addressObj.getString("street")
            val suite = addressObj.getString("suite")
            val city = addressObj.getString("city")
            val zipCode = addressObj.getString("zipcode")
            val geoObj = addressObj.getJSONObject("geo")
            val lat = geoObj.getString("lat").toDouble()
            val lng = geoObj.getString("lng").toDouble()
            val geo = Geo(lat,lng)
            val address = Address(street,city,suite,zipCode,geo)

            usersList.add(Users(id,name,username,email,address,phone,website,company))


        }

        usersList.sortWith(
            Comparator { p0, p1 -> p0!!.name.compareTo(p1!!.name,true) })

        return usersList

    }
}