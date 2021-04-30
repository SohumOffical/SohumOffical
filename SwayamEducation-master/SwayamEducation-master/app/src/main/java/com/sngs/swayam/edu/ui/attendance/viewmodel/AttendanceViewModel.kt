package com.sngs.swayam.edu.ui.attendance.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sngs.swayam.edu.data.model.Promo
import com.sngs.swayam.edu.data.model.Standard
import com.sngs.swayam.edu.data.model.Student
import com.sngs.swayam.edu.data.model.Subject
import com.sngs.swayam.edu.data.repository.*

class AttendanceViewModel (application: Application): AndroidViewModel(application){
    var studAttendanceRepository: StudAttendanceRepository?=null


    var subjectRepository: SubjectRepository?=null
    var subjectListLiveData : LiveData<List<Subject>>?=null

    var standardRepository: StandardRepository?=null
    var standardListLiveData : LiveData<List<Standard>>?=null

    var studentRepository: StudentRepository?=null
    var studentListLiveData : LiveData<List<Student>>?=null

    init {
        studAttendanceRepository = StudAttendanceRepository.instance
        subjectRepository = SubjectRepository.instance
        standardRepository = StandardRepository.instance
        studentRepository = StudentRepository.instance

        subjectListLiveData = MutableLiveData()
        standardListLiveData = MutableLiveData()
        studentListLiveData = MutableLiveData()
    }

    fun fetchAllSubject(){
        subjectListLiveData = subjectRepository?.fetchAllSubject();
    }

    fun fetchAllStandard(){
        standardListLiveData = standardRepository?.fetchAllStandard();
    }

    fun fetchAllStudent(){
        studentListLiveData = studentRepository?.fetchAllStudent();
    }
}