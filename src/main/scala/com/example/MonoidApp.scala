package com.example

import scalaz._
import Scalaz._
object MonoidApp extends App{

  println(Monoid[List[Int]].zero)
  println(Tags.Multiplication(10) |+| Monoid[Int @@ Tags.Multiplication].zero)

  def lengthCompare(lhs: String, rhs: String) : Ordering =(lhs.length ?|? rhs.length) |+| (lhs ?|? rhs)
}
