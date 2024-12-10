import kotlin.test.Test
import kotlin.test.assertEquals

class DayNineTest {
    @Test
    fun `parseDenseDisk returns a list representing the fragmented disk`() {
        assertEquals(
            listOf("0", "0", ".", ".", ".", "1", "1", "1", ".", ".", ".", "2", ".", ".", ".", "3", "3", "3", ".", "4", "4", ".", "5", "5", "5", "5", ".", "6", "6", "6", "6", ".", "7", "7", "7", ".", "8", "8", "8", "8", "9", "9"),
            parseDenseFormat("2333133121414131402")
        )
    }

    @Test
    fun `defragmentDisk fills all of the empty space in the disk`() {
        assertEquals(
            "0099811188827773336446555566..............".toList().map { it.toString() },
            defragmentDisk(parseDenseFormat("2333133121414131402"))
        )
    }

    @Test
    fun `defragmentDisk works with even number of elements`() {
        assertEquals(
            listOf("0","."),
            defragmentDisk(parseDenseFormat("11"))
        )

        assertEquals(
            listOf("0","1",".","."),
            defragmentDisk(parseDenseFormat("1111"))
        )

        assertEquals(
            listOf("0","1",".","."),
            defragmentDisk(parseDenseFormat("121"))
        )

        assertEquals(
            listOf("0","2","1",".",".","."),
            defragmentDisk(parseDenseFormat("12111"))
        )

    }

    @Test
    fun `defragmentDisk works with odd number of elements`() {
        assertEquals(
            listOf("0","1","."),
            defragmentDisk(parseDenseFormat("111"))
        )

        assertEquals(
            listOf("0","2","1",".","."),
            defragmentDisk(parseDenseFormat("11111"))
        )


    }

    @Test
    fun `defragmentDisk works with 2 digit file ids`() {
        System.out.println(defragmentDisk(parseDenseFormat("1111111111111111111111")))
        assertEquals(
            listOf("0", "10", "1", "9", "2", "8", "3", "7", "4", "6", "5", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."),
            defragmentDisk(parseDenseFormat("1111111111111111111111"))
        )
    }

    @Test
    fun `calculate check sum`() {
        assertEquals(
            1928,
            calculateCheckSum(defragmentDisk(parseDenseFormat("2333133121414131402")))
        )
    }
}