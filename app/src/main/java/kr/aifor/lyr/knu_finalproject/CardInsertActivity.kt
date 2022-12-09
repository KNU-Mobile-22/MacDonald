package kr.aifor.lyr.knu_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.TextView

class CardInsertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_insert)

        val data1 = intent.getStringExtra("data1")
        val data2 = intent.getStringExtra("data2")
        val fireBaseData = intent.getSerializableExtra("fireBaseData") as HashMap<String, Menu>
        val orderMap = intent.getSerializableExtra("orderMap") as HashMap<Int, Int>
        val orderText : TextView = findViewById(R.id.cardInsertOrderList)

        Log.d("intent", "PaymentSelect -> CardInsert: ${data1}, ${data2}")

        //주문내역 출력
        var orderList :String = ""
        val keys = orderMap.keys
        var priceSum = 0
        for (key in keys) {
            val num = orderMap.get(key)!!
            val menu = fireBaseData.get(key.toString())!!
            val name = menu.name
            val price: Int = menu.price * num
            priceSum += price
            orderList+= "${name} : ${num}개 : ${price}원\n"
        }
        orderList+="                  총액 : ${priceSum}"
        orderText.setText(orderList)
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