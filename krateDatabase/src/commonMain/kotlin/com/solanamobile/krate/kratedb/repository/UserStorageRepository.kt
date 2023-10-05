package com.solanamobile.krate.kratedb.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.moriatsushi.koject.Provides
import com.solanamobile.krate.kratedb.Database
import com.solanamobile.krate.kratedb.DriverFactory
import com.solanamobile.krate.kratedb.LoggedInUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

@Provides
class UserStorageRepository(
    driverFactory: DriverFactory
) {

    private val db: Database = Database(driverFactory.createDriver())

    val loggedInUser: Flow<LoggedInUser?> =
        db.authUserQueries
            .selectAll()
            .asFlow()
            .mapToOneOrNull(Dispatchers.IO)

    fun saveLoggedInUser(
        token: String,
        id: String,
        displayName: String,
        imageUrl: String,
    ) {
        db.authUserQueries.insert(token, id, displayName, imageUrl)
    }

    fun clearLoggedInUser() {
        db.authUserQueries.clear()
    }
}