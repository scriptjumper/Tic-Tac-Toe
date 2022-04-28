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

    // Frame size
    private final static int width = 800;
    private final static int height = 800;

    // Background color using RGB color code (#323232 - Dark Grey Almost Black)
    private final static int pane_bg_red = 50;
    private final static int pane_bg_green = 50;
    private final static int pane_bg_blue = 50;

    // Text field background color using RGB color code (#191919 - Black)
    private final static int textField_bg_red = 25;
    private final static int textField_bg_green = 25;
    private final static int textField_bg_blue = 25;

    // Text field foreground color using RGB color code (#1aff00 - Green/Light Green)
    private final static int textField_fg_red = 0;
    private final static int textField_fg_green = 136;
    private final static int textField_fg_blue = 255;

    // Font styling
    private final static String fontName = "Sathu";
    private final static int fontStyle = Font.BOLD; // 1
    private final static int fontSize = 75;

    // Game Name
    private final static String gameName = "Tic-Tac-Toe";

    // Panel bounds
    private final static int xBounds = 0;
    private final static int yBounds = 0;
    private final static int boundsWidth = width; // Linked to width of the frame
    private final static int boundsHeight = 100;

    // Grid Layout
    private final static int rows = 3;
    private final static int cols = 3;

    // Grid Colors (#969696 - Medium Grey)
    private final static int grid_bg_red = 150;
    private final static int grid_bg_green = 150;
    private final static int grid_bg_blue = 150;

    // Grid Buttons
    private final static int buttonFontSize = 120;

    // Players
    private final static String x = "X";
    // X Player color Red
    private final static int xPlayer_red = 255;
    private final static int xPlayer_green = 0;
    private final static int xPlayer_blue = 0;
    private final static String o = "O";
    // O Player color Blue
    private final static int oPlayer_red = 0;
    private final static int oPlayer_green = 136;
    private final static int oPlayer_blue = 255;

    // constructor
    TicTacToe() {
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
            jButtons[i].setFont(new Font(fontName, fontStyle, buttonFontSize));
            jButtons[i].setFocusable(false);
            jButtons[i].addActionListener(this);
        }

        // Menu options
        final JMenuBar tableMenuBar = createTableMenuBar();
        jFrame.setJMenuBar(tableMenuBar);

        jPanel_title.add(jLabel_textField); // Adding text field to the title
        jFrame.add(jPanel_title, BorderLayout.NORTH); // Adding title to the frame
        jFrame.add(jPanel_button);

        firstTurn();
    }

    private JMenuBar createTableMenuBar() {
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        return tableMenuBar;
    }

    /**
     * Creating window menu to handle restarting and exting the game
     * @return
     */
    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");

        final JMenuItem restart = new JMenuItem("Restart");
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new TicTacToe();
            }
        });
        fileMenu.add(restart);

        // Close game and window
        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);

        return fileMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 9; i++) {
            if(e.getSource() == jButtons[i]) {
                if(player1_turn) {
                    setBlockText(i, xPlayer_red, xPlayer_green, xPlayer_blue, x, false, o);
                } else {
                    setBlockText(i, oPlayer_red, oPlayer_green, oPlayer_blue, o, true, x);
                }
            }
        }
    }

    /**
     * Setting text (either X or O) on block
     * @param idx
     * @param red
     * @param green
     * @param blue
     * @param playerText
     * @param isPlayerTurn
     * @param nextPlayerText
     */
    private void setBlockText(int idx, int red, int green, int blue, String playerText, boolean isPlayerTurn, String nextPlayerText) {
        if(Objects.equals(jButtons[idx].getText(), "")) {
            jButtons[idx].setForeground(new Color(red, green, blue));
            jButtons[idx].setText(playerText);
            player1_turn = isPlayerTurn;
            jLabel_textField.setText(nextPlayerText + " turn");
            checkWinningCombinations(playerText);
        }
    }

    /**
     * Randomly choosing a player to start the game
     */
    private void firstTurn() {
        try {
            jPanel_button.setVisible(false);
            Thread.sleep(2000);
            jPanel_button.setVisible(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(random.nextInt(2) == 0) {
            player1_turn = true;
            jLabel_textField.setText(x + " turn");
        } else {
            player1_turn = false;
            jLabel_textField.setText(o + " turn");
        }
    }

    /**
     * This matrix is used to find indexes to check all
     * possible winning triplets in board[0..8]
     * @param playerText ("X" or "O")
     */
    private void checkWinningCombinations(final String playerText) {
        final int[][] combinations = {
                {0, 1, 2}, // Check first row.
                {3, 4, 5}, // Check second Row
                {6, 7, 8}, // Check third Row
                {0, 3, 6}, // Check first column
                {1, 4, 7}, // Check second Column
                {2, 5, 8}, // Check third Column
                {0, 4, 8}, // Check first Diagonal
                {2, 4, 6}  // Check second Diagonal
        };

        for (int i = 0; i < combinations.length; i++) {
            if ((Objects.equals(jButtons[combinations[i][0]].getText(), playerText)) &&
                    (Objects.equals(jButtons[combinations[i][1]].getText(), playerText)) &&
                    (Objects.equals(jButtons[combinations[i][2]].getText(), playerText))) {
                endGameMessage(combinations[i][0], combinations[i][1], combinations[i][2], playerText);
            }
        }

        if (!Objects.equals(jButtons[0].getText(), "") &&
                !Objects.equals(jButtons[1].getText(), "") &&
                !Objects.equals(jButtons[2].getText(), "") &&
                !Objects.equals(jButtons[3].getText(), "") &&
                !Objects.equals(jButtons[4].getText(), "") &&
                !Objects.equals(jButtons[5].getText(), "") &&
                !Objects.equals(jButtons[6].getText(), "") &&
                !Objects.equals(jButtons[7].getText(), "") &&
                !Objects.equals(jButtons[8].getText(), "")) {
            endGameMessage(0, 0, 0, "");
        }
    }

    /**
     * Either setting a message for the winner or a draw
     * @param a
     * @param b
     * @param c
     * @param player winning player
     */
    private void endGameMessage(final int a, final int b, final int c, final String player) {
        final String endGameMessage;

        if (!Objects.equals(player, "")) {
            endGameMessage = player + " wins";

            // Setting the background color of the winning combination
            jButtons[a].setBackground(Color.BLUE);
            jButtons[a].setOpaque(true);
            jButtons[b].setBackground(Color.BLUE);
            jButtons[b].setOpaque(true);
            jButtons[c].setBackground(Color.BLUE);
            jButtons[c].setOpaque(true);
        } else {
            endGameMessage = "Draw";
        }

        for(int i = 0; i < 9; i++) {
            jButtons[i].setEnabled(false);
        }

        jLabel_textField.setText(endGameMessage);
    }
}