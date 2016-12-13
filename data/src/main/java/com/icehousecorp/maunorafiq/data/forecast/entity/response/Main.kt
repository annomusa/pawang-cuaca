package com.icehousecorp.maunorafiq.data.forecast.entity.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import javax.annotation.Generated

@Generated("org.jsonschema2pojo")
class Main {

    /**

     * @return
     * *     The temp
     */
    /**

     * @param temp
     * *     The temp
     */
    @SerializedName("temp")
    @Expose
    var temp: Double? = null
    /**

     * @return
     * *     The tempMin
     */
    /**

     * @param tempMin
     * *     The temp_min
     */
    @SerializedName("temp_min")
    @Expose
    var tempMin: Double? = null
    /**

     * @return
     * *     The tempMax
     */
    /**

     * @param tempMax
     * *     The temp_max
     */
    @SerializedName("temp_max")
    @Expose
    var tempMax: Double? = null
    /**

     * @return
     * *     The pressure
     */
    /**

     * @param pressure
     * *     The pressure
     */
    @SerializedName("pressure")
    @Expose
    var pressure: Double? = null
    /**

     * @return
     * *     The seaLevel
     */
    /**

     * @param seaLevel
     * *     The sea_level
     */
    @SerializedName("sea_level")
    @Expose
    var seaLevel: Double? = null
    /**

     * @return
     * *     The grndLevel
     */
    /**

     * @param grndLevel
     * *     The grnd_level
     */
    @SerializedName("grnd_level")
    @Expose
    var grndLevel: Double? = null
    /**

     * @return
     * *     The humidity
     */
    /**

     * @param humidity
     * *     The humidity
     */
    @SerializedName("humidity")
    @Expose
    var humidity: Double? = null
    /**

     * @return
     * *     The tempKf
     */
    /**

     * @param tempKf
     * *     The temp_kf
     */
    @SerializedName("temp_kf")
    @Expose
    var tempKf: Double? = null

}
