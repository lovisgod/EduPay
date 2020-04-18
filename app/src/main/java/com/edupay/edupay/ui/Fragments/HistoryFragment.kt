package com.edupay.edupay.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.edupay.edupay.R
import com.edupay.edupay.databinding.FragmentHistoryBinding
import com.edupay.edupay.model.FeeItem
import com.edupay.edupay.model.FeeItemHistory
import com.edupay.edupay.ui.Adaptars.FeeListHistoryAdapter
import com.edupay.edupay.viewmodel.BusinessViewModel

/**
 * A simple [Fragment] subclass.
 */
class HistoryFragment : Fragment() {
    private val adapter = FeeListHistoryAdapter()
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var navController: NavController
    val fee_list: ArrayList<FeeItemHistory> = ArrayList()

    private val viewModel: BusinessViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, BusinessViewModel.Factory(activity.application))
            .get(BusinessViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        navController = Navigation.findNavController(this.requireActivity(),R.id.app_nav_host_fragment)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_history, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        val layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL,false)
        binding.feeLists.layoutManager = layoutManager
        binding.feeLists.adapter = adapter

        binding.payFeeBtn.setOnClickListener {
            navController.navigate(R.id.action_historyFragment_to_chooseSchoolFragment)
        }

        val sampleFee1 = FeeItemHistory(student_name = "Sam sam", amount = "N40,000", category = "Uniform Fee", payment_date = "14TH Jan")
        val sampleFee2 = FeeItemHistory(student_name = "Sam sam", amount = "N100,000", category = "Tution Fee", payment_date = "14TH Jan")
        val sampleFee3 = FeeItemHistory(student_name = "Sam sam", amount = "N10,000", category = "FoodFee", payment_date = "14TH Jan")
        val sampleFee4 = FeeItemHistory(student_name = "Sam sam", amount = "N20,000", category = "Phone Fee", payment_date = "14TH Jan")
        val sampleFee5 = FeeItemHistory(student_name = "Sam sam", amount = "N40,000", category = "Uniform Fee", payment_date = "14TH Jan")
        val sampleFee6 = FeeItemHistory(student_name = "Sam sam", amount = "N40,000", category = "Uniform Fee", payment_date = "14TH Jan")
        val sampleFee7 = FeeItemHistory(student_name = "Sam sam", amount = "N40,000", category = "Uniform Fee", payment_date = "14TH Jan")

        fee_list.add(sampleFee1)
        fee_list.add(sampleFee2)
        fee_list.add(sampleFee3)
        fee_list.add(sampleFee4)
        fee_list.add(sampleFee5)
        fee_list.add(sampleFee6)
        fee_list.add(sampleFee7)

        adapter.setDataList(fee_list)

        return binding.root
    }

}
