package john.pazekha.krakow.ui.recycler

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.ObservableField
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class Section(val name: String, val entries: List<Parcelable>) : ExpandableGroup<Parcelable>(name, entries) {
    val isExpanded = ObservableField<Boolean>()

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createTypedArrayList(EntryJob)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
        parcel.writeString(name)
        parcel.writeTypedList(entries)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Section> {
        override fun createFromParcel(parcel: Parcel): Section {
            return Section(parcel)
        }

        override fun newArray(size: Int): Array<Section?> {
            return arrayOfNulls(size)
        }
    }
}
