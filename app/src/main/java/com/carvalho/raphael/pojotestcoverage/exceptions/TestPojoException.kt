package com.carvalho.raphael.pojotestcoverage.exceptions

class TestPojoException : Exception {
    internal constructor(message: String) : super(message)

    internal constructor(message: String, cause: Throwable) : super(message, cause)
}
