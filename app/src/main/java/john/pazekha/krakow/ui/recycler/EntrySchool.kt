package john.pazekha.krakow.ui.recycler

import android.os.Parcel
import android.os.Parcelable

class EntrySchool(val period: String, val study: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(period)
        parcel.writeString(study)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EntrySchool> {
        override fun createFromParcel(parcel: Parcel): EntrySchool {
            return EntrySchool(parcel)
        }

        override fun newArray(size: Int): Array<EntrySchool?> {
            return arrayOfNulls(size)
        }
    }

}
