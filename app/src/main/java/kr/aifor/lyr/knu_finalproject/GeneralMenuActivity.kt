package kr.aifor.lyr.knu_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
// import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
//import kr.aifor.lyr.knu_finalproject.databinding.ActivitySelectSetBinding
import kotlin.collections.HashMap

/**
 * TaskList
 * 1. 버거 주문시 세부메뉴로 Intent
 */


class GeneralMenuActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var rec_Burger: RecyclerView
    lateinit var MenuManager: GridLayoutManager
    lateinit var orderList: RecyclerView
    var orderMap = HashMap<Int, Int>()
    var menuList = MenuList()
    var CurrentMenu: Int = 0
    lateinit var menuAdapter: MenuAdapter
    lateinit var orderAdapter: orderAdapter
    var fireBaseData: HashMap<String, Menu> = java.util.HashMap()
    var tempData: HashMap<String, Menu> = java.util.HashMap()

    lateinit var requestLaunch: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("Gen", "OnCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gen_menu_layout)

        //레이아웃의 View 가져오기
        rec_Burger = findViewById(R.id.recyclerview)
        orderList = findViewById(R.id.orderList)

        val btn_burger: Button = findViewById(R.id.gen_btn_burger)
        btn_burger.setOnClickListener(this)
        val btn_side: Button = findViewById(R.id.gen_btn_side)
        btn_side.setOnClickListener(this)
        val btn_drink: Button = findViewById(R.id.gen_btn_drink)
        btn_drink.setOnClickListener(this)
        val btn_coffee: Button = findViewById(R.id.gen_btn_coffee)
        btn_coffee.setOnClickListener(this)
        val btn_desert: Button = findViewById(R.id.gen_btn_desert)
        btn_desert.setOnClickListener(this)

        fireBaseData = intent.getSerializableExtra("fireBaseData") as HashMap<String, Menu>
        // orderMap에서 사용하기 위한 fireBaseData의 복제본
        tempData = intent.getSerializableExtra("tempData") as HashMap<String, Menu>
        Log.d("Gen", "Gen = ${fireBaseData}")
        Log.d("Gen", "Gen = ${fireBaseData.get("101")!!.name}")

        requestLaunch = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                var resultData: Int = it.data!!.getIntExtra("result", 0)
                // Toast.makeText(applicationContext, "${resultData}", Toast.LENGTH_SHORT).show()

                if (orderMap.containsKey(resultData))
                    orderMap.put(resultData, orderMap.get(resultData)!! + 1)
                else
                    orderMap.put(resultData, 1)

                if (resultData < 1000) {
                    val orderMenu = tempData.get(resultData.toString())!!
                    orderMenu.left--
                    tempData.put(orderMenu.code.toString(), orderMenu)
                } else {
                    var burgerCode = resultData / 1000000
                    var sideCode = (resultData % 1000000) / 1000
                    var drinkCode = resultData % 1000

                    var burger = tempData.get(burgerCode.toString())!!
                    var side = tempData.get(sideCode.toString())!!
                    var drink = tempData.get(drinkCode.toString())!!

                    burger.left--
                    side.left--
                    drink.left--

                    tempData.put(burger.code.toString(), burger)
                    tempData.put(side.code.toString(), side)
                    tempData.put(drink.code.toString(), drink)
                }
                orderAdapter.notifyDataSetChanged()
            }
        }

        //뒤로가기
        val backButton = findViewById<ImageButton>(R.id.gen_btn_back)
        backButton.setOnClickListener {
            intent.putExtra("result", "test")
            setResult(RESULT_OK, intent)
            finish()
        }

        //결제하기
        val paymentButton = findViewById<Button>(R.id.gen_btn_payment)
        paymentButton.setOnClickListener {
            if (orderMap.isEmpty()) {
                Toast.makeText(applicationContext, "주문할 메뉴를 선택해주세요", Toast.LENGTH_SHORT).show()
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

//취소하기 - 주문내역 사라짐
        val cancelButton = findViewById<Button>(R.id.gen_btn_cancel)
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

        //menuAdapter 연결
        MenuManager = GridLayoutManager(this, 3)
        // menuAdapter = MenuAdapter(menuList.burgerList, fireBaseData)
        menuAdapter = MenuAdapter(menuList.burgerList, fireBaseData)
        var Menu_List = rec_Burger.apply {
            setHasFixedSize(true)
            layoutManager = MenuManager
            adapter = menuAdapter
        }
        menuAdapter.setOnItemClickListener(object : MenuAdapter.OnItemCLickListener {
            override fun onItemClick(v: View, data: Menu, position: Int) =
                ItemClickLister(v, data, position)
        })
        //orderAdapter 연결
        // orderAdapter = orderAdapter(orderMap, fireBaseData)
        orderAdapter = orderAdapter(orderMap, tempData)
        orderList.layoutManager = LinearLayoutManager(this)
        orderList.adapter = orderAdapter


    }

    /**
     * 카테고리 클릭리스너
     *
     * var CurrentMenu :
     *  0 - 버거
     *  1 - 디저트
     *  2 - 커피
     *  3 - 음료
     *  4 - 사이드
     *
     *  RecyclerView가 새로 만들어졌기 때문에 ItemClickListener도 새로 넣어줘야 함
     */
    override fun onClick(p0: View) {
        var data: Array<Menu> = menuList.burgerList
        when (p0.id) {
            R.id.gen_btn_burger -> {
                data = menuList.burgerList
                CurrentMenu = 0
            }
            R.id.gen_btn_desert -> {
                data = menuList.desertList
                CurrentMenu = 1
            }
            R.id.gen_btn_coffee -> {
                data = menuList.coffeeList
                CurrentMenu = 2
            }
            R.id.gen_btn_drink -> {
                data = menuList.drinkList
                CurrentMenu = 3
            }
            R.id.gen_btn_side -> {
                data = menuList.sideList
                CurrentMenu = 4
            }
        }
        // menuAdapter = MenuAdapter(data, fireBaseData)
        menuAdapter = MenuAdapter(data, fireBaseData)
        rec_Burger.apply {
            setHasFixedSize(true)
            layoutManager = MenuManager
            adapter = menuAdapter
        }
        menuAdapter.setOnItemClickListener(object : MenuAdapter.OnItemCLickListener {
            override fun onItemClick(v: View, data: Menu, position: Int) =
                ItemClickLister(v, data, position)
        })
    }

    // 각 메뉴 아이템 클릭리스너
    fun ItemClickLister(v: View, data: Menu, position: Int) {

        if (fireBaseData.get((data.code).toString())!!.left == 0) {
            Toast.makeText(applicationContext, "해당 상품은 품절입니다.", Toast.LENGTH_SHORT).show()
        } else {
            if (data.code / 100 == 1) {
                /**
                 * 버거 선택시 Intent에 선택한 버거 코드를 넣어 사이드 선택 메뉴로 전달.
                 */
                if (tempData.get("${data.code}")!!.left <= 0) {
                    Toast.makeText(v.context, "최대 주문 가능 수량입니다.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val intent2 = Intent(this, SelectSetActivity::class.java)
                    if (orderMap == null)
                        Log.d("Gen", "OrderMap is Null")
                    // intent2.putExtra("fireBaseData", fireBaseData)
                    intent2.putExtra("fireBaseData", fireBaseData)
                    intent2.putExtra("tempData", tempData)
                    intent2.putExtra("burgerCode", data.code)
                    intent2.putExtra("intentFrom", "General")
                    // intent2.putExtra("orderMap", orderMap)
                    // intent2.putExtra("data2", "test2")
                    Log.d("burgerCode", "${data.code}")

                    // startActivity(intent2)
                    requestLaunch.launch(intent2)
                }
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

/**
 * database에서 data 가져오는거
 */
/**
 * 나중에 Menu의 버거리스트 만드는거 다른데로 옮기기기
 */