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
import com.edupay.edupay.databinding.FragmentLandingBinding
import com.edupay.edupay.model.FeeItem
import com.edupay.edupay.ui.Adaptars.FeeListAdapter
import com.edupay.edupay.viewmodel.AuthViewModel
import com.edupay.edupay.viewmodel.BusinessViewModel

/**
 * A simple [Fragment] subclass.
 */
class LandingFragment : Fragment() {
    private lateinit var binding: FragmentLandingBinding
    private lateinit var navController: NavController
    private var adapter = FeeListAdapter()
    val fee_list: ArrayList<FeeItem> = ArrayList()

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
        navController = Navigation.findNavController(this.requireActivity(), R.id.app_nav_host_fragment)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_landing, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        val layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL,false)
        binding.feeLists.layoutManager = layoutManager
        binding.feeLists.adapter = adapter

        val sampleFee1 = FeeItem(fee_class = "JSS1", amount = "N40,000", category = "Uniform Fee")
        val sampleFee2 = FeeItem(fee_class = "JSS1", amount = "N100,000", category = "Tution Fee")
        val sampleFee3 = FeeItem(fee_class = "JSS1", amount = "N10,000", category = "FoodFee")
        val sampleFee4 = FeeItem(fee_class = "JSS1", amount = "N20,000", category = "Phone Fee")
        val sampleFee5 = FeeItem(fee_class = "JSS1", amount = "N40,000", category = "Uniform Fee")
        val sampleFee6 = FeeItem(fee_class = "JSS1", amount = "N40,000", category = "Uniform Fee")
        val sampleFee7 = FeeItem(fee_class = "JSS1", amount = "N40,000", category = "Uniform Fee")

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
