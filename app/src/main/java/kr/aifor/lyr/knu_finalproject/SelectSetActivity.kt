package kr.aifor.lyr.knu_finalproject

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import com.example.selectset.databinding.ActivityMainBinding
import kr.aifor.lyr.knu_finalproject.databinding.ActivitySelectSetBinding

class SelectSetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySelectSetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //단품, 세트 선택
        binding.singlebtn.setOnClickListener {
            binding.sidelay.visibility = View.GONE
            binding.drinklay.visibility = View.GONE
            binding.sidebtn.isEnabled = false
            binding.drinkbtn.isEnabled = false
            binding.sidetxt.setText("")
            binding.drinktxt.setText("")
            binding.sideimg.setImageResource(R.drawable.grayright)
            binding.drinkimg.setImageResource(R.drawable.grayright)
            binding.singlebtn.setBackgroundColor(Color.rgb(255, 235, 59))
            binding.singlebtn.setTextColor(Color.rgb(255, 0, 0))
            binding.setbtn.setTextColor(Color.rgb(255, 255, 255))
            binding.setbtn.setBackgroundColor(Color.rgb(0, 0, 0))
            binding.frenchFriesBtn.isSelected = false
            binding.cheeseStickBtn.isSelected = false
            binding.chickenNuggetsBtn.isSelected = false
            binding.cokeBtn.isSelected = false
            binding.ciderBtn.isSelected = false
            binding.zerocokeBtn.isSelected = false
            binding.orangeJuiceBtn.isSelected = false
            binding.coffeeBtn.isSelected = false
        }

        binding.setbtn.setOnClickListener {
            binding.sidelay.visibility = View.VISIBLE
            binding.drinklay.visibility = View.VISIBLE
            binding.sidetxt.setTextColor(Color.BLACK)
            binding.drinktxt.setTextColor(Color.BLACK)
            binding.sidebtn.isEnabled = true
            binding.drinkbtn.isEnabled = true
            binding.sideimg.setImageResource(R.drawable.under)
            binding.drinkimg.setImageResource(R.drawable.under)
            binding.sidetxt.setText("")
            binding.drinktxt.setText("")
            binding.setbtn.setBackgroundColor(Color.rgb(255, 235, 59))
            binding.setbtn.setTextColor(Color.rgb(255, 0, 0))
            binding.singlebtn.setTextColor(Color.rgb(255, 255, 255))
            binding.singlebtn.setBackgroundColor(Color.rgb(0, 0, 0))
            binding.pricetxt.setText(" ")
        }
        binding.sidebtn.setOnClickListener {
            if (binding.sidelay.visibility == View.VISIBLE) {
                binding.sidelay.visibility = View.GONE
                binding.sideimg.setImageResource(R.drawable.right)
            } else {
                binding.sidelay.visibility = View.VISIBLE
                binding.sideimg.setImageResource(R.drawable.under)
            }
        }

        binding.drinkbtn.setOnClickListener {
            if (binding.drinklay.visibility == View.VISIBLE) {
                binding.drinklay.visibility = View.GONE
                binding.drinkimg.setImageResource(R.drawable.right)
            } else {
                binding.drinklay.visibility = View.VISIBLE
                binding.drinkimg.setImageResource(R.drawable.under)
            }
        }

        binding.dessertbtn.setOnClickListener {
            if (binding.dessertlay.visibility == View.VISIBLE) {
                binding.dessertlay.visibility = View.GONE
                binding.dessertimg.setImageResource(R.drawable.right)
            } else {
                binding.dessertlay.visibility = View.VISIBLE
                binding.dessertimg.setImageResource(R.drawable.under)
            }
            var input_txt: String = ""
            if (binding.icecreamcbx.isChecked) {
                input_txt += " 아이스크림"
            }
            if (binding.churuscbx.isChecked) {
                input_txt += " 츄러스"
            }
            if (binding.applepiecbx.isChecked) {
                input_txt += " 애플파이"
            }
            binding.desserttxt.setText(input_txt)
        }
        //사이드 선택
        binding.frenchFriesBtn.setOnClickListener {
            binding.sidetxt.setText("감자튀김")
            binding.sideimg.setImageResource(R.drawable.right)
            binding.sidelay.visibility = View.GONE
            binding.frenchFriesBtn.isSelected = true
            binding.cheeseStickBtn.isSelected = false
            binding.chickenNuggetsBtn.isSelected = false
        }
        binding.cheeseStickBtn.setOnClickListener {
            binding.sidetxt.setText("치즈스틱")
            binding.sideimg.setImageResource(R.drawable.right)
            binding.sidelay.visibility = View.GONE
            binding.cheeseStickBtn.isSelected = true
            binding.frenchFriesBtn.isSelected = false
            binding.chickenNuggetsBtn.isSelected = false
        }
        binding.chickenNuggetsBtn.setOnClickListener {
            binding.sidetxt.setText("치킨너겟")
            binding.sideimg.setImageResource(R.drawable.right)
            binding.sidelay.visibility = View.GONE
            binding.chickenNuggetsBtn.isSelected = true
            binding.frenchFriesBtn.isSelected = false
            binding.cheeseStickBtn.isSelected = false
        }
        //음료 선택
        binding.cokeBtn.setOnClickListener {
            binding.drinktxt.setText("콜라")
            binding.drinkimg.setImageResource(R.drawable.right)
            binding.drinklay.visibility = View.GONE
            binding.cokeBtn.isSelected = true
            binding.ciderBtn.isSelected = false
            binding.zerocokeBtn.isSelected = false
            binding.orangeJuiceBtn.isSelected = false
            binding.coffeeBtn.isSelected = false
        }
        binding.ciderBtn.setOnClickListener {
            binding.drinktxt.setText("사이다")
            binding.drinkimg.setImageResource(R.drawable.right)
            binding.drinklay.visibility = View.GONE
            binding.cokeBtn.isSelected = false
            binding.ciderBtn.isSelected = true
            binding.zerocokeBtn.isSelected = false
            binding.orangeJuiceBtn.isSelected = false
            binding.coffeeBtn.isSelected = false
        }
        binding.zerocokeBtn.setOnClickListener {
            binding.drinktxt.setText("제로콜라")
            binding.drinkimg.setImageResource(R.drawable.right)
            binding.drinklay.visibility = View.GONE
            binding.cokeBtn.isSelected = false
            binding.ciderBtn.isSelected = false
            binding.zerocokeBtn.isSelected = true
            binding.orangeJuiceBtn.isSelected = false
            binding.coffeeBtn.isSelected = false
        }
        binding.orangeJuiceBtn.setOnClickListener {
            binding.drinktxt.setText("오렌지주스")
            binding.drinkimg.setImageResource(R.drawable.right)
            binding.drinklay.visibility = View.GONE
            binding.cokeBtn.isSelected = false
            binding.ciderBtn.isSelected = false
            binding.zerocokeBtn.isSelected = false
            binding.orangeJuiceBtn.isSelected = true
            binding.coffeeBtn.isSelected = false
        }
        binding.coffeeBtn.setOnClickListener {
            binding.drinktxt.setText("커피")
            binding.drinkimg.setImageResource(R.drawable.right)
            binding.drinklay.visibility = View.GONE
            binding.cokeBtn.isSelected = false
            binding.ciderBtn.isSelected = false
            binding.zerocokeBtn.isSelected = false
            binding.orangeJuiceBtn.isSelected = false
            binding.coffeeBtn.isSelected = true
        }
        //디저트(추천메뉴) 선택
        binding.noselectBtn.setOnClickListener {
            binding.desserttxt.setText(" X")
            binding.dessertimg.setImageResource(R.drawable.right)
            binding.dessertlay.visibility = View.GONE
            binding.cokeBtn.isSelected = true
            binding.icecreamcbx.isChecked = false
            binding.churuscbx.isChecked = false
            binding.applepiecbx.isChecked = false
        }
        binding.dessertbtn.setOnClickListener {
            if (binding.dessertlay.visibility == View.VISIBLE) {
                binding.dessertlay.visibility = View.GONE
                binding.dessertimg.setImageResource(R.drawable.right)
            } else {
                binding.dessertlay.visibility = View.VISIBLE
                binding.dessertimg.setImageResource(R.drawable.under)
            }
            var input_txt: String = ""
            if (binding.icecreamcbx.isChecked) {
                input_txt += " 아이스크림"
            }
            if (binding.churuscbx.isChecked) {
                input_txt += " 츄러스"
            }
            if (binding.applepiecbx.isChecked) {
                input_txt += " 애플파이"
            }
            binding.desserttxt.setText(input_txt)
        }
    }
}