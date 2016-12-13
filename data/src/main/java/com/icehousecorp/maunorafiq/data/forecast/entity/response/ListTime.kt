package com.icehousecorp.maunorafiq.data.forecast.entity.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.ArrayList

import javax.annotation.Generated

@Generated("org.jsonschema2pojo")
class ListTime {

    /**

     * @return
     * *     The dt
     */
    /**

     * @param dt
     * *     The dt
     */
    @SerializedName("dt")
    @Expose
    var dt: Int? = null
    /**

     * @return
     * *     The main
     */
    /**

     * @param main
     * *     The main
     */
    @SerializedName("main")
    @Expose
    var main: Main? = null
    /**

     * @return
     * *     The weather
     */
    /**

     * @param weather
     * *     The weather
     */
    @SerializedName("weather")
    @Expose
    var weather: List<Weather> = ArrayList()
    /**

     * @return
     * *     The clouds
     */
    /**

     * @param clouds
     * *     The clouds
     */
    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null
    /**

     * @return
     * *     The wind
     */
    /**

     * @param wind
     * *     The wind
     */
    @SerializedName("wind")
    @Expose
    var wind: Wind? = null
    /**

     * @return
     * *     The rain
     */
    /**

     * @param rain
     * *     The rain
     */
    @SerializedName("rain")
    @Expose
    var rain: Rain? = null
    /**

     * @return
     * *     The sys
     */
    /**

     * @param sys
     * *     The sys
     */
    @SerializedName("sys")
    @Expose
    var sys: Sys_? = null
    /**

     * @return
     * *     The dtTxt
     */
    /**

     * @param dtTxt
     * *     The dt_txt
     */
    @SerializedName("dt_txt")
    @Expose
    var dtTxt: String? = null

}
