package hr.fer.ruazosa.calculator

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        Calculator.addNumber("3")
        Calculator.addOperator("+")
        Calculator.addNumber("2")
        Calculator.evaluate()

        assertEquals(5.00, Calculator.result, 0.00)

    }
}