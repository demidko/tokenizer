# Tokenizer

Java tokenizer library written in the Kotlin language.

## Download

You need Gradle or Maven or other build tool

[![](https://jitpack.io/v/demidko/tokenizer.svg)](https://jitpack.io/#demidko/tokenizer)

## Usage

```kotlin
val tokens = "475i + '7days' + unknown".tokenize()
// 475
// i 
// +
// '7days'
// +
// unknown

val tokensWithCustomTypes = "475i + '7days' + unknown".tokenize { this.getType() }
// also, you can use tokenizer with custom types
```







