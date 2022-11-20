package com.example.home_payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var btn_main : Button
    lateinit var btn_take_in : Button
    lateinit var btn_take_out : Button
    lateinit var btn_option : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        btn_take_in=findViewById(R.id.take_in)
        btn_take_out=findViewById(R.id.take_out)
        btn_main=findViewById(R.id.main)
        btn_option=findViewById(R.id.option)
    }

    fun btnClicked(v: View){
        var btnText : String

        if(v.id == R.id.option){
            btnText=btn_option.text.toString()
            if (btnText == "간편 모드"){
                btn_option.text="포장 및 매장을 선택해주세요"
                //btn_option.setTextSize(10)
            }
            else{
                btn_option.text="간편 모드"
            }
            Toast.makeText(applicationContext, "Button Clicked", Toast.LENGTH_SHORT).show()
        }
    }

}