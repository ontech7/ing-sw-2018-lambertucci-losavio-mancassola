package it.polimi.se2018.network.client;

import it.polimi.se2018.network.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    /**
     * Trigger client's message handler
     * @param message Message to be handled
     * @throws RemoteException If client is connected no more, this exception will be thrown
     */
    void notify(Message message) throws RemoteException;
}
