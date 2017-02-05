package com.example


object CanTrustyApp extends App{


  trait CanTruthy[A] {
    self=>
    def truthys(a: A): Boolean
  }
  object CanTruthy{
    def apply[A] (implicit ev: CanTruthy[A]): CanTruthy[A] = ev
    def truthys[A] ( f: A=> Boolean ) : CanTruthy[A] = new CanTruthy[A] {
      override def truthys(a: A): Boolean = f(a)
    }
  }

  trait CanTruthyOps[A]{
    def self: A
    implicit def F: CanTruthy[A]
    final def truthy : Boolean = F.truthys(self)
  }

  object ToCanIsTruthysOps{
    implicit def toCanTrusthysOps [A] (v: A) (implicit ev: CanTruthy[A]) = {
      new CanTruthyOps[A] {
        def self  = v
        implicit def F= ev
      }
    }
  }
  import ToCanIsTruthysOps._


  implicit val intCanTruthy: CanTruthy[Int] = CanTruthy.truthys({
    case 0 => false
    case _   => true
  })
//
//  implicit val listCanTruthy: CanTruthy[List[_]] = CanTruthy.truthys({
//    case Nil => false
//    case _   => true
//  })


  1.truthy
}
