package org.classifier.modules

import kotlin.math.sqrt

enum class NumberType {
    PRIME,
    ODD,
    EVEN
}

class EvaluationResult {
    val primes = mutableListOf<PrimeNumber>()
    val odds = mutableListOf<OddNumber>()
    val evens = mutableListOf<EvenNumber>()
}

class PrimeNumberProcessor(private val range: IntRange) {
    
    fun processNumbers(): EvaluationResult {
        val result = EvaluationResult()
        
        for (number in range) {
            val numberTypes = validateNumber(number)
            
            numberTypes.forEach { numberType ->
                when (numberType) {
                    NumberType.PRIME -> result.primes.add(PrimeNumber(number))
                    NumberType.ODD -> {
                        val oddNumber = OddNumber(number)
                        oddNumber.divisors.addAll(findDivisors(number))
                        result.odds.add(oddNumber)
                    }
                    NumberType.EVEN -> {
                        val evenNumber = EvenNumber(number)
                        evenNumber.divisors.addAll(findDivisors(number))
                        result.evens.add(evenNumber)
                    }
                }
            }
        }
        
        return result
    }
    
    private fun validateNumber(number: Int): Set<NumberType> {
        val types = mutableSetOf<NumberType>()
        
        if (number > 1) {
            if (isPrime(number)) types.add(NumberType.PRIME)
        }
        
        if (number % 2 == 0) {
            types.add(NumberType.EVEN)
        } else {
            types.add(NumberType.ODD)
        }
        
        return types
    }
    
    private fun isPrime(number: Int): Boolean {
        if (number <= 1) return false
        if (number == 2) return true
        if (number % 2 == 0) return false
        
        for (i in 3..sqrt(number.toDouble()).toInt() step 2) {
            if (number % i == 0) return false
        }
        
        return true
    }
    
    private fun findDivisors(number: Int): List<Int> {
        return (1..number).filter { number % it == 0 }
    }
}

// class PrimeNumberProcessor(private val range: IntRange) {
    
//     fun processNumbers(): EvaluationResult {
//         val result = EvaluationResult()
        
//         for (number in range) {
//             when (validateNumber(number)) {
//                 NumberType.PRIME -> result.primes.add(PrimeNumber(number))
//                 NumberType.ODD -> {
//                     val oddNumber = OddNumber(number)
//                     oddNumber.divisors.addAll(findDivisors(number))
//                     result.odds.add(oddNumber)
//                 }
//                 NumberType.EVEN -> {
//                     val evenNumber = EvenNumber(number)
//                     evenNumber.divisors.addAll(findDivisors(number))
//                     result.evens.add(evenNumber)
//                 }
//             }
//         }
        
//         return result
//     }
    
//     private fun validateNumber(number: Int): NumberType {

//         if (number <= 1) return NumberType.ODD
//         if (number == 2) return NumberType.PRIME
//         if (number % 2 == 0) return NumberType.EVEN
        
//         for (i in 3..sqrt(number.toDouble()).toInt() step 2) {
//             if (number % i == 0) return NumberType.ODD
//         }
        
//         return NumberType.PRIME
//     }
    
//     private fun findDivisors(number: Int): List<Int> {
//         return (1..number).filter { number % it == 0 }
//     }
// }