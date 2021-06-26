package com.github.demidko.tokenizer

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test


class TokenizerTest {

  @Test
  fun testSomeLibraryMethod() {
    val classUnderTest = Tokenizer()
    assertThat(classUnderTest.someLibraryMethod(), equalTo(true))
  }
}
