package kr.aifor.lyr.knu_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CheeseMenuActivity : AppCompatActivity(), View.OnClickListener {

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
        setContentView(R.layout.activity_cheese_menu)

        rec_orderList = findViewById(R.id.easy_orderList_cheese)
        completeButton = findViewById(R.id.cheese_complete_btn)

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

/*        cancleButton = findViewById<Button>(R.id.cheese_cancle_btn)
        cancleButton.setOnClickListener {
            val keys = orderMap.keys.toIntArray()
            for (k in keys) {
                Log.d("Gen", "k =${k}")
                orderMap.remove(k)
            }
            orderAdapter.notifyDataSetChanged()
        }*/
        cancleButton = findViewById<Button>(R.id.cheese_cancle_btn)
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
        checkSoldout("119", R.id.burger_119_img)
        checkSoldout("120", R.id.burger_120_img)
        checkSoldout("121", R.id.burger_121_img)
        checkSoldout("122", R.id.burger_122_img)

/*        if (fireBaseData.get("119")!!.left == 0) {
            burgerImageView = findViewById(R.id.burger_119_img)
            burgerImageView.setImageResource(R.drawable.soldout_img)
        }
        if (fireBaseData.get("120")!!.left == 0) {
            burgerImageView = findViewById(R.id.burger_120_img)
            burgerImageView.setImageResource(R.drawable.soldout_img)
        }
        if (fireBaseData.get("121")!!.left == 0) {
            burgerImageView = findViewById(R.id.burger_121_img)
            burgerImageView.setImageResource(R.drawable.soldout_img)
        }
        if (fireBaseData.get("122")!!.left == 0) {
            burgerImageView = findViewById(R.id.burger_122_img)
            burgerImageView.setImageResource(R.drawable.soldout_img)
        }*/

        var burger_119_grid = findViewById<GridLayout>(R.id.cheese_119_grid)
        burger_119_grid.setOnClickListener(this)

        var burger_120_grid = findViewById<GridLayout>(R.id.cheese_120_grid)
        burger_120_grid.setOnClickListener(this)

        var burger_121_grid = findViewById<GridLayout>(R.id.cheese_121_grid)
        burger_121_grid.setOnClickListener(this)

        var burger_122_grid = findViewById<GridLayout>(R.id.cheese_122_grid)
        burger_122_grid.setOnClickListener(this)


    }

    override fun onClick(p0: View) {
        var code = 0
        when (p0.id) {
            R.id.cheese_119_grid -> code = 119
            R.id.cheese_120_grid -> code = 120
            R.id.cheese_121_grid -> code = 121
            R.id.cheese_122_grid -> code = 122

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