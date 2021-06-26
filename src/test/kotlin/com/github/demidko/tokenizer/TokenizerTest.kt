package com.github.demidko.tokenizer

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test


class TokenizerTest {

  enum class Types { MyNumber, MyString, Other }


  @Test
  fun tokenizeTest() {

    val tokens = "475i + '7days' + unknown".tokenize()

    assertThat(
      tokens.toString(), equalTo(
        "[Token(lexeme=475, type=DECIMAL_DIGIT_NUMBER), " +
          "Token(lexeme=i, type=LOWERCASE_LETTER), " +
          "Token(lexeme=+, type=MATH_SYMBOL), " +
          "Token(lexeme='7days', type=OTHER_PUNCTUATION), " +
          "Token(lexeme=+, type=MATH_SYMBOL), " +
          "Token(lexeme=unknown, type=LOWERCASE_LETTER)]"
      )
    )
  }
}
