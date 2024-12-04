import kotlin.test.Test
import kotlin.test.assertEquals

class DayFourTest {
    @Test
    fun `matches XMAS`() {
        assertEquals(
            countPatternsInString("XMAS", xmasMatcher),
            1
        )
    }

    @Test
    fun `matches SAMX`() {
        assertEquals(
            countPatternsInString("SAMX", xmasMatcher),
            1
        )
    }

    @Test
    fun `matches multiple`() {
        assertEquals(
            countPatternsInString("SAMXXMASSAMX", xmasMatcher),
            3
        )
    }

    @Test
    fun `matches multiple with overlapping letters`() {
        assertEquals(
            countPatternsInString("SAMXMASAMX", xmasMatcher),
            3
        )
    }

    @Test
    fun `can convert from horizontal to vertical strings`() {
        val wordSearch = listOf(
            "ab",
            "cd"
        )
        val wordSearchTranspose = listOf(
            "ac",
            "bd"
        )

        assertEquals(wordSearchTranspose, transposeStringMatrix(wordSearch))
    }

    @Test
    fun `can convert from horizontal to vertical strings with irregular length`() {
        val wordSearch = listOf(
            "abc",
            "def"
        )
        val wordSearchTranspose = listOf(
            "ad",
            "be",
            "cf"
        )

        assertEquals(wordSearchTranspose, transposeStringMatrix(wordSearch))
    }

    @Test
    fun `getDiagonals returns a list of diagonal strings`() {
        var wordSearch = listOf(
            "ab",
            "de"
        )
        var diagonalList = listOf(
            "a",
            "bd",
            "e"
        )
        assertEquals(diagonalList, getDiagonals(wordSearch))

        wordSearch = listOf(
            "abc",
            "def",
            "ghi"
        )
        diagonalList = listOf(
            "a",
            "bd",
            "ceg",
            "fh",
            "i"
        )
        assertEquals(diagonalList, getDiagonals(wordSearch))
    }

    @Test
    fun `getDownwardDiagonals returns a list of diagonal strings`() {
        var wordSearch = listOf(
            "ab",
            "de"
        )
        var diagonalList = listOf(
            "b",
            "ae",
            "d"
        )
        assertEquals(diagonalList, getDownwardDiagonals(wordSearch))

        wordSearch = listOf(
            "abc",
            "def",
            "ghi"
        )
        diagonalList = listOf(
            "c",
            "bf",
            "aei",
            "dh",
            "g"
        )
        assertEquals(diagonalList, getDownwardDiagonals(wordSearch))
    }

    @Test
    fun `wordSearch returns the number of occurances`() {
        val wordSearch = listOf(
          "MMMSXXMASM",
          "MSAMXMSMSA",
          "AMXSXMAAMM",
          "MSAMASMSMX",
          "XMASAMXAMM",
          "XXAMMXXAMA",
          "SMSMSASXSS",
          "SAXAMASAAA",
          "MAMMMXMMMM",
          "MXMXAXMASX",
        )
        assertEquals(18, wordSearchCount(wordSearch, xmasMatcher))
    }

}