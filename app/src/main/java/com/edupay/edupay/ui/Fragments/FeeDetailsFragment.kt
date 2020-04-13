package com.edupay.edupay.ui.Fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.edupay.edupay.R
import com.edupay.edupay.databinding.FragmentFeeDetailsBinding
import com.edupay.edupay.model.FeeItem
import com.edupay.edupay.ui.Adaptars.FeeListAdapter
import com.edupay.edupay.viewmodel.BusinessViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 */
class FeeDetailsFragment : Fragment() {
    private lateinit var binding: FragmentFeeDetailsBinding
    private lateinit var navController: NavController

    private val viewModel: BusinessViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, BusinessViewModel.Factory(activity.application))
            .get(BusinessViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = arguments
        // Inflate the layout for this fragment
        navController = Navigation.findNavController(this.requireActivity(), R.id.app_nav_host_fragment)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fee_details, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        arguments?.let {
            binding.totalAmount.text = it.getString("fee_total", "")
            binding.feeTitle.text = it.getString("fee_title", "")
        }

        binding.submitBtn.setOnClickListener {
           navController.navigate(R.id.action_feeDetailsFragment_to_confirmationFragment)
        }
        return binding.root
    }

}
