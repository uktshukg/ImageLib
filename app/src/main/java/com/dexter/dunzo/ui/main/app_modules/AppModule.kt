package com.dexter.dunzo.ui.main.app_modules

import android.content.Context
import com.dexter.baseproject.scopes.AppScope
import com.dexter.dunzo.ui.main.App
import dagger.Module
import dagger.Provides

@Module
abstract class AppModule {
    @Module
  companion object{
        @JvmStatic
        @Provides
        @AppScope
        fun applicationContext(app: App): Context = app

    }
}