package com.icehousecorp.maunorafiq.data.weather.entity.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import javax.annotation.Generated

@Generated("org.jsonschema2pojo")
class Wind {

    /**

     * @return
     * *     The speed
     */
    /**

     * @param speed
     * *     The speed
     */
    @SerializedName("speed")
    @Expose
    var speed: Double? = null
    /**

     * @return
     * *     The deg
     */
    /**

     * @param deg
     * *     The deg
     */
    @SerializedName("deg")
    @Expose
    var deg: Double? = null

}
