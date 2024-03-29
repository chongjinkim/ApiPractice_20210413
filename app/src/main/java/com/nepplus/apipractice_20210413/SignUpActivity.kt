package com.nepplus.apipractice_20210413

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nepplus.apipractice_20210413.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

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

            ServerUtil.putRequestSignUp(
                inputEmail,
                inputPassword,
                inputNickName,
                object : ServerUtil.JsonResponseHandler {

                    override fun onResponse(jsonObj: JSONObject) {

                        val code = jsonObj.getInt("code")

                        if (code == 200) {

                            runOnUiThread {

                                Toast.makeText(mContext, "가입을 환영합니다.", Toast.LENGTH_SHORT).show()
                                finish()
                            }

                        } else {

                            val message = jsonObj.getString("message")

                            runOnUiThread {

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