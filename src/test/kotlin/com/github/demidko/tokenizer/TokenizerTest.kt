package com.github.demidko.tokenizer

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test


class TokenizerTest {

  enum class Types { MyNumber, MyString, Other }

  @Test
  fun tokenizeTest() {
    val tokens = "475i + '7days' + unknown".tokenize()
    assertThat(tokens, equalTo(listOf("475", "i", "+", "'7days'", "+", "unknown")))
  }
}
