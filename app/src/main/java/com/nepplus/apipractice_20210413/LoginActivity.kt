package com.nepplus.apipractice_20210413

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.nepplus.apipractice_20210413.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvetns()
        setValue()
    }

    override fun setupEvetns() {

        signUpBtn.setOnClickListener {

            val myIntent = Intent(mContext, SignUpActivity::class.java)

            startActivity(myIntent)

        }


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
                            //로그인을 성공한 경우
                            //로그인을 한 사람 + 닉네임 환영합니다
                            //메인화면으로 이동

                            val dataObj = jsonObj.getJSONObject("data")
                            val userObj = dataObj.getJSONObject("user")

                            val nickname = userObj.getString("nick_name")

                            runOnUiThread {
                                Toast.makeText(mContext, "${nickname}님 환영합니다.", Toast.LENGTH_SHORT).show()

                                val myIntent = Intent(mContext, MainActivity::class.java)

                                startActivity(myIntent)

                                finish()
                            }

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