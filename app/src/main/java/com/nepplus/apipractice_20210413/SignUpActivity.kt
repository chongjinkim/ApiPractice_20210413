package com.nepplus.apipractice_20210413

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvetns()
        setValue()
    }

    override fun setupEvetns() {

        signUpBtn.setOnClickListener {

            val inputEmail = emailEdt.text.toString()
            val inputPassword = pwEdt.text.toString()
            val inputNickName = nickNameEdt.text.toString()


        }


    }

    override fun setValue() {

    }


}