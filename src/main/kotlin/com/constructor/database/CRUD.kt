package com.constructor.database

import java.io.Closeable
import java.sql.DriverManager
import java.sql.Statement

class Connection(private val coreConnection: java.sql.Connection) :
    java.sql.Connection by coreConnection, Closeable {

    override fun close() {
        coreConnection.close()
    }

    override fun createStatement(): Statement {
        return coreConnection.createStatement()
    }
}
class CRUD
{
    fun create(id: Int, stringLine : String, num : Int, boolField : Boolean)
    { //
        connect().use {
            val st1 = it.createStatement()
            val resultSet1 = st1.executeQuery(
                """
            INSERT INTO test_table VALUES ($id.ToString() , $stringLine , $num.toString() , $boolField.toString())
            """.trimIndent()
            )
            resultSet1.close()
            st1.close()
        }
    }

    fun read() : ArrayList<String>{
        connect().use {
            val st1 = it.createStatement()
            val resultSet1 = st1.executeQuery(
                """
            SELECT *
              FROM test_table
             ORDER BY id
            """.trimIndent()
            )

            val DataToReturn = arrayListOf<String>()

            // не универсальный код. Решение только для одной таблицы
            while (resultSet1.next()) {
                val a = resultSet1.getString("id")
                val b = resultSet1.getString("stringfield")
                val c = resultSet1.getString("intfield")
                val d = resultSet1.getString("boolfield")

                // плохо знаю пока что синтаксис
                // можно было и через цикл
                DataToReturn.add(a)
                DataToReturn.add(b)
                DataToReturn.add(c)
                DataToReturn.add(d)
            }
            resultSet1.close()
            st1.close()
            return DataToReturn
        }
    }
    fun delete(id : Int)
    {
        val i_id = id.toString()

        connect().use {
            val st1 = it.createStatement()
            try {
                val resultSet1 = st1.executeQuery(
                    """
                DELETE FROM test_table 
                WHERE id = $i_id ;
                """.trimIndent()
                ) // запрос некоректно составлен
                resultSet1.close()
                st1.close()
            }
            catch (e : Exception)
            {
                println("Dont work")
                println(e.message)
            }
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
}