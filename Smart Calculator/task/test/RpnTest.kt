import calculator.toPostFix
import org.junit.Test

class RpnTest {

    @Test
    fun test_toPostFix() {
        val inFix = "33000000000000000000 + 20000000000000000000 + 11000000000000000000 + 49000000000000000000 - 32000000000000000000 - 9000000000000000000 + 1000000000000000000 - 80000000000000000000 + 4000000000000000000 + 1"
        val postFix = toPostFix(inFix)
        println(postFix)
        assert(postFix == "33000000000000000000 20000000000000000000 + 11000000000000000000 + 49000000000000000000 + 32000000000000000000 - 9000000000000000000 - 1000000000000000000 + 80000000000000000000 - 4000000000000000000 + 1 +")
    }
}