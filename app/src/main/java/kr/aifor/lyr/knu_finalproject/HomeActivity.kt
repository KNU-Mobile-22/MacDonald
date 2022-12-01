package kr.aifor.lyr.knu_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class HomeActivity : AppCompatActivity() {
    lateinit var btn_main: Button
    lateinit var btn_take_in: Button
    lateinit var btn_take_out: Button
    lateinit var btn_option: Button

    lateinit var requestLanch: ActivityResultLauncher<Intent>
    var currentMode: String = "general"

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
            }
        }

        btn_take_out.setOnClickListener {
            Toast.makeText(applicationContext, "포장 버튼 Clicked", Toast.LENGTH_SHORT).show()

            if (currentMode.equals("general")) {
                val intent = Intent(this, GeneralMenuActivity::class.java)
                intent.putExtra("data1", "takeIn")
                intent.putExtra("data2", "generalMode")
                requestLanch.launch(intent)
            }
        }
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
                currentMode = "easy"
                btnText = btn_option.text.toString()
                if (btnText == "키오스크가 어려우신가요?") {
                    btn_option.text = "포장 및 매장을 선택해주세요"
                    //btn_option.setTextSize(10)
                } else {
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