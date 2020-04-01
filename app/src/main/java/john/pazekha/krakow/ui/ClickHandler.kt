package john.pazekha.krakow.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_CALL
import android.content.Intent.ACTION_VIEW
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.app.ActivityCompat
import john.pazekha.krakow.model.ContactInfo

class ClickHandler(private val contactInfo: ContactInfo, private val context: Context) : IClickHandler {

    override fun onWhatsappClicked() {
        val url = "https://api.whatsapp.com/send?phone=${contactInfo.phone}&text=Hello%20from%20Krakow,%20John!"
        startActivity(Intent(ACTION_VIEW, Uri.parse(url)))
    }

    override fun onPhoneClicked() {
        val permission = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.CALL_PHONE
        )
        if (permission == PackageManager.PERMISSION_GRANTED) {
            startActivity(Intent(ACTION_CALL, Uri.parse("tel:${contactInfo.phone}")))
        }
    }

    override fun onEmailClicked() {
        val emailIntent = Intent(
            Intent.ACTION_SENDTO,
            Uri.parse("mailto:${contactInfo.email}")
        )
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Message from Krakow")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi John, how are you?")
        startActivity(emailIntent)
    }

    override fun onLinkedinClicked() {
        startActivity(Intent(ACTION_VIEW, Uri.parse(contactInfo.linkedin)))
    }

    private fun startActivity(intent: Intent) {
        context.startActivity(intent)
    }
}