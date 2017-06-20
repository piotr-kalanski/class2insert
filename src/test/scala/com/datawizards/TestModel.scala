package com.datawizards

object TestModel {
  case class Person(name: String, age: Int)
  case class PersonV2(name: String, age: Int, title: Option[String], salary: Option[Long])
}
