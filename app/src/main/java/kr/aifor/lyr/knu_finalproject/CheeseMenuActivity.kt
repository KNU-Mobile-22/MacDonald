package kr.aifor.lyr.knu_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CheeseMenuActivity : AppCompatActivity() {

    var orderMap = HashMap<Int, Int>()
    lateinit var rec_orderList: RecyclerView
    lateinit var orderAdapter: orderAdapter
    lateinit var completeButton: Button
    lateinit var cancleButton: Button
    lateinit var burgerImageView: ImageView
    var fireBaseData: HashMap<String, Menu> = java.util.HashMap()
    lateinit var requestLaunch: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheese_menu)

        rec_orderList = findViewById(R.id.easy_orderList_cheese)
        completeButton = findViewById(R.id.cheese_complete_btn)

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

        cancleButton = findViewById<Button>(R.id.cheese_cancle_btn)
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

        if(fireBaseData.get("119")!!.left==0){
            burgerImageView=findViewById(R.id.burger_119_img)
            burgerImageView.setImageResource(R.drawable.soldout_img)
        }
        if(fireBaseData.get("120")!!.left==0){
            burgerImageView=findViewById(R.id.burger_120_img)
            burgerImageView.setImageResource(R.drawable.soldout_img)
        }
        if(fireBaseData.get("121")!!.left==0){
            burgerImageView=findViewById(R.id.burger_121_img)
            burgerImageView.setImageResource(R.drawable.soldout_img)
        }
        if(fireBaseData.get("122")!!.left==0){
            burgerImageView=findViewById(R.id.burger_122_img)
            burgerImageView.setImageResource(R.drawable.soldout_img)
        }

        var burger_119_grid = findViewById<GridLayout>(R.id.cheese_119_grid)
        burger_119_grid.setOnClickListener {
            if(fireBaseData.get("119")!!.left==0){
                Toast.makeText(applicationContext,"해당 상품은 품절입니다.", Toast.LENGTH_SHORT).show()
            }
            val intent2 = Intent(this, SelectSetActivity::class.java)
            if (orderMap == null)
                Log.d("Gen", "OrderMap is Null")
            intent2.putExtra("fireBaseData", fireBaseData)
            intent2.putExtra("burgerCode", 119)

            requestLaunch.launch(intent2)
        }

        var burger_120_grid = findViewById<GridLayout>(R.id.cheese_120_grid)
        burger_120_grid.setOnClickListener {
            if(fireBaseData.get("120")!!.left==0){
                Toast.makeText(applicationContext,"해당 상품은 품절입니다.", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent2 = Intent(this, SelectSetActivity::class.java)
                if (orderMap == null)
                    Log.d("Gen", "OrderMap is Null")
                intent2.putExtra("fireBaseData", fireBaseData)
                intent2.putExtra("burgerCode", 120)

                requestLaunch.launch(intent2)
            }
        }

        var burger_121_grid = findViewById<GridLayout>(R.id.cheese_121_grid)
        burger_121_grid.setOnClickListener {
            if(fireBaseData.get("121")!!.left==0){
                Toast.makeText(applicationContext,"해당 상품은 품절입니다.", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent2 = Intent(this, SelectSetActivity::class.java)
                if (orderMap == null)
                    Log.d("Gen", "OrderMap is Null")
                intent2.putExtra("fireBaseData", fireBaseData)
                intent2.putExtra("burgerCode", 121)

                requestLaunch.launch(intent2)
            }
        }

        var burger_122_grid = findViewById<GridLayout>(R.id.cheese_122_grid)
        burger_122_grid.setOnClickListener {
            if(fireBaseData.get("122")!!.left==0){
                Toast.makeText(applicationContext,"해당 상품은 품절입니다.", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent2 = Intent(this, SelectSetActivity::class.java)
                if (orderMap == null)
                    Log.d("Gen", "OrderMap is Null")
                intent2.putExtra("fireBaseData", fireBaseData)
                intent2.putExtra("burgerCode", 122)

                requestLaunch.launch(intent2)
            }
        }


    }
}