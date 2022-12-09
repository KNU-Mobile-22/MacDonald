package kr.aifor.lyr.knu_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BeafMenuActivity : AppCompatActivity() {

    var orderMap = HashMap<Int, Int>()
    lateinit var rec_orderList: RecyclerView
    lateinit var orderAdapter: orderAdapter
    lateinit var completeButton: Button
    lateinit var cancleButton: Button
    var fireBaseData: HashMap<String, Menu> = java.util.HashMap()
    lateinit var requestLaunch: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beaf_menu)

        rec_orderList = findViewById(R.id.easy_orderList_beaf)
        completeButton = findViewById(R.id.beaf_complete_btn)

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

        cancleButton = findViewById<Button>(R.id.beaf_cancle_btn)
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

        var burger_101_grid = findViewById<GridLayout>(R.id.burger_101_grid)
        burger_101_grid.setOnClickListener {
            val intent2 = Intent(this, SelectSetActivity::class.java)
            if (orderMap == null)
                Log.d("Gen", "OrderMap is Null")
            intent2.putExtra("fireBaseData", fireBaseData)
            intent2.putExtra("burgerCode", 101)

            requestLaunch.launch(intent2)
        }

        var burger_102_grid = findViewById<GridLayout>(R.id.burger_102_grid)
        burger_102_grid.setOnClickListener {
            val intent2 = Intent(this, SelectSetActivity::class.java)
            if (orderMap == null)
                Log.d("Gen", "OrderMap is Null")
            intent2.putExtra("fireBaseData", fireBaseData)
            intent2.putExtra("burgerCode", 102)

            requestLaunch.launch(intent2)
        }

        var burger_110_grid = findViewById<GridLayout>(R.id.burger_110_grid)
        burger_110_grid.setOnClickListener {
            val intent2 = Intent(this, SelectSetActivity::class.java)
            if (orderMap == null)
                Log.d("Gen", "OrderMap is Null")
            intent2.putExtra("fireBaseData", fireBaseData)
            intent2.putExtra("burgerCode", 110)

            requestLaunch.launch(intent2)
        }

        var burger_113_grid = findViewById<GridLayout>(R.id.burger_113_grid)
        burger_113_grid.setOnClickListener {
            val intent2 = Intent(this, SelectSetActivity::class.java)
            if (orderMap == null)
                Log.d("Gen", "OrderMap is Null")
            intent2.putExtra("fireBaseData", fireBaseData)
            intent2.putExtra("burgerCode", 113)

            requestLaunch.launch(intent2)
        }

        var burger_118_grid = findViewById<GridLayout>(R.id.burger_118_grid)
        burger_118_grid.setOnClickListener {
            val intent2 = Intent(this, SelectSetActivity::class.java)
            if (orderMap == null)
                Log.d("Gen", "OrderMap is Null")
            intent2.putExtra("fireBaseData", fireBaseData)
            intent2.putExtra("burgerCode", 118)

            requestLaunch.launch(intent2)
        }

        var burger_123_grid = findViewById<GridLayout>(R.id.burger_123_grid)
        burger_123_grid.setOnClickListener {
            val intent2 = Intent(this, SelectSetActivity::class.java)
            if (orderMap == null)
                Log.d("Gen", "OrderMap is Null")
            intent2.putExtra("fireBaseData", fireBaseData)
            intent2.putExtra("burgerCode", 123)

            requestLaunch.launch(intent2)
        }
    }
}