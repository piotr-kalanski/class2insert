package com.datawizards.jdbc2class

import java.sql.DriverManager

import com.datawizards.TestModel.{Person, PersonV2, PersonV3}
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Jdbc2ClassTest extends FunSuite {

  test("Select table") {
    Class.forName("org.h2.Driver")
    val connectionString = "jdbc:h2:mem:test"
    val connection = DriverManager.getConnection(connectionString, "", "")
    connection.createStatement().execute("create table people(name VARCHAR, age INT)")
    connection.createStatement().execute("insert into people values('r1', 1)")
    connection.createStatement().execute("insert into people values('r2', 2)")

    val result = selectTable[Person](connection, "people")._1
    val expected = Seq(Person("r1", 1), Person("r2", 2))
    assertResult(expected)(result)

    connection.close()
  }

  test("Select subset of table columns - old version") {
    Class.forName("org.h2.Driver")
    val connectionString = "jdbc:h2:mem:test"
    val connection = DriverManager.getConnection(connectionString, "", "")
    connection.createStatement().execute("create table peoplev2(NAME VARCHAR, AGE INT, SALARY INT)")
    connection.createStatement().execute("insert into peoplev2 values('r1', 1, 10)")
    connection.createStatement().execute("insert into peoplev2 values('r2', 2, 11)")

    val result = selectTable[Person](connection, "peoplev2", Seq("NAME", "AGE"))._1
    val expected = Seq(Person("r1", 1), Person("r2", 2))
    assertResult(expected)(result)

    connection.close()
  }

  test("Select more columns - new version") {
    Class.forName("org.h2.Driver")
    val connectionString = "jdbc:h2:mem:test"
    val connection = DriverManager.getConnection(connectionString, "", "")
    connection.createStatement().execute("create table peoplev2(NAME VARCHAR, AGE INT, SALARY INT)")
    connection.createStatement().execute("insert into peoplev2 values('r1', 1, 10)")
    connection.createStatement().execute("insert into peoplev2 values('r2', 2, 11)")

    val result = selectTable[PersonV3](connection, "peoplev2", Seq("NAME", "AGE", "SALARY", "TITLE"))._1
    val expected = Seq(PersonV3("r1", 1, Some(10L), None), PersonV3("r2", 2, Some(11L), None))
    assertResult(expected)(result)

    connection.close()
  }

}
