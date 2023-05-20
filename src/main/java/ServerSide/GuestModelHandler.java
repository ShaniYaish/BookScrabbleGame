package ServerSide;

import ServerSide.ClientHandler;

import java.io.InputStream;
import java.io.OutputStream;

public class GuestModelHandler implements ClientHandler {

    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        // implement
    }

    @Override
    public void close() {

    }
}
