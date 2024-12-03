import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.AssertionError

class DayThreeTest {
    @Test
    fun `regex matches single instance `() {
        val target = "mul(1,1)"
        assert(getMuls(target) == listOf(target))
    }

    @Test
    fun `regex matches many instances `() {
        assert(getMuls("mul(1,1) mul(2,2)")
                == listOf("mul(1,1)", "mul(2,2)")
        )
        assert(getMuls("mul(1,1) mul(2,2) mul(3,3)")
                == listOf("mul(1,1)", "mul(2,2)", "mul(3,3)")
        )
    }

    @Test
    fun `regex matches many instances with garbage in between`() {
        assert(getMuls("mul(1,1)3owty42h8qufp3ijo[mul(2,2)")
                == listOf("mul(1,1)", "mul(2,2)")
        )
        assert(getMuls("mul(1,1)mul(2,a)()sadfmul(2,2)mul(,3)mul(3,3)")
                == listOf("mul(1,1)", "mul(2,2)", "mul(3,3)")
        )
    }

    @Test
    fun `parseMul multiplies the entries in a mul`() {
        assert(parseMul("mul(2,2)") == 4)
        assert(parseMul("mul(2,0)") == 0)
        assert(parseMul("mul(9,5)") == 45)
    }

    @Test
    fun `parseMul rejects invalid muls`() {
        assertThrows<AssertionError> {
            parseMul("mul(9,-5)")
        }
        assertThrows<AssertionError> {
            parseMul("mul(  9 ,5)")
        }
        assertThrows<AssertionError> {
            parseMul("sum(1,1)")
        }
    }

    @Test
    fun `getSumOfMuls returns the sum of all valid muls in a string`() {
        val target = "mul(1,1)mul(2,a)()sadfmul(2,2)mul(,3)mul(3,3)"
        assert(getSumOfMuls(target) == (1 + 4 + 9))
    }

}