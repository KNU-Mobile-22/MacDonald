package kr.aifor.lyr.knu_finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DessertMenuActivity : AppCompatActivity() {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dessert_menu)

        rec_drink = findViewById(R.id.easy_dessert_recyclerview)
        rec_orderList = findViewById(R.id.easy_orderList_dessert)
        completeButton = findViewById(R.id.dessert_complete_btn)
        cancleButton = findViewById(R.id.dessert_cancle_btn)

        orderMap = intent.getSerializableExtra("orderMap") as HashMap<Int, Int>
        fireBaseData = intent.getSerializableExtra("fireBaseData") as HashMap<String, Menu>

        MenuManager = GridLayoutManager(this, 3)
        menuAdapter = MenuAdapter(menuList.desertList, fireBaseData)
        var Menu_List = rec_drink.apply {
            setHasFixedSize(true)
            layoutManager = MenuManager
            adapter = menuAdapter
        }

        menuAdapter.setOnItemClickListener(object : MenuAdapter.OnItemCLickListener {
            override fun onItemClick(v: View, data: Menu, position: Int) =
                ItemClickLister(v, data, position)
        })

        val cancelButton = findViewById<Button>(R.id.dessert_cancle_btn)
        cancelButton.setOnClickListener {
            val keys = orderMap.keys.toIntArray()
            for (k in keys) {
                Log.d("Gen", "k =${k}")
                orderMap.remove(k)
            }
            orderAdapter.notifyDataSetChanged()
        }

        orderAdapter = orderAdapter(orderMap, fireBaseData)
        rec_orderList.layoutManager = LinearLayoutManager(this)
        rec_orderList.adapter = orderAdapter

        completeButton.setOnClickListener {
            intent.putExtra("orderMap", orderMap)
            setResult(RESULT_OK, intent)
            finish()
        }

    }

    fun ItemClickLister(v: View, data: Menu, position: Int) {
        if (data.code / 100 == 1) {
            /**
             * 버거 선택시 Intent에 선택한 버거 코드를 넣어 사이드 선택 메뉴로 전달.
             */
        } else {
            if (orderMap.containsKey(data.code))
                orderMap.put(data.code, orderMap.get(data.code)!! + 1)
            else
                orderMap.put(data.code, 1)
            orderAdapter.notifyDataSetChanged()
        }

    }
}