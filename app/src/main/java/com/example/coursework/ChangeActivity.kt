package com.example.coursework

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChangeActivity: AppCompatActivity() {
    lateinit var idText: EditText
    lateinit var tablePrice: TablePrice

    lateinit var nameBtn: Button
    lateinit var descriptionBtn: Button
    lateinit var priceBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change)

        idText = findViewById(R.id.editText_id)
        tablePrice = TablePrice(this)

        nameBtn = findViewById(R.id.change_name_btn)
        descriptionBtn = findViewById(R.id.change_description_btn)
        priceBtn = findViewById(R.id.change_price_btn)

        nameBtn.isEnabled = false
        descriptionBtn.isEnabled = false
        priceBtn.isEnabled = false

    }

    fun choose_item(view: View) {
        if(idText.text.isEmpty()){
            val toast = Toast.makeText(this, "Поле ID не может быть пустым", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        } else {
            val id = idText.text.toString().toInt()

            if (tablePrice.checkId(id)) {
                val name: TextView = findViewById(R.id.text_near_name)
                val description: TextView = findViewById(R.id.text_near_description)
                val price: TextView = findViewById(R.id.text_near_price)

                val data = tablePrice.returnItemData(id)

                name.text = "Текущее название: ${data[0]}"
                description.text = "Текущее описание: ${data[1]}"
                price.text = "Текущая цена: ${data[2]}"

                nameBtn.isEnabled = true
                descriptionBtn.isEnabled = true
                priceBtn.isEnabled = true
            } else {
                val toast = Toast.makeText(this, "Нет такого элемента", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        startActivity(Intent(this, MainActivity::class.java))
    }

    fun changeName(view: View) {
        if(idText.text.isEmpty()){
            val toast = Toast.makeText(this, "Поле ID не может быть пустым", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        } else {
            val id = idText.text.toString().toInt()

            if (tablePrice.checkId(id)) {
                val nameText: EditText = findViewById(R.id.name_editText)
                val nameHelpText: TextView = findViewById(R.id.text_near_name)
                val name = nameText.text.toString()

                if (name.isEmpty()) {
                    val toast =
                        Toast.makeText(this, "Название не может быть пустым", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.TOP, 0, 0)
                    toast.show()
                } else {
                    tablePrice.changeName(id, name)
                    val data = tablePrice.returnItemData(id)
                    nameHelpText.text = "Текущее название: ${data[0]}"
                }
            } else {
                val toast = Toast.makeText(this, "Неправильные данные", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
            }
        }
    }

    fun changeDescription(view: View) {
        if(idText.text.isEmpty()){
            val toast = Toast.makeText(this, "Поле ID не может быть пустым", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        } else {
            val id = idText.text.toString().toInt()

            if (tablePrice.checkId(id)) {
                val descriptionText: EditText = findViewById(R.id.description_editText)
                val description = descriptionText.text.toString()
                val descriptionHelpText: TextView = findViewById(R.id.text_near_description)

                if (description.isEmpty()) {
                    val toast =
                        Toast.makeText(this, "Описание не может быть пустым", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.TOP, 0, 0)
                    toast.show()
                } else {
                    tablePrice.changeDescription(id, description)
                    val data = tablePrice.returnItemData(id)
                    descriptionHelpText.text = "Текущее описание: ${data[1]}"
                }
            } else {
                val toast = Toast.makeText(this, "Неправильные данные", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
            }
        }
    }

    fun changePrice(view: View) {
        val id = idText.text.toString().toInt()

        if (tablePrice.checkId(id)){
            val priceText: EditText = findViewById(R.id.price_editText)
            val priceHelpText: TextView = findViewById(R.id.text_near_price)

            if(priceText.text.isEmpty()){
                val toast = Toast.makeText(this, "Цена не может быть пустой", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
            } else {
                val price = priceText.text.toString().toInt()
                tablePrice.changePrice(id, price)
                val data = tablePrice.returnItemData(id)
                priceHelpText.text = "Текущая цена: ${data[2]}"
            }
        } else {
            val toast = Toast.makeText(this, "Неправильные данные", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        }
    }

    fun deletePosition(view: View) {
        val id = idText.text.toString().toInt()

        if(tablePrice.checkId(id)) {
            tablePrice.deletePosition(id)
            tablePrice.updateTableData()

            val toast = Toast.makeText(this, "Элемент удален", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        } else {
            val toast = Toast.makeText(this, "Неправильные данные", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        }
    }


}