package calculator

import java.lang.Exception
import java.math.BigDecimal
import java.util.ArrayDeque

fun calculate(variables: Map<String, BigDecimal>, postFix: String): BigDecimal {
    val stack = ArrayDeque<String>()
    val split = postFix.split("\\s+".toRegex())
    for (item in split) {
        if ("[(^/*+-]{2,}".toRegex().matches(item)) {
            throw Exception("Invalid expression")
        } else if ("[(^/*+-]".toRegex().matches(item)) {
            try {
                val varA = stack.pop()
                val varB = stack.pop()
                val a = if (variables.containsKey(varA)) {
                    variables[varA]!!
                } else {
                    varA.toBigDecimal()
                }
                val b = if (variables.containsKey(varA)) {
                    variables[varB]!!
                } else {
                    varB.toBigDecimal()
                }
                when (item) {
                    "^" -> stack.push(a.pow(b.toInt()).toString())
                    "/" -> stack.push((b / a).toString())
                    "*" -> stack.push((b * a).toString())
                    "+" -> stack.push((b + a).toString())
                    "-" -> stack.push((b - a).toString())
                }
            } catch (e: Exception) {
                throw Exception("Invalid expression")
            }
        } else {
            if (variables.containsKey(item)) {
                stack.push(variables[item].toString())
            } else {
                stack.push(item)
            }
        }
    }
    return  if (!stack.isEmpty()) {
        stack.pop().toBigDecimal()
    } else {
        BigDecimal.ZERO
    }
}