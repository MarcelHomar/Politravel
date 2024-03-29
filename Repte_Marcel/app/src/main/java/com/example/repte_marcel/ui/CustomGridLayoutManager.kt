package com.example.repte_marcel.ui

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager


class CustomGridLayoutManager(context: Context?) : LinearLayoutManager(context) {
    private var isScrollEnabled = true

    fun setScrollEnabled(flag: Boolean) {
        isScrollEnabled = flag
    }

    override fun canScrollVertically(): Boolean {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically()
    }
}