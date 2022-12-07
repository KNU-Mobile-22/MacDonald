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

    lateinit var requestLanch: ActivityResultLauncher<Intent>
    var currentMode: String = "general"

    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        btn_take_in = findViewById(R.id.take_in)
        btn_take_out = findViewById(R.id.take_out)
        btn_main = findViewById(R.id.main)
        btn_option = findViewById(R.id.option)


        requestLanch = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                val resultData = it.data?.getStringExtra("result")
                Toast.makeText(applicationContext, resultData, Toast.LENGTH_SHORT).show()
            }
        }

        btn_take_in.setOnClickListener {
            Toast.makeText(applicationContext, "매장 버튼 Clicked", Toast.LENGTH_SHORT).show()

            if (currentMode.equals("general")) {
                val intent = Intent(this, GeneralMenuActivity::class.java)
                intent.putExtra("data1", "takeIn")
                intent.putExtra("data2", "generalMode")
                requestLanch.launch(intent)
            } else if (currentMode.equals("easy")) {
                val intent = Intent(this, EasyMenuActivity::class.java)

                requestLanch.launch(intent)
            }
        }

        btn_take_out.setOnClickListener {
            Toast.makeText(applicationContext, "포장 버튼 Clicked", Toast.LENGTH_SHORT).show()

            if (currentMode.equals("general")) {
                val intent = Intent(this, GeneralMenuActivity::class.java)
                intent.putExtra("data1", "takeIn")
                intent.putExtra("data2", "generalMode")
                requestLanch.launch(intent)
            } else if (currentMode.equals("easy")) {
                val intent = Intent(this, EasyMenuActivity::class.java)

                requestLanch.launch(intent)
            }
        }

        btn_main.setOnClickListener {
            Toast.makeText(applicationContext, "로고 버튼 Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SupervisorActivity::class.java)
            requestLanch.launch(intent)
        }



        database = Firebase.database.reference
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var curMenu = snapshot.child("burgerList/1955버거").getValue()

                Log.d("myLog", "curMenu: ${curMenu}")

                var totalSellCnt = snapshot.child("stockData/totalSellCount").getValue()
                Log.d("myLog", "totalSellCnt: ${totalSellCnt}")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun btnClicked(v: View) {
        var btnText: String

//        if (v.id == R.id.option) {
//            btnText = btn_option.text.toString()
//            if (btnText == "간편 모드") {
//                btn_option.text = "포장 및 매장을 선택해주세요"
//                //btn_option.setTextSize(10)
//            } else {
//                btn_option.text = "간편 모드"
//            }
//            Toast.makeText(applicationContext, "Button Clicked", Toast.LENGTH_SHORT).show()
//        }

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

}