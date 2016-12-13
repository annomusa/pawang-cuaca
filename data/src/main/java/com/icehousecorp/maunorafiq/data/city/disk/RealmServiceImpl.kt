package com.icehousecorp.maunorafiq.data.city.disk

import com.icehousecorp.maunorafiq.data.city.entity.CityEntity

import java.util.ArrayList

import javax.inject.Inject
import javax.inject.Singleton

import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort

/**
 * Created by maunorafiq on 12/1/16.
 */

@Singleton
class RealmServiceImpl
@Inject
constructor() : RealmService {

    private val TAG = this.javaClass.simpleName

    private val realm: Realm

    init {
        this.realm = Realm.getDefaultInstance()
    }

    override fun get(): List<CityEntity> {
        val realmResults = realm.where(CityEntity::class.java).findAll().sort("ordinal", Sort.ASCENDING)

        val cityEntityList = ArrayList<CityEntity>()
        cityEntityList.addAll(realmResults)

        return cityEntityList
    }

    override fun put(cityName: String): Boolean {
        val isEmpty = realm.where(CityEntity::class.java).equalTo("cityName", cityName).findAll().size == 0
        val position = realm.where(CityEntity::class.java).findAll().size
        if (isEmpty) {
            realm.executeTransaction { realm1 ->
                val cityEntity = realm1.createObject(CityEntity::class.java, cityName)
                cityEntity.ordinal = position
            }
            return true
        }
        return false
    }

    override fun update(cityName: String, ordinal: Int) {

    }
}
