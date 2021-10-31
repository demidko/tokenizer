package com.github.demidko.tokenizer

/**
 * @param namesSeparators list of names-allowed symbols. For example lower_snake_case, css-case-var
 * @param unaryTokens symbols always one token only. For example, {{{ -> {, {, {
 * @param stringsQuotes quote types for string literals, "string" 'string' `string`
 */
fun String.tokenize(
  skipSpaces: Boolean = true,
  namesSeparators: String = "_",
  unaryTokens: String = ".,{}=:<>/\\",
  stringsQuotes: String = "\"'`"
) = mutableListOf<String>().apply {
  var startTokenIdx = 0
  for (finishTokenIdx in 1 until length) {
    if (isDiff(startTokenIdx, finishTokenIdx, namesSeparators, unaryTokens, stringsQuotes)) {
      add(substring(startTokenIdx until finishTokenIdx), skipSpaces)
      startTokenIdx = finishTokenIdx
    }
  }
  add(substring(startTokenIdx until length), skipSpaces)
}

private fun MutableList<String>.add(token: String, skipSpaces: Boolean) {
  when {
    !skipSpaces || token.isNotBlank() -> add(token)
  }
}

/**
 * We are redefining the notion of a difference between two characters,
 * based on which the string is split into lexemes.
 * @param typeIdx first symbol
 * @param otherIdx other symbol
 * @param namesSeparators list of names-allowed symbols. For example lower_snake_case, css-case-var
 * @param unaryTokens symbols always one token only. For example, {{{ -> {, {, {
 * @param stringsQuotes quote types for string literals, "string" 'string' `string`
 * @return is the second index a break character?
 */
private fun String.isDiff(
  typeIdx: Int,
  otherIdx: Int,
  namesSeparators: String,
  unaryTokens: String,
  stringsQuotes: String,
): Boolean {

  // make sure symbols are available
  val type = get(typeIdx)
  val other = get(otherIdx)

  // handle entities names
  if (type.isLetter()) {
    // Solve names with hyphens and underscores
    // Otherwise, we will assume that the name has been entered.
    return (other !in namesSeparators) && !other.isLetterOrDigit()
  }

  // handle characters that break anyway
  if (type in unaryTokens) {
    return true
  }

  // handle string literals
  for (quote in stringsQuotes) {
    if (type == quote) {
      return getOrNull(otherIdx - 1) == quote
        && getOrNull(otherIdx - 2) != '\\'
        && otherIdx - 1 != typeIdx
    }
  }

  // use the standard character type check
  return type.category != other.category
}