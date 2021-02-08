package com.phb.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list_alumni.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class ListAlumniActivity : AppCompatActivity() {

    private lateinit var adapter: RVAdapterAlumni
    private var alumni = ArrayList<Alumni>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_alumni)

        adapter = RVAdapterAlumni(this, alumni)
        recylerView.adapter = adapter

        getData()
        recylerView.layoutManager = LinearLayoutManager(this)
    }

    private fun getData() {
        database.use {
            alumni.clear()
            var result = select(Alumni.TABLE_ALUMNI)
            var dataKayu = result.parseList(classParser<Alumni>())
            alumni.addAll(dataKayu)
            adapter.notifyDataSetChanged()
        }
    }
}