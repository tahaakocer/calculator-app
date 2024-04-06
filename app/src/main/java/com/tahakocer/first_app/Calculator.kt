import java.util.*

class Calculator {
    fun evaluate(expression: String): Double {
        val outputQueue = LinkedList<String>()
        val operatorStack = Stack<String>()
        val tokenizer = StringTokenizer(expression.replace(" ", ""), "+-*/(),", true)

        while (tokenizer.hasMoreTokens()) {
            val token = tokenizer.nextToken()

            when {
                token.matches(Regex("[0-9]+")) -> outputQueue.add(token) // Number
                token.matches(Regex("[a-zA-Z]+")) -> operatorStack.push(token) // Function
                token in "+-*/" -> {
                    while (!operatorStack.isEmpty() &&
                        operatorStack.peek() !in listOf("(", ")") &&
                        (precedence(operatorStack.peek()) > precedence(token) ||
                                (precedence(operatorStack.peek()) == precedence(token) && isLeftAssociative(token)))
                    ) {
                        outputQueue.add(operatorStack.pop())
                    }
                    operatorStack.push(token)
                }
                token == "," -> {
                    while (!operatorStack.isEmpty() && operatorStack.peek() != "(") {
                        outputQueue.add(operatorStack.pop())
                    }
                }
                token == "(" -> operatorStack.push(token) // Left parenthesis
                token == ")" -> {
                    while (operatorStack.peek() != "(") {
                        if (operatorStack.isEmpty()) throw IllegalArgumentException("Mismatched parentheses")
                        outputQueue.add(operatorStack.pop())
                    }
                    operatorStack.pop() // Discard left parenthesis
                    if (!operatorStack.isEmpty() && operatorStack.peek().matches(Regex("[a-zA-Z]+"))) {
                        outputQueue.add(operatorStack.pop())
                    }
                }
            }
        }

        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek() in "()") throw IllegalArgumentException("Mismatched parentheses")
            outputQueue.add(operatorStack.pop())
        }

        return evaluateRPN(outputQueue)
    }

    private fun evaluateRPN(queue: Queue<String>): Double {
        val stack = Stack<Double>()

        while (queue.isNotEmpty()) {
            val token = queue.poll()
            if (token.matches(Regex("[0-9]+"))) {
                stack.push(token.toDouble())
            } else if (token.matches(Regex("[a-zA-Z]+"))) {
                val argument = stack.pop()
                stack.push(applyFunction(token, argument))
            } else if (token in "+-*/") {
                val operand2 = stack.pop()
                val operand1 = stack.pop()
                stack.push(applyOperator(token, operand1, operand2))
            }
        }

        return stack.pop()
    }

    private fun applyOperator(operator: String, operand1: Double, operand2: Double): Double {
        return when (operator) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> operand1 / operand2
            else -> throw IllegalArgumentException("Invalid operator")
        }
    }

    private fun applyFunction(function: String, argument: Double): Double {
        return when (function) {
            "sin" -> Math.sin(Math.toRadians(argument))
            "cos" -> Math.cos(Math.toRadians(argument))
            "tan" -> Math.tan(Math.toRadians(argument))
            "sqrt" -> Math.sqrt(argument)
            else -> throw IllegalArgumentException("Invalid function")
        }
    }

    private fun precedence(operator: String): Int {
        return when (operator) {
            "+", "-" -> 1
            "*", "/" -> 2
            else -> 0
        }
    }

    private fun isLeftAssociative(operator: String): Boolean {
        return operator in "+-*/"
    }
}


