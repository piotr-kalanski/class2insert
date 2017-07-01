package com.datawizards.class2jdbc

import com.datawizards.TestModel.{Person, PersonV3}
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Class2JdbcTest extends FunSuite {

  test("Generate inserts") {
    val data = Seq(Person("p1", 1), Person("p2", 2))

    val expected = Seq(
      """INSERT INTO Person(name,age) VALUES('p1',1)""",
      """INSERT INTO Person(name,age) VALUES('p2',2)"""
    )

    assertResult(expected) { generateInserts(data) }
  }

  test("Generate inserts - custom table name") {
    val data = Seq(Person("p1", 1), Person("p2", 2))

    val expected = Seq(
      """INSERT INTO PEOPLE(name,age) VALUES('p1',1)""",
      """INSERT INTO PEOPLE(name,age) VALUES('p2',2)"""
    )

    assertResult(expected) { generateInserts(data, "PEOPLE") }
  }

  test("Generate inserts - custom table name and columns") {
    val data = Seq(Person("p1", 1), Person("p2", 2))

    val expected = Seq(
      """INSERT INTO PEOPLE(person_name,person_age) VALUES('p1',1)""",
      """INSERT INTO PEOPLE(person_name,person_age) VALUES('p2',2)"""
    )

    assertResult(expected) { generateInserts(data, "PEOPLE", Seq("person_name", "person_age")) }
  }

  test("Generate inserts - option type") {
    val data = Seq(PersonV3("p1", 1, None, Some("t1")), PersonV3("p2", 2, Some(2000L), None))

    val expected = Seq(
      """INSERT INTO PersonV3(name,age,salary,title) VALUES('p1',1,null,'t1')""",
      """INSERT INTO PersonV3(name,age,salary,title) VALUES('p2',2,2000,null)"""
    )

    assertResult(expected) { generateInserts(data) }
  }

}
