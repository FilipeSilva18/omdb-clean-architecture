package br.com.filipe.omdb

import android.app.Application
import br.com.filipe.omdb.di.appModule
import br.com.filipe.omdb.di.dataModule
import br.com.filipe.omdb.di.domainModule
import br.com.filipe.omdb.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieApp)
            androidFileProperties()
            modules(
                listOf(
                    appModule,
                    dataModule,
                    domainModule,
                    presentationModule
                )
            )
        }
    }
}