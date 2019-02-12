package com.student;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.student.dao.StudentDao;
import com.student.dao.StudentDaoImpl;
import org.apache.thrift.TException;

import java.io.Reader;
import java.util.List;

public class StudentServiceImpl implements StudentService.Iface {

    private SqlMapClient sqlMapClient;
    private StudentDao studentDao = new StudentDaoImpl();


    public StudentServiceImpl(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    @Override
    public Student getStudent(int id) throws TException {
        System.out.println("Retrieving student....");
        return studentDao.getStudentById(id, sqlMapClient);
    }

    @Override
    public void addStudent(Student student) throws TException {
        System.out.println("Adding student...");
        studentDao.addStudent(student, sqlMapClient);
    }

    @Override
    public List<Student> getAll() throws TException {
        return studentDao.getAllStudents(sqlMapClient);
    }

    @Override
    public void updateSudent(Student student) throws TException {
        studentDao.updateStudent(student, sqlMapClient);
    }

    @Override
    public void deleteStudent(int id) throws TException {
        studentDao.deleteStudent(id, sqlMapClient);
    }
}
