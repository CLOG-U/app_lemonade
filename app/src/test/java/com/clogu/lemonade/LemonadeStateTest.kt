package com.clogu.lemonade

import org.junit.Assert.assertEquals
import org.junit.Test

class LemonadeStateTest {

    @Test
    fun selectingLemon_startsSqueezeWithGeneratedCount() {
        val result = nextLemonadeState(LemonadeState()) { 3 }

        assertEquals(LemonadeStep.SQUEEZE, result.step)
        assertEquals(3, result.squeezesRemaining)
    }

    @Test
    fun squeezing_waitsUntilLastRequiredTap() {
        val firstTap = nextLemonadeState(
            LemonadeState(LemonadeStep.SQUEEZE, squeezesRemaining = 2)
        )
        val secondTap = nextLemonadeState(firstTap)

        assertEquals(LemonadeStep.SQUEEZE, firstTap.step)
        assertEquals(1, firstTap.squeezesRemaining)
        assertEquals(LemonadeStep.DRINK, secondTap.step)
    }

    @Test
    fun completeCycle_returnsToTree() {
        val emptyGlass = nextLemonadeState(LemonadeState(LemonadeStep.DRINK))
        val tree = nextLemonadeState(emptyGlass)

        assertEquals(LemonadeStep.RESTART, emptyGlass.step)
        assertEquals(LemonadeStep.SELECT, tree.step)
    }
}

