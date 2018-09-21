package parser.mathexpression

abstract class Operation : Expression {
    override fun printStructure() {
        printStructure(0)
    }

    abstract fun getOperator(): String
}