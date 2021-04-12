package com.nepplus.apipractice_20210413.utils

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

//화면 (액티비티)의 입장에서, 서버에 다녀오면 할 행동을 적는 가이드북
//행동 지침을 기록하는 개념 : Interface

    interface JsonResponseHandler{

        fun onResponse(jsonObj : JSONObject)
    }

    //서버에 요청할 에 필요한 것들 즉 Reuqest를 API에 날리는 법을 배운다.
    companion object{

//        이 중괄호 안에 적히는 변수나 함수는 서버 유틸 자체의 클래스 적히는 static이다. ->
//    serverutil 변수 or 함수 클래스 자체의 기능으로 활요 ㅇ가능
//java static 개념에 대응이 된다.


//        서버의 호스트 주소를 저장해두었다.
        val HOST_URL = "http://15.164.153.174"

 //서버로 로그인에 요청하는 기능 -> 결국 기능 fun으로 만든다.

        fun postRequestLogin(email : String, pw : String, handler : JsonResponseHandler?){

//            어느 주소로 가야 하는가? 호스트주소 / 기능주소

        val urlString = "${HOST_URL}/user"
//필요한 이메일 비번 + 로그인 결과에 대한 처리방안(가이드북)
//


//            갈 때 어떤 파라미터를 가져가야 하는가 ? Post vs get으로 나뉜다
//           모든 정보 종합 + 어떤 메소드를 가져 가는가- 어디로 가는지

        val formData = FormBody.Builder()
            .add("email", email)
            .add("password", pw)
            .build()

//            모든 정보를 들고 -> 실제 API 진행 요청
        val request = Request.Builder()
            .url(urlString) // 어디로 가는지 작성
            .post(formData) // 들고갈 데이터가 무엇인지 작성을 한다.
            .build() //마무리

//       정리된 정보를 들고 실제 API를 진행을 한다.

//  클라이언트로써 동작을 하는 코드

            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
//          서버에 연결 자체를 실패 - 서버에 접근할 수 없는 상황
//            데이터 요금 소진, 서버 터짐등의 이유로 연결 실패
//                    반대 로그인 - 비번 틀림, 회원가입 이메일 등등 중복 실패 ==> 연결은 성공, 결과만 실패, 여기에서 처리하지 않는다.
                }

                override fun onResponse(call: Call, response: Response) {

//                    서버에 응답을 받나내는 경우에 성공하는 경우
                    //응답response 바디만 본다.body -> String으로 저장


//                    toString사용 아님 String() 사용
                   val bodyString = response.body!!.string()

//                    bodyString은 인코딩이 되어있는 상태라 -> 사람이 읽기가 어렵다. (한글 깨짐)
//                    bodyString은 jsonobject로 변환을 시키게 되면은 읽을 수 있게 된다.

                    val jsonObj = JSONObject(bodyString)

                    Log.d("서버응답테스트", jsonObj.toString())

//                    받아낸 서버응답은, severtuitl이 아니라 활용하는 게 아니라
//                    화면에서 UI를 받아내기 위해 사용
//                    code -> 400

//              완성해낸 jsonObj변수를 => 액티비티에 넘겨주자 => 파싱 등등 처리는 액티비티에서 작성
                handler?.onResponse(jsonObj)

                }


            })

//           응답에 대한 처리가 필요함


        }

    }
}