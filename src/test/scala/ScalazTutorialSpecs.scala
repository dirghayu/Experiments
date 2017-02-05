import org.scalatest.{FlatSpec, Matchers}

import  scalaz._
import Scalaz._


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
}