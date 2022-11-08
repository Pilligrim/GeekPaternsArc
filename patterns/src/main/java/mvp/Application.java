package mvp;

import javax.swing.*;

public class Application {

    public static void main(String... args) {
        SwingUtilities.invokeLater(() -> {
            final View view = new View();
            final Model model = new Model();
            new Presenter(view, model);
        });
    }
}
