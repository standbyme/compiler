package StandbyMe.lexer

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import StandbyMe.compiler.universal.Token
object Lexer {

  def skip_blank(char__list: List[Char]): List[Char] = char__list.span(_.isWhitespace)._2

  @tailrec
  def outer(identified_token__listbuffer: ListBuffer[Token], rest_char__list: List[Char]): List[Token] = {
    val char_skipped_blank = skip_blank(rest_char__list)
    if (char_skipped_blank.isEmpty) identified_token__listbuffer.toList
    else Matcher(char_skipped_blank) match {
      case Some((token, next_rest_char__list)) => outer(identified_token__listbuffer += token, next_rest_char__list)
      case None => throw new Exception("Lexer works wrong")
    }
  }

  def apply(code: String): List[Token] = outer(ListBuffer[Token](), code.toList)
}
