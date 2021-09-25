package calculator

import java.lang.Exception
import java.util.ArrayDeque

fun toPostFix(inFix: String): String {
    val stack = ArrayDeque<String>()
    val postFix = ArrayDeque<String>()
    val split = inFix
        .replace("(", " ( ")
        .replace(")", " ) ")
        .split("\\s+".toRegex())
    for (item in split) {
        if ("\\s*".toRegex().matches(item)) {
            continue
        } else if ("[()^/*+-]{2,}".toRegex().matches(item)) {
            throw Exception("Invalid expression")
        } else if ("[()^/*+-]".toRegex().matches(item)) {
            if (stack.isEmpty()) {
                stack.push(item)
                continue
            }
            when (item) {
                "(" -> stack.push(item)
                ")" -> {
                    while (stack.peek() != "(") {
                        if (stack.isEmpty()) {
                            throw Exception("Invalid expression")
                        }
                        postFix.push(stack.pop())
                    }
                    stack.pop()
                }
                "^" -> {
                    while (stack.peek() == "^") {
                        postFix.push(stack.pop())
                    }
                    stack.push(item)
                }
                "/", "*" -> {
                    while (stack.peek() in arrayOf("^", "/", "*")) {
                        postFix.push(stack.pop())
                    }
                    stack.push(item)
                }
                "+", "-" -> {
                    while (stack.peek() in arrayOf("^", "/", "*", "+", "-")) {
                        postFix.push(stack.pop())
                    }
                    stack.push(item)
                }
            }
        } else {
            postFix.push(item)
        }
    }
    while (!stack.isEmpty()) {
        postFix.push(stack.pop())
    }
    return postFix.reversed().joinToString(" ")
}
