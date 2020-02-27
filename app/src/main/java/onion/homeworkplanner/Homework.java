package onion.homeworkplanner;

public class Homework {

    int id, daysleft_homework;
    String subject_homework, description_homework,date_homework;

    public Homework (int id, String subject_homework, String description_homework, String date_homework, int daysleft_homework){
        this.id = id;
        this.subject_homework = subject_homework;
        this.description_homework = description_homework;
        this.date_homework = date_homework;
        this.daysleft_homework = daysleft_homework;
    }

    public Homework(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject_homework() {
        return subject_homework;
    }

    public void setSubject_homework(String subject_homework) {
        this.subject_homework = subject_homework;
    }

    public String getDescription_homework() {
        return description_homework;
    }

    public void setDescription_homework(String description_homework) {
        this.description_homework = description_homework;
    }

    public String getDate_homework() {
        return date_homework;
    }

    public void setDate_homework(String date_homework) {
        this.date_homework = date_homework;
    }

    public int getDaysleft_homework(){
        return daysleft_homework;
    }

    public void setDaysleft_homework(int daysleft_homework){
        this.daysleft_homework = daysleft_homework;
    }

}
