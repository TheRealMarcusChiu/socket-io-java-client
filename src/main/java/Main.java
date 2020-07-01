import io.socket.client.IO;
import io.socket.client.Socket;

import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws URISyntaxException {
        Socket socket = IO.socket("https://meet.marcuschiu.com:443");
        socket
                .on(Socket.EVENT_CONNECT, (arg) -> {
                    System.out.println("Connected to Socket.IO server\n");

                    System.out.println("Emitting message to Socket.IO server\n");
                    socket.emit("create or join", "roomname");
                })
                .on("created-room", arg -> {
                    System.out.println("Received message from Socket.IO server");
                    System.out.println("created-room: " + arg[0]);
                    socket.disconnect();
                })
                .on(Socket.EVENT_DISCONNECT, arg -> {
                    System.out.println("\nDisconnected from Socket.IO Server: " + arg[0]);
                });

        System.out.println("Connecting to Socket.IO server");
        socket.connect();
    }
}
