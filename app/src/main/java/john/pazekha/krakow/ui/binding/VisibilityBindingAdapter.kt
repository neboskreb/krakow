package john.pazekha.krakow.ui.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibility")
fun setImageUrl(view: View, isVisible: Boolean) {
    if (isVisible) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.INVISIBLE
    }
}
