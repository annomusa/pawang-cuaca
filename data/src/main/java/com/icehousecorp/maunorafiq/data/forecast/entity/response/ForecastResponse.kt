package com.icehousecorp.maunorafiq.data.forecast.entity.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.ArrayList

import javax.annotation.Generated

@Generated("org.jsonschema2pojo")
class ForecastResponse {

    /**

     * @return
     * *     The city
     */
    /**

     * @param city
     * *     The city
     */
    @SerializedName("city")
    @Expose
    var city: City? = null
    /**

     * @return
     * *     The cod
     */
    /**

     * @param cod
     * *     The cod
     */
    @SerializedName("cod")
    @Expose
    var cod: String? = null
    /**

     * @return
     * *     The message
     */
    /**

     * @param message
     * *     The message
     */
    @SerializedName("message")
    @Expose
    var message: Double? = null
    /**

     * @return
     * *     The cnt
     */
    /**

     * @param cnt
     * *     The cnt
     */
    @SerializedName("cnt")
    @Expose
    var cnt: Int? = null
    /**

     * @return
     * *     The list
     */
    /**

     * @param list
     * *     The list
     */
    @SerializedName("list")
    @Expose
    var list: List<ListTime> = ArrayList()

}
