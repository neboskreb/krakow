package john.pazekha.krakow.ui.recycler

import android.os.Parcel
import android.os.Parcelable

class EntryJob(
    val logoUrl: String,
    val period: String,
    val title: String,
    val company: String,
    val description: String
) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(logoUrl)
        parcel.writeString(period)
        parcel.writeString(title)
        parcel.writeString(company)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EntryJob> {
        override fun createFromParcel(parcel: Parcel): EntryJob {
            return EntryJob(parcel)
        }

        override fun newArray(size: Int): Array<EntryJob?> {
            return arrayOfNulls(size)
        }
    }

}
