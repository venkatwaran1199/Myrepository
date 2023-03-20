package com.example.repositoryassignment.views.list

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.BindingConversion
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BindingAdapter {

    companion object{

        @androidx.databinding.BindingAdapter("android:emptydatabase")
        @JvmStatic
        fun emptydatabase(view:View,status:MutableLiveData<Boolean>){
            if(view is ImageView && status.value == true) {
                view.visibility = View.VISIBLE
            }else if(view is Button && status.value == true){
                view.visibility = View.VISIBLE
            }else if(view is TextView && status.value == true){
                view.visibility = View.VISIBLE
            }
        }

        @androidx.databinding.BindingAdapter(value = ["android:Sharename" , "android:Shareurl"], requireAll = true)
        @JvmStatic
        fun ShareIntent(view:ImageView,name:String?=null,url:String?=null){
            view.setOnClickListener {

                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT,"I am ${name} and you can visit me on my repo ${url}")
                    }
                    view.context.startActivity(shareIntent)
            }
        }

        @androidx.databinding.BindingAdapter("android:Sendtowebsite")
        @JvmStatic
        fun SendtoWebsite(view:ConstraintLayout,url:String){
            view.setOnClickListener {
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse(url)
                view.context.startActivity(openURL)
            }
        }

        @androidx.databinding.BindingAdapter("android:imageurl")
        @JvmStatic
        fun imageurl(view:ImageView,url:String){
            Glide.with(view.context).load(url).into(view)
        }
    }
}