package com.ayodkay.alpha.helpiechallenge.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ayodkay.alpha.helpiechallenge.App.Companion.context
import com.ayodkay.alpha.helpiechallenge.R
import com.ayodkay.alpha.helpiechallenge.models.Users
import com.ayodkay.alpha.helpiechallenge.ui.users.UsersPosts

class UsersAdapters internal constructor(private val userList: ArrayList<Users>):
    RecyclerView.Adapter<UsersAdapters.UsersHolder>() {

    inner class UsersHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val ids: TextView = itemView.findViewById(R.id.userId)
        val names : TextView = itemView.findViewById(R.id.name)
        val emails : TextView = itemView.findViewById(R.id.email)
        val citys: TextView = itemView.findViewById(R.id.city)
        val companyName : TextView = itemView.findViewById(R.id.company_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.users_card, parent, false)
        return UsersHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UsersHolder, position: Int) {
        val userPosition = userList[position]

        with(userPosition){
            with(holder){
                ids.text = "User ID: $id"
                names.text  =  "Name: $name"
                emails.text  = "Email: $email"
                citys.text  = "City: ${address.city}"
                companyName.text  = "Company Mame: ${company.name}"

                itemView.setOnClickListener {
                    context.startActivity(Intent(context,UsersPosts::class.java).apply {
                        putExtra("userId",id)
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    })
                }
            }

        }


    }
}