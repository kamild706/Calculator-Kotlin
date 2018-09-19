package parser

import java.math.BigDecimal

class Sum(leftOperand: Expression, rightOperand: Expression) : BinaryOperation(leftOperand, rightOperand) {

    override fun getOperator(): String {
        return "+"
    }

    override fun evaluate(): BigDecimal {
        return leftOperand.evaluate().add(rightOperand.evaluate())
    }
}