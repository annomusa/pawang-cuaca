package com.icehousecorp.maunorafiq.data.weather.entity.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import javax.annotation.Generated

@Generated("org.jsonschema2pojo")
class Sys {

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
     * *     The country
     */
    /**

     * @param country
     * *     The country
     */
    @SerializedName("country")
    @Expose
    var country: String? = null
    /**

     * @return
     * *     The sunrise
     */
    /**

     * @param sunrise
     * *     The sunrise
     */
    @SerializedName("sunrise")
    @Expose
    var sunrise: Int? = null
    /**

     * @return
     * *     The sunset
     */
    /**

     * @param sunset
     * *     The sunset
     */
    @SerializedName("sunset")
    @Expose
    var sunset: Int? = null

}
