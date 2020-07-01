package com.example.currencytest.ui.converterscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencytest.App
import com.example.currencytest.MainActivity
import com.example.currencytest.R
import com.example.currencytest.ui.converterscreen.list.*
import kotlinx.android.synthetic.main.converter_fragment.*
import javax.inject.Inject

class ConverterFragment : Fragment() {
    @Inject
    lateinit var factory: ConverterViewModelFactory
    lateinit var viewModel: ConverterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as App).appComponent.inject(this)
        viewModel = factory.create(ConverterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.converter_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val onAddClickListener = View.OnClickListener { (activity as MainActivity).addNewPair() }

        val adapter = CompositeAdapter.Builder()
            .add(ConverterDelegateAdapter())
            .add(
                AddButtonDelegateAdapter(
                    onAddClickListener
                )
            )
            .build()
        val removeCallback = object :
            RemoveCallback {
            override fun removeItem(position: Int) {
                viewModel.onItemRemoved((adapter.getAdapterItem(position) as? RateItem)?.content())
            }
        }
        val swipeHelper =
            SwipeCallback(
                removeCallback
            )
        ItemTouchHelper(swipeHelper).attachToRecyclerView(pairsList)
        pairsList.adapter = adapter
        pairsList.layoutManager = LinearLayoutManager(context)
        viewModel.errorString.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, getString(it), Toast.LENGTH_SHORT).show()
        })
        viewModel.listData.observe(viewLifecycleOwner, Observer {
            val modelList = mutableListOf<DelegateAdapterItem>(
                AddButtonItem()
            )
            modelList.addAll(it.map {
                RateItem(
                    it
                )
            })
            adapter.submitList(modelList)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        pairsList.adapter = null
    }
}