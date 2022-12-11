package kr.aifor.lyr.knu_finalproject

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kr.aifor.lyr.knu_finalproject.databinding.ActivitySelectSetBinding

class SelectSetActivity : AppCompatActivity() {
    var fireBaseData: HashMap<String, Menu> = java.util.HashMap()
    var tempData: HashMap<String, Menu> = java.util.HashMap()
    var isSet: Boolean = false
    var currentSideCode: Int = 0
    var currentDrinkCode: Int = 0
    lateinit var setImg: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySelectSetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fireBaseData = intent.getSerializableExtra("fireBaseData") as HashMap<String, Menu>
        tempData = intent.getSerializableExtra("tempData") as HashMap<String, Menu>
        var burgerCode = intent.getIntExtra("burgerCode", 0)
        Log.d("Gen", "Select = ${fireBaseData.get("101")!!.name}")

        // 버거 이름 설정
        binding.selectTxt.setText(fireBaseData.get(burgerCode.toString())!!.name)

        // 버거 그림 설정
        var imageID = resources.getIdentifier("c${burgerCode}", "drawable", packageName)
        Log.d("imageID", "${imageID}")
        binding.selectImg.setImageResource(imageID)

        if (fireBaseData.get("202")!!.left == 0) {
            setImg = findViewById<ImageButton>(R.id.french_fries_btn)
            setImg.setImageResource(R.drawable.soldout_img)
        }

        if (fireBaseData.get("205")!!.left == 0) {
            setImg = findViewById<ImageButton>(R.id.coleslaw_btn)
            setImg.setImageResource(R.drawable.soldout_img)
        }

        if (fireBaseData.get("206")!!.left == 0) {
            setImg = findViewById<ImageButton>(R.id.cheese_stick_btn)
            setImg.setImageResource(R.drawable.soldout_img)
        }

        if (fireBaseData.get("208")!!.left == 0) {
            setImg = findViewById<ImageButton>(R.id.macnugget_btn)
            setImg.setImageResource(R.drawable.soldout_img)
        }

        if (fireBaseData.get("303")!!.left == 0) {
            setImg = findViewById<ImageButton>(R.id.coke_btn)
            setImg.setImageResource(R.drawable.soldout_img)
        }

        if (fireBaseData.get("306")!!.left == 0) {
            setImg = findViewById<ImageButton>(R.id.sprite_btn)
            setImg.setImageResource(R.drawable.soldout_img)
        }

        if (fireBaseData.get("304")!!.left == 0) {
            setImg = findViewById<ImageButton>(R.id.cokezero_btn)
            setImg.setImageResource(R.drawable.soldout_img)
        }

        if (fireBaseData.get("307")!!.left == 0) {
            setImg = findViewById<ImageButton>(R.id.spritezero_btn)
            setImg.setImageResource(R.drawable.soldout_img)
        }

        if (fireBaseData.get("405")!!.left == 0) {
            setImg = findViewById<ImageButton>(R.id.americano_btn)
            setImg.setImageResource(R.drawable.soldout_img)
        }

        if (fireBaseData.get("403")!!.left == 0) {
            setImg = findViewById<ImageButton>(R.id.latte_btn)
            setImg.setImageResource(R.drawable.soldout_img)
        }

        if (fireBaseData.get("404")!!.left == 0) {
            setImg = findViewById<ImageButton>(R.id.cappuccino_btn)
            setImg.setImageResource(R.drawable.soldout_img)
        }


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

            currentSideCode = 0
            currentDrinkCode = 0
            isSet = false

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

            isSet = true
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

        //사이드 선택
        binding.frenchFriesBtn.setOnClickListener {
            if (fireBaseData.get("202")!!.left == 0) {
                Toast.makeText(applicationContext, "해당 상품은 품절입니다.", Toast.LENGTH_SHORT).show()
            } else if (tempData.get("202")!!.left == 0) {
                Toast.makeText(applicationContext, "최대 주문 가능 수량입니다.", Toast.LENGTH_SHORT).show()
            } else {
                binding.sidetxt.setText("후렌치 후라이")
                binding.sideimg.setImageResource(R.drawable.right)
                binding.sidelay.visibility = View.GONE
                currentSideCode = 202
            }
        }
        binding.coleslawBtn.setOnClickListener {
            if (fireBaseData.get("205")!!.left == 0) {
                Toast.makeText(applicationContext, "해당 상품은 품절입니다.", Toast.LENGTH_SHORT).show()
            } else if (tempData.get("205")!!.left == 0) {
                Toast.makeText(applicationContext, "최대 주문 가능 수량입니다.", Toast.LENGTH_SHORT).show()
            } else {
                binding.sidetxt.setText("코우슬로")
                binding.sideimg.setImageResource(R.drawable.right)
                binding.sidelay.visibility = View.GONE
                currentSideCode = 205
            }
        }
        binding.cheeseStickBtn.setOnClickListener {
            if (fireBaseData.get("206")!!.left == 0) {
                Toast.makeText(applicationContext, "해당 상품은 품절입니다.", Toast.LENGTH_SHORT).show()
            } else if (tempData.get("206")!!.left == 0) {
                Toast.makeText(applicationContext, "최대 주문 가능 수량입니다.", Toast.LENGTH_SHORT).show()
            } else {
                binding.sidetxt.setText("치즈스틱 2조각")
                binding.sideimg.setImageResource(R.drawable.right)
                binding.sidelay.visibility = View.GONE
                currentSideCode = 206
            }
        }
        binding.macnuggetBtn.setOnClickListener {
            if (fireBaseData.get("208")!!.left == 0) {
                Toast.makeText(applicationContext, "해당 상품은 품절입니다.", Toast.LENGTH_SHORT).show()
            } else if (tempData.get("208")!!.left == 0) {
                Toast.makeText(applicationContext, "최대 주문 가능 수량입니다.", Toast.LENGTH_SHORT).show()
            } else {
                binding.sidetxt.setText("맥너겟 4조각")
                binding.sideimg.setImageResource(R.drawable.right)
                binding.sidelay.visibility = View.GONE
                currentSideCode = 208
            }
        }

        //음료 선택
        binding.cokeBtn.setOnClickListener {
            if (fireBaseData.get("303")!!.left == 0) {
                Toast.makeText(applicationContext, "해당 상품은 품절입니다.", Toast.LENGTH_SHORT).show()
            } else if (tempData.get("303")!!.left == 0) {
                Toast.makeText(applicationContext, "최대 주문 가능 수량입니다.", Toast.LENGTH_SHORT).show()
            } else {
                binding.drinktxt.setText("코카 콜라")
                binding.drinkimg.setImageResource(R.drawable.right)
                binding.drinklay.visibility = View.GONE
                currentDrinkCode = 303
            }
        }
        binding.spriteBtn.setOnClickListener {
            if (fireBaseData.get("306")!!.left == 0) {
                Toast.makeText(applicationContext, "해당 상품은 품절입니다.", Toast.LENGTH_SHORT).show()
            } else if (tempData.get("306")!!.left == 0) {
                Toast.makeText(applicationContext, "최대 주문 가능 수량입니다.", Toast.LENGTH_SHORT).show()
            } else {
                binding.drinktxt.setText("스프라이트")
                binding.drinkimg.setImageResource(R.drawable.right)
                binding.drinklay.visibility = View.GONE
                currentDrinkCode = 306
            }
        }
        binding.cokezeroBtn.setOnClickListener {
            if (fireBaseData.get("304")!!.left == 0) {
                Toast.makeText(applicationContext, "해당 상품은 품절입니다.", Toast.LENGTH_SHORT).show()
            } else if (tempData.get("304")!!.left == 0) {
                Toast.makeText(applicationContext, "최대 주문 가능 수량입니다.", Toast.LENGTH_SHORT).show()
            } else {
                binding.drinktxt.setText("코카 콜라 제로")
                binding.drinkimg.setImageResource(R.drawable.right)
                binding.drinklay.visibility = View.GONE
                currentDrinkCode = 304
            }
        }
        binding.spritezeroBtn.setOnClickListener {
            if (fireBaseData.get("307")!!.left == 0) {
                Toast.makeText(applicationContext, "해당 상품은 품절입니다.", Toast.LENGTH_SHORT).show()
            } else if (tempData.get("307")!!.left == 0) {
                Toast.makeText(applicationContext, "최대 주문 가능 수량입니다.", Toast.LENGTH_SHORT).show()
            } else {
                binding.drinktxt.setText("스프라이트 제로")
                binding.drinkimg.setImageResource(R.drawable.right)
                binding.drinklay.visibility = View.GONE
                currentDrinkCode = 307
            }
        }
        binding.americanoBtn.setOnClickListener {
            if (fireBaseData.get("405")!!.left == 0) {
                Toast.makeText(applicationContext, "해당 상품은 품절입니다.", Toast.LENGTH_SHORT).show()
            } else if (tempData.get("405")!!.left == 0) {
                Toast.makeText(applicationContext, "최대 주문 가능 수량입니다.", Toast.LENGTH_SHORT).show()
            } else {
                binding.drinktxt.setText("아메리카노")
                binding.drinkimg.setImageResource(R.drawable.right)
                binding.drinklay.visibility = View.GONE
                currentDrinkCode = 405
            }
        }
        binding.latteBtn.setOnClickListener {
            if (fireBaseData.get("403")!!.left == 0) {
                Toast.makeText(applicationContext, "해당 상품은 품절입니다.", Toast.LENGTH_SHORT).show()
            } else if (tempData.get("403")!!.left == 0) {
                Toast.makeText(applicationContext, "최대 주문 가능 수량입니다.", Toast.LENGTH_SHORT).show()
            } else {
                binding.drinktxt.setText("카페라떼")
                binding.drinkimg.setImageResource(R.drawable.right)
                binding.drinklay.visibility = View.GONE
                currentDrinkCode = 403
            }
        }
        binding.vanilaLatteBtn.setOnClickListener {
            if (fireBaseData.get("401")!!.left == 0) {
                Toast.makeText(applicationContext, "해당 상품은 품절입니다.", Toast.LENGTH_SHORT).show()
            } else if (tempData.get("401")!!.left == 0) {
                Toast.makeText(applicationContext, "최대 주문 가능 수량입니다.", Toast.LENGTH_SHORT).show()
            } else {
                binding.drinktxt.setText("바닐라 라떼")
                binding.drinkimg.setImageResource(R.drawable.right)
                binding.drinklay.visibility = View.GONE
                currentDrinkCode = 401
            }
        }
        binding.cappuccinoBtn.setOnClickListener {
            if (fireBaseData.get("404")!!.left == 0) {
                Toast.makeText(applicationContext, "해당 상품은 품절입니다.", Toast.LENGTH_SHORT).show()
            } else if (tempData.get("404")!!.left == 0) {
                Toast.makeText(applicationContext, "최대 주문 가능 수량입니다.", Toast.LENGTH_SHORT).show()
            } else {
                binding.drinktxt.setText("카푸치노")
                binding.drinkimg.setImageResource(R.drawable.right)
                binding.drinklay.visibility = View.GONE
                currentDrinkCode = 404
            }
        }

        //장바구니 담기 버튼
        binding.addToCartBtn.setOnClickListener {

            if (isSet == false) {

                intent.putExtra("result", burgerCode)
                setResult(RESULT_OK, intent)
                finish()

                Log.d("resultCode", "resultCode: ${burgerCode}")
            } else {
                if (currentSideCode == 0 || currentDrinkCode == 0) {
                    Toast.makeText(applicationContext, "사이드 혹은 음료를 선택하세요", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    var resultCode =
                        (burgerCode.toString() + currentSideCode.toString() + currentDrinkCode.toString()).toInt()
                    Log.d("resultCode", "resultCode: ${resultCode}")
                    intent.putExtra("result", resultCode)
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        }
    }
}