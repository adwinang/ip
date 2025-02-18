package app;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A custom control that represents a dialog box, consisting of a text label
 * and an image representation arranged horizontally.
 * The app.DialogBox extends the HBox layout to position its components.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a app.DialogBox with the specified text and image.
     * This dialog box arranges the text and image horizontally.
     *
     * @param s the text to be displayed in the dialog box
     * @param i the image to be displayed alongside the text
     */
    public DialogBox(String s, Image i) {
        text = new Label(s);
        displayPicture = new ImageView(i);

        //Styling the dialog box
        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);


        this.getChildren().addAll(text, displayPicture);
    }
}
