package com.example.contactstest

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// list abstraction over concrete implementation
//when you find {  .. } = class
// [ .. ] means an array or list

/**
 * {
"contacts": [
{
"id": "c200",
"name": "Ravi Tamada",
"email": "ravi@gmail.com",
"address": "xx-xx-xxxx,x - street, x - country",
"gender" : "male",
"phone": {
"mobile": "+91 0000000000",
"home": "00 000000",
"office": "00 000000"
}
},
 **/

/**
 * PARCELABLE AND SERIALIZABLE
 * Process to decompose complex data. => Marshalling - the process of decomposing
 *
 * ContactResponse => decompose
 * Reconstruction == ContactResponse // has to reconstruct the same way
 *
 * PARCELABLE ANDROID (android.os) => Parcel data container for IPC (Inter-process Communication - communicate between two application process's)
 * SERIALIZABLE JAVA (javax.seria..?) => Java Reflection
 *
 *
 *
 */

@Parcelize
data class ContactResponse(val contacts: List<ContactItem>):Parcelable

@Parcelize
data class ContactItem(
    val id: String,
    val name: String,
    val email: String,
    val address:String,
    val gender: String,
    val phone: PhoneItem
): Parcelable

@Parcelize
data class PhoneItem(
    val mobile: String,
    val home:String,
    val office: String
):Parcelable

/*
Java
public class ContactItem{
    final String id;
    final String name;
    final String email;
    final String address;
    final String gender;
    final String phone;

    public ContactItem(String id, String name, String email, String addres){
        this.id = id;
        this.name = name....
    }

    @Override
    public String toString(){}
    @Override
    public int hashCode(){}
    @Override
    public ContactItem copy(){}

}
 */


