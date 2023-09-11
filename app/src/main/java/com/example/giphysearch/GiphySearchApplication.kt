package com.example.giphysearch

import android.app.Application
import android.os.Build
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.giphysearch.application_container.ApplicationContainer
import com.example.giphysearch.application_container.ApplicationContainerNetworkImpl

class GiphySearchApplication : Application(), ImageLoaderFactory {

    lateinit var container: ApplicationContainer

    override fun onCreate() {
        super.onCreate()
        container = ApplicationContainerNetworkImpl()
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                add(if (Build.VERSION.SDK_INT >= 28) ImageDecoderDecoder.Factory() else GifDecoder.Factory())
            }
            .build()
    }

}
