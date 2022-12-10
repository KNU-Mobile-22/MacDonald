package kr.aifor.lyr.knu_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class PaymentSelectActivity : AppCompatActivity() {
    var orderMap = HashMap<Int, Int>()
    var fireBaseData: HashMap<String, Menu> = java.util.HashMap()
    var data1 : String = ""
    var data2 : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_select)
        var payment = "Card"

        fireBaseData = intent.getSerializableExtra("fireBaseData") as HashMap<String, Menu>
        orderMap = intent.getSerializableExtra("orderMap") as HashMap<Int, Int>

        Log.d("intent", "GeneralMenu -> PaymentSelect: ${data1}, ${data2}")

        val cardButton = findViewById<Button>(R.id.pay_btn_card)
        cardButton.setOnClickListener {
            payment = "Card"
            moveInsert(payment)
        }

        var cashButton = findViewById<Button>(R.id.pay_btn_cash)
        cashButton.setOnClickListener {
            payment = "Cash"
            moveInsert(payment)
        }

    }
    fun moveInsert(payment : String){
        val intent2 = Intent(this, CardInsertActivity::class.java)
        // CardInsertActivity로 데이터 전달
        intent2.putExtra("data1", data1)
        intent2.putExtra("data2", data2)
        intent2.putExtra("fireBaseData", fireBaseData)
        intent2.putExtra("orderMap", orderMap)
        intent2.putExtra("payment", payment)
        startActivity(intent2)
        finish()
    }

}