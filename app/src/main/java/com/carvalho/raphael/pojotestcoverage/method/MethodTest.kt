package com.carvalho.raphael.pojotestcoverage.method

import com.carvalho.raphael.pojotestcoverage.annotations.IgnorePojoTest
import com.carvalho.raphael.pojotestcoverage.exceptions.TestPojoException
import com.carvalho.raphael.pojotestcoverage.method.GetTest.GET
import com.carvalho.raphael.pojotestcoverage.method.GetTest.checkGet
import com.carvalho.raphael.pojotestcoverage.method.SetTest.SET
import com.carvalho.raphael.pojotestcoverage.method.SetTest.checkSet
import java.lang.reflect.Field
import java.lang.reflect.Method

object MethodTest {
    internal fun check(aClass: Class<*>, methods: Array<Method>, fields: List<Field>) {
        for (m in methods) {
            if (!isValidMethod(m)) continue
            val obj = aClass.getConstructor().newInstance()

            val name = m.name
            try {
                if (name.startsWith(GET))
                    checkGet(obj, m, fields)
                else if (name.startsWith(SET)) checkSet(obj, m, fields)

            } catch (e: Exception) {
                throw TestPojoException("Error in field " + name, e)
            }

        }
    }

    private fun isValidMethod(m: Method): Boolean {
        return !haveIgnorePojoTestAnnotation(m) && !isObjectMethod(m)
    }

    private fun isObjectMethod(m: Method): Boolean {
        return "getClass" == m.name
    }

    private fun haveIgnorePojoTestAnnotation(m: Method): Boolean {
        return m.getAnnotation(IgnorePojoTest::class.java) != null
    }
}