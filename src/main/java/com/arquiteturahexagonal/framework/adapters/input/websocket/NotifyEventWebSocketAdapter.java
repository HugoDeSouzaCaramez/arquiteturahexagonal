package com.arquiteturahexagonal.framework.adapters.input.websocket;

public class NotifyEventWebSocketAdapter extends WebSocketServer {

    public NotifyEventWebSocketAdapter(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println(
                conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the server!");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        broadcast(message);
        System.out.println(conn + ": " + message);
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        broadcast(message.array());
        System.out.println(conn + ": " + message);
    }

    public static void startServer() throws IOException, InterruptedException {
        var ws = new NotifyEventWebSocketAdapter(new InetSocketAddress("localhost", 8887));
        ws.setReuseAddr(true);
        ws.start();
        System.out.println("Topology & Inventory" +
                " webSocket started on port: " + ws.getPort());
        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String in = sysin.readLine();
            ws.broadcast(in);
            if (in.equals("exit")) {
                ws.stop();
                break;
            }
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
        }
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);
    }
}
