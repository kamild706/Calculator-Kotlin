package parser.mathexpression

import java.math.BigDecimal

class Negation(operand: Expression) : UnaryOperation(operand) {

    override fun getOperator(): String {
        return "-"
    }

    override fun evaluate(): BigDecimal {
        return operand.evaluate().negate()
    }
}