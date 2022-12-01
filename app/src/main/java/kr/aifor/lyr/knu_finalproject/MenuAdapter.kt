package kr.aifor.lyr.knu_finalproject

import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(var MenuList: Array<Menu>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemCLickListener{
        fun onItemClick(v:View, data:Menu, position: Int)
    }
    private var listener : OnItemCLickListener? = null
    fun setOnItemClickListener(listener:OnItemCLickListener){
        this.listener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_menu, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val Menu = MenuList[position]
        val MenuViewHolder = holder as MyViewHolder
        MenuViewHolder.Menu_name.text = Menu.name
        MenuViewHolder.Menu_price.text = Menu.price.toString() + "원"
        MenuViewHolder.Menu_IMG.setImageResource(Menu.image_uri)
        holder.bind(Menu)

    }

    override fun getItemCount(): Int {
        return MenuList.size
    }
    inner class MyViewHolder(val layout: View) : RecyclerView.ViewHolder(layout) {
        val Menu_name: TextView = layout.findViewById(R.id.rec_menu_name)
        val Menu_IMG: ImageView = layout.findViewById(R.id.rec_menu_img)
        val Menu_price:TextView = layout.findViewById(R.id.rec_menu_price)
        fun bind(item:Menu){
            val name = item.name
            val price = item.price
            val code = item.code

            itemView.setOnClickListener {
                listener?.onItemClick(itemView, item, position)
                Log.d("Gen", "OnClickListener")
                Log.d("Gen","InAdapter : ${name}, ${price}")
            }
        }



    }
}
