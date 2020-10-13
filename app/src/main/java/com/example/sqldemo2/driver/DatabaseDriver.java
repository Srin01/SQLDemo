package com.example.sqldemo2.driver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqldemo2.modal.Student;
import com.example.sqldemo2.schema.StudentSchema;
import com.example.sqldemo2.sql.MySQLHelper;

import java.util.ArrayList;

public class DatabaseDriver
{
    MySQLHelper mySQLHelper;
    Context context;
    SQLiteDatabase database;

    public DatabaseDriver(Context context) {
        this.context = context;
        mySQLHelper = new MySQLHelper(context);
        database = mySQLHelper.getWritableDatabase();
    }

    public ArrayList<Student> getAllstudents()
    {
        ArrayList<Student> students = new ArrayList<>();

        String[] columns = {"id","firstName", "lastName", "marks"};
        Cursor cursor = database.query("student", columns, null, null, null, null, null);

        if(cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do
            {
                //do some processing
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String firstName = cursor.getString(cursor.getColumnIndex("firstName"));
                String lastName = cursor.getString(cursor.getColumnIndex("lastName"));
                int marks = cursor.getInt(cursor.getColumnIndex("marks"));

                Student student = setStudentDetails(id, firstName, lastName, marks);
                students.add(student);

            }while(cursor.moveToNext());
        }

        return students;
    }

    private Student setStudentDetails(int id, String firstName, String lastName, int marks)
    {
        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setMarks(marks);
        return student;
    }

    public void insertStudent(Student s)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentSchema.Student._firstName, s.getFirstName());
        contentValues.put(StudentSchema.Student._lastName, s.getLastName());
        contentValues.put(StudentSchema.Student._marks, s.getMarks());

        long id = database.insert("student", null,contentValues);
    }
}

