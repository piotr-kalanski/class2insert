# class2sql

SQL insert and select statements generator based on Scala case class.

[![Build Status](https://api.travis-ci.org/piotr-kalanski/class2sql.png?branch=development)](https://api.travis-ci.org/piotr-kalanski/class2sql.png?branch=development)
[![codecov.io](http://codecov.io/github/piotr-kalanski/class2sql/coverage.svg?branch=development)](http://codecov.io/github/piotr-kalanski/class2sql/coverage.svg?branch=development)
[<img src="https://img.shields.io/maven-central/v/com.github.piotr-kalanski/class2sql_2.11.svg?label=latest%20release"/>](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22class2sql_2.11%22)
[![Stories in Ready](https://badge.waffle.io/piotr-kalanski/class2sql.png?label=Ready)](https://waffle.io/piotr-kalanski/class2sql)
[![License](http://img.shields.io/:license-Apache%202-red.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)

# Table of contents

- [Goals](#goals)
- [Getting started](#getting-started)
- [Examples](#examples)

# Goals

- Provide simple tool for generating SQL inserts based on Scala case classes
- Provide easy API to query JDBC table and map result to Scala case class

# Getting started

## Include dependencies

```scala
"com.github.piotr-kalanski" % "class2sql_2.11" % "0.1.0"
```

or

```xml
<dependency>
    <groupId>com.github.piotr-kalanski</groupId>
    <artifactId>class2sql_2.11</artifactId>
    <version>0.1.0</version>
</dependency>
```

# Examples

## Generate inserts

```scala
import com.datawizards.class2jdbc._

case class Person(name: String, age: Int)

val data = Seq(Person("p1", 1), Person("p2", 2))
generateInserts(data)
```

```sql
INSERT INTO Person(name,age) VALUES('p1',1)
INSERT INTO Person(name,age) VALUES('p2',2)
```

## Select from table

```scala
import com.datawizards.jdbc2class._

case class Person(name: String, age: Int)

val connectionString = "jdbc:h2:mem:test"
val connection = DriverManager.getConnection(connectionString, "", "")
selectTable[Person](connection, "people")
```