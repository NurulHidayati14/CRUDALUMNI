package com.phb.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.update
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var oldNama = intent.getStringExtra("oldNama")
        var oldAngkatan = intent.getStringExtra("oldAngkatan")
        var oldJurusan = intent.getStringExtra("oldJurusan")


        if (oldAngkatan.isNullOrBlank()){
            buttonUpdate.isEnabled = false
        }else{
            buttonSimpan.isEnabled = false
            editTextNama.setText(oldNama)
            editTextUmur.setText(oldAngkatan)
            editTextPenyakit.setText(oldJurusan)
        }

        buttonSimpan.setOnClickListener {
            addDataAlumni()

            // clear data
            clearData()
        }

        buttonLihatData.setOnClickListener {
            startActivity<ListAlumniActivity>()
        }

        buttonUpdate.setOnClickListener {
            database.use {
                update(Alumni.TABLE_ALUMNI,
                    Alumni.NAMA to editTextNama.text.toString(),
                    Alumni.ANGKATAN to editTextUmur.text.toString(),
                    Alumni.JURUSAN to editTextPenyakit.text.toString())
                    .whereArgs("${Alumni.NAMA} = {nama}",
                    "nama" to oldNama
                    ).exec()
            }

            // clear data
            clearData()
            toast("Data Diupdate")
        }
    }

    private fun addDataAlumni() {
        database.use {
            insert(Alumni.TABLE_ALUMNI,
                Alumni.NAMA to editTextNama.text.toString(),
                Alumni.ANGKATAN to editTextUmur.text.toString(),
                Alumni.JURUSAN to editTextPenyakit.text.toString()
            )
            toast("Data berhasil disimpan!")
        }
    }

    fun clearData(){
        editTextNama.text.clear()
        editTextUmur.text.clear()
        editTextPenyakit.text.clear()
    }
}