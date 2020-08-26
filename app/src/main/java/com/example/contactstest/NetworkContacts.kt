package com.example.contactstest

import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.NullPointerException
import java.net.HttpURLConnection
import java.net.URL

/**
 * Query and Return Contact Response
 */


class NetworkContacts(val url: String) {
    // ContactResponse - class type returns the list of contact items
    fun executeNetworkCall(): ContactResponse{

        var contactUrl = URL(url)
        var httpURLConnection: HttpURLConnection =
            contactUrl.openConnection() as HttpURLConnection

        //time measured in millisec -
        //configure the HTTPURLCONNECTION
        httpURLConnection.connectTimeout = 10000
        httpURLConnection.readTimeout = 15000
        httpURLConnection.requestMethod = "GET"
        httpURLConnection.doInput = true
        //Execute the connection
        httpURLConnection.connect()
        //Serialization of httpURLConnection
        val inputStream = httpURLConnection.inputStream
        //receive the Server Response
        val responseCode = httpURLConnection.responseCode

        //creates a string representation of data
        val stringResponse = parseIStoString(inputStream)
        val contactResponse: ContactResponse = parsePoko(stringResponse)


        return contactResponse
    }

    //returns ContactResponse type
    private fun parsePoko(stringResponse: String?): ContactResponse {
        if(stringResponse == null)
            throw  NullPointerException()

        val jsonResponse: JSONObject = JSONObject(stringResponse)
        val jsonArray = jsonResponse.getJSONArray("contacts")

        /**
         * for(index in 0..jsonArray.length())
         * 0 to ->n
         *
         * until - 0 to n-1
         *
         */

        var phoneItem: PhoneItem
        var contactItem: ContactItem

        val listOfContactItem = mutableListOf<ContactItem>() // = new ArrayList<ContactItems>()

        for (index in 0 until jsonArray.length()){
            val jsonItem = jsonArray.get(index) as JSONObject
            val jsonItemPhone = jsonItem.getJSONObject("phone")
            phoneItem = PhoneItem(jsonItemPhone.getString("mobile"),
            jsonItemPhone.getString("home"),
            jsonItemPhone.getString("office"))

            contactItem = ContactItem(
                phone = phoneItem,
                id = jsonItem.getString("id"),
                name = jsonItem.getString("name"),
                gender = jsonItem.getString("gender"),
                email = jsonItem.getString("email"),
                address = jsonItem.getString("address")
            )

            listOfContactItem.add(contactItem)
        }

        return  ContactResponse(listOfContactItem)

    }

    private fun parseIStoString(inputStream: InputStream):String?{

        val builder = StringBuilder()
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line: String? = reader.readLine()
        while (line != null) {
            builder.append("$line\n")
            line = reader.readLine()

        }
        if (builder.length == 0) {
            return null
        }
        return builder.toString()

    }

}