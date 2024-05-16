package seng201.team43.gui.factories;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;
import javafx.util.Callback;
import seng201.team43.models.Cart;

/**
 * Cell factory for carts to use in ListView
 * @author seng201 teaching team, Luke Hallett
 */
public class CartCellFactory implements Callback<ListView<Cart>, ListCell<Cart>> {
    /*
    TODO: write the docstring LUUUKKEEEE :).
     */
    @Override
    public ListCell<Cart> call(ListView<Cart> cartListView) {
        return new ListCell<>() {
            @Override
            public void updateItem(Cart cart, boolean empty) {
                super.updateItem(cart, empty);

                if(empty || cart == null) {
                    setGraphic(null);
                } else {
                    Label label = new Label(String.format("%s Cart\nSize: %s units\nSpeed: %s m/s", cart.getType().label, cart.getSize(), cart.getSpeed()));
                    label.setFont(new Font(20));
                    label.setMaxWidth(Double.MAX_VALUE);

                    setGraphic(label);
                }
            }
        };
    }
}
