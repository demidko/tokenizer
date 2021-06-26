# Tokenizer

Java tokenizer library written in the Kotlin language.

## Usage

```kotlin

val tokens = "475i + '7days' + unknown".tokenize()

// [ Token(lexeme=475, type=DECIMAL_DIGIT_NUMBER), 
// Token(lexeme=i, type=LOWERCASE_LETTER), 
// Token(lexeme=+, type=MATH_SYMBOL), 
// Token(lexeme='7days', type=OTHER_PUNCTUATION), 
// Token(lexeme=+, type=MATH_SYMBOL), 
// Token(lexeme=unknown, type=LOWERCASE_LETTER) ]

// or alse

fun getCustomType(lexeme: String): CustomType = ...

val tokensWithCustomTypes = "475i + '7days' + unknown".tokenize(::getCustomType)
```

## Download with [Gradle](https://gradle.org/)

Add the JitPack repository to your `build.gradle.kts`:

```kotlin
repositories {
    maven("https://jitpack.io")
}
```

Add the dependency:

```kotlin
dependencies {
    implementation("com.github.demidko:tokenizer:2021.06.26")
}
```

## Download with [Maven](https://maven.apache.org/)

Add the JitPack repository to your `pom.xml`:

```xml

<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

Add the dependency:

```xml

<dependencies>
  <dependency>
    <groupId>com.github.demidko</groupId>
    <artifactId>tokenizer</artifactId>
    <version>2021.06.26</version>
  </dependency>
</dependencies>
```






