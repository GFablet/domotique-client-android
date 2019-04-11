package com.ironsecurity.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class CClient implements Runnable {

    private static final int PORT = 45000; // Valeur arbitraire
    private static final String ADDR_IP = "192.168.56.1"; // Addresse ip du serveur



    private Socket socket;
    private BufferedReader input;

    @Override
    public void run() {
        while(true)
        {
            try {
                //On déclare un nouveau socket avec l'adresse IP et le port du serveur
                InetAddress serverAddr = InetAddress.getByName(ADDR_IP);
                socket = new Socket(serverAddr, PORT);

                while (!Thread.currentThread().isInterrupted()) {

                    this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String message = input.readLine();
                    if (null == message || "Disconnect".contentEquals(message)) {
                        Thread.interrupted();
                        break;
                    }
                }

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    /**
     * Méthode d'envoie de message au serveur java
     * @param message
     */
    public void sendMessage(final String message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (null != socket) {
                        PrintWriter out = new PrintWriter(new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream())),
                                true);
                        out.println(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void askServer(final String message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (null != socket) {
                        PrintWriter out = new PrintWriter(new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream())),
                                true);
                        out.println(message);
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        final String RESPONSE = in.readLine();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //return RESPONSE;
    }

}