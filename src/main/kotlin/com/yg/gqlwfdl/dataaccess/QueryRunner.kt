package com.yg.gqlwfdl.dataaccess

import com.yg.gqlwfdl.logMessage
import io.reactiverse.pgclient.PgPool
import io.reactiverse.pgclient.PgRowSet
import io.reactiverse.pgclient.Row
import io.vertx.core.AsyncResult
import io.vertx.core.Handler
import org.jooq.Query
import org.jooq.conf.ParamType
import org.springframework.stereotype.Component
import kotlin.coroutines.experimental.suspendCoroutine

/**
 * Responsible for executing queries.
 */
interface QueryRunner {
    /**
     * Executes the passed in [query] (which would typically be a SELECT query) and returns the [Row]s with the returned
     * data.
     */
    suspend fun fetchResults(query: Query): List<Row>
}

/**
 * An object used to run queries against a Postgres database.
 *
 * @property connectionPool The database connection pool.
 */
@Component
class PgQueryRunner(private val connectionPool: PgPool) : QueryRunner {
    override suspend fun fetchResults(query: Query): List<Row> =
            awaitResult<PgRowSet> {
                val sql = query.getSQL(ParamType.INLINED)
                logMessage("Executing query: $sql")
                connectionPool.query(sql, it)
            }.toList()
}

/**
 * Executes the passed in [block], which is one of the reactive-pg-client methods which relies on a vert.x [Handler],
 * and wraps it in a coroutine, which is suspended while awaiting a response from the database, and resumed when the
 * response is received.
 */
private suspend inline fun <T> awaitResult(crossinline block: (Handler<AsyncResult<T>>) -> Unit): T {
    return suspendCoroutine { cont ->
        try {
            block(Handler { asyncResult ->
                if (asyncResult.succeeded()) cont.resume(asyncResult.result())
                else cont.resumeWithException(asyncResult.cause())
            })
        } catch (e: Exception) {
            cont.resumeWithException(e)
        }
    }
}