import lexer.*
import parser.MathParser
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    while (true) {
        print("calc> ")
        val input = scanner.nextLine()
        val lexer = MathLexer(input)
        val parser = MathParser(lexer)
        val expression = parser.parse()
        expression.printStructure()
        println(expression.evaluate())
    }
}