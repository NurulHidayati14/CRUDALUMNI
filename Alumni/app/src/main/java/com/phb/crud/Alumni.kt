package com.phb.crud

data class Alumni(var id: Long?, var nama: String?, var angkatan: String?, var jurusan: String?){
    companion object{
        const val TABLE_ALUMNI: String = "TABLE_ALUMNI"
        const val ID: String = "ID_"
        const val NAMA: String = "NAMA"
        const val ANGKATAN: String = "ANGKATAN"
        const val JURUSAN: String = "JURUSAN"

    }
}