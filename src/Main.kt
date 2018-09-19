import lexer.*
import parser.Parser
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    while (true) {
        print("calc> ")
        val input = scanner.nextLine()
        val lexer = Lexer(input)
        val parser = Parser(lexer)
        val expression = parser.parse()
        expression.printStructure(0)
        /*var token = lexer.nextToken()
        while (token.type != TokenType.EOI) {
            println(token.value)
            token = lexer.nextToken()
        }*/
    }
}