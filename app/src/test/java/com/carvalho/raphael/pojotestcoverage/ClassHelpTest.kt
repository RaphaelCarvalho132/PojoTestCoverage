package com.carvalho.raphael.pojotestcoverage

// Empty class
class Empty

// Class inheritance
open class Mom(val momField: Any)
class Son(momField: Any, val sonField: Any) : Mom(momField)

// Interface with field
interface InterfaceTest { val interfaceField: Any }
class InterfaceImplementation(override val interfaceField: Any, val implementationField: Any)
    : InterfaceTest