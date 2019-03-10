package in.semicolonindia.studentcrm.rest;

/**
 * Created by Rupesh on 20-08-2017.
 */

@SuppressWarnings("ALL")
public interface BaseUrl {

    String sBaseURL = "http://semicolonindia.in/school-crm-demo/index.php?mobile/";
    public String sLoginURL = sBaseURL + "login";
    public String sGetChangePasswordURL = sBaseURL + "update_user_password";
    public String sGetAUploadImageURL = sBaseURL + "update_user_image";
    public String sForgotPasswordURL = sBaseURL + "reset_password";
    public String sGetAssignmentURL = sBaseURL + "get_assignment";
    public String sHolidayURL = sBaseURL + "get_holiday";
    public String sGetTeacherURL = sBaseURL + "get_teachers";
    public String sGetTeacherMsg = sBaseURL + "get_teacher_message";
    public String sGetSubjectURL = sBaseURL + "get_subjects";
    public String sGetRoutineURL = sBaseURL + "get_class_routine";
    public String sGetSyllabusURL = sBaseURL + "get_syllabus";
    public String sGetMarksURL = sBaseURL + "get_student_mark_information";
    public String sGetProfile = sBaseURL + "get_loggedin_user_profile";
    public String sGetNoticeboardURL = sBaseURL + "get_event_calendar";
    public String sGetStudyMaterialURL = sBaseURL + "get_study_material";
    public String sGetPaymentURL = sBaseURL + "get_single_student_accounting";
    public String sGetBookListURL = sBaseURL + "get_book_list";
    public String sGetAttendanceListURL = sBaseURL + "get_student_attendance";
    public String sMarksListURL = sBaseURL + "get_exam_list";


    public String sGetBookRequestURL = sBaseURL + "get_book_issued_list";
    public String sGetSubmitBookRequestURL = sBaseURL + "submit_book_request";
    public String sGetTransportURL = sBaseURL + "get_transports";
    public String sGetTransportRequestURL  = sBaseURL + "submit_transport_request";
    public String sSendUpdateURL = sBaseURL + "update_user_image";
    public String sUploadUserInfoURL = sBaseURL + "update_user_info";
    public String sGetContactsTeacher = sBaseURL + "get_contacts_teacher";
    public String sSendMsg = sBaseURL + "send_message";
    public String sGetMsg = sBaseURL + "get_message";
    public String sGetNewMsg = sBaseURL + "get_new_message";

    public String sSyllabusDocDownloadURL = "http://semicolonindia.in/school-crm-demo/uploads/syllabus/";
    public String sAssignmentDownloadURL = "http://semicolonindia.in/school-crm-demo/uploads/assignment/";
    public String sStudyMaterialDownloadURL = "http://semicolonindia.in/school-crm-demo/uploads/document/";
}

