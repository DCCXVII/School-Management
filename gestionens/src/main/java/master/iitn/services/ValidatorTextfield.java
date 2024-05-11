package master.iitn.services;

import java.util.function.Predicate;

import javafx.beans.property.BooleanProperty;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TextField;

public class ValidatorTextfield extends TextField {

    private final Predicate<String> validation;
    private BooleanProperty isValidProperty = new SimpleBooleanProperty();

    ValidatorTextfield(Predicate<String> validation) {
        this.validation = validation;

        textProperty().addListener((o, oldvalue, newText) -> {
            isValidProperty.setValue(validation.test(newText));
        });
        isValidProperty.setValue(validation.test(""));
    }

}
