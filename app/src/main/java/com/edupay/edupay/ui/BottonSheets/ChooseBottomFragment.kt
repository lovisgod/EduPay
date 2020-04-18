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
import com.edupay.edupay.ui.Adaptars.PeriodListAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChooseBottomFragment(var layout: Int): BottomSheetDialogFragment() {
    private lateinit var adapter :PeriodListAdapter
    var periodList =ArrayList<Period>()

    companion object{
        fun newInstance( layout:Int): ChooseBottomFragment? {
            val bottomSheet = ChooseBottomFragment(layout = layout)
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
            adapter = PeriodListAdapter()
            val recy =view.findViewById<RecyclerView>(R.id.period_list)
            recy.layoutManager = LinearLayoutManager(this.requireContext(),LinearLayoutManager.VERTICAL, false)
            recy.adapter = adapter

            val period1 = Period(label = "First term")
            val period2 = Period(label = "Second term")
            val period3= Period(label = "Third term")

            periodList.add(period1)
            periodList.add(period2)
            periodList.add(period3)

            adapter.setDataList(periodList)
        }
        return  view
    }
}