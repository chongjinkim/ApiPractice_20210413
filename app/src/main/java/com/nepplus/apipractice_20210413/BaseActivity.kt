package com.nepplus.apipractice_20210413

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(){

    val mContext = this

    abstract fun setupEvetns()
    abstract fun setValue()
}