package com.carvalho.raphael.pojotestcoverage.method

import com.carvalho.raphael.pojotestcoverage.field.FieldTest
import org.junit.Assert.assertEquals
import java.lang.reflect.Field
import java.lang.reflect.Method

object GetTest {
    internal val GET = "get"

    internal fun checkGet(obj: Any, m: Method, fields: List<Field>) {
        val field = FieldTest.findField(m, fields)
        val expected = FieldTest.generateFieldValue(field.type)

        field.isAccessible = true
        field.set(obj, expected)// Insert value in field
        field.isAccessible = false

        assertEquals(expected, m.invoke(obj))
    }
}