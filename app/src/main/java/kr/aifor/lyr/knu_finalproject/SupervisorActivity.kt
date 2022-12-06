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

class SupervisorActivity : AppCompatActivity() {
    lateinit var database: DatabaseReference
    lateinit var allSellView: TextView
    lateinit var allSumView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supervisor)



        database = Firebase.database.reference
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var totalSellCount = snapshot.child("stockData/totalSellCount").getValue()
                Log.d("myLog", "totalSellCount: ${totalSellCount}")
                var allSellView = findViewById<TextView>(R.id.all_sell)
                allSellView.setText("${totalSellCount}")


                var totalSellMoney = snapshot.child("stockData/totalSellMoney").getValue()
                Log.d("myLog", "totalSellMoney: ${totalSellMoney}")
                var allSumView = findViewById<TextView>(R.id.all_sum)
                allSumView.setText("${totalSellMoney}")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}