package com.student;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.List;

public class StudentClient {

    public static void main(String[] a) throws Exception {

        TTransport transport = new TSocket("localhost", 9090);
        TProtocol protocol = new TBinaryProtocol(transport);
        transport.open();

        StudentService.Client client = new StudentService.Client(protocol);

        System.out.println("Connected with server on port 9090");

        try {
            perform(client);
        } catch (TException e) {
            e.printStackTrace();
        }

        transport.close();
    }


    public static void perform(StudentService.Client client) throws TException {

        System.out.println("Creating a new student ....");
        client.addStudent(new Student().setId(1).setName("Harry").setScore(45).setStandard(3).setDivision(Division.D));

        System.out.println("Retrieving student data ....");
        Student result = client.getStudent(1);
        System.out.println(result);

        System.out.println("Updating student data ....");
        client.updateSudent(result.setStandard(3).setDivision(Division.A).setScore(90).setName("Rob"));

        System.out.println("Retrieving updated student data ....");
        result = client.getStudent(1);
        System.out.println(result);

        System.out.println("Creating another student data ....");
        client.addStudent(new Student().setId(2).setName("Harry").setScore(45).setStandard(3).setDivision(Division.D));

        System.out.println("Retrieving student data ....");
        result = client.getStudent(2);
        System.out.println(result);

        System.out.println("Retrieving all data ....");
        List<Student> students = client.getAll();
        System.out.println(students);

        System.out.println("Deleting all data....");
        client.deleteStudent(1);
        client.deleteStudent(2);

        System.out.println("Retrieving all data ....");
        students = client.getAll();
        System.out.println(students);

    }
}