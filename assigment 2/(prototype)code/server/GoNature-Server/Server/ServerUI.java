package Server;

import gui.ServerController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;
import javafx.application.Application;
import javafx.stage.Stage;

public class ServerUI extends Application {
   public static final int DEFAULT_PORT = 5556;

   public static void main(String[] args) {
      launch(args);
   }

   public void start(Stage primaryStage) throws InvocationTargetException {
      try {
         ServerController aFrame = new ServerController();
         aFrame.start(primaryStage);
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }


}
