package john.pazekha.krakow.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import john.pazekha.krakow.R


private val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

@BindingAdapter("android:url")
fun setImageUrl(view: ImageView, url: String?) {

    Glide.with(view.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_broken_image)
        .transition(withCrossFade(factory))
        .into(view)
}
