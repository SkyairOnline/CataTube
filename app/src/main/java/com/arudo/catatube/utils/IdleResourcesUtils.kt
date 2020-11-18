package com.arudo.catatube.utils

import androidx.test.espresso.IdlingRegistry
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.OkHttpClient

object IdleResourcesUtils {
    fun registerIdlingResources(client: OkHttpClient) {
        IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("OkHttp", client))
    }
}