package com.datawizards

object TestModel {
  case class Person(name: String, age: Int)
  case class PersonV2(name: String, age: Int, salary: Option[Long])
  case class PersonV3(name: String, age: Int, salary: Option[Long], title: Option[String])
}
