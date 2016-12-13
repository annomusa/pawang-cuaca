package com.icehousecorp.maunorafiq.data.city.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by maunorafiq on 12/1/16.
 */
@RealmClass
open class CityEntity : RealmObject {

    @PrimaryKey
    var cityName: String? = null
    var ordinal: Int = 0

    constructor() {
    }

    constructor(cityName: String) {
        this.cityName = cityName
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("*** City Entity ***\n")
        stringBuilder.append("City name : " + this.cityName + "\n")
        stringBuilder.append("City Ordinal : " + this.ordinal + "\n")

        return stringBuilder.toString()
    }
}
