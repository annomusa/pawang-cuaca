package com.icehousecorp.maunorafiq.domain.city

/**
 * Created by maunorafiq on 12/5/16.
 */

class City(val cityName: String?) {

    var ordinal: Int = 0

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("*** City ***\n")
        stringBuilder.append("City name : " + this.cityName + "\n")
        stringBuilder.append("City ordinal : " + this.ordinal + "\n")
        stringBuilder.append("************\n")

        return stringBuilder.toString()
    }
}
