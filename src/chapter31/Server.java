package chapter31;

import java.io.*;
import java.net.*;
import java.util.Date;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Server extends Application{
	@Override
	public void start(Stage primaryStage) {
		TextArea tArea = new TextArea();
		Scene scene = new Scene(new ScrollPane(tArea), 450, 200);
		primaryStage.setTitle("Server");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		new Thread(() -> {
			try {
				// Create a server socket
				ServerSocket serverSocket = new ServerSocket(8000);
				Platform.runLater(() -> tArea.appendText("Server started at " + new Date() + "\n"));
				
				// Listen for a connection request
				Socket socket = serverSocket.accept();
				
				// Create data input and output stream
				DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
				DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
				
				while(true) {
					// Receive radius from the client
					double radius = inputFromClient.readDouble();
					double area = radius * radius * Math.PI;
					outputToClient.writeDouble(area);
					
					Platform.runLater(() -> {
						tArea.appendText("Radius receives from client: " + radius + '\n');
						tArea.appendText("Area is " + area + '\n');
					});
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
