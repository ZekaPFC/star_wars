package com.marko.starwars.data.resident

import io.reactivex.Observable
import io.reactivex.Single

interface ResidentDataSource {

    fun getResident(residentId: Int): Single<Resident>

    fun getResidents(): Observable<List<Resident>>
}