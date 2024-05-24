package com.example.ejercicio_ocho

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun save(v: View){
        val name = findViewById<EditText>(R.id.txtName)
        val phone = findViewById<EditText>(R.id.txtPhoneNomber)
        val contact = Contact(0, name.text.toString(), phone.text.toString())

        val retrofit = RetrofitApp.getRetrofit()
        val service = retrofit.create(IContact::class.java)

        val petition: Call<Boolean> = service.addPersona(contact)

        petition.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                val status = response.body()
                Log.v("Respuesta", "Respuesta $status")
                if (status == true) {
                    Toast.makeText(applicationContext, "Save", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

        })
        //ProvicionalData.listContact.add(contact)
        //Toast.makeText(this, "Save", Toast.LENGTH_LONG).show()
        finish()
    }

    fun cancelOrDelete(v: View){
        finish()
    }


}