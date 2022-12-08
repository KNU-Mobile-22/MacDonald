package kr.aifor.lyr.knu_finalproject

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

/**
 *Task List
 * 1. 코루틴 사용해서 database 받아오는거 구현하기
 * 2. 개 미친 java.lang.Long cannot be cast to java.lang.String 해결하기
 *      -> 가격 구현하기
 */
var menu = MenuList()

class orderAdapter(var orderList: HashMap<Int, Int>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var database: DatabaseReference
    var data = HashMap<String, HashMap<String, String>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.rec_orderlist, parent, false)
        database = Firebase.database.reference
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                data = snapshot.child("").getValue() as HashMap<String, HashMap<String, String>>
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
        return orderHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var keys = orderList.keys.toList()
        val code = keys[position]
        val num = orderList.get(code)
        val order = holder as orderHolder

        /**
         * 요수정사항 : 가격 변경
         * 각 메뉴의 가격을 firebase로 가져와서 수정하거나, 가격 자체를 param으로 받아와야 함
         * 1. firebase로 가져오는 경우, 데이터의 이동이 더 적어 간편하지만 세트메뉴의 가격들을 직접 계산해주어야 하는 불편함이 있다.
         * 2. param으로 가져오는 경우, 데이터의 이동이 더 많아진다. 하지만, 코드를 짜기에 용이함
         *  애초에 onBindViewHolder 함수는 int를 파라미터로 받으니 1번 방법을 채택하는 것이 맞을 듯 싶다.
         *
         * 요수정사항 : + 버튼
         * 재고 량을 초과할 수 없도록 설정
         * firebase에서 재고량을 받아와서 재고량 이상의 수량으로 + 입력시 Toast로 경고 메시지 출력
         */
        val textListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                val curNum = order.orderNum.text.toString().toInt()
                when (v!!.id) {
                    order.plusButton.id -> {
                        var num: Int = curNum + 1
                        orderList.put(code, num)
                        order.orderNum.setText(num.toString())
                    }
                    order.minusButton.id -> {
                        if (curNum > 1) {
                            var num: Int = curNum - 1
                            orderList.put(code, num)
                            order.orderNum.setText(num.toString())
                        }
                    }
                    order.cancelButton.id -> {
                        orderList.remove(code)
                        notifyDataSetChanged()
                    }
                }
            }
        }

        var name: String
        var price: Int
        if (data.get(code.toString()) == null) {
            name = "null"
            price = -1
            Log.d("Gen", "num : " + num!!.javaClass.name)
            Log.d("Gen", "code : ${code.javaClass.name}")
        } else {
            name = data.get(code.toString())!!.get("name")!!
            Log.d("Gen", "num : " + num!!.javaClass.name)
            Log.d("Gen", "code : ${data.get(code.toString())!!.get("price")!!.javaClass.name}")
            //Log.d("Gen", "Type ? : ${data.get(code.toString())!!.get("price")!!.toInt().javaClass.name}")
            price = 0
            //var temp : Long = data.get(code.toString())!!.get("price")!!.toLong()
            //Log.d("Gen", "string = ${temp!!.javaClass.name}")
        }
        order.orderName.setText(name)
        order.orderNum.setText(num.toString())
        order.plusButton.setOnClickListener(textListener)
        order.minusButton.setOnClickListener(textListener)
        order.cancelButton.setOnClickListener(textListener)
        order.orderPrice.setText(price.toString())
    }

    override fun getItemCount(): Int {
        return orderList.size
    }


    inner class orderHolder(val layout: View) : RecyclerView.ViewHolder(layout) {
        val orderName: TextView = layout.findViewById(R.id.orderName)
        val orderNum: TextView = layout.findViewById(R.id.orderNum)
        val orderPrice: TextView = layout.findViewById(R.id.orderPrice)
        val plusButton: TextView = layout.findViewById(R.id.plus)
        val minusButton: TextView = layout.findViewById(R.id.minus)
        val cancelButton: TextView = layout.findViewById(R.id.cancel)


        fun bind(item: Menu) {
            val name = item.name
            val price = item.price * orderNum.text.toString().toInt()
            val code = item.code

            itemView.setOnClickListener {
                Log.d("Gen", "OnClickListener")
                Log.d("Gen", "InAdapter : ${name}, ${price}")
            }
        }
    }
}