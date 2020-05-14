package com.example.coursework

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
    }

    fun add_position(view: View){
        val tablePrice = TablePrice(this)

        val nameText: EditText = findViewById(R.id.name_add)
        val descriptionText: EditText = findViewById(R.id.description_add)
        val priceText: EditText = findViewById(R.id.price_add)

        val name = nameText.text.toString()
        val description = descriptionText.text.toString()

        if(priceText.text.isEmpty()) {
            val toast = Toast.makeText(this, "Цена не может быть пустой", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        } else {
            val price = priceText.text.toString().toInt()

            if(name.isEmpty()){
                val toast = Toast.makeText(this, "Название не может быть пустым", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
            } else if(description.isEmpty()){
                val toast = Toast.makeText(this, "Описание не может быть пустым", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
            } else {
                tablePrice.add_to_table(name, description, price)
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
}