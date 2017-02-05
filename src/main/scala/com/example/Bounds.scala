package com.example

object ViewBounds extends App {

  /** Excepcts impliit variable to be available*/
   class Names[ T <% Ordered[T]](val firstName: T, val lastName: T){
     def greater = if(firstName > lastName) firstName else lastName
   }

  val n =new Names("D", "K")
  println(n.greater)
}

object TypeBounds extends App {

  class Animal
  class Dog extends Animal
  class Puppy extends Dog

  object AnimalCarer {
    def display(t:Dog) = println(t)
    def displayWithUpperBound[T <: Dog](t:T) = println(t)
    def displayWithLowerBound[T >: Animal](t:T) = println(t)
  }
  val animal = new Animal
  val dog  = new Dog
  val puppy = new Puppy


//  AnimalCarer.display(animal)
  AnimalCarer.display(dog)
  AnimalCarer.display(puppy)

//  AnimalCarer.displayWithUpperBound(animal)
  AnimalCarer.displayWithUpperBound(dog)
  AnimalCarer.displayWithUpperBound(puppy)

  AnimalCarer.displayWithLowerBound(animal)
  AnimalCarer.displayWithLowerBound(dog)
  AnimalCarer.displayWithLowerBound(puppy)





}

