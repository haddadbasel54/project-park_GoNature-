package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Utility class for displaying popup windows.
 * 
 * @author Pier Mbariky
 */
public class popupCaller {
	/**
	 * Displays a popup window with the given message.
	 * 
	 * @param message The message to be displayed in the popup window.
	 * @throws IOException If an I/O error occurs while loading the popup window.
	 */
	public static void showPopup(String message) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(popupCaller.class.getResource("PopupWindow.fxml"));
		Parent root = fxmlLoader.load();
		popupWindowController controller = fxmlLoader.getController();
		controller.setMessage(message);
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Status window");

		// Show the new stage
		stage.show();

	}
}
