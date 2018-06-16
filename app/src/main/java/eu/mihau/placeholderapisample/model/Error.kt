package eu.mihau.placeholderapisample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Error(val message : String) : Parcelable