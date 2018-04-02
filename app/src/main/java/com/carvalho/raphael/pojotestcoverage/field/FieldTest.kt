package com.carvalho.raphael.pojotestcoverage.field

import com.carvalho.raphael.pojotestcoverage.exceptions.TestPojoException
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.*

internal object FieldTest {
    internal fun listFields(aClass: Class<*>): List<Field> {
        val fields = ArrayList<Field>()
        val superclass = aClass.superclass

        if (superclass != null) fields.addAll(listFields(superclass))
        fields.addAll(Arrays.asList(*aClass.declaredFields))

        return fields
    }

    internal fun findField(m: Method, fields: List<Field>): Field {
        val nameSB = StringBuilder(m.name).replace(0, 3, "")
        val lowerCase = nameSB.substring(0, 1).toLowerCase()
        val name = nameSB.replace(0, 1, lowerCase).toString()

        val field = fields.find { name == it.name }

        if (field != null) {
            return field
        } else throw TestPojoException("Field not found")
    }

    internal fun generateFieldValue(type: Class<*>): Any {
        when (type) {
            String::class.java -> return "string"
            Char::class.java -> return 'a'
            Long::class.java, Int::class.java -> return 123
            Double::class.java, Float::class.java -> return 12.3
            Boolean::class.java -> return false
            else -> return Proxy.newProxyInstance(
                    type.classLoader,
                    arrayOf(type),
                    {proxy, method, args ->})
        }
    }
}