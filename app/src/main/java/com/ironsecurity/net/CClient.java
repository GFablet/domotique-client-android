package com.ironsecurity.net;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.CheckBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class CClient extends AsyncTask<String, String, String> {

    private String response;

    @Override
    protected String doInBackground(String... strings) {

        try {
            InetAddress serverAddr = InetAddress.getByName(CServer.ADDR_IP);
            Socket socket = new Socket(serverAddr, CServer.PORT);
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            System.out.println("Création des flux de sortie");
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            for(String string : strings)
            {
                pw.println(string);
                System.out.println(string);
                response = br.readLine();
                System.out.println("Fermeture des flux, de la socket et du serveur ");

            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }



   /* private Socket socket;
    private BufferedReader input;

    @Override
    public void run() {
        while(true)
        {
            try {
                //On déclare un nouveau socket avec l'adresse IP et le port du serveur
                InetAddress serverAddr = InetAddress.getByName(CServer.ADDR_IP);
                socket = new Socket(serverAddr, CServer.PORT);

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

    }*/

    /**
     * Méthode d'envoie de message au serveur java
     * @param message
     */
    /*public void sendMessage(final String message) {
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
    }*/

}