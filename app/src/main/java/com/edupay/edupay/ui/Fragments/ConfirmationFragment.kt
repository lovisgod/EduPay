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
import com.edupay.edupay.databinding.FragmentConfirmationBinding
import com.edupay.edupay.viewmodel.BusinessViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 */
class ConfirmationFragment : Fragment() {
    private lateinit var binding: FragmentConfirmationBinding
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
        // Inflate the layout for this fragment
        navController = Navigation.findNavController(this.requireActivity(), R.id.app_nav_host_fragment)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_confirmation, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        binding.submitBtn.setOnClickListener {
            Snackbar.make(binding.pageTitle, "This will go the payment gateway page", Snackbar.LENGTH_LONG)
                .setBackgroundTint(this.requireContext().getColor(R.color.colorAccent))
                .show()
        }
        return binding.root
    }

}
