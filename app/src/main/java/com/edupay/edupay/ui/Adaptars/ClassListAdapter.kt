package com.edupay.edupay.ui.Adaptars

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.edupay.edupay.R
import com.edupay.edupay.databinding.ClassItemBinding
import com.edupay.edupay.databinding.PeriodItemBinding
import com.edupay.edupay.model.CloseDialog
import com.edupay.edupay.model.Period
import com.edupay.edupay.viewmodel.BusinessViewModel
import org.greenrobot.eventbus.EventBus

class ClassListAdapter(var viewModel: BusinessViewModel): RecyclerView.Adapter<ClassListAdapter.Viewholder>()  {
    private  var classList : ArrayList<Period> = ArrayList()

    class Viewholder(itemView: ClassItemBinding): RecyclerView.ViewHolder(itemView.root) {
        val label = itemView.classLabel
        val layout = itemView.classLa
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val itemView : ClassItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.class_item, parent, false)
        return  Viewholder(itemView)
    }

    override fun getItemCount(): Int {
        if (this.classList.isNotEmpty()) {
            return classList.size
        }

        return  0
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        var class_ = classList.get(position)
        holder.label.text = class_.label
        holder.layout.setOnClickListener {
            viewModel.setClassText(class_.label)
            EventBus.getDefault().post(CloseDialog(event = "close"))
        }
    }

    fun setDataList (list: ArrayList<Period>) {
        this.classList = list
        notifyDataSetChanged()
    }
}