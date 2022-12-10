package kr.aifor.lyr.knu_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class PaymentSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_select)

        val data1 = intent.getStringExtra("data1")
        val data2 = intent.getStringExtra("data2")
        val fireBaseData = intent.getSerializableExtra("fireBaseData")
        val orderMap = intent.getSerializableExtra("orderMap")

        Log.d("intent", "GeneralMenu -> PaymentSelect: ${data1}, ${data2}")

        val cardButton = findViewById<Button>(R.id.pay_btn_card)
        cardButton.setOnClickListener {
            val intent2 = Intent(this, CardInsertActivity::class.java)
            // CardInsertActivity로 데이터 전달
            intent2.putExtra("data1", data1)
            intent2.putExtra("data2", data2)
            intent2.putExtra("fireBaseData", fireBaseData)
            intent2.putExtra("orderMap", orderMap)
            startActivity(intent2)
            finish()
        }

        var cashButton = findViewById<Button>(R.id.pay_btn_cash)
        cashButton.setOnClickListener {
            val intent2 = Intent(this, CashInsertActivity::class.java)
            // CashInsertActivity로 데이터 전달
            intent2.putExtra("data1", data1)
            intent2.putExtra("data2", data2)
            intent2.putExtra("fireBaseData", fireBaseData)
            intent2.putExtra("orderMap", orderMap)
            startActivity(intent2)
            finish()
        }

    }
}