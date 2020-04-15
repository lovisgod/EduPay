package com.edupay.edupay.ui.Adaptars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.edupay.edupay.R
import com.edupay.edupay.databinding.FeeHistoryItemBinding
import com.edupay.edupay.databinding.FeeItemBinding
import com.edupay.edupay.model.FeeItem
import com.edupay.edupay.model.FeeItemHistory

class FeeListHistoryAdapter: RecyclerView.Adapter<FeeListHistoryAdapter.Viewholder>()  {
    private  var feeList : ArrayList<FeeItemHistory> = ArrayList()

    class Viewholder(itemView: FeeHistoryItemBinding): RecyclerView.ViewHolder(itemView.root) {
        val feeClass = itemView.studentName
        val amount = itemView.feeAmount
        val category = itemView.feeCategory
        val date = itemView.dateText
        val layout = itemView.feeCard
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val itemView : FeeHistoryItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.fee_history_item, parent, false)
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
        holder.feeClass.text = fee.student_name
        holder.amount.text = fee.amount
        holder.category.text = fee.category
        holder.date.text = fee.payment_date

        holder.layout.setOnClickListener {

        }
    }

    fun setDataList (list: ArrayList<FeeItemHistory>) {
        this.feeList = list
        notifyDataSetChanged()
    }
}