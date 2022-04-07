import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {

    // Application configs
    Random random = new Random();
    JFrame jFrame = new JFrame();
    JPanel jPanel_title = new JPanel();
    JPanel jPanel_button = new JPanel();
    JLabel jLabel_textField = new JLabel();
    JButton[] jButtons = new JButton[9];
    boolean player1_turn;

    // Frame size (TODO: could be a constant size for both the width and height which is a choice to make later on...)
    int width = 800;
    int height = 800;

    // Background colour using RGB colour code (TODO: Make colour scheme match the scriptjumper colour scheme or something close...)
    int pane_bg_red = 50;
    int pane_bg_green = 50;
    int pane_bg_blue = 50;

    // Text field background colour using RGB colour code (TODO: Make colour scheme match the scriptjumper colour scheme or something close...)
    int textField_bg_red = 25;
    int textField_bg_green = 25;
    int textField_bg_blue = 25;

    // Text field foreground colour using RGB colour code (TODO: Make colour scheme match the scriptjumper colour scheme or something close...)
    int textField_fg_red = 25;
    int textField_fg_green = 255;
    int textField_fg_blue = 0;

    // Font styling
    String fontName = "TimesRoman"; // TODO: will change to a personal favorite
    int fontStyle = Font.BOLD; // 1
    int fontSize = 75;

    // Game Name
    String gameName = "Tic-Tac-Toe";

    // Panel bounds
    int xBounds = 0;
    int yBounds = 0;
    int boundsWidth = width; // Linked to width of the frame
    int boundsHeight = 100;

    // constructor
    TicTacToe() {
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(width, height);
        jFrame.getContentPane().setBackground(new Color(pane_bg_red, pane_bg_green, pane_bg_blue));
        jFrame.setLayout(new BorderLayout());
        jFrame.setVisible(true);

        jLabel_textField.setBackground(new Color(textField_bg_red, textField_bg_green, textField_bg_blue));
        jLabel_textField.setForeground(new Color(textField_fg_red, textField_fg_green, textField_fg_blue));
        jLabel_textField.setFont(new Font(fontName, fontStyle, fontSize));
        jLabel_textField.setHorizontalAlignment(JLabel.CENTER);
        jLabel_textField.setText(gameName);
        jLabel_textField.setOpaque(true);

        jPanel_title.setLayout(new BorderLayout());
        jPanel_title.setBounds(xBounds, yBounds, boundsWidth, boundsHeight);

        jPanel_title.add(jLabel_textField); // Adding text field to the title
        jFrame.add(jPanel_title); // Adding title to the frame
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //
    }

    // Determines who's turn is first X or O
    public void firstTurn() {
        //
    }

    // Check if there is any winning conditions
    // Or if any player(X or O) has won
    public void checkWinningConditions() {
        //
    }

    public void xWins(int a, int b, int c) {
        //
    }

    public void oWins(int a, int b, int c) {
        //
    }
}
