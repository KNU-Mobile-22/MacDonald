package kr.aifor.lyr.knu_finalproject

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.math.ceil
import kotlin.math.floor

var menu = MenuList()

class orderAdapter(var orderList: HashMap<Int, Int>, var firebaseData: HashMap<String, Menu>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data = firebaseData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.rec_orderlist, parent, false)

        return orderHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var keys = orderList.keys.toList()
        val code = keys[position]
        var num: Int = orderList.get(code)!!
        val order = holder as orderHolder

        val textListener = object : View.OnClickListener {
            //각 주문내역 별 클릭리스너
            override fun onClick(v: View?) {
                val curNum = order.orderNum.text.toString().toInt()
                when (v!!.id) {
                    //+버튼
                    order.plusButton.id -> {
                        num = curNum + 1
                        if (code < 1000) {
                            if (num > data.get(code.toString())!!.left) {
                                num--
                                Toast.makeText(v.context, "최대 주문 가능 수량입니다.", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        } else {
                            var burgerCode = code / 1000000
                            var sideCode = (code % 1000000) / 1000
                            var drinkCode = code % 1000

                            val burger = data.get(burgerCode.toString())!!
                            val side = data.get(sideCode.toString())!!
                            val drink = data.get(drinkCode.toString())!!
                            if (num > burger.left) {
                                num--
                                Toast.makeText(
                                    v.context,
                                    "${burger.name}의 수량이 최대 주문 가능 수량입니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (num > side.left) {
                                num--
                                Toast.makeText(
                                    v.context,
                                    "${side.name}의 수량이 최대 주문 가능 수량입니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (num > drink.left) {
                                num--
                                Toast.makeText(
                                    v.context,
                                    "${drink.name}의 수량이 최대 주문 가능 수량입니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        orderList.put(code, num)
                        order.orderNum.setText(num.toString())
                    }
                    //-버튼
                    order.minusButton.id -> {
                        if (curNum > 1) {
                            var num: Int = curNum - 1
                            orderList.put(code, num)
                            order.orderNum.setText(num.toString())
                        }
                    }
                    //x버튼
                    order.cancelButton.id -> {
                        orderList.remove(code)
                        notifyDataSetChanged()
                    }
                }
            }
        }

        var name: String
        var price: Int

        if (code > 1000) {
            var burgerCode = code / 1000000
            var sideCode = (code % 1000000) / 1000
            var drinkCode = code % 1000

            val burger = data.get(burgerCode.toString())!!
            val side = data.get(sideCode.toString())!!
            val drink = data.get(drinkCode.toString())!!

            name = burger.name + " 세트(" + side.name + ", " + drink.name + ")"
            var dbPrice = (burger.price + side.price + drink.price) * 0.8
            price = floor(dbPrice / 100).toInt() * 100
        } else {
            val curMenu = data.get(code.toString())!!
            name = curMenu.name
            price = curMenu.price
        }

        //주문 목록출력
        order.orderName.setText(name)
        order.orderNum.setText(num.toString())
        order.orderPrice.setText(price.toString() + "원")
        //클릭리스너
        order.plusButton.setOnClickListener(textListener)
        order.minusButton.setOnClickListener(textListener)
        order.cancelButton.setOnClickListener(textListener)
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
    }
}