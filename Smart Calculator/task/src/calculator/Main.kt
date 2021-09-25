package calculator

import java.lang.Exception
import java.lang.NumberFormatException
import java.math.BigDecimal
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val variables = mutableMapOf<String, BigDecimal>()
    while (true) {
        when (val input = scanner.nextLine()) {
            "/exit" -> {
                println("Bye!")
                return
            }
            "/help" -> {
                println(
                    """
                        The program calculates expressions like these: 4 + 6 - 8, 2 - 3 - 4, and
                        so on. It supports both unary and binary minus operators. If the user
                        has entered several same operators following each other, the program still
                        works (like Java or Python REPL).
                        
                        Consider that the even number of minuses gives a plus, and the odd number
                        of minuses gives a minus! For example: 2 -- 2 equals 2 - (-2) equals 2 + 2.
                    """.trimIndent())
            }
            else -> {
                if (input.trim().isEmpty()) continue

                if ("\\s*/[a-zA-Z]+\\s*".toRegex().matches(input)) {
                    println("Unknown command")
                    continue
                }

                if ("\\s*[a-zA-Z]+\\s*".toRegex().matches(input)) {
                    if (variables.containsKey(input.trim())) {
                        println(variables[input.trim()])
                    } else {
                        println("Unknown variable")
                    }
                    continue
                }

                if ("\\s*[a-zA-Z]\\w*\\s*=\\s*[+-]*[0-9a-zA-Z]+\\s*".toRegex().matches(input)) {
                    val (variable, value) = input.split("\\s*=\\s*".toRegex())

                    if (!"\\s*[a-zA-Z]+\\s*".toRegex().matches(variable)) {
                        println("Invalid assignment")
                        continue
                    }

                    if (variables.containsKey(value.trim())) {
                        variables[variable.trim()] = variables[value.trim()]!!
                    } else {
                        try {
                            variables[variable.trim()] = value.trim().toBigDecimal()
                        } catch (e: NumberFormatException) {
                            println("Invalid assignment")
                        }
                    }
                } else {
                    try {
                        val postFix = toPostFix(removeRepeatedOperators(input))
                        println(calculate(variables, postFix))
                    } catch (e: Exception) {
                        println(e.message)
                    }
                }
            }
        }
    }
}

fun removeRepeatedOperators(input: String): String {
    var temp = input
    while (temp.contains("[-+]{2,}".toRegex())) {
        temp = temp.replace("--", "+")
            .replace("++", "+")
            .replace("-+", "-")
            .replace("+-", "-")
    }
    return temp
}