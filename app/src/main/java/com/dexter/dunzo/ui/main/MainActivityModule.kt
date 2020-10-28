package com.dexter.dunzo.ui.main

import com.dexter.baseproject.scopes.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    /****************************************************************
     * Fragments
     ****************************************************************/
    @FragmentScope
    @ContributesAndroidInjector(modules = [MainFragModule::class])
    abstract fun fragOne(): MainFragment


}


