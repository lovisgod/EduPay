package com.edupay.edupay.ui.Adaptars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.edupay.edupay.R
import com.edupay.edupay.databinding.FeeItemBinding
import com.edupay.edupay.model.FeeItem
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fee_item.view.*

class FeeListAdapter: RecyclerView.Adapter<FeeListAdapter.Viewholder>()  {
   private  var feeList : ArrayList<FeeItem> = ArrayList()

    class Viewholder(itemView: FeeItemBinding): RecyclerView.ViewHolder(itemView.root) {
       val feeClass = itemView.feeClass
        val amount = itemView.feeAmount
        val category = itemView.feeCategory
        val openDetails = itemView.openFeeDetails
        val layout = itemView.feeCard
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
       val itemView : FeeItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.fee_item, parent, false)
        return  Viewholder(itemView)
    }

    override fun getItemCount(): Int {
        if (this.feeList.isNotEmpty()) {
            return feeList.size
        }

        return  0
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        var fee = feeList.get(position)
        holder.feeClass.text = fee.fee_class
        holder.amount.text = fee.amount
        holder.category.text = fee.category

        holder.layout.setOnClickListener {
//            Snackbar.make(holder.layout, "fee card clicked", Snackbar.LENGTH_LONG).show()
            val bundle = Bundle()
            bundle.putString("fee_total", fee.amount)
            bundle.putString("fee_title", fee.category)
            Navigation.findNavController(holder.layout).navigate(R.id.action_landingFragment_to_feeDetailsFragment, bundle)
        }
    }

    fun setDataList (list: ArrayList<FeeItem>) {
        this.feeList = list
        notifyDataSetChanged()
    }
}