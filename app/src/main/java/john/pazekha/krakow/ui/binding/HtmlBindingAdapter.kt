package john.pazekha.krakow.ui.binding

import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:html")
fun setHtmlText(textView: TextView, html: String?) {
    textView.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(html)
    }
}
