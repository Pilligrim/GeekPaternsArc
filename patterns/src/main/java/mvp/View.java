package mvp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Provides the UI elements
 */
public class View {
    // A list of listeners subscribed to this view
    private final ArrayList<ViewListener> listeners;
    private final JLabel label;

    public View() {
        final JFrame frame = new JFrame();
        frame.setSize(200, 100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout());

        final JButton button = new JButton("Hello, world!");

        button.addActionListener(e -> notifyListenersOnButtonClicked());
        frame.add(button);

        label = new JLabel();
        frame.add(label);

        this.listeners = new ArrayList<>();

        frame.setVisible(true);
    }

    // Iterate through the list, notifying each listener individuality
    private void notifyListenersOnButtonClicked() {
        listeners.forEach(ViewListener::onButtonClicked);
    }

    // Subscribe a listener
    public void addListener(final ViewListener listener) {
        listeners.add(listener);
    }

    public void setLabelText(final String text) {
        label.setText(text);
    }
}
