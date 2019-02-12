package com.student.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.student.Student;

import java.util.List;

public interface StudentDao {

    public void addStudent(Student student, SqlMapClient sqlMapClient);

    public Student getStudentById(int id, SqlMapClient sqlMapClient);

    public List<Student> getAllStudents(SqlMapClient sqlMapClient);

    public void updateStudent(Student student, SqlMapClient sqlMapClient);

    public void deleteStudent(int id, SqlMapClient sqlMapClient);

}
