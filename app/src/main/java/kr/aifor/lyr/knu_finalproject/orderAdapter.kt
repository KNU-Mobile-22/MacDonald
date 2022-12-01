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

class orderAdapter(var orderList: HashMap<String, Int>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var inflater: LayoutInflater
    var keys = orderList.keys.toList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_menu, parent, false)
        return orderHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val orderName = keys[position]
        val orderNum = orderList.get(orderName)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }


    inner class orderHolder(val layout: View) : RecyclerView.ViewHolder(layout) {
    }
    /*public MainAdapter(Context context, <HashMap<String,String> modelList) {
        this.inflater = LayoutInflater.from(context);
        this.modelList = modelList;
    }



    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        //holder.bindData();
        holder.mainText.setText(modelList.get(position)); // value for the given key
    }


    class MainViewHolder extends RecyclerView.ViewHolder {

        TextView mainText, subText;

        public MainViewHolder(View itemView) {
            super(itemView);
            mainText = (TextView) itemView.findViewById(R.id.mainText);
            subText = (TextView) itemView.findViewById(R.id.subText);
        }*/


}