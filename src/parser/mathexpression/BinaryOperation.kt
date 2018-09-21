package parser.mathexpression

abstract class BinaryOperation(val leftOperand: Expression, val rightOperand: Expression) : Operation() {
    override fun printStructure(indentation: Int) {
        for (i in 0 until indentation)
            print("  ")
        println("${this::class.simpleName}(")

        leftOperand.printStructure(indentation + 1)
        rightOperand.printStructure(indentation + 1)

        for (i in 0 until indentation)
            print("  ")
        println(")")
    }
}