package it.polimi.se2018.network.client.socket;

import it.polimi.se2018.network.message.Message;
import it.polimi.se2018.network.client.Client;
import it.polimi.se2018.network.client.IConnection;
import it.polimi.se2018.network.server.socket.ServerInterface;

public class SocketConnection implements IConnection, SocketClient {
    private static final int PORT = 1111;
    private Client client;
    private ServerInterface server;

    public SocketConnection(Client client) {
        this.client = client;
        NetworkHandler netHand = new NetworkHandler(Client.getIpAddress(), PORT, this);
        netHand.start();
        this.server = netHand;
    }

    @Override
    public void send(Message message) {
        server.send(message);
    }

    @Override
    public void notify(Message message) {
        client.notify(message);
    }

    @Override
    public void registerClient(String username) {
        // nothing to do in socket
    }
}
