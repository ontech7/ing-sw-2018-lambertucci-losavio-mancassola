package it.polimi.se2018.network.client.socket;

import it.polimi.se2018.network.message.Message;
import it.polimi.se2018.network.client.ClientInterface;
import it.polimi.se2018.network.server.socket.ServerInterface;

import java.io.*;
import java.net.Socket;

/**
 * This class represents the server in the client.
 * It creates the real Socket object and then it starts listening
 * Extends {@link Thread}
 * Implements {@link ServerInterface}
 * @author mett29, MicheleLambertucci
 */
public class NetworkHandler extends Thread implements ServerInterface {

    private Socket socketClient;

    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    private ClientInterface client;

    private boolean connectionEstablished;

    NetworkHandler(String host, int port, ClientInterface client) {
        try {
            this.socketClient = new Socket(host, port);
            this.oos = new ObjectOutputStream(new BufferedOutputStream(socketClient.getOutputStream()));
            this.oos.flush();
            this.ois = new ObjectInputStream(new BufferedInputStream(socketClient.getInputStream()));
            this.client = client;
            this.connectionEstablished = true;
        } catch (IOException e) {
            this.connectionEstablished = false;
        }
    }

    @Override
    public void run() {
        boolean loop = true;
        while (connectionEstablished && loop && !this.socketClient.isClosed()) {
            try {
                Message message = (Message) ois.readObject();
                if (message == null) {
                    loop = false;
                } else {
                    try {
                        client.notify(message);
                    } catch (NullPointerException e) {
                        //do nothing
                    }
                }
            } catch (IOException|ClassNotFoundException e) {
                loop = false;
                System.out.println("Client can't communicate with the server anymore. Connection closed.");
            }
        }
    }

    /**
     * Send a message to the server
     * @param message Message to be sent
     */
    public synchronized void send(Message message) {
        try {
            oos.reset();
            oos.writeObject(message);
            oos.flush();
        } catch (IOException e) {
            System.out.println("Can't send this message right now.");
        }
    }
}
