package com.example.ejercicio_ocho

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.PUT

interface IContact {
    companion object {
        const val url = "http://10.1.2.35:4567"
    }

    @GET("/contacts")
    fun getPersonas(): Call<List<Contact>>

    @PUT("/contacto/agregar")
    fun addPersona(@Body contact: Contact): Call<Boolean>

    @PUT("/persona/modificar")
    fun modifyPersona(@Body contact: Contact): Call<Boolean>

    @HTTP(method = "DELETE", path = "/persona/borrar", hasBody = true)
    fun deletePersona(@Body contact: Contact): Call<Boolean>

}