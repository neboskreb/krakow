package john.pazekha.krakow.ui.binding

import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import john.pazekha.krakow.R

@BindingAdapter("android:expanded")
fun setExpandedState(view: ImageView, isExpanded: Boolean) {
    view.setImageResource(R.drawable.ic_group_arrow)
    val rotate: RotateAnimation
    if (isExpanded) {
        rotate = RotateAnimation(0f, 90f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f)
    } else {
        rotate = RotateAnimation(90f, 0f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f)
    }
    rotate.duration = 300
    rotate.fillAfter = true
    view.animation = rotate
}
