package kr.aifor.lyr.knu_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FishMenuActivity : AppCompatActivity(), View.OnClickListener {

    var orderMap = HashMap<Int, Int>()
    lateinit var rec_orderList: RecyclerView
    lateinit var orderAdapter: orderAdapter
    lateinit var completeButton: Button
    lateinit var cancleButton: Button
    var tempData: HashMap<String, Menu> = java.util.HashMap()
    lateinit var burgerImageView: ImageView
    var fireBaseData: HashMap<String, Menu> = java.util.HashMap()
    lateinit var requestLaunch: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fish_menu)

        rec_orderList = findViewById(R.id.easy_orderList_fish)
        completeButton = findViewById(R.id.fish_complete_btn)

        orderMap = intent.getSerializableExtra("orderMap") as HashMap<Int, Int>
        fireBaseData = intent.getSerializableExtra("fireBaseData") as HashMap<String, Menu>

        orderAdapter = orderAdapter(orderMap, fireBaseData)
        rec_orderList.layoutManager = LinearLayoutManager(this)
        rec_orderList.adapter = orderAdapter

        completeButton.setOnClickListener {
            intent.putExtra("orderMap", orderMap)
            setResult(RESULT_OK, intent)
            finish()
        }

        cancleButton = findViewById<Button>(R.id.fish_cancle_btn)
        cancleButton.setOnClickListener {
            val keys = orderMap.keys.toIntArray()
            for (k in keys) {
                Log.d("Gen", "k =${k}")
                orderMap.remove(k)
            }
            orderAdapter.notifyDataSetChanged()
        }

        requestLaunch = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                var resultData: Int = it.data!!.getIntExtra("result", 0)
                Toast.makeText(applicationContext, "${resultData}", Toast.LENGTH_SHORT).show()

                if (orderMap.containsKey(resultData))
                    orderMap.put(resultData, orderMap.get(resultData)!! + 1)
                else
                    orderMap.put(resultData, 1)
                orderAdapter.notifyDataSetChanged()
            }
        }

        fun checkSoldout(code : String, imgId : Int){
            if(fireBaseData.get(code)!!.left == 0){
                burgerImageView = findViewById(imgId)
                burgerImageView.setImageResource(R.drawable.soldout_img)
            }
        }
        checkSoldout("106", R.id.burger_106_img)
        checkSoldout("107", R.id.burger_107_img)

        var burger_106_grid = findViewById<GridLayout>(R.id.fish_106_grid)
        burger_106_grid.setOnClickListener(this)

        var burger_107_grid = findViewById<GridLayout>(R.id.fish_107_grid)
        burger_107_grid.setOnClickListener(this)


    }

    override fun onClick(p0: View) {
        var code = 0
        when (p0.id) {
            R.id.fish_106_grid -> code = 106
            R.id.fish_107_grid -> code = 107
        }
        if (fireBaseData.get(code.toString())!!.left == 0) {
            Toast.makeText(applicationContext, "해당 상품은 품절입니다.", Toast.LENGTH_SHORT).show()
        } else if (tempData.get(code.toString())!!.left == 0) {
            Toast.makeText(applicationContext, "최대 주문 가능 수량입니다.", Toast.LENGTH_SHORT).show()
        } else {
            val intent2 = Intent(this, SelectSetActivity::class.java)
            if (orderMap == null)
                Log.d("Gen", "OrderMap is Null")
            intent2.putExtra("fireBaseData", fireBaseData)
            intent2.putExtra("tempData", tempData)
            intent2.putExtra("burgerCode", code)

            requestLaunch.launch(intent2)
        }
    }
}