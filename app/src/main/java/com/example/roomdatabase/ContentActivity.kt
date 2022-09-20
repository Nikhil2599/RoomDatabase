package com.example.roomdatabase

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleCursorAdapter

class ContentActivity : AppCompatActivity() {
    lateinit var cpListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        cpListView = findViewById(R.id.cpListview)

        val uriSms: Uri = Uri.parse("content://sms/inbox")
        val cursor: Cursor? = contentResolver.query(uriSms, null, null, null, null)

        var from = arrayOf("body","address")
        var to = intArrayOf(android.R.id.text1,android.R.id.text2)

        var adapter = SimpleCursorAdapter(this,
            android.R.layout.simple_list_item_2
            ,cursor,from,to)

        cpListView.adapter = adapter

    }
}