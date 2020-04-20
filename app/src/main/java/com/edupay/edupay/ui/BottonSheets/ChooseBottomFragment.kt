package com.edupay.edupay.ui.BottonSheets

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edupay.edupay.R
import com.edupay.edupay.model.Period
import com.edupay.edupay.ui.Adaptars.ClassListAdapter
import com.edupay.edupay.ui.Adaptars.PeriodListAdapter
import com.edupay.edupay.viewmodel.BusinessViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChooseBottomFragment(var layout: Int, var viewmodel: BusinessViewModel): BottomSheetDialogFragment() {
    private lateinit var adapter :PeriodListAdapter
    private lateinit var adapterC: ClassListAdapter
    var periodList = ArrayList<Period>()

    companion object{
        fun newInstance( layout:Int, viewmodel: BusinessViewModel): ChooseBottomFragment? {
            val bottomSheet = ChooseBottomFragment(layout = layout, viewmodel = viewmodel)
            return bottomSheet
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(layout, container, false)
        if (layout == R.layout.period_layout) {
            adapter = PeriodListAdapter(viewmodel)
            val recy =view.findViewById<RecyclerView>(R.id.period_list)
            recy.layoutManager = LinearLayoutManager(this.requireContext(),LinearLayoutManager.VERTICAL, false)
            recy.adapter = adapter

            val period1 = Period(label = "Second term")
            val period2 = Period(label = "First term")
            val period3= Period(label = "Third term")

            periodList.add(period1)
            periodList.add(period2)
            periodList.add(period3)

            adapter.setDataList(periodList)
        }

        if (layout ==R.layout.class_layout) {
            adapterC = ClassListAdapter(viewmodel)
            val recy =view.findViewById<RecyclerView>(R.id.class_list)
            recy.layoutManager = LinearLayoutManager(this.requireContext(),LinearLayoutManager.VERTICAL, false)
            recy.adapter = adapterC

            val jss1 = Period(label = "Jss 1")
            val jss2 = Period(label = "Jss 2")
            val jss3= Period(label = "Jss 3")

            periodList.add(jss1)
            periodList.add(jss2)
            periodList.add(jss3)

            adapterC.setDataList(periodList)
        }
        return  view
    }
}