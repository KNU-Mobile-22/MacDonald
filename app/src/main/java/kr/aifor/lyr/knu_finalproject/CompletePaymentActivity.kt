package kr.aifor.lyr.knu_finalproject

import android.app.Activity
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View.inflate
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kr.aifor.lyr.knu_finalproject.databinding.ActivityCompletePaymentBinding
import kr.aifor.lyr.knu_finalproject.databinding.ActivitySelectSetBinding
import kr.aifor.lyr.knu_finalproject.databinding.PointDialogBinding
import kotlin.system.exitProcess

class CompletePaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // val binding = ActivityCompletePaymentBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_complete_payment)

        val totalOrderNum = intent.getIntExtra("totalOrderNum", 0)
        val orderNumber: TextView = findViewById(R.id.order_number)
        orderNumber.setText("${totalOrderNum + 200}")

        val data1 = intent.getStringExtra("data1")
        val data2 = intent.getStringExtra("data2")

        Log.d("intent", "CardInsert -> CompletePayment: ${data1}, ${data2}")

        val orderFinishButton = findViewById<Button>(R.id.comp_btn_orderFinish)

        val text_number = findViewById<TextView>(R.id.btn_number)

        val btn_one = findViewById<Button>(R.id.btn_one)
        val btn_two = findViewById<Button>(R.id.btn_two)
        val btn_three = findViewById<Button>(R.id.btn_three)

        val btn_four = findViewById<Button>(R.id.btn_four)
        val btn_five = findViewById<Button>(R.id.btn_five)
        val btn_six = findViewById<Button>(R.id.btn_six)

        val btn_seven = findViewById<Button>(R.id.btn_seven)
        val btn_eight = findViewById<Button>(R.id.btn_eight)
        val btn_nine = findViewById<Button>(R.id.btn_nine)

        val btn_clear = findViewById<Button>(R.id.btn_clear)
        val btn_zero = findViewById<Button>(R.id.btn_zero)

        var btn_point = findViewById<Button>(R.id.comp_btn_point)

        /*
        ????????? '????????? ???????????????' ??????
        ?????? ????????? ???????????? ???????????? ????????? ???????????????
        '-'??? ????????????
        ????????? ?????? ????????? 11?????????
         */

        /*
        1. ????????? ????????? 0?????? ??????????????? 0??? ????????? '????????? ???????????????'??? ???????????? 0??? ???????????????
         */

        var customer_number = ""
        //clear ??? ????????? ????????? ??? ?????????

        btn_clear.setOnClickListener {
            text_number.setText("")
            customer_number = ""
        }

        btn_one.setOnClickListener {
            val index = "1"
            customer_number += index
            text_number.setText(customer_number)
        }

        btn_two.setOnClickListener {
            val index = "2"
            customer_number += index
            text_number.setText(customer_number)
        }

        btn_three.setOnClickListener {
            val index = "3"
            customer_number += index
            text_number.setText(customer_number)
        }

        btn_four.setOnClickListener {
            val index = "4"
            customer_number += index
            text_number.setText(customer_number)
        }

        btn_five.setOnClickListener {
            val index = "5"
            customer_number += index
            text_number.setText(customer_number)
        }

        btn_six.setOnClickListener {
            val index = "6"
            customer_number += index
            text_number.setText(customer_number)
        }

        btn_seven.setOnClickListener {
            val index = "7"
            customer_number += index
            text_number.setText(customer_number)
        }

        btn_eight.setOnClickListener {
            val index = "8"
            customer_number += index
            text_number.setText(customer_number)
        }

        btn_nine.setOnClickListener {
            val index = "9"
            customer_number += index
            text_number.setText(customer_number)
        }

        btn_zero.setOnClickListener {
            val index = "0"
            customer_number += index
            text_number.setText(customer_number)
        }

        orderFinishButton.setOnClickListener {
            finish()
        }

/*        btn_point.setOnClickListener {
            Log.d("myLog", "compBtnPoint Clicked!")
            val dialogBinding = PointDialogBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setTitle("")
                setView(dialogBinding.root)
                setPositiveButton("??????", null)
                show()
            }
        }*/

        var isTimerRunning = false
        val countDown = object : CountDownTimer(30000, 1000) {

            override fun onTick(p0: Long) {
                // countDownInterval ?????? ?????? (????????? 1000ms)
                Log.d("myLog", "onTick")
            }

            override fun onFinish() {
                // ???????????? ???????????? ??????
                isTimerRunning = false
                finish()
            }
        }.start()

        btn_point.setOnClickListener {
            if (customer_number.equals(""))
                Toast.makeText(applicationContext, "?????? ????????? ???????????????.", Toast.LENGTH_SHORT).show()
            else {
                Toast.makeText(applicationContext, "????????? ?????????????????????.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }


    }
}