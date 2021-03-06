package com.example.coursecatalog.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.coursecatalog.database.Assessment
import com.example.coursecatalog.database.CourseEntity
import com.example.coursecatalog.database.Mentor
import com.example.coursecatalog.database.TermEntity

@BindingAdapter("termTitleFormatted")
fun TextView.setTermTitleFormatted(item: TermEntity?) {
    item?.let {
        text = item.termTitle
    }
}

@BindingAdapter("startDateFormatted")
fun TextView.setStartDateFormatted(item: TermEntity?) {
    item?.let {
        text = formatDateAsString(item.startDate)
    }
}

@BindingAdapter("endDateFormatted")
fun TextView.setEndDateFormatted(item: TermEntity?) {
    item?.let {
        text = formatDateAsString(item.endDate)
    }
}

@BindingAdapter("courseTitleFormatted")
fun TextView.setCourseTitleFormatted(item: CourseEntity?) {
    item?.let {
        text = item.courseTitle
    }
}

@BindingAdapter("courseStartDateFormatted")
fun TextView.setCourseStartDateFormatted(item: CourseEntity?) {
    item?.let {
        text = formatDateAsString(item.startDate)
    }
}

@BindingAdapter("courseEndDateFormatted")
fun TextView.setCourseEndDateFormatted(item: CourseEntity?) {
    item?.let {
        text = formatDateAsString(item.endDate)
    }
}

@BindingAdapter("mentorName")
fun TextView.setMentorName(item: Mentor?) {
    item?.let {
        text = item.name
    }
}

@BindingAdapter("mentorPhone")
fun TextView.setMentorPhone(item: Mentor?) {
    item?.let {
        text = item.phone
    }
}

@BindingAdapter("mentorEmail")
fun TextView.setMentorEmail(item: Mentor?) {
    item?.let {
        text = item.email
    }
}

@BindingAdapter("courseNotes")
fun TextView.setNotes(item: CourseEntity?) {
    item?.let {
        text = item.notes
    }
}

@BindingAdapter("assessmentTitle")
fun TextView.setAssessmentTitle(item: Assessment?) {
    item?.let {
        text = item.title
    }
}

@BindingAdapter("assessmentDueDate")
fun TextView.setAssessmentDueDate(item: Assessment?) {
    item?.let {
        text = formatDateAsString(item.dueDate)
    }
}

@BindingAdapter("assessmentType")
fun TextView.setAssessmentType(item: Assessment?) {
    item?.let {
        text = item.type
    }
}

@BindingAdapter("assessmentNotes")
fun TextView.setAssessmentNotes(item: Assessment?) {
    item?.let {
        text = item.notes
    }
}