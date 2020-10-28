package com.dexter.dunzo.ui.main

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection

open class BaseFragment :Fragment(){

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}
