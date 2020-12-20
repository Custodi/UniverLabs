package Test_Ending;

import java.net.*;

import java.io.*;
import java.math.*;



public class Test {

    public final static int DEFAULT_PORT = 8001;//определение порта


    public void runServer() throws IOException {//метод сервера runServer

        DatagramSocket s = null;//создание объекта DatagramSocket

        try {

            boolean stopFlag = false;//создание флага stopFlag и его инициализация

//значением false

            byte[] buf = new byte[512];//буфер для приема/передачи дейтаграммы

            s = new DatagramSocket(DEFAULT_PORT);//привязка сокета к

//реальному объекту с портом DEFAULT_PORT

            System.out.println("UDPServer: Started on " + s.getLocalAddress() + ":"

                    + s.getLocalPort());//вывод к консоль сообщения
            String[] a = new String[3];

            while (!stopFlag) {//цикл до тех пор, пока флаг не примет значение true

                DatagramPacket recvPacket = new DatagramPacket(buf, buf.length);//создание объекта дейтаграммы для получения данных
                s.receive(recvPacket);
                String message = new String(recvPacket.getData());//извлечение переменных из пакета
                String delimiter = "&";
                String[] subStr;
                subStr = message.split(delimiter);
                DatagramPacket sendPacket = new DatagramPacket(buf, 0, recvPacket.getAddress(), recvPacket.getPort());//формирование объекта                                                                  // дейтаграммы для отсылки данных
                int n = 0;//количество байт в ответе
                int x = Integer.parseInt(subStr[0]);
                int y = Integer.parseInt(subStr[1]);
                int z = Integer.parseInt(subStr[2]);
                double result = y + Math.pow(Math.E, x - y)/(y+x*x/(y+Math.pow(x,3)/y))*Math.pow(1+Math.pow(Math.tan(z/3.),2), Math.sqrt(Math.abs(y)+3));
                String v = Double.toString(result);
                buf = v.getBytes();
                DatagramPacket SendPacket = new DatagramPacket(buf, 0, recvPacket.getAddress(), recvPacket.getPort());//формирование объекта // дейтаграммы для отсылки данных

                SendPacket.setData(buf);//установить массив посылаемых данных

                SendPacket.setLength(buf.length);//установить длину посылаемых данных

                s.send(SendPacket);//послать сами данные

                stopFlag = true;

            } // while(server is not stopped)

            System.out.println("UDPServer: Stopped");

        } finally {

            if (s != null) {

                s.close();//закрытие сокета сервера

            }

        }

    }

    public static void main(String[] args) {//метод main

        try {

            Test udpSvr = new Test();//создание объекта udpSvr

            udpSvr.runServer();//вызов метода объекта runServer

        } catch (IOException ex) {

            ex.printStackTrace();

        }

    }

}