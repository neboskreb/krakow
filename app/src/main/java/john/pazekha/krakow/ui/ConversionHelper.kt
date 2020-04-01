package john.pazekha.krakow.ui

import android.content.Context
import john.pazekha.krakow.model.CvData
import john.pazekha.krakow.R
import john.pazekha.krakow.model.Education
import john.pazekha.krakow.model.Skill
import john.pazekha.krakow.model.Summary
import john.pazekha.krakow.model.Job
import john.pazekha.krakow.ui.recycler.*

class ConversionHelper(private val context: Context) {

    fun toSections(data: CvData): List<Section> {
        return listOf(
            toSummarySection(data.summary),
            toJobsSection(data.jobs),
            toStudySection(data.education),
            toSkillSection(data.skills)
        )
    }

    private fun toSummarySection(data: Summary): Section {
        return Section(
            getString(R.string.section_summary),
            listOf(EntrySummary(data.formattedSummary))
        )
    }

    private fun toJobsSection(data: List<Job>): Section {
        return Section(
            getString(R.string.section_jobs),
            map(data) {
                EntryJob(
                    it.logoUrl,
                    it.dates,
                    it.title,
                    it.company,
                    it.description
                )
            }
        )
    }

    private fun toStudySection(data: List<Education>): Section {
        return Section(
            getString(R.string.section_study),
            map(data) {
                EntrySchool(
                    it.period,
                    it.school
                )
            }
        )
    }

    private fun toSkillSection(data: List<Skill>): Section {
        return Section(
            getString(R.string.section_skills),
            map(data) {
                EntrySkill(
                    it.field,
                    collect(it.skills)
                )
            }
        )
    }

    private fun collect(skills: List<String>): String {
        val builder = StringBuilder()
        for (skill in skills) {
            if (builder.isNotEmpty()) {
                builder.append("\n")
            }
            builder.append("• ").append(skill)
        }
        return builder.toString()
    }

    private fun <I, O> map(list: List<I>, mapping: (I) -> O): List<O> {
        val result = ArrayList<O>()
        for (item in list) {
            result.add(mapping(item))
        }
        return result
    }

    private fun getString(stringId: Int): String {
        return context.getString(stringId)
    }
}