package kr.aifor.lyr.knu_finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class CompletePaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_payment)

        val data1 = intent.getStringExtra("data1")
        val data2 = intent.getStringExtra("data2")

        Log.d("intent", "CardInsert -> CompletePayment: ${data1}, ${data2}")

        val orderFinishButton = findViewById<Button>(R.id.comp_btn_orderFinish)
        orderFinishButton.setOnClickListener {
            finish()
        }
    }
}