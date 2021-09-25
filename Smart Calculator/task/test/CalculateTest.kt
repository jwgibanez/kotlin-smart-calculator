import calculator.calculate
import org.junit.Test
import java.math.BigDecimal

class CalculateTest {

    @Test
    fun test_calculate() {
        val postFix = "33000000000000000000 20000000000000000000 + 11000000000000000000 + 49000000000000000000 + 32000000000000000000 - 9000000000000000000 - 1000000000000000000 + 80000000000000000000 - 4000000000000000000 + 1 +"
        val result = calculate(emptyMap(), postFix)
        println(result)
        assert(result == BigDecimal("-2999999999999999999"))
    }
}