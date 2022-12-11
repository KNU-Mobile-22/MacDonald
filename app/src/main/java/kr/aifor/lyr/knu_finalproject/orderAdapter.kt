package kr.aifor.lyr.knu_finalproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
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
        var num = orderList.get(code)!!
        val order = holder as orderHolder

        //code길이에 따라 필요한 변수 가져오기
        var orderMenu : Menu = Menu("", 0, 0, 0, 0)
        var burger : Menu = Menu("", 0, 0, 0, 0)
        var side : Menu = Menu("", 0, 0, 0, 0)
        var drink : Menu = Menu("", 0, 0, 0, 0)
        if(code < 1000)
            orderMenu = data.get(code.toString())!!
        else{
            var burgerCode = code / 1000000
            var sideCode = (code % 1000000) / 1000
            var drinkCode = code % 1000

            burger = data.get(burgerCode.toString())!!
            side = data.get(sideCode.toString())!!
            drink = data.get(drinkCode.toString())!!
        }


        //각 주문내역 별 클릭리스너
        val textListener = object : View.OnClickListener {
            override fun onClick(v: View?) {

                when (v!!.id) {
                    //+버튼
                    order.plusButton.id -> {
                        num++
                        if (code < 1000) {//단품
                            if (orderMenu.left <= 0) {
                                num--
                                Toast.makeText(v.context, "최대 주문 가능 수량입니다.", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            else {
                                orderMenu.left--
                                data.put(code.toString(), orderMenu)
                            }
                        } else {//세트
                            if (burger.left <= 0) {
                                num--
                                Toast.makeText(
                                    v.context,
                                    "${burger.name}의 수량이 최대 주문 가능 수량입니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (side.left <= 0) {
                                num--
                                Toast.makeText(
                                    v.context,
                                    "${side.name}의 수량이 최대 주문 가능 수량입니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (drink.left<= 0) {
                                num--
                                Toast.makeText(
                                    v.context,
                                    "${drink.name}의 수량이 최대 주문 가능 수량입니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }else{
                                burger.left--
                                side.left--
                                drink.left--
                                putSetData(burger, side, drink)
                            }
                        }

                        orderList.put(code, num)
                        order.orderNum.setText(num.toString())
                    }
                    //-버튼
                    order.minusButton.id -> {
                        if (num > 1) {
                            if(code < 1000){
                                orderMenu.left++
                                data.put(orderMenu.code.toString(), orderMenu)
                            }
                            else{
                                burger.left++
                                side.left++
                                drink.left++
                                putSetData(burger, side, drink)
                            }
                            num--
                            orderList.put(code, num)
                            order.orderNum.setText(num.toString())
                        }
                    }
                    //x버튼
                    order.cancelButton.id -> {
                        if(code<1000){
                            orderMenu.left += num
                            data.put(code.toString(), orderMenu)
                        }
                        else{
                            burger.left += num
                            side.left += num
                            drink.left += num

                            putSetData(burger, side, drink)
                        }
                        orderList.remove(code)
                        notifyDataSetChanged()
                    }
                }
            }

            private fun putSetData(burger: Menu,side: Menu,drink: Menu) {
                data.put(burger.code.toString(), burger)
                data.put(side.code.toString(), side)
                data.put(drink.code.toString(), drink)
            }
        }

        var name: String
        var price: Int

        if (code > 1000) {
            name = burger.name + " 세트(" + side.name + ", " + drink.name + ")"
            var dbPrice = (burger.price + side.price + drink.price) * 0.8
            price = floor(dbPrice / 100).toInt() * 100
        } else {
            name = orderMenu.name
            price = orderMenu.price
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