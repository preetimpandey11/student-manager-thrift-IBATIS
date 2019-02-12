package com.student;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import java.io.IOException;
import java.io.Reader;

public class StudentServer {

    private static StudentService.Processor<StudentServiceImpl> processor;
    private static StudentServiceImpl serviceImpl;

    public static void main(String[] a) {

        try (Reader reader = Resources.getResourceAsReader("sql-maps-config.xml")) {
            SqlMapClient sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
            serviceImpl = new StudentServiceImpl(sqlMapClient);
            processor = new StudentService.Processor<>(serviceImpl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                startServer();
            } catch (TTransportException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void startServer() throws TTransportException {
        TServerTransport transport = new TServerSocket(9090);
        TServer server = new TSimpleServer(new TServer.Args(transport).processor(processor));
        System.out.println("Starting server on port 9090");
        server.serve();
    }

}
