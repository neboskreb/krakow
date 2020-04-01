package john.pazekha.krakow.model

data class ContactInfo(
    val name:       String,
    val role:       String,
    val phone:      String,
    val showWhatsapp: Boolean,
    val showPhoneCall: Boolean,
    val email:      String,
    val showEmail:  Boolean,
    val linkedin:   String,
    val showWeb:    Boolean
)
