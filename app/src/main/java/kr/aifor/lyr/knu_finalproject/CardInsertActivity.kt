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
import org.w3c.dom.Text
import kotlin.math.floor

class CardInsertActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_insert)

        val payment = intent.getStringExtra("payment")
        val fireBaseData = intent.getSerializableExtra("fireBaseData") as HashMap<String, Menu>
        val orderMap = intent.getSerializableExtra("orderMap") as HashMap<Int, Int>
        val orderName: TextView = findViewById(R.id.cardInsertOLName)
        val orderNum: TextView = findViewById(R.id.cardInsertOLNum)
        val orderPrice: TextView = findViewById(R.id.cardInsertOLPrice)
        val insertPayment: TextView = findViewById(R.id.InsertPayment)
        var updateSet = HashSet<Int>()

        //주문내역 출력
        var orderPrintName: String = ""
        var orderPrintNum = ""
        var orderPrintPrice = ""
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
                /*
                price =
                    (((burgerMenu.price + sideMenu.price + drinkMenu.price) * 0.8).toInt()) * num
                    */
                var dbPrice = (burgerMenu.price + sideMenu.price + drinkMenu.price) * 0.8 * num
                price = floor(dbPrice / 100).toInt() * 100
            }
            priceSum += price
            if (name.length > 18) {
                name = name.substring(0 until 18) + "\n" + name.subSequence(18 until name.length)
                orderPrintNum += "\n"
                orderPrintPrice += "\n"
            }
            orderPrintName += "${name}\n"
            orderPrintNum += "${num}개\n"
            orderPrintPrice += "${price}원\n"
        }
        orderPrintName += "총액"
        orderPrintPrice += "${priceSum}원"
        orderName.setText(orderPrintName)
        orderNum.setText(orderPrintNum)
        orderPrice.setText(orderPrintPrice)
        if (payment == "Card")
            insertPayment.setText("카드를 넣어주세요.")
        else
            insertPayment.setText("현금을 넣어주세요.")

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
                    if (key < 1000) {
                        updateSet.add(key)
                    } else {
                        updateSet.add(key / 1000000)
                        updateSet.add((key % 1000000) / 1000)
                        updateSet.add(key % 1000)
                    }
                    Log.d("Gen", "key : ${key}")
                }
                val update = updateSet.iterator()
                while (update.hasNext()) {
                    var key = update.next()
                    val left = fireBaseData.get(key.toString())!!.left
                    Log.d("Gen", "${key.toString()} left : ${left}")
                    database.child("${key.toString()}").child("left").setValue(left)
                }

                database.child("totalOrderNum").setValue(++db_totalOrderNum)
                db_totalSellPrice += priceSum
                database.child("totalSellPrice").setValue(db_totalSellPrice)


                val intent2 = Intent(this, CompletePaymentActivity::class.java)
                // CompletePaymentActivity로 데이터 전달
                intent2.putExtra("totalOrderNum", db_totalOrderNum)
                startActivity(intent2)
                finish()
            }
        }
    }
}