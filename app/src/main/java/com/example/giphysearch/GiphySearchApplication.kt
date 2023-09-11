package com.example.giphysearch

import android.app.Application
import com.example.giphysearch.data.application_container.ApplicationContainer
import com.example.giphysearch.data.application_container.ApplicationContainerDefaultImpl

class GiphySearchApplication : Application() {

    lateinit var container: ApplicationContainer

    override fun onCreate() {
        super.onCreate()
        container = ApplicationContainerDefaultImpl()
        //container = ApplicationContainerFakeImpl()
    }

}
