package john.pazekha.krakow.ui.recycler

import android.os.Parcel
import android.os.Parcelable

class EntrySummary(val description: String) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()!!)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EntrySummary> {
        override fun createFromParcel(parcel: Parcel): EntrySummary {
            return EntrySummary(parcel)
        }

        override fun newArray(size: Int): Array<EntrySummary?> {
            return arrayOfNulls(size)
        }
    }

}
