package com.example.coursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tablePrice = TablePrice(this)
        val count = tablePrice.count()
        tablePrice.load_from_table()

        myRecycler.layoutManager = LinearLayoutManager(this)
        myRecycler.setHasFixedSize(true)

        myRecycler.adapter = RecyclerAdapter(count)
    }

    fun go_to_add_position(view: View){
        startActivity(Intent(this, AddActivity::class.java))
    }

    fun go_to_change(view: View) {
        startActivity(Intent(this, ChangeActivity::class.java))
    }
}
