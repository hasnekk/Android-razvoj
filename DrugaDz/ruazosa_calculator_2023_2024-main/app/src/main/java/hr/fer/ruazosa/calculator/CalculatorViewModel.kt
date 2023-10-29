package hr.fer.ruazosa.calculator

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {

    val calculationResult = MutableLiveData<String>() // LIVE DATA => trigger observers
    private var expression = mutableListOf<String>()
    private var numberToAddToExpression = "0"

    init {
        expression.add(numberToAddToExpression)
        calculationResult.value = numberToAddToExpression // sets the value of the LIVE DATA
    }

    fun resetCalculator() {
        Calculator.reset()
        numberToAddToExpression = "0"
        expression.clear()
        expression.add(numberToAddToExpression)
        calculationResult.value = numberToAddToExpression
    }

    fun evaluate() {
       for (i in expression.indices) {
           if (i % 2 == 0) {
               Calculator.addNumber(expression[i])
           }
           else {
               Calculator.addOperator(expression[i])
           }
       }
        Calculator.evaluate()
        expression.clear()
        expression.add(Calculator.result.toString())
        calculationResult.value = Calculator.result.toString()
        Calculator.reset()
    }

    fun addToExpression(numberOrOperator: String) {
        when (numberOrOperator) {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> {
                if (numberToAddToExpression == "0") {
                    numberToAddToExpression = numberOrOperator
                } else {
                    numberToAddToExpression += numberOrOperator
                }

                // sets the value of the Live Data to trigger the observer in the activity
                calculationResult.value = numberToAddToExpression

                // changes the last expression to the numberToAddToExpression
                // on inital it will be 0 added in the contructor so this changes it to the number pressed
                expression[expression.size - 1] = numberToAddToExpression
            }
            "+" -> {
                if (expression.size % 2 == 1) {
                    expression.add("+")
                    numberToAddToExpression = "0"
                    expression.add(numberToAddToExpression)
                }
            }
            "-" -> {
                if (expression.size % 2 == 1) {
                    expression.add("-")
                    numberToAddToExpression = "0"
                    expression.add(numberToAddToExpression)
                }
            }
            "*" -> {
               if (expression.size % 2 == 1){
                   expression.add("*")
                   numberToAddToExpression = "0"
                   expression.add(numberToAddToExpression)
               }
            }
            "/" -> {
                if (expression.size % 2 == 1) {
                    expression.add("/")
                    numberToAddToExpression = "0"
                    expression.add(numberToAddToExpression)
                }
            }
        }
    }
}