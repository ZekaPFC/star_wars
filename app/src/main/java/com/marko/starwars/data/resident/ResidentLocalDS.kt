package com.marko.starwars.data.resident

import io.reactivex.Observable
import io.reactivex.Single

class ResidentLocalDS: ResidentDataSource {
    override fun getResident(residentId: Int): Single<Resident> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getResidents(): Observable<List<Resident>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}