package kr.aifor.lyr.knu_finalproject

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EasyMenuActivity : AppCompatActivity() {
    lateinit var requestLaunch: ActivityResultLauncher<Intent>
    var fireBaseData: HashMap<String, Menu> = java.util.HashMap()
    var tempData: HashMap<String, Menu> = java.util.HashMap()
    var orderMap = HashMap<Int, Int>()
    lateinit var orderAdapter: orderAdapter
    lateinit var orderList: RecyclerView

    lateinit var helpView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_menu)

        var paymentButton = findViewById<Button>(R.id.easy_payment_btn)
        orderList = findViewById(R.id.easy_main_orderList)
        helpView = findViewById(R.id.help_msg_txt)

        fireBaseData = intent.getSerializableExtra("fireBaseData") as HashMap<String, Menu>
        tempData = intent.getSerializableExtra("tempData") as HashMap<String, Menu>
        println(fireBaseData.keys.size)

        requestLaunch = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                val result = it.data?.getSerializableExtra("orderMap") as HashMap<Int, Int>
                tempData = it.data?.getSerializableExtra("tempData") as HashMap<String, Menu>
                // Toast.makeText(applicationContext, "orderMap 받아오기 성공", Toast.LENGTH_SHORT).show()
                orderMap = result
                Log.d("myLog", "orderMap: ${orderMap}")
                // orderAdapter = orderAdapter(orderMap, fireBaseData)
                orderAdapter = orderAdapter(orderMap, tempData)
                orderList.layoutManager = LinearLayoutManager(this)
                orderList.adapter = orderAdapter
            }
            if (orderMap.isEmpty()) {
                //nothing
            } else {
                paymentButton.setBackgroundColor(Color.parseColor("#FFEB3B"))
                paymentButton.setTextColor(Color.parseColor("#000000"))
                helpView.setText(" 6. 결제하기 버튼을 누르세요. ")

            }
        }


        var beafButton = findViewById<Button>(R.id.easy_beaf_btn)
        beafButton.setOnClickListener {
            val intent2 = Intent(this, BeafMenuActivity::class.java)
            intent2.putExtra("fireBaseData", fireBaseData)
            intent2.putExtra("tempData", tempData)
            intent2.putExtra("orderMap", orderMap)
            requestLaunch.launch(intent2)
            // startActivity(intent2)
        }

        var chickenButton = findViewById<Button>(R.id.easy_chicken_btn)
        chickenButton.setOnClickListener {
            val intent2 = Intent(this, ChickenMenuActivity::class.java)
            intent2.putExtra("fireBaseData", fireBaseData)
            intent2.putExtra("tempData", tempData)
            intent2.putExtra("orderMap", orderMap)
            requestLaunch.launch(intent2)
            // startActivity(intent2)
        }

        var cheeseButton = findViewById<Button>(R.id.easy_cheese_btn)
        cheeseButton.setOnClickListener {
            val intent2 = Intent(this, CheeseMenuActivity::class.java)
            intent2.putExtra("fireBaseData", fireBaseData)
            intent2.putExtra("tempData", tempData)
            intent2.putExtra("orderMap", orderMap)
            requestLaunch.launch(intent2)
            // startActivity(intent2)
        }

        var bulgogiButton = findViewById<Button>(R.id.easy_bulgogi_btn)
        bulgogiButton.setOnClickListener {
            val intent2 = Intent(this, BulgogiMenuActivity::class.java)
            intent2.putExtra("fireBaseData", fireBaseData)
            intent2.putExtra("tempData", tempData)
            intent2.putExtra("orderMap", orderMap)
            requestLaunch.launch(intent2)
            // startActivity(intent2)
        }

        var shrimpButton = findViewById<Button>(R.id.easy_shrimp_btn)
        shrimpButton.setOnClickListener {
            val intent2 = Intent(this, ShrimpMenuActivity::class.java)
            intent2.putExtra("fireBaseData", fireBaseData)
            intent2.putExtra("tempData", tempData)
            intent2.putExtra("orderMap", orderMap)
            requestLaunch.launch(intent2)
            // startActivity(intent2)
        }

        var fishButton = findViewById<Button>(R.id.easy_fish_btn)
        fishButton.setOnClickListener {
            val intent2 = Intent(this, FishMenuActivity::class.java)
            intent2.putExtra("fireBaseData", fireBaseData)
            intent2.putExtra("tempData", tempData)
            intent2.putExtra("orderMap", orderMap)
            requestLaunch.launch(intent2)
            // startActivity(intent2)
        }

        var sideButton = findViewById<Button>(R.id.easy_side_btn)
        sideButton.setOnClickListener {
            val intent2 = Intent(this, SideMenuActivity::class.java)
            intent2.putExtra("fireBaseData", fireBaseData)
            intent2.putExtra("tempData", tempData)
            intent2.putExtra("orderMap", orderMap)
            requestLaunch.launch(intent2)
            // startActivity(intent2)
        }

        var drinkButton = findViewById<Button>(R.id.easy_drink_btn)
        drinkButton.setOnClickListener {
            val intent2 = Intent(this, DrinkMenuActivity::class.java)
            intent2.putExtra("fireBaseData", fireBaseData)
            intent2.putExtra("tempData", tempData)
            intent2.putExtra("orderMap", orderMap)
            requestLaunch.launch(intent2)
            // startActivity(intent2)
        }

        var dessertButton = findViewById<Button>(R.id.easy_dessert_btn)
        dessertButton.setOnClickListener {
            val intent2 = Intent(this, DessertMenuActivity::class.java)
            intent2.putExtra("fireBaseData", fireBaseData)
            intent2.putExtra("tempData", tempData)
            intent2.putExtra("orderMap", orderMap)
            requestLaunch.launch(intent2)
            // startActivity(intent2)
        }

        paymentButton.setOnClickListener {
            if (orderMap.isEmpty()) {
                Toast.makeText(applicationContext, "주문할 메뉴를 선택해주세요", Toast.LENGTH_SHORT).show()
                paymentButton.setBackgroundColor(Color.parseColor("#DA291C"))
                paymentButton.setTextColor(Color.parseColor("#FFFFFFFF"))
                helpView.setText(" 2. 카테고리를 선택해주세요. ")

            } else {
                val intent2 = Intent(this, PaymentSelectActivity::class.java)
                if (orderMap == null)
                    Log.d("Gen", "OrderMap is Null")
                // intent2.putExtra("fireBaseData", fireBaseData)
                intent2.putExtra("fireBaseData", tempData)
                intent2.putExtra("orderMap", orderMap)
                intent2.putExtra("data2", "test2")

                startActivity(intent2)
                finish()
            }
        }

/*        val cancelButton = findViewById<Button>(R.id.easy_cancle_btn)
        cancelButton.setOnClickListener {
            val keys = orderMap.keys.toIntArray()
            for (k in keys) {
                Log.d("Gen", "k =${k}")
                orderMap.remove(k)
            }
            orderAdapter.notifyDataSetChanged()
        }*/

        val cancelButton = findViewById<Button>(R.id.easy_cancle_btn)
        cancelButton.setOnClickListener {
            val keys = orderMap.keys.toIntArray()
            for (code in keys) {
                var num = orderMap.get(code)!!
                if (code < 1000) {
                    val menu = tempData.get(code.toString())!!
                    menu.left += num
                    tempData.put(menu.code.toString(), menu)
                } else {
                    var burgerCode = code / 1000000
                    var sideCode = (code % 1000000) / 1000
                    var drinkCode = code % 1000

                    var burger = tempData.get(burgerCode.toString())!!
                    var side = tempData.get(sideCode.toString())!!
                    var drink = tempData.get(drinkCode.toString())!!

                    burger.left += num
                    side.left += num
                    drink.left += num

                    tempData.put(burger.code.toString(), burger)
                    tempData.put(side.code.toString(), side)
                    tempData.put(drink.code.toString(), drink)
                }
                orderMap.remove(code)
            }
            orderAdapter.notifyDataSetChanged()
        }

        orderAdapter = orderAdapter(orderMap, tempData)
        orderList.layoutManager = LinearLayoutManager(this)
        orderList.adapter = orderAdapter

    }
}