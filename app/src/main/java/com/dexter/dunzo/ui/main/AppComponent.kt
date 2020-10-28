package com.dexter.dunzo.ui.main

import com.dexter.dunzo.ui.main.app_modules.ActivityBindingModule
import com.dexter.dunzo.ui.main.app_modules.AppModule
import com.dexter.dunzo.ui.main.app_modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [ActivityBindingModule::class,
    AndroidInjectionModule::class,
    NetworkModule::class,
    AppModule::class,
    AndroidSupportInjectionModule::class])
interface AppComponent {

    fun inject(application: App)


    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun applicationBind(application: App): Builder



    }
}