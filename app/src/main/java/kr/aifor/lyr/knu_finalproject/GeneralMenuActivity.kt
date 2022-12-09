package kr.aifor.lyr.knu_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
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
        Log.d("myLog", fireBaseData.javaClass.name)

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
            val intent2 = Intent(this, PaymentSelectActivity::class.java)
            if (orderMap == null)
                Log.d("Gen", "OrderMap is Null")
            intent2.putExtra("fireBaseData", fireBaseData)
            intent2.putExtra("orderMap", orderMap)
            intent2.putExtra("data2", "test2")

            startActivity(intent2)
            finish()
        }

        //취소하기 - 주문내역 사라짐
        val cancelButton = findViewById<Button>(R.id.gen_btn_cancel)
        cancelButton.setOnClickListener {
            val keys = orderMap.keys.toIntArray()
            for (k in keys) {
                Log.d("Gen", "k =${k}")
                orderMap.remove(k)
            }
            orderAdapter.notifyDataSetChanged()
        }

        //menuAdapter 연결
        MenuManager = GridLayoutManager(this, 3)
        menuAdapter = MenuAdapter(menuList.burgerList)
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
        orderAdapter = orderAdapter(orderMap, fireBaseData)
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
        menuAdapter = MenuAdapter(data)
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

/**
 * database에서 data 가져오는거
 */
/**
 * 나중에 Menu의 버거리스트 만드는거 다른데로 옮기기기
 */