package lexer

import org.junit.Test

import org.junit.Assert.assertEquals

class MathLexerTest {

    private fun tokenize(input: String, expectedOutput: Array<String>) {
        val lexer = MathLexer(input)
        var token = lexer.nextToken()
        var index = 0

        while (token.type !== TokenType.END_OF_INPUT) {
            assertEquals(token.value, expectedOutput[index++])
            token = lexer.nextToken()
        }
        assertEquals(expectedOutput.size, index)
    }

    @Test
    fun expressionTest1() {
        tokenize("2+2 + 3", arrayOf("2", "+", "2", "+", "3"))
    }

    @Test
    fun expressionTest2() {
        tokenize("(1+1)", arrayOf("(", "1", "+", "1", ")"))
    }

    @Test
    fun expressionTest3() {
        tokenize("2 *2", arrayOf("2", "*", "2"))
    }

    @Test
    fun expressionTest4() {
        tokenize("3 * 1 + 6.54", arrayOf("3", "*", "1", "+", "6.54"))
    }

    @Test
    fun expressionTest5() {
        tokenize("(4+3 * 1.54) * (5.65 + 3)",
                arrayOf("(", "4", "+", "3", "*", "1.54", ")", "*", "(", "5.65", "+", "3", ")"))
    }

    @Test
    fun expressionTest6() {
        tokenize("1-65.7", arrayOf("1", "-", "65.7"))
    }

    @Test
    fun expressionTest7() {
        tokenize("(5-54 - 12.54) * 45", arrayOf("(", "5", "-", "54", "-", "12.54", ")", "*", "45"))
    }

    @Test
    fun expressionTest8() {
        tokenize("54 / 8 /34 * 2", arrayOf("54", "/", "8", "/", "34", "*", "2"))
    }

    @Test
    fun expressionTest9() {
        tokenize("54 / (12 + 4 - 54 * 54 / (54 - 23.43))",
                arrayOf("54", "/", "(", "12", "+", "4", "-", "54", "*", "54", "/", "(", "54", "-", "23.43", ")", ")"))
    }

    @Test
    fun expressionTest10() {
        tokenize("54 ^ 23 ^ 543.65", arrayOf("54", "^", "23", "^", "543.65"))
    }

    @Test
    fun expressionTest11() {
        tokenize("12 * (13 ^ 67.2) ^ 34.3 / 4",
                arrayOf("12", "*", "(", "13", "^", "67.2", ")", "^", "34.3", "/", "4"))
    }

    @Test
    fun expressionTest12() {
        tokenize("-2", arrayOf("-", "2"))
    }

    @Test
    fun expressionTest13() {
        tokenize("-54 * 45 / 34.6 * (12 ^ (-34 * 3) + 3 )",
                arrayOf("-", "54", "*", "45", "/", "34.6", "*","(", "12", "^", "(", "-", "34", "*", "3", ")", "+", "3", ")"))
    }

    @Test(expected = InvalidCharacterException::class)
    fun expressionTest14() {
        tokenize("54. 65", arrayOf("54.65"))
    }
}
