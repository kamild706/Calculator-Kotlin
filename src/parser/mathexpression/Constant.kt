package parser.mathexpression

import lexer.Token
import java.math.BigDecimal

class Constant(token: Token) : Expression {
    val value = BigDecimal(token.value)

    override fun evaluate(): BigDecimal {
        return value
    }

    override fun printStructure(indentation: Int) {
        for (i in 0 until indentation)
            print("  ")
        println("${this::class.simpleName}($value)")
    }

    override fun printStructure() {
        printStructure(0)
    }
}
