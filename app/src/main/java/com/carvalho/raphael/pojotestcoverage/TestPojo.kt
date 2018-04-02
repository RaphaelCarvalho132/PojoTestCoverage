package com.carvalho.raphael.pojotestcoverage

import com.carvalho.raphael.pojotestcoverage.field.FieldTest.listFields
import com.carvalho.raphael.pojotestcoverage.exceptions.TestPojoException
import com.carvalho.raphael.pojotestcoverage.method.MethodTest.check

object TestPojo {
    @Throws(TestPojoException::class)
    fun testPojoClass(aClass: Class<*>) {
        try {
            val methods = aClass.methods
            val fields = listFields(aClass)

            check(aClass, methods, fields)
        } catch (e: Exception) {
            throw TestPojoException("Error in class " + aClass, e)
        }
    }
}