import org.scalatest.{FlatSpec, Matchers}

import scalaz.Scalaz._
import scalaz._
object scalazMethods {
  def compareStrings(first:String,  second: String):Boolean = first === second
  // HAD to move here becuse there is another method ==== in Spcs which collide

  //  def compareAppleAndOrangs = "apple" === 0 ///Gives compilation error type mismatch
  //  def compareAppleAndOrangs = Some("apple") === "Oranges" ///Gives compilation error type mismatch

}

class ScalazTutorialTypeClassesSpecs extends FlatSpec with Matchers {
  "EqualOps typeclass" should "compare same object and give compilation errors when comparing different objects" in {
    scalazMethods.compareStrings("hello", "hello") shouldBe true
    scalazMethods.compareStrings("hello", "world") shouldBe false

    "hello" =/= "world" shouldBe true
    "hello" =/= "hello" shouldBe false
//    "hello" =/= 1 shouldBe false // compailation error

    1 > 2.0 shouldBe false // With basic scala it compiles
//    1 gt 2.0 shouldBe false // Compilation error. can not compare int with float
    1 gt 2 shouldBe false

    1.0 ?|? 2.0 shouldBe Ordering.LT
    2.0 ?|? 1.0 shouldBe Ordering.GT
    1.0 ?|? 1.0 shouldBe Ordering.EQ

    3.show.println
  }

"Enums" should "return list" in {
  'a' |-> 'e' shouldBe List('a','b','c','d','e')
}
  "Functor typeclass" should "able to map" in {
    def addSix[F[_]](toAdd: F[Int])(implicit mapper: Functor[F]): F[Int] = {
      mapper.map(toAdd)(_ + 6)
    }

    addSix(3.some) shouldBe 9.some
    addSix(none[Int]) shouldBe none
    addSix(List(1,2,3)) shouldBe List(7, 8, 9)
    addSix(List.empty[Int]) shouldBe List.empty
  }

  "Monad typeclass"
}


