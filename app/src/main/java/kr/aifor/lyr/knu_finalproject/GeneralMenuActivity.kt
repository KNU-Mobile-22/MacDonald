package kr.aifor.lyr.knu_finalproject

import MenuList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.view.menu.MenuAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GeneralMenuActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var rec_Burger: RecyclerView
    lateinit var MenuManager: GridLayoutManager
    var burgerList = MenuList()
    var sideList = MenuList()
    var drinkList = MenuList()
    var coffeeList = MenuList()
    var desertList = MenuList()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.gen_menu_layout)

        //레이아웃의 View 가져오기
        rec_Burger = findViewById(R.id.recyclerview)
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

        //데이터 초기화
        burgerList.makeBurgerList()
        sideList.makeSideList()
        drinkList.makeDrinkList()
        coffeeList.makeCoffeeList()
        desertList.makeDesertList()

        //adapter 연결
        MenuManager = GridLayoutManager(this, 3)
        val menuAdapter = MenuAdapter(burgerList)
        var Menu_List = rec_Burger.apply {
            setHasFixedSize(true)
            layoutManager = MenuManager
            adapter = menuAdapter
        }

    }

    override fun onClick(p0: View) {
        var data: MenuList = burgerList
        when (p0.id) {
            R.id.gen_btn_burger -> data = burgerList
            R.id.gen_btn_desert -> data = desertList
            R.id.gen_btn_coffee -> data = coffeeList
            R.id.gen_btn_drink -> data = drinkList
            R.id.gen_btn_side -> data = sideList
        }
        val menuAdapter = MenuAdapter(data)
        var Menu_List = rec_Burger.apply {
            setHasFixedSize(true)
            layoutManager = MenuManager
            adapter = menuAdapter
        }


    }
}
/**
 * 나중에 Menu의 버거리스트 만드는거 다른데로 옮기기기
 */