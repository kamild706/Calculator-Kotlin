package parser

abstract class Operation : Expression {

    abstract fun getOperator(): String
}