package com.example.contactstest

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET



    //Steps to setting up retrofit
    //1 - data classes (ContactResponse)
    //2 - dependencies (implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    //    implementation 'com.squareup.retrofit2:converter-gson:2.9.0') <= Gradle Scripts
    //3 - interface
    //4 - retrofit builder

    interface RetrofitNetwork{

        @GET("contacts")
        fun getMeContacts():Call<ContactResponse>
        // Call type that expose enqueue and execute

        companion object{
            fun initRetrofit(): RetrofitNetwork{
                return Retrofit.Builder()
                    .baseUrl("https://api.androidhive.info/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RetrofitNetwork::class.java)
                //.baseUrl & ... are all attached to Retrofit.Builder
            }
        }
    }

