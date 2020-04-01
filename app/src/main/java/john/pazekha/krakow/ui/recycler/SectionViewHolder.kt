package john.pazekha.krakow.ui.recycler

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import john.pazekha.krakow.databinding.RowLayoutSectionBinding

class SectionViewHolder(private val binding: RowLayoutSectionBinding) : GroupViewHolder(binding.root) {
    private lateinit var model: Section

    fun onBind(model: Section) {
        this.model = model
        binding.model = model
    }

    override fun expand() {
        setExpanded(true)
    }

    override fun collapse() {
        setExpanded(false)
    }

    private fun setExpanded(isExpanded: Boolean) {
        model.isExpanded.set(isExpanded)
    }
}