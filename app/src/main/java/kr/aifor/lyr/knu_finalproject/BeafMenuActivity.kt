package kr.aifor.lyr.knu_finalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.aifor.lyr.knu_finalproject.databinding.BeafMenuLayoutBinding

class BeafMenuActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = BeafMenuLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btn_beafBurger1 = binding.btnBeafBurger1

        btn_beafBurger1.setOnClickListener {
            val menuName = btn_beafBurger1.getText().toString()
            intent.putExtra("result", menuName)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}