package com.phb.crud

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RVAdapterAlumni(val context: Context, val items: ArrayList<Alumni>) : RecyclerView.Adapter<RVAdapterAlumni.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bindItem(items: Alumni){
            itemView.namaAlumni.text = items.nama
            itemView.umur.text = items.angkatan
            itemView.penyakit.text = items.jurusan

            itemView.btnEdit.setOnClickListener {
                itemView.context.startActivity<MainActivity>(
                    "oldNama" to items.nama,
                    "oldUmur" to items.angkatan,
                    "oldPenyakit" to items.jurusan
                )
            }

            itemView.btnHapus.setOnClickListener {
                itemView.context.database.use {
                    delete(Alumni.TABLE_ALUMNI, "(${Alumni.NAMA} = {nama})",
                        "nama" to items.nama.toString())
                }
                itemView.context.toast("Data Dihapus")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }
}