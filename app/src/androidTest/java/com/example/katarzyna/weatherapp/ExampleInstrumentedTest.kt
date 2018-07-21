package com.example.katarzyna.weatherapp

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented basic_weather_info, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under basic_weather_info.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.katarzyna.weatherapp", appContext.packageName)
    }
}
