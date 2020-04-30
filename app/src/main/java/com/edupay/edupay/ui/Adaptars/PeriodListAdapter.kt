package com.edupay.edupay.ui.Adaptars

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.edupay.edupay.R
import com.edupay.edupay.databinding.PeriodItemBinding
import com.edupay.edupay.model.CloseDialog
import com.edupay.edupay.model.Period
import com.edupay.edupay.viewmodel.BusinessViewModel
import org.greenrobot.eventbus.EventBus

class PeriodListAdapter(var viewModel: BusinessViewModel): RecyclerView.Adapter<PeriodListAdapter.Viewholder>()  {
    private  var periodList : ArrayList<Period> = ArrayList()

    class Viewholder(itemView: PeriodItemBinding): RecyclerView.ViewHolder(itemView.root) {
       val label = itemView.periodLabel
        val layout = itemView.period
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val itemView : PeriodItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.period_item, parent, false)
        return  Viewholder(itemView)
    }

    override fun getItemCount(): Int {
        if (this.periodList.isNotEmpty()) {
            return periodList.size
        }

        return  0
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        var period = periodList.get(position)
        holder.label.text = period.label
        holder.layout.setOnClickListener {
            viewModel.setPeriodText(period.label)
            EventBus.getDefault().post(CloseDialog(event = "close"))
        }
    }

    fun setDataList (list: ArrayList<Period>) {
        this.periodList = list
        notifyDataSetChanged()
    }
}