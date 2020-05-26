package com.example.umair.supercricketliveline;

import android.net.Network;
import android.os.AsyncTask;

import com.example.umair.supercricketliveline.POJOClasses.CheckInternetConnection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class CheckInternet extends AsyncTask<Void, Void, Boolean>  {

    private CheckInternetConnection internetlistnener;
    private boolean b;
    public CheckInternet(CheckInternetConnection listener) {
        this.internetlistnener=listener;
    }
    @Override
    protected Boolean doInBackground(Void... voids) {

        try {
            int timeoutMs = 1500;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);

            sock.connect(sockaddr, timeoutMs);
            sock.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        this.internetlistnener.onInternetConnected(aBoolean);
    }

}


