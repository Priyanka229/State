package apptentive.com.states.beans

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class State(
    @SerializedName("name") val name: String?,
    @SerializedName("abbreviation") val abbreviation: String?,
    @SerializedName("capital") val capital: String?,
    @SerializedName("largest_city") val largest_city: String?,
    @SerializedName("established_date") val established_date: String?,
    @SerializedName("population") val population: Long?,
    @SerializedName("total_area_km2") val total_area_km2: Long?,
    @SerializedName("land_area_km2") val land_area_km2: Long?,
    @SerializedName("water_area_km2") val water_area_km2: Long?,
    @SerializedName("number_of_reps") val number_of_reps: Long?
): Parcelable