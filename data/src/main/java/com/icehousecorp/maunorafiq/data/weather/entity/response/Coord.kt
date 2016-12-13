package com.icehousecorp.maunorafiq.data.weather.entity.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import javax.annotation.Generated

@Generated("org.jsonschema2pojo")
class Coord {

    /**

     * @return
     * *     The lon
     */
    /**

     * @param lon
     * *     The lon
     */
    @SerializedName("lon")
    @Expose
    var lon: Double? = null
    /**

     * @return
     * *     The lat
     */
    /**

     * @param lat
     * *     The lat
     */
    @SerializedName("lat")
    @Expose
    var lat: Double? = null

}
