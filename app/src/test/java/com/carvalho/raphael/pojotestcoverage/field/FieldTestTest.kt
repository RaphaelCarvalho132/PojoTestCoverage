package com.carvalho.raphael.pojotestcoverage.field

import com.carvalho.raphael.pojotestcoverage.Empty
import com.carvalho.raphael.pojotestcoverage.InterfaceImplementation
import com.carvalho.raphael.pojotestcoverage.Mom
import com.carvalho.raphael.pojotestcoverage.Son
import com.carvalho.raphael.pojotestcoverage.exceptions.TestPojoException
import com.carvalho.raphael.pojotestcoverage.field.FieldTest.findField
import com.carvalho.raphael.pojotestcoverage.field.FieldTest.listFields
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FieldTestTest {
    @Test
    fun `listFields$classWithoutInheritance`() {
        val fields = listFields(Mom::class.java)

        assertEquals("momField", fields[0].name)
        assertEquals(1, fields.size)
    }

    @Test
    fun `listFields$classWithInheritance`() {
        val fields = listFields(Son::class.java)

        assertEquals("momField", fields[0].name)
        assertEquals("sonField", fields[1].name)
        assertEquals(2, fields.size)
    }

    @Test
    fun `listFields$classImplementsInterface`() {
        val fields = listFields(InterfaceImplementation::class.java)

        assertEquals("interfaceField", fields[0].name)
        assertEquals("implementationField", fields[1].name)
        assertEquals(2, fields.size)
    }

    @Test
    fun `listFields$classWithoutFields`() {
        val fields = listFields(Empty::class.java)

        assertTrue(fields.isEmpty())
    }

    @Test
    fun `findField$found`() {
        val fields = Mom::class.java.declaredFields.toList()
        findField(Mom::class.java.getMethod("getMomField"), fields)
    }

    @Test(expected = TestPojoException::class)
    fun `findField$fieldNotFound`() {
        val fields = Mom::class.java.declaredFields.toList()
        val field = findField(Son::class.java.getMethod("getSonField"), fields)

        assertEquals("momField", field.name)
    }

    @Test
    fun `generateFieldValue$production_sources_for_module_app`() {
    }
}