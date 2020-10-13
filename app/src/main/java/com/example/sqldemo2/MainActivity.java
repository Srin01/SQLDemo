package com.example.sqldemo2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.sqldemo2.driver.DatabaseDriver;
import com.example.sqldemo2.modal.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    DatabaseDriver databaseDriver;
    EditText firstName_editText;
    EditText lastName_editText;
    EditText marks_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseDriver = new DatabaseDriver(this);
        bindViews();

    }

    private void bindViews()
    {
        firstName_editText = findViewById(R.id.editText_firstName);
        lastName_editText = findViewById(R.id.editText_lastName);
        marks_editText = findViewById(R.id.editText_marks);
    }

    public void addStudent(View view)
    {
       Student student = generateStudentDetails();
        databaseDriver.insertStudent(student);
        resetFields();
    }

    private Student generateStudentDetails()
    {
        Student student = new Student();
        student.setFirstName(firstName_editText.getText().toString());
        student.setLastName(lastName_editText.getText().toString());
        student.setMarks(Integer.parseInt(marks_editText.getText().toString()));
        return student;
    }

    private void resetFields()
    {
        firstName_editText.setText("");
        lastName_editText.setText("");
        marks_editText.setText("");
    }

    public void printDetails(View view)
    {
        ArrayList<Student> students = databaseDriver.getAllstudents();

        System.out.println("Your database has " + students.size() + " students ");
        for (int i = 0; i < students.size(); i++)
        {
            System.out.println(students.get(i).getFirstName() + " " + students.get(i).getLastName());
        }
    }

}