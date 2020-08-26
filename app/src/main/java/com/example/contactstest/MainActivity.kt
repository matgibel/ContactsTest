package com.example.contactstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val urlContacts: String = "https://api.androidhive.info/contacts/"

    //Base Url is seperated by .../ , Everything with dots is base url
    val urlBaseUrl = "https://api.androidhive.info"

    //End point starts with slash
    val urlEndPoint = "contacts/"

    // similar to late init - Lazy<FragmentDisplay> => UNINITIALIZED
    // invoke lambda
    val displayFragment: FragmentDisplay by lazy {
        FragmentDisplay()
    }
    //df1 = Fr()
    //df1/passData()
    //df2 = Fr()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val policy =
            StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        getMeContacts()

    }



    private fun getMeContacts(){
        val network = NetworkContacts(urlContacts)
        val contactsResponse =
            network.executeNetworkCall()

        val displayFragment = FragmentDisplay.newInstance(contactsResponse)

        supportFragmentManager.beginTransaction().replace(R.id.fl_fragment_container,displayFragment).commit()


        //Toast.makeText(this,
        //contactsResponse.toString(),
        //Toast.LENGTH_LONG).show()
    }

}