package com.example.mvvm_rxjava_realm.activities.baseActivity.model

import android.widget.ImageView
import com.google.gson.annotations.SerializedName
import com.bumptech.glide.Glide
import androidx.databinding.BindingAdapter


class Movies {
    @SerializedName("Title")
    var Title = ""
    @SerializedName("Year")
    var Year = ""
    @SerializedName("imdbID")
    var imdbID = ""
    @SerializedName("Type")
    var Type = ""
    @SerializedName("Poster")
    var Poster = ""

    object ImageBindingAdapter {
        @JvmStatic
        @BindingAdapter("bind:posterImage")
        fun setImageUrl(view: ImageView, url: String) {
            Glide.with(view.context).load(url).into(view)
        }
    }
}
