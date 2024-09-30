package DZ_5_PA;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_ADDRESS = "127.0.0.1"; // Адрес сервера (localhost)
    private static final int SERVER_PORT = 12345; // Порт сервера

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            // Поток для чтения входящих сообщений от сервера
            new Thread(() -> {
                String incomingMessage;
                try {
                    while ((incomingMessage = in.readLine()) != null) {
                        System.out.println("Сообщение: " + incomingMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Отправка сообщений на сервер
            System.out.println("Введите сообщение: ");
            while (scanner.hasNextLine()) {
                String messageToSend = scanner.nextLine();
                out.println(messageToSend);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

