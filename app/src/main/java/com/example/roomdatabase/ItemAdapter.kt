package com.example.roomdatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.databinding.ItemRowBinding

class ItemAdapter(private val item:ArrayList<EmployEntity>
//,
                  //private val updateListener:(id:Int)->Unit,
                  //private val deleteListener:(id:Int)->Unit
): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    class ViewHolder (binding: ItemRowBinding):RecyclerView.ViewHolder(binding.root){
         val llMain=binding.llMain
        val tvName=binding.tvNameRecycler
        val tvEmail=binding.tvEmail
        val ivEdit=binding.ivEdit
        val ivDelete=binding.ivDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context= holder.itemView.context
        val item=item[position]
        holder.tvName.text=item.name
        holder.tvEmail.text=item.email
        if(position%2==0)
            holder.llMain.setBackgroundColor(ContextCompat.getColor(context,R.color.lightgray))
        else
        {
            holder.llMain.setBackgroundColor(ContextCompat.getColor(context,R.color.white))
        }
        //holder.ivEdit.setOnClickListener {
            //updateListener.invoke(item.id)
       // }
        //holder.ivEdit.setOnClickListener {
            //deleteListener.invoke(item.id)
       // }
    }
}