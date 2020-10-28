package com.dexter.dunzo.ui.main

import com.dexter.baseproject.scopes.ActivityScope
import com.dexter.dunzo.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity
}