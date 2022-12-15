package kr.aifor.lyr.knu_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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
    lateinit var btn_main: ImageView
    lateinit var btn_take_in: Button
    lateinit var btn_take_out: Button
    lateinit var btn_option: Button
    lateinit var view_guide: TextView

    var orderCount: Int = -1
    var orderPrice: Int = -1
    var loc: String = ""

    lateinit var requestLaunch: ActivityResultLauncher<Intent>

    var currentMode: String = "general"
    var menuList = MenuList()

    var fireBaseData: HashMap<String, Menu> = java.util.HashMap()
    var tempData: HashMap<String, Menu> = java.util.HashMap()
    lateinit var database: DatabaseReference

    var isNetworkDone = false

    var isTimerRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        btn_take_in = findViewById(R.id.take_in)
        btn_take_out = findViewById(R.id.take_out)
        btn_main = findViewById(R.id.main)
        btn_option = findViewById(R.id.option)
        view_guide = findViewById(R.id.guideText)

        database = Firebase.database.reference
        // initDatabase()
        Log.d("Gen", "loading")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                /**
                data라는 해시맵에 메뉴 코드를 key, Menu 클래스를 value로 하여
                데이터를 삽입
                 */
                isNetworkDone = false
                Log.d("Gen", "startNetworking")
                for (item in snapshot.children) {
                    when (item.key) {
                        "totalOrderNum" -> orderCount = item.getValue(Int::class.java)!!
                        "totalSellPrice" -> orderPrice = item.getValue(Int::class.java)!!
                        else -> {
                            val key = item.key
                            val menuVal = item.getValue(Menu::class.java)
                            fireBaseData.put(key!!, menuVal!!)
                            tempData.put(key!!, menuVal!!)
                        }
                    }
                }
                isNetworkDone = true
                Log.d("myLog", "firebase init complete!")
                // Toast.makeText(applicationContext, "firebase 갱신 성공", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        // initDatabase()

        var Str = (menuList.burgerList[0].javaClass.name)
        // fireBaseData = intent.getSerializableExtra("fireBaseData") as HashMap<String, Menu>
        // orderCount = intent.getIntExtra("totalOrderNum", 0)
        // orderPrice = intent.getIntExtra("totalSellPrice", 0)

        // Log.d("Gen", "Home = ${fireBaseData}")
        // Log.d("Gen", "Home = ${fireBaseData.get("101")!!.name}")
        requestLaunch = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                val resultData = it.data?.getStringExtra("result")
                // Toast.makeText(applicationContext, resultData, Toast.LENGTH_SHORT).show()
            }
        }

        btn_take_in.setOnClickListener {
            // Toast.makeText(applicationContext, "매장 버튼 Clicked", Toast.LENGTH_SHORT).show()
            loc = "takeIn"
            if (currentMode.equals("general") && isNetworkDone) {
                var intent = Intent(this, GeneralMenuActivity::class.java)
                intent = makeIntent(intent, loc)
                requestLaunch.launch(intent)

            } else if (currentMode.equals("easy") && isNetworkDone) {
                var intent = Intent(this, EasyMenuActivity::class.java)
                intent = makeIntent(intent, loc)
                requestLaunch.launch(intent)
            }

            currentMode = "general"
            view_guide.visibility = VISIBLE
            btn_option.text = "여기를 터치하세요"
        }

        btn_take_out.setOnClickListener {
            // Toast.makeText(applicationContext, "포장 버튼 Clicked", Toast.LENGTH_SHORT).show()

            loc = "takeOut"
            if (currentMode.equals("general") && isNetworkDone) {
                var intent = Intent(this, GeneralMenuActivity::class.java)
                intent = makeIntent(intent, loc)
                requestLaunch.launch(intent)

            } else if (currentMode.equals("easy") && isNetworkDone) {
                var intent = Intent(this, EasyMenuActivity::class.java)
                intent = makeIntent(intent, loc)
                requestLaunch.launch(intent)
            }

            currentMode = "general"
            view_guide.visibility = VISIBLE
            btn_option.text = "여기를 터치하세요"
        }

        var count = 0


        val countDown = object : CountDownTimer(1000, 1000) {

            override fun onTick(p0: Long) {
                // countDownInterval 마다 호출 (여기선 1000ms)
            }

            override fun onFinish() {
                // 타이머가 종료되면 호출
                count = 0
                isTimerRunning = false
                Log.d("myLog", "count: $count")
            }
        }

        btn_main.setOnClickListener {
            if (isTimerRunning == false) {
                countDown.start()
                isTimerRunning = true
            }
            // Toast.makeText(applicationContext, "로고 버튼 Clicked", Toast.LENGTH_SHORT).show()
            count++
            Log.d("myLog", "count: $count")

            if (count == 5) {
                count = 0
                val intent = Intent(this, SupervisorActivity::class.java)
                requestLaunch.launch(intent)
            }

        }


    }

    fun btnClicked(v: View) {
        var btnText: String

        when (v.id) {
            R.id.option -> {

                btnText = btn_option.text.toString()
                if (currentMode == "general") {
                    currentMode = "easy"
                    view_guide.visibility = INVISIBLE
                    btn_option.text = "일반 모드로 돌아가기"
                    //btn_option.setTextSize(10)
                } else {
                    currentMode = "general"
                    view_guide.visibility = VISIBLE
                    btn_option.text = "여기를 터치하세요"
                }
                /*Toast.makeText(
                    applicationContext,
                    "current mode: ${currentMode}",
                    Toast.LENGTH_SHORT
                ).show()*/
            }
        }
    }

    fun makeIntent(intent: Intent, loc: String): Intent {
        intent.putExtra("loc", loc)
        intent.putExtra("fireBaseData", fireBaseData)
        intent.putExtra("tempData", tempData)
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