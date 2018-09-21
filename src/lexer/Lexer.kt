package lexer

abstract class Lexer(private val input: CharArray) {

    var index = 0
        private set

    protected var currentChar: Char? = input[index]

    constructor(input: String) : this(input.toCharArray())

    protected fun consume() {
        index++
        currentChar = if (index < input.size) input[index] else null
    }

    abstract fun nextToken(): Token
}