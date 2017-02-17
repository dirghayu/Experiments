package com.example

  import scalaz._
  import Scalaz._


sealed trait COption[+A]
case class CSome[A] (counter: Int, a: A) extends COption[A]
case object CNone extends COption[Nothing]

object COption{
implicit def cOptionEqual[A] : Equal[COption[A]] = Equal.equalA
  implicit val cOptionFunctor = new Functor [COption]{
    def map[A, B] (fa: COption[A])(f: A=> B): COption[B] = fa match {
      case CNone => CNone
      case CSome(c, a) => CSome(c + 1, f(a))
    }
  }
}


object COptionApp extends App{
import  COption._
  println((CSome(0, "ho"): COption[String]).map{s:String=>s +"ha"})
  println((CSome(0, "ho"): COption[String]).map{identity})


}