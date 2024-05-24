package com.example.ejercicio_ocho

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditActivity : AppCompatActivity() {

    var position: Int = 0
    var id: Int = 0
    lateinit var txtName: EditText
    lateinit var txtPhoneNumber: EditText
    lateinit var btnDeleteOrCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val txtTitle = findViewById<TextView>(R.id.txtTitle)
        btnDeleteOrCancel = findViewById(R.id.btnDeleteOrCancel)
        btnDeleteOrCancel.text = "Eliminar"
        txtTitle.text = "Modificar Contacto"

        position = intent.getIntExtra("position", -1)
        Log.e("Contact", "Se recibio un ${position}")
        txtName = findViewById(R.id.txtName)
        txtPhoneNumber = findViewById(R.id.txtPhoneNomber)

        val contact = ProvicionalData.listContact[position]
        txtName.setText(contact.name)
        txtPhoneNumber.setText(contact.phone)
        Toast.makeText(this, "ID ${contact.id}", Toast.LENGTH_LONG).show()
        id = contact.id
    }
    fun save(v: View) {
        val contact = Contact(id , txtName.text.toString(), txtPhoneNumber.text.toString())

        val retrofit = RetrofitApp.getRetrofit()
        val service = retrofit.create(IContact::class.java)
        val petition = service.modifyPersona(contact)

        petition.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                val success = response.body()
                if (success!!) {
                    Toast.makeText(applicationContext, "Guardado", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                }

            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
            }

        })
        finish()
    }
    fun cancelOrDelete(v: View) {
        val contact = Contact(id , txtName.text.toString(), txtPhoneNumber.text.toString())

        val retrofit = RetrofitApp.getRetrofit()
        val service = retrofit.create(IContact::class.java)
        val petition = service.deletePersona(contact)

        petition.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                val success = response.body()
                if (success!!) {
                    Toast.makeText(applicationContext, "Eliminado", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
            }

        })
        finish()
    }

}