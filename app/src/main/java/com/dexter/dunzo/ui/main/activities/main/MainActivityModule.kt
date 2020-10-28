package com.dexter.dunzo.ui.main.activities.main

import com.dexter.baseproject.scopes.FragmentScope
import com.dexter.dunzo.ui.main.fragments.main.MainFragModule
import com.dexter.dunzo.ui.main.fragments.main.MainFragment
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


