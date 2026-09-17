package com.clogu.lemonade

import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_5
import app.cash.paparazzi.Paparazzi
import com.clogu.lemonade.ui.theme.LemonadeTheme
import org.junit.Rule
import org.junit.Test

class LemonadeScreenshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = PIXEL_5,
        theme = "android:Theme.Material.Light.NoActionBar"
    )

    @Test
    fun treeState() {
        snapshot(LemonadeState(LemonadeStep.SELECT))
    }

    @Test
    fun lemonState() {
        snapshot(LemonadeState(LemonadeStep.SQUEEZE, squeezesRemaining = 3))
    }

    @Test
    fun lemonadeState() {
        snapshot(LemonadeState(LemonadeStep.DRINK))
    }

    @Test
    fun emptyGlassState() {
        snapshot(LemonadeState(LemonadeStep.RESTART))
    }

    private fun snapshot(state: LemonadeState) {
        paparazzi.snapshot {
            LemonadeTheme(darkTheme = false) {
                LemonadeApp(initialState = state)
            }
        }
    }
}

