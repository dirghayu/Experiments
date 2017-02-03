import org.scalatest.{FlatSpec, Matchers}

import scalaz.Scalaz._
import scalaz._


class ScalazTutorialTypeClassesSpecs extends FlatSpec with Matchers {
  "Equal typeclass" should "compare same object and give compilation errors when comparing different objects" in {
    ScalazTutorialTypeClassesSpecs.compareStrings("hello", "hello") shouldBe true
    ScalazTutorialTypeClassesSpecs.compareStrings("hello", "world") shouldBe false
  }
}

object ScalazTutorialTypeClassesSpecs {
  def compareStrings(first:String,  second: String):Boolean = first === second

//  def compareAppleAndOrangs = "apple" === 0 ///Gives compilation error type mismatch
}