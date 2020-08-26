package com.example.contactstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val urlContacts: String = "https://api.androidhive.info/contacts/"

    //Base Url is seperated by .../ , Everything with dots is base url
    val urlBaseUrl = "https://api.androidhive.info/"

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


        //val policy =
        //    StrictMode.ThreadPolicy.Builder().permitAll().build()
        //StrictMode.setThreadPolicy(policy)

        //getMeContacts()

        initNetworkCall()
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

    fun initNetworkCall(){
        RetrofitNetwork.initRetrofit().getMeContacts().enqueue(object: Callback<ContactResponse>{
            override fun onResponse(call: Call<ContactResponse>, response: Response<ContactResponse>) {
                if (response.isSuccessful){
                    initFragment(response.body())
                }
            }

            override fun onFailure(call: Call<ContactResponse>, t: Throwable) {
                // this@MainActivity - jump to mainActivity - "this" will only go to enclosing object in ex would be Callback
                Toast.makeText(this@MainActivity,"Pay your internet connection", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initFragment(body: ContactResponse?) {
        // ?. safely call - safely check if body is not null- if null let{...}
        body?.let {

            val fragmentDisplay = FragmentDisplay.newInstance(it)
            supportFragmentManager.beginTransaction().replace(R.id.fl_fragment_container,fragmentDisplay).commit()
        }
    }

}