package com.example.contacts.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class PageFragment constructor() : Fragment() {
    private var layoutId: Int = 0

    constructor(@LayoutRes layoutRes: Int) : this() {
        layoutId = layoutRes
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(layoutId, container, false)
    }
}