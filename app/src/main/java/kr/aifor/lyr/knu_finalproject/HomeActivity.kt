package kr.aifor.lyr.knu_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    lateinit var btn_main: Button
    lateinit var btn_take_in: Button
    lateinit var btn_take_out: Button
    lateinit var btn_option: Button

    var orderCount: Int = -1
    var orderPrice: Int = -1
    var loc: String = ""

    lateinit var requestLaunch: ActivityResultLauncher<Intent>

    var currentMode: String = "general"
    var menuList = MenuList()

    var fireBaseData: HashMap<String, Menu> = java.util.HashMap()
    lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        btn_take_in = findViewById(R.id.take_in)
        btn_take_out = findViewById(R.id.take_out)
        btn_main = findViewById(R.id.main)
        btn_option = findViewById(R.id.option)
        database = Firebase.database.reference

        // initDatabase()

        var Str = (menuList.burgerList[0].javaClass.name)
        fireBaseData = intent.getSerializableExtra("fireBaseData") as HashMap<String, Menu>
        orderCount = intent.getIntExtra("totalOrderNum", 0)
        orderPrice = intent.getIntExtra("totalSellPrice", 0)

        Log.d("Gen", "Home = ${fireBaseData}")
        Log.d("Gen", "Home = ${fireBaseData.get("101")!!.name}")
        requestLaunch = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                val resultData = it.data?.getStringExtra("result")
                Toast.makeText(applicationContext, resultData, Toast.LENGTH_SHORT).show()
            }
        }

        btn_take_in.setOnClickListener {
            Toast.makeText(applicationContext, "매장 버튼 Clicked", Toast.LENGTH_SHORT).show()
            loc = "takeIn"
            if (currentMode.equals("general")) {
                var intent = Intent(this, GeneralMenuActivity::class.java)
                intent = makeIntent(intent, loc)
                requestLaunch.launch(intent)
            } else if (currentMode.equals("easy")) {
                var intent = Intent(this, EasyMenuActivity::class.java)
                intent = makeIntent(intent, loc)
                requestLaunch.launch(intent)
            }
        }

        btn_take_out.setOnClickListener {
            Toast.makeText(applicationContext, "포장 버튼 Clicked", Toast.LENGTH_SHORT).show()

            loc = "takeOut"
            if (currentMode.equals("general")) {
                var intent = Intent(this, GeneralMenuActivity::class.java)
                intent = makeIntent(intent, loc)
                requestLaunch.launch(intent)
            } else if (currentMode.equals("easy")) {
                var intent = Intent(this, EasyMenuActivity::class.java)
                intent = makeIntent(intent, loc)
                requestLaunch.launch(intent)
            }
        }

        btn_main.setOnClickListener {
            Toast.makeText(applicationContext, "로고 버튼 Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SupervisorActivity::class.java)
            requestLaunch.launch(intent)
        }

    }

    fun btnClicked(v: View) {
        var btnText: String

        when (v.id) {
            R.id.option -> {

                btnText = btn_option.text.toString()
                if (btnText == "키오스크가 어려우신가요?") {
                    currentMode = "easy"
                    btn_option.text = "일반 모드로 돌아가기"
                    //btn_option.setTextSize(10)
                } else {
                    currentMode = "general"
                    btn_option.text = "키오스크가 어려우신가요?"
                }
                Toast.makeText(
                    applicationContext,
                    "current mode: ${currentMode}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun makeIntent(intent: Intent, loc: String): Intent {
        intent.putExtra("loc", loc)
        intent.putExtra("fireBaseData", fireBaseData)
        intent.putExtra("totalOrderNum", orderCount)
        intent.putExtra("totalSellPrice", orderPrice)

        return intent
    }

    fun initDatabase() {
        Log.d("myLog", "${menuList.burgerList.size}")
        for (i in 0 until menuList.burgerList.size) {
            database.child("${menuList.burgerList[i].code}")
                .setValue(menuList.burgerList[i])
        }

        Log.d("myLog", "${menuList.sideList.size}")
        for (i in 0 until menuList.sideList.size) {
            database.child("${menuList.sideList[i].code}")
                .setValue(menuList.sideList[i])
        }

        Log.d("myLog", "${menuList.drinkList.size}")
        for (i in 0 until menuList.drinkList.size) {
            database.child("${menuList.drinkList[i].code}")
                .setValue(menuList.drinkList[i])
        }

        Log.d("myLog", "${menuList.coffeeList.size}")
        for (i in 0 until menuList.coffeeList.size) {
            database.child("${menuList.coffeeList[i].code}")
                .setValue(menuList.coffeeList[i])
        }

        Log.d("myLog", "${menuList.desertList.size}")
        for (i in 0 until menuList.desertList.size) {
            database.child("${menuList.desertList[i].code}")
                .setValue(menuList.desertList[i])
        }

        database.child("totalOrderNum").setValue(0)
        database.child("totalSellPrice").setValue(0)
    }


}