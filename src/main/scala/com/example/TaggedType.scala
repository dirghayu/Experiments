package com.example

import scalaz._

object TaggedType extends App{

  sealed trait KiloGram
  def KiloGram[A](a: A): A @@ KiloGram = Tag[A, KiloGram](a)

  val mass = KiloGram(20.0)

  val doubleWeight  = 2 * Tag.unwrap(mass)

  println(s"doubleWeight = $doubleWeight")

  sealed trait JoulPerKilogram
  def JoulPerKilogram[A](a : A): A @@ JoulPerKilogram = Tag[A, JoulPerKilogram](a)
  def energy(m : Double @@ JoulPerKilogram): Double @@ JoulPerKilogram =
    JoulPerKilogram(299792458.0 * 299792458 * Tag.unwrap(m))
}
