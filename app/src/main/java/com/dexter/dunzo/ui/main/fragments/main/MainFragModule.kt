package com.dexter.dunzo.ui.main.fragments.main

import androidx.lifecycle.ViewModel
import com.dexter.baseproject.scopes.FragmentScope
import com.dexter.dunzo.ui.main.api.ApiClientImpl
import com.dexter.dunzo.ui.main.api.IApiClient
import com.dexter.dunzo.ui.main.utilities.createPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Provider

@Module
abstract class MainFragModule {

    @Binds
    abstract fun server(server: ApiClientImpl): IApiClient
    @Module
    companion object {

        @Provides
        @JvmStatic
        fun presenter(
            fragment: MainFragment,
            presenterProvider: Provider<MainViewModel>
        ): ViewModel = fragment.createPresenter(presenterProvider)

        @JvmStatic
        @Provides
        @FragmentScope
        fun compositeDisposable(): CompositeDisposable = CompositeDisposable()
    }
}
