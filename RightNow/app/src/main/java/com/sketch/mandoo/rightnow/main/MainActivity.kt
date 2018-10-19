package com.sketch.mandoo.rightnow.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.sketch.mandoo.rightnow.R
import com.sketch.mandoo.rightnow.databinding.ActivityMainBinding
import com.sketch.mandoo.rightnow.main.key.*
import com.sketch.mandoo.rightnow.main.listener.Listener
import com.sketch.mandoo.rightnow.route.RouteActivity
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

        val mainViewModelFactory = MainViewModelFactory(selectBusEvent)

        mainListViewModel = ViewModelProviders.of(this, mainViewModelFactory).get(MainListViewModel::class.java)
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

    private val selectBusEvent = object : Listener.SelectBusListener {
        override fun searchItemBusSelectEvent(item: MainViewModel) {
            val bundle = Bundle()
            bundle.putString(BUS_ID, item.getBusId().value)
            bundle.putString(BUS_NUMBER, item.getBusNumber().value)
            bundle.putString(BUS_FIRST_TIME, item.getBusFirstTime().value)
            bundle.putString(BUS_LAST_TIME, item.getBusLastTime().value)
            bundle.putString(BUS_TERM, item.getBusTerm().value)
            bundle.putString(BUS_ROUTE_TYPE, item.getBusRouteType().value)
            val intent = Intent(this@MainActivity, RouteActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}
