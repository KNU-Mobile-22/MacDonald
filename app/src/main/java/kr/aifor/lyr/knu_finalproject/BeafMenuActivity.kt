package kr.aifor.lyr.knu_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class BeafMenuActivity : AppCompatActivity(), View.OnClickListener {

    var orderMap = HashMap<Int, Int>()
    lateinit var rec_orderList: RecyclerView
    lateinit var orderAdapter: orderAdapter
    lateinit var completeButton: Button
    lateinit var cancleButton: Button
    lateinit var burgerImageView: ImageView
    var fireBaseData: HashMap<String, Menu> = java.util.HashMap()
    var tempData: HashMap<String, Menu> = java.util.HashMap()
    lateinit var requestLaunch: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beaf_menu)

        rec_orderList = findViewById(R.id.easy_orderList_beaf)
        completeButton = findViewById(R.id.beaf_complete_btn)

        orderMap = intent.getSerializableExtra("orderMap") as HashMap<Int, Int>
        fireBaseData = intent.getSerializableExtra("fireBaseData") as HashMap<String, Menu>
        tempData = intent.getSerializableExtra("tempData") as HashMap<String, Menu>

        // orderAdapter = orderAdapter(orderMap, fireBaseData)
        orderAdapter = orderAdapter(orderMap, tempData)
        rec_orderList.layoutManager = LinearLayoutManager(this)
        rec_orderList.adapter = orderAdapter

        completeButton.setOnClickListener {
            intent.putExtra("orderMap", orderMap)
            intent.putExtra("tempData", tempData)
            setResult(RESULT_OK, intent)
            finish()
        }

/*        cancleButton = findViewById<Button>(R.id.beaf_cancle_btn)
        cancleButton.setOnClickListener {
            val keys = orderMap.keys.toIntArray()
            for (k in keys) {
                Log.d("Gen", "k =${k}")
                orderMap.remove(k)
            }
            orderAdapter.notifyDataSetChanged()
        }*/

        cancleButton = findViewById<Button>(R.id.beaf_cancle_btn)
        cancleButton.setOnClickListener {
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

        /*
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
        */

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

        fun checkSoldout(code: String, imgId: Int) {
            if (fireBaseData.get(code)!!.left == 0) {
                burgerImageView = findViewById(imgId)
                burgerImageView.setImageResource(R.drawable.soldout_img)
            }
        }
        checkSoldout("101", R.id.burger_101_img)
        checkSoldout("102", R.id.burger_102_img)
        checkSoldout("110", R.id.burger_110_img)
        checkSoldout("113", R.id.burger_113_img)
        checkSoldout("118", R.id.burger_118_img)
        checkSoldout("123", R.id.burger_123_img)

/*        if (fireBaseData.get("101")!!.left == 0) {
            burgerImageView = findViewById(R.id.burger_101_img)
            burgerImageView.setImageResource(R.drawable.soldout_img)
        }
        if (fireBaseData.get("102")!!.left == 0) {
            burgerImageView = findViewById(R.id.burger_102_img)
            burgerImageView.setImageResource(R.drawable.soldout_img)
        }
        if (fireBaseData.get("110")!!.left == 0) {
            burgerImageView = findViewById(R.id.burger_110_img)
            burgerImageView.setImageResource(R.drawable.soldout_img)
        }
        if (fireBaseData.get("113")!!.left == 0) {
            burgerImageView = findViewById(R.id.burger_113_img)
            burgerImageView.setImageResource(R.drawable.soldout_img)
        }
        if (fireBaseData.get("118")!!.left == 0) {
            burgerImageView = findViewById(R.id.burger_118_img)
            burgerImageView.setImageResource(R.drawable.soldout_img)
        }
        if (fireBaseData.get("123")!!.left == 0) {
            burgerImageView = findViewById(R.id.burger_123_img)
            burgerImageView.setImageResource(R.drawable.soldout_img)
        }*/

        var burger_101_grid = findViewById<GridLayout>(R.id.burger_101_grid)
        burger_101_grid.setOnClickListener(this)

        var burger_102_grid = findViewById<GridLayout>(R.id.burger_102_grid)
        burger_102_grid.setOnClickListener(this)

        var burger_110_grid = findViewById<GridLayout>(R.id.burger_110_grid)
        burger_110_grid.setOnClickListener(this)

        var burger_113_grid = findViewById<GridLayout>(R.id.burger_113_grid)
        burger_113_grid.setOnClickListener(this)

        var burger_118_grid = findViewById<GridLayout>(R.id.burger_118_grid)
        burger_118_grid.setOnClickListener(this)

        var burger_123_grid = findViewById<GridLayout>(R.id.burger_123_grid)
        burger_123_grid.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        var code = 0
        when (p0.id) {
            R.id.burger_101_grid -> code = 101
            R.id.burger_102_grid -> code = 102
            R.id.burger_110_grid -> code = 110
            R.id.burger_113_grid -> code = 113
            R.id.burger_118_grid -> code = 118
            R.id.burger_123_grid -> code = 123

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