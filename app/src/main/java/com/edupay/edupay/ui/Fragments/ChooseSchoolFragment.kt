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
import com.edupay.edupay.model.CloseDialog
import com.edupay.edupay.ui.BottonSheets.ChooseBottomFragment
import com.edupay.edupay.viewmodel.BusinessViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * A simple [Fragment] subclass.
 */
class ChooseSchoolFragment : Fragment() {
    private lateinit var binding: FragmentChooseSchoolBinding
    private lateinit var navController: NavController
    private lateinit var bottom: ChooseBottomFragment

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

        viewModel._termText.observe(viewLifecycleOwner, Observer {
            binding.inputLayoutTerm.text =it
        })

        binding.historyHeader.setOnClickListener {
            navController.navigate(R.id.action_chooseSchoolFragment_to_historyFragment)
        }

        binding.inputLayoutSchoolSession.setOnClickListener {
            println("it's getting here")
            bottom = ChooseBottomFragment.newInstance(R.layout.period_layout,viewModel)!!
            bottom.show(this.requireActivity().supportFragmentManager.beginTransaction(), "dialog_playback")
        }

        binding.inputLayoutClass.setOnClickListener {
            bottom = ChooseBottomFragment.newInstance(R.layout.class_layout,viewModel)!!
            bottom.show(this.requireActivity().supportFragmentManager.beginTransaction(), "dialog_playback")
        }

        binding.inputLayoutTerm.setOnClickListener {
            bottom = ChooseBottomFragment.newInstance(R.layout.term_layout,viewModel)!!
            bottom.show(this.requireActivity().supportFragmentManager.beginTransaction(), "dialog_playback")
        }

        binding.loginBtn.setOnClickListener {
            navController.navigate(R.id.action_chooseSchoolFragment_to_landingFragment)
        }

        return binding.root
    }


    /**
     * handle event listening
     */

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onCloseDialog(event: CloseDialog) {
        println("this is getting here ooo")
        bottom.dismiss()
    }


    override fun onStart() {
        super.onStart()
        // registers the event listener
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        // unregister the event listener
        EventBus.getDefault().unregister(this)
    }

}
