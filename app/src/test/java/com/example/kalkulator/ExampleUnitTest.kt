package com.example.kalkulator

import org.junit.Test
import org.junit.Assert.*

class ExampleUnitTest {

    @Test
    fun testOrderOfOperations() {
        assertEquals("6", calculateExpression("2+2*2"))
        assertEquals("6", calculateExpression("2+2×2"))
    }

    @Test
    fun testParentheses() {
        assertEquals("8", calculateExpression("(2+2)*2"))
        assertEquals("8", calculateExpression("(2+2)×2"))
    }

    @Test
    fun testDivisionByZero() {
        val result = calculateExpression("1/0")
        assertNotNull(result)
        assertTrue(result == "1/0" || result == "Infinity" || result == "NaN")
    }

    @Test
    fun testIncompleteExpression() {
        val inputMul = "2*"
        assertEquals("2*", calculateExpression(inputMul))

        val inputAdd = "2+"
        assertNotNull(calculateExpression(inputAdd))
    }

    @Test
    fun testUnclosedParenthesis() {
        val input = "((2+2)"
        assertEquals(input, calculateExpression(input))
    }

    @Test
    fun testEmptyExpression() {
        assertEquals("", calculateExpression(""))
        assertEquals("", calculateExpression("   "))
    }

    @Test
    fun testDomainError() {
        val result = calculateExpression("√( -1 )")
        assertNotNull(result)
        assertEquals("√( -1 )", result)
    }

    @Test
    fun testLargeNumbers() {
        val result = calculateExpression("1234567890*1234567890")
        assertNotNull(result)
        assertFalse(result.isEmpty())
    }

    @Test
    fun testDecimalSeparator() {
        assertEquals("3,5", calculateExpression("2,5+1"))
        assertEquals("3,5", calculateExpression("2.5+1"))
    }
}
