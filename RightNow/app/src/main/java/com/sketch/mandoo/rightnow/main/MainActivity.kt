package com.sketch.mandoo.rightnow.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.sketch.mandoo.rightnow.R
import com.sketch.mandoo.rightnow.databinding.ActivityMainBinding
import com.sketch.mandoo.rightnow.utils.log

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainListViewModel: MainListViewModel

    private var errorSnackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.rcvSearch.let {
            it.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            it.setHasFixedSize(true)
        }

        mainListViewModel = ViewModelProviders.of(this).get(MainListViewModel::class.java)
        mainListViewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        binding.imgSearch.setOnClickListener {
            search(it)
        }

        binding.viewModel = mainListViewModel

    }

    private fun showError(@StringRes errorMessage: String) {
        errorSnackBar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackBar?.setAction(R.string.retry, mainListViewModel.errorClickListener)
        errorSnackBar?.show()
        log(errorMessage)
    }

    private fun hideError() {
        errorSnackBar?.dismiss()
    }

    private fun search(it: View) {
        mainListViewModel.setBusNumber(binding.edtSearch.text.toString())
        mainListViewModel.searchClickListener.onClick(it)
    }
}
