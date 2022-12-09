package kr.aifor.lyr.knu_finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class CompletePaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_payment)

        val data1 = intent.getStringExtra("data1")
        val data2 = intent.getStringExtra("data2")

        Log.d("intent", "CardInsert -> CompletePayment: ${data1}, ${data2}")

        val orderFinishButton = findViewById<Button>(R.id.comp_btn_orderFinish)

        val text_number=findViewById<TextView>(R.id.btn_number)

        val btn_one=findViewById<Button>(R.id.btn_one)
        val btn_two=findViewById<Button>(R.id.btn_two)
        val btn_three=findViewById<Button>(R.id.btn_three)

        val btn_four=findViewById<Button>(R.id.btn_four)
        val btn_five=findViewById<Button>(R.id.btn_five)
        val btn_six=findViewById<Button>(R.id.btn_six)

        val btn_seven=findViewById<Button>(R.id.btn_seven)
        val btn_eight=findViewById<Button>(R.id.btn_eight)
        val btn_nine=findViewById<Button>(R.id.btn_nine)

        val btn_clear=findViewById<Button>(R.id.btn_clear)
        val btn_zero=findViewById<Button>(R.id.btn_zero)
        val btn_sharp=findViewById<Button>(R.id.btn_sharp)

        /*
        초기에 '번호를 입력하세요' 있음
        아무 번호나 눌렀을때 없어지고 번호로 시작해야함
        '-'는 안할꺼임
        번호는 거의 대부분 11자리임
         */

        /*
        1. 대부분 번호가 0으로 시작하므로 0이 눌리면 '번호를 입력하세요'가 없어지고 0이 입력돼야함
         */

        var customer_number=""
        //clear 를 누르면 번호가 다 삭제됨


        btn_clear.setOnClickListener{
            text_number.setText("")
        }

        btn_one.setOnClickListener{
            val index="1"
            customer_number+=index
            text_number.setText(customer_number)
        }

        btn_two.setOnClickListener{
            val index="2"
            customer_number+=index
            text_number.setText(customer_number)
        }

        btn_three.setOnClickListener{
            val index="3"
            customer_number+=index
            text_number.setText(customer_number)
        }

        btn_four.setOnClickListener{
            val index="4"
            customer_number+=index
            text_number.setText(customer_number)
        }

        btn_five.setOnClickListener{
            val index="5"
            customer_number+=index
            text_number.setText(customer_number)
        }

        btn_six.setOnClickListener{
            val index="6"
            customer_number+=index
            text_number.setText(customer_number)
        }

        btn_seven.setOnClickListener{
            val index="7"
            customer_number+=index
            text_number.setText(customer_number)
        }

        btn_eight.setOnClickListener{
            val index="8"
            customer_number+=index
            text_number.setText(customer_number)
        }

        btn_nine.setOnClickListener{
            val index="9"
            customer_number+=index
            text_number.setText(customer_number)
        }

        btn_zero.setOnClickListener{
            val index="0"
            customer_number+=index
            text_number.setText(customer_number)
        }

        orderFinishButton.setOnClickListener {
            finish()
        }

    }
}