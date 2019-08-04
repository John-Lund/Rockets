package com.example.android.rockets.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.spacexdata.com/v3/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface RocketApiService {
    @GET("rockets")
    fun getRocketsAsync():
            Deferred<String>
}

object RocketApi {
    val retrofitService: RocketApiService by lazy { retrofit.create(RocketApiService::class.java) }
}

fun convertStringToRocketsList(response: String): ArrayList<Rocket> {
    val rocketsList = arrayListOf<Rocket>()
    val jsonArray = JSONArray(response)
    if (jsonArray.length() == 0) {
        return rocketsList
    }
    for (index in 0 until jsonArray.length()) {

        val jsonObject: JSONObject? = jsonArray.optJSONObject(index)
        if (null != jsonObject) {
            val id = jsonObject.optInt("id", 0)
            val stages = jsonObject.optInt("stages", 0)
            val rocketName = jsonObject.optString("rocket_name", "no name")
            val imagesJsonArray = jsonObject.optJSONArray("flickr_images")
            val imageUrlString: String = imagesJsonArray.get(0) as String? ?: "no pics"
            val wikiUrlString = jsonObject.optString("wikipedia", "no wiki")
            val description = jsonObject.optString("description", "no description")
            val height = jsonObject.optJSONObject("height")
            val heightMeters = height.optDouble("meters", 0.0)
            val heightFeet = height.optDouble("feet", 0.0)

            rocketsList.add(
                Rocket(
                    id = id,
                    stages = stages,
                    rocketName = rocketName,
                    imageUrlString = imageUrlString,
                    wikiUrlString = wikiUrlString,
                    description = description,
                    height = heightMeters,
                    heightFeet = heightFeet
                )
            )
        }
    }
    return rocketsList
}