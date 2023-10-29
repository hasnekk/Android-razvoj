package hr.fer.ruazosa.calculator

object Calculator {
    var result: Float = 0F
    var expression: MutableList<String> = mutableListOf()

    fun reset() {
        result = 0F
        expression = mutableListOf()
    }

    fun addNumber(number: String) {
        try {
            val num = number.toFloat() // used to see if a valid number
        } catch (e: NumberFormatException) {
            throw Exception("Not valid number")
        }
        if (expression.count() % 2 == 0) {
            expression.add(number)
        } else {
            throw Exception("Not a valid order of numbers in expression")
        }
    }

    fun addOperator(operator: String) {
        if (expression.count() % 2 != 1) {
            throw Exception("Not a valid order of operator in expression")
        }
        when (operator) {
            "+" -> expression.add(operator)
            "-" -> expression.add(operator)
            "*" -> expression.add(operator)
            "/" -> expression.add(operator)
            else -> {
                throw Exception("Not a valid operator")
            }
        }
    }

    fun evaluate() {
        if (expression.count() % 2 == 0) {
            throw Exception("Not a valid expression")
        }

        // Create two lists to separate operators and operands
        val operators = mutableListOf<String>()
        val operands = mutableListOf<Float>()

        // Iterate through the expression to split operators and operands
        for (i in expression.indices) {
            if (i % 2 == 0) {
                // Operand
                operands.add(expression[i].toFloat())
            } else {
                // Operator
                while (operators.isNotEmpty() && hasHigherPrecedence(operators.last(), expression[i])) { // checks if the last on added is of higher priority
                    // Process operators with higher precedence
                    val operator = operators.removeAt(operators.size - 1)
                    val operand2 = operands.removeAt(operands.size - 1)
                    val operand1 = operands.removeAt(operands.size - 1)
                    val result = applyOperator(operator, operand1, operand2)
                    operands.add(result)
                }
                operators.add(expression[i])
            }
        }

        // Process any remaining operators
        while (operators.isNotEmpty()) {
            val operator = operators.removeAt(operators.size - 1)
            val operand2 = operands.removeAt(operands.size - 1)
            val operand1 = operands.removeAt(operands.size - 1)
            val result = applyOperator(operator, operand1, operand2)
            operands.add(result)
        }

        // The final result will be at the top of the operands list
        result = operands[0]
    }

    // Function to determine operator precedence
    fun hasHigherPrecedence(op1: String, op2: String): Boolean {
        return (op1 == "*" || op1 == "/") && (op2 == "+" || op2 == "-")
    }

    // Function to apply operators
    fun applyOperator(operator: String, operand1: Float, operand2: Float): Float {
        return when (operator) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> operand1 / operand2
            else -> throw Exception("Invalid operator: $operator")
        }
    }
}