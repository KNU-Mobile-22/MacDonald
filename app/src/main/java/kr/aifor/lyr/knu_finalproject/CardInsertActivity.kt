package kr.aifor.lyr.knu_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox

class CardInsertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_insert)

        val data1 = intent.getStringExtra("data1")
        val data2 = intent.getStringExtra("data2")

        Log.d("intent", "PaymentSelect -> CardInsert: ${data1}, ${data2}")

        val cardCheckBox = findViewById<CheckBox>(R.id.card_check_test)
        cardCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                val intent2 = Intent(this, CompletePaymentActivity::class.java)
                // CompletePaymentActivity로 데이터 전달
                intent2.putExtra("data1", data1)
                intent2.putExtra("data2", data2)
                startActivity(intent2)
                finish()
            }
        }
    }
}