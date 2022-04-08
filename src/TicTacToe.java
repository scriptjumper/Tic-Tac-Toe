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

    // Grid Layout
    int rows = 3;
    int cols = 3;

    // Grid Colours
    int grid_bg_red = 150;
    int grid_bg_green = 150;
    int grid_bg_blue = 150;

    // Grid Buttons
    String buttonFontName = "TimesRoman";
    int buttonFontStyle = Font.BOLD;
    int buttonFontSize = 120;

    // Players
    String x = "X";
    String o = "O";

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

        jPanel_button.setLayout(new GridLayout(rows, cols));
        jPanel_button.setBackground(new Color(grid_bg_red, grid_bg_green, grid_bg_blue));

        for(int i = 0; i < 9; i++) {
            jButtons[i] = new JButton();
            jPanel_button.add(jButtons[i]);
            jButtons[i].setFont(new Font(buttonFontName, buttonFontStyle, buttonFontSize));
            jButtons[i].setFocusable(false);
            jButtons[i].addActionListener(this);
        }

        jPanel_title.add(jLabel_textField); // Adding text field to the title
        jFrame.add(jPanel_title, BorderLayout.NORTH); // Adding title to the frame
        jFrame.add(jPanel_button);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 9; i++) {
            if(e.getSource() == jButtons[i]) {
                if(player1_turn) {
                    setBlockText(i, 255, 0, 0, x, false, o);
                } else {
                    setBlockText(i, 0, 0, 255, o, true, x);
                }
            }
        }
    }

    // Setting text (either X or O) on block
    public void setBlockText(int idx, int red, int green, int blue, String playerText, boolean isPlayerTurn, String nextPlayerText) {
        if(Objects.equals(jButtons[idx].getText(), "")) {
            jButtons[idx].setForeground(new Color(red, green, blue));
            jButtons[idx].setText(playerText);
            player1_turn = isPlayerTurn;
            jLabel_textField.setText(nextPlayerText + " turn");
            checkWinningConditions();
        }
    }

    // Determines who's turn is first X or O
    public void firstTurn() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(random.nextInt(2) == 0) {
            player1_turn = true;
            jLabel_textField.setText(x + " turn");
        }
        else {
            player1_turn = false;
            jLabel_textField.setText(o + " turn");
        }
    }

    // Check if there is any winning conditions
    // Or if any player(X or O) has won
    public void checkWinningConditions() {
        // check X win conditions
        if(
                (Objects.equals(jButtons[0].getText(), x)) &&
                        (Objects.equals(jButtons[1].getText(), x)) &&
                        (Objects.equals(jButtons[2].getText(), x))
        ) {
            winningPlayer(0, 1, 2, x);
        }
        if(
                (Objects.equals(jButtons[3].getText(), x)) &&
                        (Objects.equals(jButtons[4].getText(), x)) &&
                        (Objects.equals(jButtons[5].getText(), x))
        ) {
            winningPlayer(3, 4, 5, x);
        }
        if(
                (Objects.equals(jButtons[6].getText(), x)) &&
                        (Objects.equals(jButtons[7].getText(), x)) &&
                        (Objects.equals(jButtons[8].getText(), x))
        ) {
            winningPlayer(6, 7, 8, x);
        }
        if(
                (Objects.equals(jButtons[0].getText(), x)) &&
                        (Objects.equals(jButtons[3].getText(), x)) &&
                        (Objects.equals(jButtons[6].getText(), x))
        ) {
            winningPlayer(0, 3, 6, x);
        }
        if(
                (Objects.equals(jButtons[1].getText(), x)) &&
                        (Objects.equals(jButtons[4].getText(), x)) &&
                        (Objects.equals(jButtons[7].getText(), x))
        ) {
            winningPlayer(1, 4, 7, x);
        }
        if(
                (Objects.equals(jButtons[2].getText(), x)) &&
                        (Objects.equals(jButtons[5].getText(), x)) &&
                        (Objects.equals(jButtons[8].getText(), x))
        ) {
            winningPlayer(2, 5, 8, x);
        }
        if(
                (Objects.equals(jButtons[0].getText(), x)) &&
                        (Objects.equals(jButtons[4].getText(), x)) &&
                        (Objects.equals(jButtons[8].getText(), x))
        ) {
            winningPlayer(0, 4, 8, x);
        }
        if(
                (Objects.equals(jButtons[2].getText(), x)) &&
                        (Objects.equals(jButtons[4].getText(), x)) &&
                        (Objects.equals(jButtons[6].getText(), x))
        ) {
            winningPlayer(2, 4, 6, x);
        }

        // Check O win conditions
        if(
                (Objects.equals(jButtons[0].getText(), o)) &&
                        (Objects.equals(jButtons[1].getText(), o)) &&
                        (Objects.equals(jButtons[2].getText(), o))
        ) {
            winningPlayer(0, 1, 2, o);
        }
        if(
                (Objects.equals(jButtons[3].getText(), o)) &&
                        (Objects.equals(jButtons[4].getText(), o)) &&
                        (Objects.equals(jButtons[5].getText(), o))
        ) {
            winningPlayer(3, 4, 5, o);
        }
        if(
                (Objects.equals(jButtons[6].getText(), o)) &&
                        (Objects.equals(jButtons[7].getText(), o)) &&
                        (Objects.equals(jButtons[8].getText(), o))
        ) {
            winningPlayer(6, 7, 8, o);
        }
        if(
                (Objects.equals(jButtons[0].getText(), o)) &&
                        (Objects.equals(jButtons[3].getText(), o)) &&
                        (Objects.equals(jButtons[6].getText(), o))
        ) {
            winningPlayer(0, 3, 6, o);
        }
        if(
                (Objects.equals(jButtons[1].getText(), o)) &&
                        (Objects.equals(jButtons[4].getText(), o)) &&
                        (Objects.equals(jButtons[7].getText(), o))
        ) {
            winningPlayer(1, 4, 7, o);
        }
        if(
                (Objects.equals(jButtons[2].getText(), o)) &&
                        (Objects.equals(jButtons[5].getText(), o)) &&
                        (Objects.equals(jButtons[8].getText(), o))
        ) {
            winningPlayer(2, 5, 8, o);
        }
        if(
                (Objects.equals(jButtons[0].getText(), o)) &&
                        (Objects.equals(jButtons[4].getText(), o)) &&
                        (Objects.equals(jButtons[8].getText(), o))
        ) {
            winningPlayer(0, 4, 8, o);
        }
        if(
                (Objects.equals(jButtons[2].getText(), o)) &&
                        (Objects.equals(jButtons[4].getText(), o)) &&
                        (Objects.equals(jButtons[6].getText(), o))
        ) {
            winningPlayer(2, 4, 6, o);
        }
    }

    public void winningPlayer(int a, int b, int c, String player) {
        jButtons[a].setBackground(Color.GREEN);
        jButtons[b].setBackground(Color.GREEN);
        jButtons[c].setBackground(Color.GREEN);

        for(int i = 0; i < 9; i++) {
            jButtons[i].setEnabled(false);
        }

        jLabel_textField.setText(player + " wins");
    }
}