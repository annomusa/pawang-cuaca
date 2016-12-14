package com.icehousecorp.maunorafiq.domain.city.interactor

import com.icehousecorp.maunorafiq.domain.UseCase
import com.icehousecorp.maunorafiq.domain.city.repository.CityRepository

import javax.inject.Inject

import rx.Observable

/**
 * Created by maunorafiq on 12/5/16.
 */

class GetCity
@Inject
constructor(private val cityRepository: CityRepository) : UseCase() {

    override fun buildUseCaseObservable(): Observable<*> {
        return this.cityRepository.getCity()
    }
}
