import com.example.{COption, CSome}
import org.scalacheck.Test
import org.scalatest.{FlatSpec, Matchers}

import scalaz._
import Scalaz._
import scalacheck.ScalazProperties._
import scalacheck.ScalaCheckBinding._


class ScalazTutorialSpecs extends FlatSpec with Matchers {
  def addInts = ((a:Int, b: Int, c: Int) => a +b + c).curried


  "<*>" should "be able to used like map" in {
    def sumUsingScalaz(x:Option[Int], y: Option[Int], z: Option[Int]): Option[Int] = x <*> (y <*> (z  map addInts) )

    val x: Option[Int] = 2.some // Scalaz way of saying Some(2)
    val y: Option[Int] = 3.some
    val z: Option[Int] = 4.some
    sumUsingScalaz(x, y ,z) shouldBe 9.some
  }

  it should "return None if one of the option is none" in {
    def sumUsingScalaz(x:Option[Int], y: Option[Int], z: Option[Int]): Option[Int] =
      x <*> (y <*> (z  map addInts) ) // <<<====== <*>  defined in ApplyOps ,
                                      // which uses implicit conversion to Apply Functor Typeclass from Option
                                      //

    val x: Option[Int] = 2.some // Scalaz way of saying Some(2)
    val y: Option[Int] = 3.some
    val z: Option[Int] = none

    sumUsingScalaz(x, y ,z) shouldBe none
  }



  "option" should "check boolean value and if it true should return the value passed to option" in {
    def boolF(month:Int): Option[String] = {
      val isWinter = (month >4 &&  month <9)

      isWinter.option("Winter")///This is better way of writing if else cases
    }

    boolF(5) shouldBe "Winter".some
    boolF(1) shouldBe none

  }

  "parseXXX" should "parse the String" in {
    "6".parseInt shouldBe Success(6)
    val failedRes = "six".parseInt
    failedRes.isFailure shouldBe true
  }
  "ternary operator" should "work as in java ternary operator" in {

    def isFirstHalf(month: Int) = (  month <= 6)

    isFirstHalf(1) ? "Winter" | "Summer" shouldBe "Winter"
    isFirstHalf(8) ? "Winter" | "Summer" shouldBe "Summer"
  }

  "allpairs" should "give all combinations of number pairing from the list" in {
    List(1,2,3,4).allPairs shouldBe List((1,2),(2,3),(3,4),(1,3),(2,4),(1,4))
  }
  "fold" should "work on all options" in {
    Option("hello").fold(-1)(_.length) shouldBe 5
    none[String].fold(-1)(_.length) shouldBe -1
  }

  "FunctorSpec" should "verify if the Functor created follows the Functor laws" in {


//    Test.check(functor.laws[Option]., Test.Parameters.default)
    //TODO only works in Console. Figure out how Test.check can be incorporated
    functor.laws[Option].check()
    pending
  }

  "COption" should "verify that functor law fails as identity is not giving the same object back" in {


    import COption._
    (CSome(0, "ho"): COption[String]).map{s:String=>s +"ha"} shouldBe CSome(1,"hoha")
    (CSome(0, "ho"): COption[String]).map{identity} shouldBe CSome(1,"ho") // This breaks Functor law

    import org.scalacheck.{Gen, Arbitrary}
    implicit def COptionArbiterary[A](implicit a: Arbitrary[A]): Arbitrary[COption[A]] = a map { a => (CSome(0, a): COption[A]) }

    //TODO this should run in console. but is throwing strange exception
    //Exception encountered when attempting to run a suite with class name: ScalazTutorialSpecs *** ABORTED ***
//        functor.laws[COption].check()

    pending
  }
}