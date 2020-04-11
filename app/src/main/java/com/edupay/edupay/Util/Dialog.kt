package com.edupay.edupay.Util

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.edupay.edupay.model.CloseDialog
import org.greenrobot.eventbus.EventBus

class Dialog {
    fun displayContactDialog(context: Context, layout: Int): AlertDialog? {
        val builder = AlertDialog.Builder(context)
        var inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val mView: View = inflater.inflate(layout, null )
        builder.setView(mView)
        val alertDialog = builder.create()
        return alertDialog
    }

    fun cancel_dailog() {
        // trigger close events
        EventBus.getDefault().post(CloseDialog(event = "close"))
    }
}