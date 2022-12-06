package kr.aifor.lyr.knu_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher

class EasyMenuActivity : AppCompatActivity() {

    lateinit var requestLanch: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_menu)

        var drinkButton = findViewById<Button>(R.id.easy_drink_btn)

        drinkButton.setOnClickListener {
            val intent2 = Intent(this, DrinkMenuActivity::class.java)

            //requestLanch.launch(intent)
            startActivity(intent2)
        }

    }
}