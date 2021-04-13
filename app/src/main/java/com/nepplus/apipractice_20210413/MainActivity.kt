package com.nepplus.apipractice_20210413

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.nepplus.apipractice_20210413.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvetns()
        setValue()
    }

    override fun setupEvetns() {
        loginBtn.setOnClickListener {

            //입력할 이메일 , 비번 추출

            val inputEmail = emailEdt.text.toString()
            val inputPassword = passwordEdt.text.toString()

//            서버에 로그인 요청 코드, 서버 또한 어떤거 했나.
            ServerUtil.postRequestLogin(
                inputEmail,
                inputPassword,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(jsonObj: JSONObject) {

                        val codeNum = jsonObj.getInt("code")

                        if (codeNum == 200) {

                        } else {

                            val message = jsonObj.getString("message")

                            runOnUiThread {
//로그인 실패 -> 띄워보자.
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                            }

                        }

                    }

                })


        }
    }

    override fun setValue() {

    }

}