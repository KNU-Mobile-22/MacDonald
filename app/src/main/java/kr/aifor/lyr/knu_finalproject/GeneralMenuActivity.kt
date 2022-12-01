package kr.aifor.lyr.knu_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.HashMap


class GeneralMenuActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var rec_Burger: RecyclerView
    lateinit var MenuManager: GridLayoutManager

    // lateinit var orderList: TextView
    lateinit var orderList: RecyclerView
    var orderMap = HashMap<String, Int>()
    var menuList = MenuList()
    var CurrentMenu: Int = 0
    lateinit var menuAdapter: MenuAdapter

    lateinit var requestLanch: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {

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

        //adapter 연결
        MenuManager = GridLayoutManager(this, 3)
        menuAdapter = MenuAdapter(menuList.burgerList)
        var Menu_List = rec_Burger.apply {
            setHasFixedSize(true)
            layoutManager = MenuManager
            adapter = menuAdapter
        }
        menuAdapter.setOnItemClickListener(object :
            kr.aifor.lyr.knu_finalproject.MenuAdapter.OnItemCLickListener {
            override fun onItemClick(v: View, data: Menu, position: Int) =
                ItemClickLister(v, data, position)
        })

        val backButton = findViewById<ImageButton>(R.id.gen_btn_back)
        backButton.setOnClickListener {
            intent.putExtra("result", "test")
            setResult(RESULT_OK, intent)
            finish()
        }

        val paymentButton = findViewById<Button>(R.id.gen_btn_payment)
        paymentButton.setOnClickListener {
            val intent2 = Intent(this, PaymentSelectActivity::class.java)
            intent2.putExtra("data1", "test1")
            intent2.putExtra("data2", "test2")
            /* 양방향으로 인텐트하려니까 팅김;; */
            // requestLanch.launch(intent2)

            startActivity(intent2)
            finish()
        }
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
        var Menu_List = rec_Burger.apply {
            setHasFixedSize(true)
            layoutManager = MenuManager
            adapter = menuAdapter
        }
        menuAdapter.setOnItemClickListener(object :
            kr.aifor.lyr.knu_finalproject.MenuAdapter.OnItemCLickListener {
            override fun onItemClick(v: View, data: Menu, position: Int) =
                ItemClickLister(v, data, position)
        })
    }

    /**
     * 각 메뉴 아이템 클릭리스너
     */
    fun ItemClickLister(v: View, data: Menu, position: Int) {
        if (data.code / 100 == 1) {
            /**
             * 버거 선택시 Intent에 선택한 버거 코드를 넣어 사이드 선택 메뉴로 전달.
             */
        } else {
            var order: String = ""
            if (orderMap.containsKey(data.name))
                orderMap.put(data.name, orderMap.get(data.name)!! + 1)
            else
                orderMap.put(data.name, 1)
            for (key in orderMap.keys) {
                order = order + "${key} + ${(data.price * orderMap.get(key)!!).toString()}\n"
            }
            // orderList.setText(order)
            Log.d("Gen", order)
        }
        Toast.makeText(
            v.context,
            "${data.name}\n${data.price}",
            Toast.LENGTH_SHORT
        ).show()
        Log.d("Gen", "${data.name}, ${data.price}")
    }
}
/**
 * 나중에 Menu의 버거리스트 만드는거 다른데로 옮기기기
 */