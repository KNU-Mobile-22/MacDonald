package kr.aifor.lyr.knu_finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.view.menu.MenuAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Menu(val name : String, val price : String,val image: String)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(R.layout.gen_menu_layout)

        val rec_Burger : RecyclerView = findViewById(R.id.recyclerview)

        //데이터 준비
        val data = ArrayList<Menu>()
        val Burger_name = arrayOf<String>("페퍼로니 피자 버거", "페퍼로니 메가 피자 버거", "맥크리스피 디럭스 버거", "맥크리스피 클래식 버거", "빅맥", "맥스파이시 상하이 버거", "1955버거", "더블 쿼터파운더 치즈", "쿼터파운더 치즈", "맥치킨 모짜렐라" ,"맥치킨", "더블 불고기 버거", "에그 불고기 버거", "불고기 버거", "슈슈버거", "슈비 버거", "베이컨 토마토 디럭스", "더블 필레오피쉬", "필레 오 피쉬", "치즈버거", "더블 치즈버거", "햄버거")
        val Burger_price = arrayOf<String>("1" ,"2", "3","4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22")
        val Burger_img = arrayOf<String>("1" ,"2", "3","4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22")
        for (i in 0..Burger_name.size-1){
            val Burger:Menu = Menu(Burger_name[i], Burger_price[i], Burger_img[i])
            data.add(Burger)
        }

        //adapter 연결
        val MenuManager = GridLayoutManager(this, 3)
        val menuAdapter = MenuAdapter(data)
        var Menu_List = rec_Burger.apply {
            setHasFixedSize(true)
            layoutManager = MenuManager
            adapter = menuAdapter
        }


    }
}