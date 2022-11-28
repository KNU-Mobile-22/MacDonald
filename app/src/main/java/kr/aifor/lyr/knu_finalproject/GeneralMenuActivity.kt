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

public class Menu(var name: String, var price: Int, var code: Int) {
    var left: Int = 50
}

/**
 * MenuList 클래스
 * var
 *  list : ArrayList<Menu>(name : String, price : Int, code : Int)
 */

/*
class MenuList{
    var list = ArrayList<Menu>()
    fun makeBurgerList(){
        this.list.add(Menu("페퍼로니 메가 피자 버거", 10000, 101))
        this.list.add(Menu("페퍼로니 피자버거", 7500, 102))
        this.list.add(Menu("맥크리스피 디럭스 버거", 7500, 103))
        this.list.add(Menu("맥크리스피 클래식 버거", 6700, 104))
        this.list.add(Menu("트리플 치즈버거", 6600, 105))
        this.list.add(Menu("필레 오 피쉬버거", 4500, 106))
        this.list.add(Menu("더블 필레 오 피쉬버거", 6000, 107))
        this.list.add(Menu("슈비버거", 6600, 108))
        this.list.add(Menu("슈슈버거", 5500, 109))
        this.list.add(Menu("1955버거", 6800, 110))
        this.list.add(Menu("맥치킨", 4300, 111))
        this.list.add(Menu("맥치킨 모짜렐라", 5800, 112))
        this.list.add(Menu("빅맥", 5700, 113))
        this.list.add(Menu("맥스파이시 상하이버거", 5700, 114))
        this.list.add(Menu("더블 불고기 버거", 5300, 115))
        this.list.add(Menu("에그 불고기 버거", 4300, 116))
        this.list.add(Menu("불고기 버거", 3300, 117))
        this.list.add(Menu("베이컨 토마토 디럭스", 6600, 118))
        this.list.add(Menu("더블 쿼터파운더 치즈", 8200, 119))
        this.list.add(Menu("쿼터파운더 치즈", 6300, 120))
        this.list.add(Menu("치즈버거", 3300, 121))
        this.list.add(Menu("더블 치즈버거", 5300, 122))
        this.list.add(Menu("햄버거", 3000, 123))
    }
    fun makeSideList() {
        this.list.add(Menu("후렌치 후라이 스몰", 1900, 201))
        this.list.add(Menu("후렌치 후라이 미디엄", 2600, 202))
        this.list.add(Menu("후렌치 후라이 라지", 3200, 203))
        this.list.add(Menu("치킨 토마토 스낵랩", 3000, 204))
        this.list.add(Menu("코우슬로", 2700, 205))
        this.list.add(Menu("골든 모짜렐라 치즈스틱 2조각", 3300, 206))
        this.list.add(Menu("골든 모짜렐라 치즈스틱 4조각", 5000, 207))
        this.list.add(Menu("맥너겟 4조각", 3000, 208))
        this.list.add(Menu("상하이 치킨 스낵랩", 3200, 209))
    }
    fun makeDrinkList() {
        this.list.add(Menu("딸기 칠러", 3700, 301))
        this.list.add(Menu("제주 한라봉 칠러", 3700, 302))
        this.list.add(Menu("코카 콜라", 2200, 303))
        this.list.add(Menu("코카 콜라 제로", 2200, 304))
        this.list.add(Menu("환타", 2200, 305))
        this.list.add(Menu("스프라이트", 2200, 306))
        this.list.add(Menu("스프라이트 제로", 2200, 307))
        this.list.add(Menu("딸기 쉐이크", 3500, 308))
        this.list.add(Menu("초코 쉐이크", 3500, 309))
    }
    fun makeCoffeeList() {
        this.list.add(Menu("바닐라 라떼", 4700, 401))
        this.list.add(Menu("아이스 바닐라 라떼", 4200, 402))
        this.list.add(Menu("카페라떼", 3700, 403))
        this.list.add(Menu("카푸치노", 3700, 404))
        this.list.add(Menu("아메리카노", 3200, 405))
        this.list.add(Menu("드립 커피", 2700, 406))
        this.list.add(Menu("아이스 드립 커피", 2200, 407))
        this.list.add(Menu("아이스 아메리카노", 3200, 408))
        this.list.add(Menu("아이스 카페라떼", 3700, 409))
        this.list.add(Menu("에스프레소", 2400, 410))
    }
    fun makeDesertList() {
        this.list.add(Menu("디핑 소스 스위트 앤 사워", 300, 501))
        this.list.add(Menu("디핑 소스 스위트 칠리", 300, 502))
        this.list.add(Menu("디핑 소스 케이준", 300, 503))
        this.list.add(Menu("스트링 치즈", 2400, 504))
        this.list.add(Menu("츄러스", 2500, 505))
        this.list.add(Menu("애플파이", 2000, 506))
        this.list.add(Menu("오레오 맥플러리", 3400, 507))
        this.list.add(Menu("딸기 선데이 아이스크림", 2500, 508))
        this.list.add(Menu("초코 선데이 아이스크림", 2500, 509))
        this.list.add(Menu("바닐라 선데이 아이스크림", 250, 510))

    }
}

 */

class MainActivity : AppCompatActivity(), View.OnClickListener {

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