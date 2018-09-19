package parser

abstract class UnaryOperation(val operand: Expression) : Operation() {

    override fun printStructure(indentation: Int) {
        for (i in 0 until indentation)
            print("  ")
        println("${this::class.simpleName}(")

        operand.printStructure(indentation + 1)

        for (i in 0 until indentation)
            print("  ")
        println(")")
    }
}