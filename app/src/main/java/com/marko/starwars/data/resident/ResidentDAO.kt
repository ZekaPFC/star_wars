package com.marko.starwars.data.resident

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
abstract class ResidentDAO {
    @Query("SELECT * FROM resident WHERE id = :residentId")
    abstract fun getResident(residentId: Int): Single<Resident>

    @Query("SELECT * FROM resident")
    abstract fun getResidents(): Observable<List<Resident>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveResident(resident: Resident): Completable
}