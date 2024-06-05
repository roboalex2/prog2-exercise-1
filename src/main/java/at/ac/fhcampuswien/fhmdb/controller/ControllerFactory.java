package at.ac.fhcampuswien.fhmdb.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerFactory {

    public static <T> T createController(Class<T> controllerType, Stage stage, URL fxmlUrl) {
        try {
            FXMLLoader loader = new FXMLLoader(fxmlUrl);

            loader.setControllerFactory(clazz -> {
                try {
                    Method getInstanceMethod = clazz.getMethod("getInstance");
                    return getInstanceMethod.invoke(null);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            Parent root = loader.load();
            T controller = loader.getController();

            stage.setScene(new Scene(root));
            stage.show();
            return controller;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}