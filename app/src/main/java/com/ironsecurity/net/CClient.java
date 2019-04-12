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

    /**
     * Méthode d'envoie de message au serveur java
     * @param strings
     * @return
     */
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



}