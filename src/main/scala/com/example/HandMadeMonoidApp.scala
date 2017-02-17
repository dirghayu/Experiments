package com.example


object HandMadeMonoidApp {

  trait MonoidOp[A]{

    val F: Monoid[A]

  }
  trait Monoid[A] {
    def associativeOp(a:A, b:A) : A
    def identity : A // Identity
  }

  object IntAddMonoid extends Monoid[Int] {
    override def associativeOp(a: Int, b: Int): Int = a +b

    override def identity: Int = 0
  }
  def sumInts(xs: List[Int],m : Monoid[Int]) = xs.fold(m.identity)(m.associativeOp)
  def sumMonoids[A](xs: List[A])(implicit m : Monoid[A]) = xs.fold(m.identity)(m.associativeOp)
  def sumImplicitlyMonoids[A:Monoid](xs: List[A]) :A = {
    val m: Monoid[A] = implicitly[Monoid[A]]
    FoldLeftList.foldLeft(xs, m.identity, m.associativeOp)
  }

  def sumImplicitlyMonoidFold[M[_]: FoldLeft, A: Monoid](xs: M[A]): A = {
    val m = implicitly[Monoid[A]]
    val fold = implicitly[FoldLeft[M]]

    fold.foldLeft(xs, m.identity, m.associativeOp)
  }


  trait FoldLeft[F[_]]{
    def foldLeft[A,B](xs: F[A], b : B, f: (B , A)=>B): B
  }
  object FoldLeft{
    implicit val foldLeftList1: FoldLeft[List] = new FoldLeft[List]{
      override def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B): B = xs.foldLeft(b)(f)
    }
  }
  object FoldLeftList {
    def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B): B = xs.foldLeft(b)(f)
  }


  def main(args: Array[String]): Unit = {
    println("expected: 6 Actual:"+sumInts(List(1,2,3), IntAddMonoid));



    implicit val intAddMonoid = IntAddMonoid
    println("expected: 6 Actual:"+sumMonoids(List(1,2,3)));
    println("sumImplicitlyMonoids:::expected: 6 Actual:"+sumImplicitlyMonoids(List(1,2,3)));
    println("sumImplicitlyMonoidFold:::expected: 6 Actual:"+sumImplicitlyMonoidFold(List(1,2,3)));
  }
}
