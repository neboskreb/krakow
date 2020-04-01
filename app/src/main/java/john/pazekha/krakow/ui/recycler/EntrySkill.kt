package john.pazekha.krakow.ui.recycler

import android.os.Parcel
import android.os.Parcelable

class EntrySkill(val field: String, val skills: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(field)
        parcel.writeString(skills)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EntrySkill> {
        override fun createFromParcel(parcel: Parcel): EntrySkill {
            return EntrySkill(parcel)
        }

        override fun newArray(size: Int): Array<EntrySkill?> {
            return arrayOfNulls(size)
        }
    }

}
