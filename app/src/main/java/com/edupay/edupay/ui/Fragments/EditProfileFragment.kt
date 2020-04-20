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

import com.edupay.edupay.R
import com.edupay.edupay.databinding.FragmentEditProfileBinding
import com.edupay.edupay.viewmodel.BusinessViewModel

/**
 * A simple [Fragment] subclass.
 */
class EditProfileFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentEditProfileBinding
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_profile, container,false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        binding.saveBtn.setOnClickListener {
            // save the profile of the user and send it
            navController.navigate(R.id.action_editProfileFragment_to_historyFragment)
        }

        return binding.root
    }

}
