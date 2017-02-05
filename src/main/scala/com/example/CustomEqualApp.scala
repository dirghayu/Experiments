package com.example

import scalaz._
import Scalaz._



object CustomEqualApp extends App{


  case class TrafficLight(name: String)
  val red = TrafficLight("red")
  val yellow = TrafficLight("yellow")
  val green = TrafficLight("green")

  implicit val TrafficLightEqual : Equal[TrafficLight] = Equal.equal(_ == _)

  println(red === yellow)


  //Below doesn't compile

//  sealed trait TrafficLight
//  case object ORed extends TrafficLight
//  case object OYellow extends TrafficLight
//  implicit val trafficLightEquals : Equal[TrafficLight] = Equal.equals(_ == _)


}
