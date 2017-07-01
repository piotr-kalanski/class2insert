package com.datawizards

import java.sql.{Connection, ResultSet}

import shapeless._
import com.datawizards.csv2class._

import scala.collection.mutable.ListBuffer
import scala.reflect.ClassTag
import scala.util.{Failure, Success, Try}

package object jdbc2class {

  object selectTable {
    def apply[T]: SelectTable[T] = new SelectTable[T]
  }

  class SelectTable[T] {
    def apply[L <: HList](connection: Connection)
                         (implicit
                            ct: ClassTag[T],
                            gen: Generic.Aux[T, L],
                            fromRow: FromRow[L]
    ): (Iterable[T], Iterable[Throwable]) =
      this.apply(connection, ct.runtimeClass.getName, Seq.empty)

    def apply[L <: HList](connection: Connection, table: String)
                         (implicit
                            ct: ClassTag[T],
                            gen: Generic.Aux[T, L],
                            fromRow: FromRow[L]
    ): (Iterable[T], Iterable[Throwable]) =
      this.apply(connection, table, Seq.empty)

    def apply[L <: HList](connection: Connection, table: String, columns: Iterable[String])
                         (implicit
                           ct: ClassTag[T],
                           gen: Generic.Aux[T, L],
                           fromRow: FromRow[L]
    ): (Iterable[T], Iterable[Throwable]) = {
      val rs = connection.createStatement().executeQuery(s"SELECT * FROM $table")
      parseResultSet[T](rs, columns)
    }
  }

  object parseResultSet {
    def apply[T]: ParseResultSet[T] = new ParseResultSet[T] {}
  }

  trait ParseResultSet[T] {
    def apply[L <: HList](rs: ResultSet, columns: Iterable[String])
                         (implicit
                          ct: ClassTag[T],
                          gen: Generic.Aux[T, L],
                          fromRow: FromRow[L]
                         ): (Iterable[T], Iterable[Throwable]) = {
      val rowParserFor = new RowParser[T] {}
      val convertedLines = new ListBuffer[Try[T]]
      val metadata = rs.getMetaData
      val resultColumns = (for(i <- 1 to metadata.getColumnCount) yield metadata.getColumnName(i)).toSet
      val columnCount = metadata.getColumnCount
      while(rs.next()) {
        val row =
          if(columns.isEmpty) (1 to columnCount).map(i => rs.getString(i))
          else columns.map(c => if(resultColumns.contains(c)) rs.getString(c) else "")
        convertedLines += rowParserFor(row.toList)
      }

      val (s,f) = convertedLines.span {
        case _:Success[T] => true
        case _:Failure[T] => false
      }

      (
        s.map (x => x.get),
        f.map (x => x.failed.get)
      )
    }
  }

}
