package com.github.demidko.tokenizer

/** A token consists of a lexeme and its semantic norm. */
data class Token<T>(val lexeme: String, val type: T?)

fun String.tokenize() = tokenize { first().category }

/** This function is the core of the tokenizer, providing parsing of tokens in linear time. */
fun <T> String.tokenize(type: String.() -> T): List<Token<T>> = when (val diff = indexOfFirstDiff()) {
  -1 -> grep(type)
  else -> substring(0 until diff).grep(type) + substring(diff until length).tokenize(type)
}

/** Processing discovered lexemes */
private fun <T> String.grep(type: String.() -> T) = when (isBlank()) {
  true -> emptyList()
  else -> listOf(Token(this, type()))
}

/** @return the first character idx differs in type from the previous ones (or -1) */
private fun String.indexOfFirstDiff(): Int {
  if (length > 1) {
    for (idx in (1 until length)) {
      if (isDiff(0, idx)) {
        return idx
      }
    }
  }
  return -1
}

/**
 * We are redefining the notion of a difference between two characters,
 * based on which the string is split into lexemes.
 * @param typeIdx first symbol
 * @param otherIdx other symbol
 * @return is the second index a break character?
 */
private fun String.isDiff(typeIdx: Int, otherIdx: Int): Boolean {

  // make sure symbols are available
  val type = get(typeIdx)
  val other = get(otherIdx)

  // handle entities names
  if (type.isLetter()) {
    // Solve names with hyphens and underscores
    // Otherwise, we will assume that the name has been entered.
    return (other !in "-_") && !other.isLetter()
  }

  // handle characters that break anyway
  if (type in ".,{}=:<>/\\") {
    return true
  }

  // handle '...' and "..." strings with escaping '\' symbol
  if (type == '"') {
    return getOrNull(otherIdx - 1) == '"'
      && getOrNull(otherIdx - 2) != '\\'
      && otherIdx - 1 != typeIdx
  }
  if (type == '\'') {
    return getOrNull(otherIdx - 1) == '\''
      && getOrNull(otherIdx - 2) != '\\'
      && otherIdx - 1 != typeIdx
  }

  // handle `...` strings without escaping '\' symbol
  if (type == '`') {
    return getOrNull(otherIdx - 1) == '`'
      && otherIdx - 1 != typeIdx
  }

  // use the standard character type check
  return type.category != other.category
}