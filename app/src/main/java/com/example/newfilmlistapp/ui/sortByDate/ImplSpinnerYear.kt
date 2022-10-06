package com.example.newfilmlistapp.ui.sortByDate

import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener

class ImplSpinnerYear : OnItemSelectedListener {

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        returnPosition(p2)

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }


    fun returnPosition(position: Int): Int = position
}