package john.pazekha.krakow.ui.recycler

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.thoughtbot.expandablerecyclerview.MultiTypeExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import john.pazekha.krakow.BR
import john.pazekha.krakow.R
import john.pazekha.krakow.databinding.RowLayoutSectionBinding
import john.pazekha.krakow.ui.recycler.MultiTypeAdapter.ChildType.*

class MultiTypeAdapter(groups: List<ExpandableGroup<*>>) :
    MultiTypeExpandableRecyclerViewAdapter<SectionViewHolder, ChildViewHolder>(groups) {


    override fun getChildViewType(position: Int, group: ExpandableGroup<*>?, childIndex: Int): Int {
        val entry = (group as Section).entries[childIndex]
        val type = detectChildType(entry)
        return ChildType.toInt(type)
    }

    private fun detectChildType(entry: Parcelable): ChildType {
        val type = when (entry) {
            is EntrySummary -> SUMMARY
            is EntryJob     -> JOBS
            is EntrySchool  -> SCHOOL
            is EntrySkill   -> SKILL
            else -> throw UnsupportedOperationException("Unsupported view type: ${entry.javaClass}")
        }
        return type
    }

    override fun isChild(viewType: Int): Boolean {
        return viewType != ExpandableListPosition.GROUP
    }
    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val binding : RowLayoutSectionBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_layout_section,
                parent,
                false
            )
        return SectionViewHolder(binding)
    }

    override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val layoutId = when (ChildType.fromInt(viewType)) {
            SUMMARY -> R.layout.row_layout_summary
            JOBS    -> R.layout.row_layout_work_experience
            SCHOOL  -> R.layout.row_layout_school
            SKILL   -> R.layout.row_layout_skills
        }
        return DataBindingViewHolder.create(parent, layoutId, BR.model)
    }

    override fun onBindChildViewHolder(
        holder: ChildViewHolder,
        flatPosition: Int,
        group: ExpandableGroup<*>,
        childIndex: Int
    ) {
        val entry = (group as Section).items[childIndex]
        (holder as DataBindingViewHolder).onBind(entry)
     }

    override fun onBindGroupViewHolder(
        holder: SectionViewHolder,
        flatPosition: Int,
        group: ExpandableGroup<*>
    ) {
        holder.onBind(group as Section)
    }


    private enum class ChildType {
        JOBS, SCHOOL, SUMMARY, SKILL;

        companion object CO {
            private const val BASE = 42

            fun toInt(value: ChildType): Int {
                return value.ordinal + BASE
            }

            fun fromInt(int: Int) : ChildType {
                return values()[int - BASE]
            }
        }
    }
}
