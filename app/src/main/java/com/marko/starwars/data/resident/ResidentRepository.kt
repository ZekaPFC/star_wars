package com.marko.starwars.data.resident

import io.reactivex.Observable

interface ResidentRepository {

    fun getResident(residentId: Int): Observable<Resident>

    fun saveResidentToDB(resident: Resident)



}