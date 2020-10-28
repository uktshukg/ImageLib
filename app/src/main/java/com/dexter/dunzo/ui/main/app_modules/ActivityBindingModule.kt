package com.dexter.dunzo.ui.main.app_modules

import com.dexter.baseproject.scopes.ActivityScope
import com.dexter.dunzo.ui.main.activities.main.MainActivity
import com.dexter.dunzo.ui.main.activities.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity
}