package org.classifier.modules

class PrimeNumber(override val value: Int) : IBaseNumber {
    override fun printValue() {
        println("Prime number: $value")
    }
}

class OddNumber(override val value: Int) : IBaseNumber {
    val divisors: MutableList<Int> = mutableListOf()

    override fun printValue() {
        println("Odd number: $value")
        println("Divisors: ${divisors.joinToString(", ")}")
    }
}

class EvenNumber(override val value: Int) : IBaseNumber {
    val divisors: MutableList<Int> = mutableListOf()

    override fun printValue() {
        println("Even number: $value")
        println("Divisors: ${divisors.joinToString(", ")}")
    }
}