package com.example.week6exercise.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.week6exercise.model.Dogs
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DogListViewmodel(application: Application): AndroidViewModel(application)  {
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    val dogsLD = MutableLiveData<ArrayList<Dogs>>()
    val dogsLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    fun refresh() {
        dogsLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Dogs>>() { }.type
                val result = Gson().fromJson<ArrayList<Dogs>>(it, sType)
                dogsLD.value = result as ArrayList<Dogs>?
                loadingLD.value = false
                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", it.toString())
                dogsLoadErrorLD.value = false
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}