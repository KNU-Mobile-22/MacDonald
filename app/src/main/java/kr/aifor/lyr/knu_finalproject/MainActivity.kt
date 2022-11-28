package kr.aifor.lyr.knu_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kr.aifor.lyr.knu_finalproject.databinding.EasyMenuLayoutBinding

class MainActivity : AppCompatActivity() {
    lateinit var requestLanch: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        var binding = EasyMenuLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val beafMenuBtn = binding.beafBurgerBtn

        requestLanch = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                val resultData = it.data?.getStringExtra("result")
                if (resultData != null) {
                    Log.d("result", resultData)
                }
            }
        }

        beafMenuBtn.setOnClickListener {
            val intent = Intent(this, BeafMenuActivity::class.java)
            // intent.putExtra("data1", "Hello")

            requestLanch.launch(intent)
        }

    }

}