package com.datawizards.class2jdbc

import com.datawizards.TestModel.{Person, PersonV2}
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
    val data = Seq(PersonV2("p1", 1, Some("t1"), None), PersonV2("p2", 2, None, Some(2000L)))

    val expected = Seq(
      """INSERT INTO PersonV2(name,age,title,salary) VALUES('p1',1,'t1',null)""",
      """INSERT INTO PersonV2(name,age,title,salaryu) VALUES('p2',2,null,2000)"""
    )

    assertResult(expected) { generateInserts(data) }
  }

}
