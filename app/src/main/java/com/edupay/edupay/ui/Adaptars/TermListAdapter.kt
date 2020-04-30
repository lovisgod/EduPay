package com.edupay.edupay.ui.Adaptars

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.edupay.edupay.R
import com.edupay.edupay.databinding.ClassItemBinding
import com.edupay.edupay.databinding.TermItemBinding
import com.edupay.edupay.model.CloseDialog
import com.edupay.edupay.model.Period
import com.edupay.edupay.viewmodel.BusinessViewModel
import org.greenrobot.eventbus.EventBus

class TermListAdapter(var viewModel: BusinessViewModel): RecyclerView.Adapter<TermListAdapter.Viewholder>()  {
    private  var termList : ArrayList<Period> = ArrayList()

    class Viewholder(itemView: TermItemBinding): RecyclerView.ViewHolder(itemView.root) {
        val label = itemView.termLabel
        val layout = itemView.termLa
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val itemView : TermItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.term_item, parent, false)
        return  Viewholder(itemView)
    }

    override fun getItemCount(): Int {
        if (this.termList.isNotEmpty()) {
            return termList.size
        }

        return  0
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        var term = termList.get(position)
        holder.label.text = term.label
        holder.layout.setOnClickListener {
            viewModel.setTermText(term.label)
            EventBus.getDefault().post(CloseDialog(event = "close"))
        }
    }

    fun setDataList (list: ArrayList<Period>) {
        this.termList = list
        notifyDataSetChanged()
    }
}