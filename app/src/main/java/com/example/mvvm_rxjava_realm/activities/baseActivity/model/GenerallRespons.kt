package com.example.mvvm_rxjava_realm.activities.baseActivity.model

import com.google.gson.annotations.SerializedName

class GenerallRespons {
    @SerializedName("Search")
    var Search: ArrayList<Movies>? = null
    @SerializedName("totalResults")
    var totalResults: Int = 0
    @SerializedName("Response")
    var Response: Boolean = false
}