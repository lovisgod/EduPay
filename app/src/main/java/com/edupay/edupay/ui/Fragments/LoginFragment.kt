package com.edupay.edupay.ui.Fragments

import android.content.Intent
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
import com.edupay.edupay.databinding.FragmentLoginBinding
import com.edupay.edupay.ui.Activity.LandingActivity
import com.edupay.edupay.viewmodel.AuthViewModel

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding
    private lateinit var navController: NavController

    private val viewModel: AuthViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, AuthViewModel.Factory(activity.application))
            .get(AuthViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        navController = Navigation.findNavController(this.requireActivity(), R.id.auth_nav_host_fragment)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        binding.loginBtn.setOnClickListener {
            startActivity(Intent(this.requireActivity(), LandingActivity::class.java))
        }
        binding.signup.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        return binding.root
    }

}
