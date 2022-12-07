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

        var beafButton = findViewById<Button>(R.id.easy_beaf_btn)
        beafButton.setOnClickListener {
            val intent2 = Intent(this, BeafMenuActivity::class.java)
            //requestLanch.launch(intent)
            startActivity(intent2)
        }

        var chickenButton = findViewById<Button>(R.id.easy_chicken_btn)
        chickenButton.setOnClickListener {
            val intent2 = Intent(this, ChickenMenuActivity::class.java)
            //requestLanch.launch(intent)
            startActivity(intent2)
        }

        var cheeseButton = findViewById<Button>(R.id.easy_cheese_btn)
        cheeseButton.setOnClickListener {
            val intent2 = Intent(this, CheeseMenuActivity::class.java)
            //requestLanch.launch(intent)
            startActivity(intent2)
        }

        var bulgogiButton = findViewById<Button>(R.id.easy_bulgogi_btn)
        bulgogiButton.setOnClickListener {
            val intent2 = Intent(this, BulgogiMenuActivity::class.java)
            //requestLanch.launch(intent)
            startActivity(intent2)
        }

        var shrimpButton = findViewById<Button>(R.id.easy_shrimp_btn)
        shrimpButton.setOnClickListener {
            val intent2 = Intent(this, ShrimpMenuActivity::class.java)
            //requestLanch.launch(intent)
            startActivity(intent2)
        }

        var fishButton = findViewById<Button>(R.id.easy_fish_btn)
        fishButton.setOnClickListener {
            val intent2 = Intent(this, FishMenuActivity::class.java)
            //requestLanch.launch(intent)
            startActivity(intent2)
        }

        var sideButton = findViewById<Button>(R.id.easy_side_btn)
        sideButton.setOnClickListener {
            val intent2 = Intent(this, SideMenuActivity::class.java)
            //requestLanch.launch(intent)
            startActivity(intent2)
        }

        var drinkButton = findViewById<Button>(R.id.easy_drink_btn)
        drinkButton.setOnClickListener {
            val intent2 = Intent(this, DrinkMenuActivity::class.java)
            //requestLanch.launch(intent)
            startActivity(intent2)
        }

        var dessertButton = findViewById<Button>(R.id.easy_dessert_btn)
        dessertButton.setOnClickListener {
            val intent2 = Intent(this, DessertMenuActivity::class.java)
            //requestLanch.launch(intent)
            startActivity(intent2)
        }

    }
}