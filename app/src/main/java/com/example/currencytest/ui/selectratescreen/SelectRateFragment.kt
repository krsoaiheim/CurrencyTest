package com.example.currencytest.ui.selectratescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencytest.App
import com.example.currencytest.MainActivity
import com.example.currencytest.R
import com.example.currencytest.db.ConvertCurrency
import kotlinx.android.synthetic.main.fragment_select.*
import javax.inject.Inject

class SelectRateFragment : Fragment() {
    @Inject
    lateinit var factory: SelectRateViewModelFactory
    lateinit var viewModel: SelectRateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_select, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as App).appComponent.inject(this)
        viewModel = factory.create(SelectRateViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter =
            CurrencyAdapter(object :
                ListCallback {
                override fun onItemClicked(currency: ConvertCurrency) {
                    viewModel.onItemSelected(currency)
                }
            })
        allCurrenciesList.adapter = adapter
        allCurrenciesList.layoutManager = LinearLayoutManager(context)
        viewModel.listData.observe(viewLifecycleOwner, Observer { loadList(it) })
        viewModel.stepFinishEvent.observe(viewLifecycleOwner, Observer {
            when (it) {
                is FirstCurrencySucceedState -> {
                    search.text = null
                    pairText.isVisible = true
                    pairText.text = "${it.baseCurrency.code} -> "
                }
                is SecondCurrencySucceedState -> {
                    pairText.text = pairText.text.toString() + it.secondCurrency.code
                    (activity as MainActivity).openConverter()

                }
                is AddErrorState -> {
                    search.text = null
                    pairText.text = null
                    pairText.isVisible = false
                    Toast.makeText(activity, getString(R.string.error), Toast.LENGTH_SHORT).show()
                }
                null -> {
                }
            }
        })
        search.addTextChangedListener { viewModel.onSearchEdited(it.toString()) }
    }

    private fun loadList(list: List<ConvertCurrency>) {
        (allCurrenciesList.adapter as CurrencyAdapter).setList(list)
    }


}