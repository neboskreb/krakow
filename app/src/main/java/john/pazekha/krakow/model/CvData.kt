package john.pazekha.krakow.model


data class CvData(
    val summary: Summary,
    val jobs:        List<Job>,
    val education:   List<Education>,
    val skills:      List<Skill>
)


