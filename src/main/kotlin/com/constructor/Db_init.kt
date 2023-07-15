package database.models

import java.io.Closeable
import java.sql.DriverManager
import java.sql.Statement
import java.text.SimpleDateFormat

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
    val password = "pass"

    val c = DriverManager.getConnection(url, user, password)
    c.autoCommit = false
    return Connection(c)
}
fun read() {
    connect().use {
        val st1 = it.createStatement()
        val resultSet1 = st1.executeQuery(
            """
            SELECT *
              FROM test_table
             ORDER BY id
            """.trimIndent()
        )
        // read
        while (resultSet1.next()) {
            val a = resultSet1.getLong("id")   // same as resultSet1.getLong(1)
            val b = resultSet1.getString("stringfield") // same as resultSet1.getString(2)
            val c = resultSet1.getString("intfield") // same as resultSet1.getString(2)
            val d = resultSet1.getString("boolfield") // same as resultSet1.getString(2)
            println("$a \n  $b \n $c \n $d \n")
        }
        resultSet1.close()
        st1.close()
    }
}