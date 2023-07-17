package com.constructor.database

import java.io.Closeable
import java.sql.*

class Connection(private val coreConnection: java.sql.Connection) :
    java.sql.Connection by coreConnection, Closeable {

    override fun close() {
        coreConnection.close()
    }

    override fun createStatement(): Statement {
        return coreConnection.createStatement()
    }
}

fun connect(): Connection {
    val url = "jdbc:postgresql://localhost:5432/Test"
    val user = "postgres"
    val password = "tyX7~Lp3+"

    val c = DriverManager.getConnection(url, user, password)
    c.autoCommit = false
    return Connection(c)
}

class CRUD
{
    // надо решить подобное говно
    val url = "jdbc:postgresql://localhost:5432/Test"
    val user = "postgres"
    val password = "tyX7~Lp3+"

    val c = DriverManager.getConnection(url, user, password)
    val temp = c
    //mb out temp. how it in kotlin?..

    fun create(id: Int, stringLine : String, num : Int, boolField : Boolean)
    {
        //done!

        try {
            val resultSet1 = """
        INSERT INTO test_table
        (id, stringfield, intfield, boolfield) VALUES (?, ?, ?, ?)
        """

            val preparedStatement: PreparedStatement = temp.prepareStatement(resultSet1)
            preparedStatement.setInt(1, id)
            preparedStatement.setString(2, stringLine)
            preparedStatement.setInt(3, num)
            preparedStatement.setBoolean(4, boolField)

            val rows = preparedStatement.executeUpdate()
            println("entry add: $rows")
        }
        catch (e : Exception)
        {
            println("Entry not added")
        }
    }

    fun read() : MutableList<String>
    {
        val result : MutableList<String> = mutableListOf()

       val querySql = """SELECT *
               FROM test_table
               ORDER BY id
        """
       val createStatement : Statement = temp.createStatement()
       val data = createStatement.executeQuery(querySql)

       while (data.next())
       {
            // mb Map?
       }
       return result
    }
    fun delete(id : Int)
    {
        // done
        val preparedStatement : PreparedStatement = temp.prepareStatement("DELETE FROM test_table WHERE Id = ?")
        preparedStatement.setInt(1, id)
        preparedStatement.executeUpdate()
    }

}