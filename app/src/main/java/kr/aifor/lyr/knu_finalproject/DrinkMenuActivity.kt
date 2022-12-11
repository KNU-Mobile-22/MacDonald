package kr.aifor.lyr.knu_finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DrinkMenuActivity : AppCompatActivity() {
    lateinit var MenuManager: GridLayoutManager
    lateinit var menuAdapter: MenuAdapter
    lateinit var rec_drink: RecyclerView

    var menuList = MenuList()

    var orderMap = HashMap<Int, Int>()
    lateinit var rec_orderList: RecyclerView
    lateinit var orderAdapter: orderAdapter

    lateinit var completeButton: Button
    lateinit var cancleButton: Button

    var fireBaseData: HashMap<String, Menu> = java.util.HashMap()
    var tempData: HashMap<String, Menu> = java.util.HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_menu)

        rec_drink = findViewById(R.id.easy_drink_recyclerview)
        rec_orderList = findViewById(R.id.easy_orderList_drink)
        completeButton = findViewById(R.id.drink_complete_btn)
        cancleButton = findViewById(R.id.drink_cancle_btn)

        orderMap = intent.getSerializableExtra("orderMap") as HashMap<Int, Int>
        fireBaseData = intent.getSerializableExtra("fireBaseData") as HashMap<String, Menu>
        tempData = intent.getSerializableExtra("tempData") as HashMap<String, Menu>

        MenuManager = GridLayoutManager(this, 3)
        menuAdapter = MenuAdapter(menuList.drinkList, fireBaseData)
        var Menu_List = rec_drink.apply {
            setHasFixedSize(true)
            layoutManager = MenuManager
            adapter = menuAdapter
        }

        menuAdapter.setOnItemClickListener(object : MenuAdapter.OnItemCLickListener {
            override fun onItemClick(v: View, data: Menu, position: Int) =
                ItemClickLister(v, data, position)
        })

/*        val cancelButton = findViewById<Button>(R.id.drink_cancle_btn)
        cancelButton.setOnClickListener {
            val keys = orderMap.keys.toIntArray()
            for (k in keys) {
                Log.d("Gen", "k =${k}")
                orderMap.remove(k)
            }
            orderAdapter.notifyDataSetChanged()
        }*/
        val cancelButton = findViewById<Button>(R.id.drink_cancle_btn)
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

        // orderAdapter = orderAdapter(orderMap, fireBaseData)
        orderAdapter = orderAdapter(orderMap, tempData)
        rec_orderList.layoutManager = LinearLayoutManager(this)
        rec_orderList.adapter = orderAdapter

        completeButton.setOnClickListener {
            intent.putExtra("orderMap", orderMap)
            setResult(RESULT_OK, intent)
            finish()
        }

    }

    fun ItemClickLister(v: View, data: Menu, position: Int) {

        if (fireBaseData.get((data.code).toString())!!.left == 0) {
            Toast.makeText(applicationContext, "해당 상품은 품절입니다.", Toast.LENGTH_SHORT).show()
        } else {
            if (data.code / 100 == 1) {
                /**
                 * 버거 선택시 Intent에 선택한 버거 코드를 넣어 사이드 선택 메뉴로 전달.
                 */
            } else {
                val orderMenu = tempData.get((data.code).toString())!!
                if (orderMap.containsKey(data.code)) {
                    if (orderMenu.left <= 0) {
                        Toast.makeText(v.context, "최대 주문 가능 수량입니다.", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        orderMenu.left--
                        tempData.put(orderMenu.code.toString(), orderMenu)
                        orderMap.put(data.code, orderMap.get(data.code)!! + 1)
                    }
                } else {
                    if (orderMenu.left <= 0) {
                        Toast.makeText(v.context, "최대 주문 가능 수량입니다.", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        orderMenu.left--
                        tempData.put(orderMenu.code.toString(), orderMenu)
                        orderMap.put(data.code, 1)
                    }
                }
                orderAdapter.notifyDataSetChanged()
            }
        }
    }
}