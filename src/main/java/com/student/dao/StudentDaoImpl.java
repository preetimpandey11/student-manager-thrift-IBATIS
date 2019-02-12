package com.student.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.student.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public void addStudent(Student student, SqlMapClient sqlMapClient) {
        try {
            sqlMapClient.insert("student.add", student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentById(int id, SqlMapClient sqlMapClient) {
        Student student = null;
        try {
            student = (Student) sqlMapClient.queryForObject("student.getById", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> getAllStudents(SqlMapClient sqlMapClient) {
        List<Student> list = new ArrayList<>();
        try {
            list = sqlMapClient.queryForList("student.getAll");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateStudent(Student student, SqlMapClient sqlMapClient) {
        try {
            sqlMapClient.update("student.update", student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(int id, SqlMapClient sqlMapClient) {
        try {
            sqlMapClient.delete("student.deleteById", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
