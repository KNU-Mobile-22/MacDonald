package kr.aifor.lyr.knu_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*

class LogoActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference
    var fireBaseData: HashMap<String, Menu> = java.util.HashMap()
    var orderCount : Int = -1
    var orderPrice : Int = -1
    var isNetworkDone = false
    var coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo)
        database = Firebase.database.reference
        Log.d("Gen","loading")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                /**
                data라는 해시맵에 메뉴 코드를 key, Menu 클래스를 value로 하여
                데이터를 삽입
                 */
                Log.d("Gen", "startNetworking")
                for (item in snapshot.children) {
                    when (item.key) {
                        "totalOrderNum" -> orderCount = item.getValue(Int::class.java)!!
                        "totalSellPrice" -> orderPrice = item.getValue(Int::class.java)!!
                        else -> {
                            val key = item.key
                            val menuVal = item.getValue(Menu::class.java)
                            fireBaseData.put(key!!, menuVal!!)
                        }
                    }
                }
                isNetworkDone = true
                Log.d("myLog", "firebase init complete!")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        coroutineScope.launch {
            delay(3000)
            var intent = Intent(this@LogoActivity, HomeActivity::class.java)
            while(isNetworkDone == false) {
                delay(1000)
                Log.d("Gen", "waiting network")
            }

            Log.d("Gen", "intent ${isNetworkDone}")
            Log.d("Gen", "logo = ${fireBaseData.get("101")!!.name}")
            intent.putExtra("fireBaseData", fireBaseData)
            intent.putExtra("totalOrderNum", orderCount)
            intent.putExtra("totalSellPrice", orderPrice)
            if(isNetworkDone == true)
                startActivity(intent)
            finish()
        }
    }
}