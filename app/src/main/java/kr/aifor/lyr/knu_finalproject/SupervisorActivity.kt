package kr.aifor.lyr.knu_finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class SupervisorActivity : AppCompatActivity() {
    lateinit var database: DatabaseReference
    lateinit var allSellView: TextView
    lateinit var allSumView: TextView
    lateinit var leftText: TextView
    var menuList = MenuList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supervisor)

        database = Firebase.database.reference
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var totalSellCount = snapshot.child("totalOrderNum").getValue()
                Log.d("myLog", "totalSellCount: ${totalSellCount}")
                allSellView = findViewById<TextView>(R.id.all_sell)
                allSellView.setText("${totalSellCount}")

                var totalSellMoney = snapshot.child("totalSellPrice").getValue()
                Log.d("myLog", "totalSellMoney: ${totalSellMoney}")
                allSumView = findViewById<TextView>(R.id.all_sum)
                allSumView.setText("${totalSellMoney}")

                for (i in 0 until menuList.burgerList.size) {
                    var resID = resources.getIdentifier("left_" + (i + 101), "id", packageName)
                    leftText = findViewById(resID)
                    var item = snapshot.child("${i + 101}/left").getValue()
                    Log.d("myLog", "burger${i + 101}_left: ${item}")
                    leftText.setText("${item}")
                }

                for (i in 0 until menuList.sideList.size) {
                    var resID = resources.getIdentifier("left_" + (i + 201), "id", packageName)
                    leftText = findViewById(resID)
                    var item = snapshot.child("${i + 201}/left").getValue()
                    Log.d("myLog", "side${i + 201}_left: ${item}")
                    leftText.setText("${item}")
                }

                for (i in 0 until menuList.drinkList.size) {
                    var resID = resources.getIdentifier("left_" + (i + 301), "id", packageName)
                    leftText = findViewById(resID)
                    var item = snapshot.child("${i + 301}/left").getValue()
                    Log.d("myLog", "drink${i + 301}_left: ${item}")
                    leftText.setText("${item}")
                }

                for (i in 0 until menuList.coffeeList.size) {
                    var resID = resources.getIdentifier("left_" + (i + 401), "id", packageName)
                    leftText = findViewById(resID)
                    var item = snapshot.child("${i + 401}/left").getValue()
                    Log.d("myLog", "coffee${i + 401}_left: ${item}")
                    leftText.setText("${item}")
                }

                for (i in 0 until menuList.desertList.size) {
                    var resID = resources.getIdentifier("left_" + (i + 501), "id", packageName)
                    leftText = findViewById(resID)
                    var item = snapshot.child("${i + 501}/left").getValue()
                    Log.d("myLog", "side${i + 501}_left: ${item}")
                    leftText.setText("${item}")
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}
