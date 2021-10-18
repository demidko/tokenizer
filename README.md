# Tokenizer

Java tokenizer library written in the Kotlin language.

## Download

You need Gradle or Maven or other build tool

[![](https://jitpack.io/v/demidko/tokenizer.svg)](https://jitpack.io/#demidko/tokenizer)

## Usage

```kotlin

val tokens = "475i + '7days' + unknown".tokenize()

// Token(lexeme=475, type=DECIMAL_DIGIT_NUMBER), 
// Token(lexeme=i, type=LOWERCASE_LETTER), 
// Token(lexeme=+, type=MATH_SYMBOL), 
// Token(lexeme='7days', type=OTHER_PUNCTUATION), 
// Token(lexeme=+, type=MATH_SYMBOL), 
// Token(lexeme=unknown, type=LOWERCASE_LETTER)

// also, you can use tokenizer with custom types

fun getCustomType(lexeme: String): CustomType = ...

val tokensWithCustomTypes = "475i + '7days' + unknown".tokenize(::getCustomType)

```







