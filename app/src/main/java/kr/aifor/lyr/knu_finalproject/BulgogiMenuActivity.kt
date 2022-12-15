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

class BulgogiMenuActivity : AppCompatActivity(), View.OnClickListener {

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
        setContentView(R.layout.activity_bulgogi_menu)

        rec_orderList = findViewById(R.id.easy_orderList_bulgogi)
        completeButton = findViewById(R.id.bulgogi_complete_btn)

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

/*        cancleButton = findViewById<Button>(R.id.bulgogi_cancle_btn)
        cancleButton.setOnClickListener {
            val keys = orderMap.keys.toIntArray()
            for (k in keys) {
                Log.d("Gen", "k =${k}")
                orderMap.remove(k)
            }
            orderAdapter.notifyDataSetChanged()
        }*/
        cancleButton = findViewById<Button>(R.id.bulgogi_cancle_btn)
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

        fun checkSoldout(code: String, imgId: Int) {
            if (fireBaseData.get(code)!!.left == 0) {
                burgerImageView = findViewById(imgId)
                burgerImageView.setImageResource(R.drawable.soldout_img)
            }
        }
        checkSoldout("115", R.id.burger_115_img)
        checkSoldout("116", R.id.burger_116_img)
        checkSoldout("117", R.id.burger_117_img)

/*        if (fireBaseData.get("115")!!.left == 0) {
            burgerImageView = findViewById(R.id.burger_115_img)
            burgerImageView.setImageResource(R.drawable.soldout_img)
        }
        if (fireBaseData.get("116")!!.left == 0) {
            burgerImageView = findViewById(R.id.burger_116_img)
            burgerImageView.setImageResource(R.drawable.soldout_img)
        }
        if (fireBaseData.get("117")!!.left == 0) {
            burgerImageView = findViewById(R.id.burger_117_img)
            burgerImageView.setImageResource(R.drawable.soldout_img)
        }*/

        var burger_115_grid = findViewById<GridLayout>(R.id.bulgogi_115_grid)
        burger_115_grid.setOnClickListener(this)

        var burger_116_grid = findViewById<GridLayout>(R.id.bulgogi_116_grid)
        burger_116_grid.setOnClickListener(this)

        var burger_117_grid = findViewById<GridLayout>(R.id.bulgogi_117_grid)
        burger_117_grid.setOnClickListener(this)

    }

    override fun onClick(p0: View) {
        var code = 0
        when (p0.id) {
            R.id.bulgogi_115_grid -> code = 115
            R.id.bulgogi_116_grid -> code = 116
            R.id.bulgogi_117_grid -> code = 117

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