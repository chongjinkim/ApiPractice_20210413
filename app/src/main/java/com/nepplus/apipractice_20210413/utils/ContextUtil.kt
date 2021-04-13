package com.nepplus.apipractice_20210413.utils

import android.content.Context

class ContextUtil {

    companion object{

       private val prefName = "Daily10Minutespref"

       private val AUTO_LOGIN = "AUTO_LOGIN"

//자동로그인 설정여부 저장

       fun setAutoLogin(context : Context, autoLogin : Boolean){

//       메모장을 열어서 -> 변수에 담아두자

         val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

           pref.edit().putBoolean(AUTO_LOGIN, autoLogin).apply() //apply(저장기능)

// 변수를 이용해서 실제 저장 여부 기록
       }

//설정해둔 자동로그인 여부를 필요하는 getter
       fun getAutoLogin(context : Context) : Boolean{

 //저장할때와 같은 메모장 활용
           val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

//    자동로그인 항목에 저장된 값을 리턴
            return pref.getBoolean(AUTO_LOGIN, false)//기본값없으면 자동로그인 X - fALSE
       }

    }
}