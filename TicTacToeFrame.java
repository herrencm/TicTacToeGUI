import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private JButton[][] buttons;
    private char currentPlayer;
    private int moveCount;

    public TicTacToeFrame() {
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        currentPlayer = 'X';
        moveCount = 0;

        initializeButtons();
    }

    private void initializeButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[row][col].addActionListener(new ButtonListener(row, col));
                add(buttons[row][col]);
            }
        }
    }

    private class ButtonListener implements ActionListener {
        private int row;
        private int col;

        public ButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().equals("") && moveCount < 9) {
                buttons[row][col].setText(String.valueOf(currentPlayer));
                moveCount++;

                if (checkForWin(row, col)) {
                    JOptionPane.showMessageDialog(TicTacToeFrame.this, "Player " + currentPlayer + " wins!");
                    if (playAgain()) {
                        resetGame();
                    } else {
                        System.exit(0);
                    }
                } else if (moveCount == 9) {
                    JOptionPane.showMessageDialog(TicTacToeFrame.this, "It's a tie!");
                    if (playAgain()) {
                        resetGame();
                    } else {
                        System.exit(0);
                    }
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }
        }
    }
    private boolean checkForWin(int row, int col) {
        if (buttons[row][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[row][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[row][2].getText().equals(String.valueOf(currentPlayer)) ) {
            return true;
        }

        if (buttons[0][col].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][col].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][col].getText().equals(String.valueOf(currentPlayer)) ) {
            return true;
        }

        if (row == col &&
                buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer)) ) {
            return true;
        }

        if (row + col == 2 &&
                buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][0].getText().equals(String.valueOf(currentPlayer)) ) {
            return true;
        }

        return false;
    }
    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        currentPlayer = 'X';
        moveCount = 0;
    }

    private boolean playAgain() {
        int choice = JOptionPane.showConfirmDialog(
                TicTacToeFrame.this,
                "Do you want to play again?",
                "Play Again",
                JOptionPane.YES_NO_OPTION
        );
        return choice == JOptionPane.YES_OPTION;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeFrame frame = new TicTacToeFrame();
            frame.setVisible(true);
        });
    }
}