# class2insert

SQL insert statements generator based on Scala case class.

[![Build Status](https://api.travis-ci.org/piotr-kalanski/class2insert.png?branch=development)](https://api.travis-ci.org/piotr-kalanski/class2insert.png?branch=development)
[![codecov.io](http://codecov.io/github/piotr-kalanski/class2insert/coverage.svg?branch=development)](http://codecov.io/github/piotr-kalanski/class2insert/coverage.svg?branch=development)
[<img src="https://img.shields.io/maven-central/v/com.github.piotr-kalanski/class2insert_2.11.svg?label=latest%20release"/>](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22class2insert_2.11%22)
[![Stories in Ready](https://badge.waffle.io/piotr-kalanski/class2insert.png?label=Ready)](https://waffle.io/piotr-kalanski/class2insert)
[![License](http://img.shields.io/:license-Apache%202-red.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)

# Table of contents

- [Goals](#goals)
- [Getting started](#getting-started)
- [Examples](#examples)

# Goals

- Speed up unit testing when using Spark
- Enable switching between Spark execution engine and Scala collections depending on use case, especially size of data without changing implementation

# Getting started

## Include dependencies

```scala
"com.github.piotr-kalanski" % "class2insert_2.11" % "0.1.0"
```

or

```xml
<dependency>
    <groupId>com.github.piotr-kalanski</groupId>
    <artifactId>class2insert_2.11</artifactId>
    <version>0.1.0</version>
</dependency>
```

# Examples