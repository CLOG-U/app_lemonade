package com.clogu.lemonade

enum class LemonadeStep {
    SELECT,
    SQUEEZE,
    DRINK,
    RESTART
}

data class LemonadeState(
    val step: LemonadeStep = LemonadeStep.SELECT,
    val squeezesRemaining: Int = 0
)

/**
 * Devuelve el siguiente estado del ciclo de preparación de la limonada.
 * El número aleatorio se recibe como función para que la lógica pueda probarse.
 */
fun nextLemonadeState(
    current: LemonadeState,
    squeezeCount: () -> Int = { (2..4).random() }
): LemonadeState = when (current.step) {
    LemonadeStep.SELECT -> LemonadeState(
        step = LemonadeStep.SQUEEZE,
        squeezesRemaining = squeezeCount().coerceIn(2, 4)
    )

    LemonadeStep.SQUEEZE -> {
        if (current.squeezesRemaining > 1) {
            current.copy(squeezesRemaining = current.squeezesRemaining - 1)
        } else {
            LemonadeState(step = LemonadeStep.DRINK)
        }
    }

    LemonadeStep.DRINK -> LemonadeState(step = LemonadeStep.RESTART)
    LemonadeStep.RESTART -> LemonadeState()
}

