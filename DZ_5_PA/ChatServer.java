package DZ_5_PA;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static Set<ClientHandler> clientHandlers = new HashSet<>(); // Список всех подключенных клиентов
    private static final int PORT = 12345; // Порт, на котором запускается сервер

    public static void main(String[] args) {
        System.out.println("Сервер чата запущен на порту " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Новое подключение: " + clientSocket.getInetAddress());
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start(); // Запуск потока для обработки сообщений клиента
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Отправка сообщения всем клиентам
    public static void broadcastMessage(String message) {
        for (ClientHandler client : clientHandlers) {
            client.sendMessage(message);
        }
    }

    // Удаление клиента из списка при отключении
    public static void removeClient(ClientHandler clientHandler) {
        clientHandlers.remove(clientHandler);
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String message;
        try {
            while ((message = in.readLine()) != null) {
                System.out.println("Получено сообщение: " + message);
                ChatServer.broadcastMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    // Метод для отправки сообщения клиенту
    public void sendMessage(String message) {
        out.println(message);
    }

    // Закрытие соединения и освобождение ресурсов
    private void closeConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            ChatServer.removeClient(this); // Удаление клиента из списка активных клиентов
            System.out.println("Клиент отключился: " + clientSocket.getInetAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

