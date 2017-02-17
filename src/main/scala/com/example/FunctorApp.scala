package com.example
import scalaz.Scalaz._
import scalaz._
object FunctorApp extends App{

  //Functor for Tuple==> only updates last values

  val tuple = (1, 2 ,3 )

  println("expected :(1,2,4) actual:" +tuple.map(_ +1))

  //Scalaz has Functor for functions also

  val f: (Int) => Int = ((x:Int)=> x *3) map( _ + 3)

  println(s"expected f(3) is 12 Actual: ${f(3)}")
  println(s"Map on List(1,2,3) ${List(1,2,3)map{3 *}}")
  println(s">| for replacing each element${List(1,2,3) >| "x"}")
  println(s"as for replacing each element${List(1,2,3) as "x"}")
  println(s"fpair for oairing each elemnt${List(1,2,3) fpair}")
  println(s"strengthL for pairing with given element${List(1,2,3) strengthL "x"}")
  println(s"strengthL for voiding with given element${List(1,2,3) void }")


  println(s"1.point[List] ${1.point[List]}")
    ////Lifting
  val liftedList = Functor[List].lift{(_: Int) * 2}

  println(liftedList(List(12,3)))



  //Operatons on Options

  println(s"<* operator on two opetions return left hnd side: ${1.some <* 2.some}")
  println(s"<* operator on two opetions return left hnd side: ${1.some <* none}")
  println(s"*> operator on two opetions return left hnd side: ${1.some *> 2.some}")
  println(s"*> operator on two opetions return left hnd side; ${none *> 2.some}")


  println(s"<*> operator can be used as extractor and map ${6.some <*> {(_:Int) + 3}.some}")
  println(s"|@| operator can be used as extractor and map then used to apply on function${(6.some |@| 5.some){_ + _}}")


  //Opertions on List]
  println(s"<*> operator  ${(List(1,2) <*> List{(_:Int)+3})}")

  //Sequencing on list
//  println(s"sequening the list ${sequenceA(List(List(1,2)))}")



  val list = List(1,2,3)
  println("If we map id function on Functor it should return same Functor")
  println((list map {identity}) === list)
  println("Composing two functions and then mapping the resulting" +
    " function over a functor should be same as first mapping one function over the functor and then mapping the other one.")
  println(list map {{(_: Int) * 3} map {(_: Int) + 1}})
  println(list map {(_: Int) * 3} map {(_: Int) + 1})
  val x = {(_:Int) * 3}


}
