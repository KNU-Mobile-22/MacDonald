package kr.aifor.lyr.knu_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CardInsertActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_insert)

        val data1 = intent.getStringExtra("data1")
        val data2 = intent.getStringExtra("data2")
        val fireBaseData = intent.getSerializableExtra("fireBaseData") as HashMap<String, Menu>
        val orderMap = intent.getSerializableExtra("orderMap") as HashMap<Int, Int>
        val orderText: TextView = findViewById(R.id.cardInsertOrderList)

        Log.d("intent", "PaymentSelect -> CardInsert: ${data1}, ${data2}")

        //주문내역 출력
        var orderList: String = ""
        val keys = orderMap.keys
        var priceSum = 0
        var num = 0
        var menu: Menu
        var name: String = ""
        var price: Int = 0

        // 세트 계산용 변수
        var burgerCode: Int = 0
        var sideCode: Int = 0
        var drinkCode: Int = 0
        var burgerMenu: Menu
        var sideMenu: Menu
        var drinkMenu: Menu

        for (key in keys) {
            num = orderMap.get(key)!!

            // 단품인 경우
            if (key < 1000) {
                menu = fireBaseData.get(key.toString())!!
                name = menu.name
                price = menu.price * num
            }
            // 세트인 경우
            else {
                burgerCode = key / 1000000
                sideCode = (key % 1000000) / 1000
                drinkCode = key % 1000

                burgerMenu = fireBaseData.get(burgerCode.toString())!!
                sideMenu = fireBaseData.get(sideCode.toString())!!
                drinkMenu = fireBaseData.get(drinkCode.toString())!!

                name = burgerMenu.name + " 세트(" + sideMenu.name + ", " + drinkMenu.name
                price =
                    (((burgerMenu.price + sideMenu.price + drinkMenu.price) * 0.8).toInt()) * num

            }
            priceSum += price
            orderList += "${name} : ${num}개 : ${price}원\n"
        }
        orderList += "                  총액 : ${priceSum}"
        orderText.setText(orderList)
        Log.d("intent", "PaymentSelect -> CardInsert: ${data1}, ${data2}")

        val cardCheckBox = findViewById<CheckBox>(R.id.card_check_test)

        var db_totalOrderNum: Int = 0
        var db_totalSellPrice: Int = 0

        database = Firebase.database.reference
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                db_totalOrderNum = snapshot.child("totalOrderNum").getValue(Int::class.java)!!
                db_totalSellPrice = snapshot.child("totalSellPrice").getValue(Int::class.java)!!
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        cardCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {


                for (key in keys) {
                    num = orderMap.get(key)!!

                    if (key < 1000) {
                        var left = fireBaseData.get(key.toString())!!.left - num
                        database.child("${key}").child("left").setValue(left)
                        db_totalOrderNum += num
                    } else {
                        var burgerLeft = fireBaseData.get(burgerCode.toString())!!.left - num
                        var sideLeft = fireBaseData.get(sideCode.toString())!!.left - num
                        var drinkLeft = fireBaseData.get(drinkCode.toString())!!.left - num
                        database.child("${burgerCode}").child("left").setValue(burgerLeft)
                        database.child("${sideCode}").child("left").setValue(sideLeft)
                        database.child("${drinkCode}").child("left").setValue(drinkLeft)
                        db_totalOrderNum += 3 * num
                    }
                }
                database.child("totalOrderNum").setValue(db_totalOrderNum)
                db_totalSellPrice += priceSum
                database.child("totalSellPrice").setValue(db_totalSellPrice)


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