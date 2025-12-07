package com.example.jetpackcomposeui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//  dragger component generate
// This annotation will generate a base class that the annotated class should extend, either directly or via the Hilt Gradle Plugin.
@HiltAndroidApp
class MyApp : Application()