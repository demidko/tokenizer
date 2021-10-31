package com.github.demidko.tokenizer

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

internal class TokenizerKtTest {

  @Test
  fun tokenizeTest() {
    val tokens = "475i + '7days' + unknown".tokenize()
    assertThat(tokens, equalTo(listOf("475", "i", "+", "'7days'", "+", "unknown")))
  }

  @Test
  fun tokenizeTest2() {
    val tokens = "475 ''di + `7days/3 df` + unknown".tokenize()
    assertThat(tokens, equalTo(listOf("475", "''", "di", "+", "`7days/3 df`", "+", "unknown")))
  }

  @Test
  fun tokenizeTest3() {
    val tokens = "unknown".tokenize()
    assertThat(tokens, equalTo(listOf("unknown")))
  }

  @Test
  fun tokenizeTest4() {
    val tokens = "uu xx".tokenize()
    assertThat(tokens, equalTo(listOf("uu", "xx")))
  }

  @Test
  fun tokenizeTest5() {
    val tokens = "uu__4".tokenize()
    assertThat(tokens, equalTo(listOf("uu__4")))
  }

  @Test
  fun tokenizeTest6() {
    val tokens = "4__u".tokenize()
    assertThat(tokens, equalTo(listOf("4", "__", "u")))
  }
}