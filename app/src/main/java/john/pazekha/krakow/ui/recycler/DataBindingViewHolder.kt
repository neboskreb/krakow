package john.pazekha.krakow.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder

class DataBindingViewHolder(private val binding : ViewDataBinding,
                            private val variableId: Int
) : ChildViewHolder(binding.root) {

    fun onBind(model: Any) {
        binding.setVariable(variableId, model)
    }

    companion object CO {
        fun create(parent: ViewGroup, layoutId: Int, variableId: Int): DataBindingViewHolder {
            val binding: ViewDataBinding =
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    layoutId,
                    parent,
                    false
                )
            return DataBindingViewHolder(
                binding,
                variableId
            )
        }
    }
}