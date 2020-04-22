package com.edupay.edupay.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.edupay.edupay.R
import com.edupay.edupay.databinding.FragmentChooseSchoolBinding
import com.edupay.edupay.ui.BottonSheets.ChooseBottomFragment
import com.edupay.edupay.viewmodel.BusinessViewModel

/**
 * A simple [Fragment] subclass.
 */
class ChooseSchoolFragment : Fragment() {
    private lateinit var binding: FragmentChooseSchoolBinding
    private lateinit var navController: NavController

    private val viewModel: BusinessViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(activity, BusinessViewModel.Factory(activity.application))
            .get(BusinessViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        navController = Navigation.findNavController(this.requireActivity(), R.id.app_nav_host_fragment)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_choose_school, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel


        viewModel._classText.observe(viewLifecycleOwner, Observer {
            binding.inputLayoutClass.text = it
        })

        viewModel._periodText.observe(viewLifecycleOwner, Observer {
            binding.inputLayoutSchoolSession.text =it
        })

        binding.historyHeader.setOnClickListener {
            navController.navigate(R.id.action_chooseSchoolFragment_to_historyFragment)
        }

        binding.inputLayoutSchoolSession.setOnClickListener {
            println("it's getting here")
            val bottom = ChooseBottomFragment.newInstance(R.layout.period_layout,viewModel)
            bottom?.show(this.requireActivity().supportFragmentManager.beginTransaction(), "dialog_playback")
        }

        binding.inputLayoutClass.setOnClickListener {
            val bottom = ChooseBottomFragment.newInstance(R.layout.class_layout,viewModel)
            bottom?.show(this.requireActivity().supportFragmentManager.beginTransaction(), "dialog_playback")
        }

        binding.loginBtn.setOnClickListener {
            navController.navigate(R.id.action_chooseSchoolFragment_to_landingFragment)
        }

        return binding.root
    }

}
