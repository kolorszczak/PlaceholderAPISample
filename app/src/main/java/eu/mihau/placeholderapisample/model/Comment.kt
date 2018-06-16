package eu.mihau.placeholderapisample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(val id : Int,
                   val name : String,
                   val email : String,
                   val body : String) : Parcelable