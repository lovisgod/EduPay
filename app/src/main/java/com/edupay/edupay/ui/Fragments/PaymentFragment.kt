package com.edupay.edupay.ui.Fragments

import android.R.attr
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.edupay.edupay.R
import com.flutterwave.raveandroid.RaveConstants
import com.flutterwave.raveandroid.RavePayActivity
import com.flutterwave.raveandroid.RavePayManager
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass.
 */
class PaymentFragment : Fragment() {
    private lateinit var f_name: String
    private lateinit var l_name: String
    private lateinit var parent_email: String
    private lateinit var desc: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var bundle = arguments
        var amount = 0.0
        bundle.let {
            amount = it!!.getDouble("amount", 0.0)
            f_name = it.getString("fname", "")
            l_name = it.getString("lname", "")
            parent_email = it.getString("parent_email", "")
            desc = it!!.getString("desc", "")
        }
        return FrameLayout(this.requireContext()).apply {
            pay(amount)
        }
    }

    fun pay (amount: Double) {
        RavePayManager(this).setAmount(amount)
            .setCountry("NG")
            .setCurrency("NGN")
            .setfName(f_name)
            .setlName(l_name)
            .setEmail(parent_email)
            .setTxRef(desc)
            .setNarration(desc)
            .acceptAccountPayments(true)
            .acceptCardPayments(true)
            .setPublicKey(this.requireContext().getString(R.string.rave_public_key))
            .setEncryptionKey(this.requireContext().getString(R.string.rave_encryption_key))
            .initialize()
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            println(data.dataString)
            val message: String = data.getStringExtra("response")!!
            if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                Snackbar.make(this.requireView(), "Payment successful", Snackbar.LENGTH_LONG)
                .setBackgroundTint(this.requireContext().getColor(R.color.colorAccent))
                .show()

                var r = Runnable {
                    Navigation.findNavController(this.requireActivity(), R.id.app_nav_host_fragment)
                        .navigate(R.id.action_paymentFragment_to_landingFragment)
                }
                Handler().postDelayed(r, 1000)

            } else if (resultCode == RavePayActivity.RESULT_ERROR) {
                Toast.makeText(this.requireContext(), "ERROR $message", Toast.LENGTH_SHORT).show()
            } else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                Toast.makeText(this.requireContext(), "CANCELLED $message", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}
