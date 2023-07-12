package com.example.fitpeoassignment.common.base

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fitpeoassignment.MainActivity
import com.example.fitpeoassignment.R


public abstract class HomeBaseFragment: Fragment() {

    lateinit var progressDialog: ProgressDialog
    lateinit var mActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as MainActivity
        progressDialog = ProgressDialog(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    open fun showDialog() {
        progressDialog.show()
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.setContentView(R.layout.progress_dialog)
        progressDialog.setIndeterminate(true)
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
    }

    open fun hideDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss()
            }
        }
    }

    fun showToast(message: Int){
        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show()
    }

}