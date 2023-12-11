import java.awt.*;
import java.awt.event.*;

class TicTacToe extends Frame implements ActionListener {

    Button[][] buttons = new Button[11][11];
    Button nextGame;
    Button exit;
    int moveCounter = 0;

    TicTacToe(){
        setLayout(null);
        setVisible(true);
        setLocation(200,400);
        setSize(800,600);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);

        for(int i = 0; i < buttons.length; i++){
            for(int j = 0; j < buttons[i].length; j++){
                buttons[i][j] = new Button();
                buttons[i][j].setSize(49, 49);
                buttons[i][j].setLocation(5 + i * 50, 42 + j * 50);
                buttons[i][j].setBackground(Color.ORANGE);
                buttons[i][j].setForeground(Color.BLACK);
                buttons[i][j].setFont(new Font("", Font.BOLD, 20));
                buttons[i][j].setLabel("");
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        nextGame = new Button("New Game");
        nextGame.addActionListener(this);
        nextGame.setForeground(Color.BLACK);
        nextGame.setBackground(Color.ORANGE);
        nextGame.setFont(new Font("", Font.BOLD, 20));
        nextGame.setSize(160, 90);
        nextGame.setLocation(586, 421);
        add(nextGame);

        exit = new Button("QUIT");
        exit.addActionListener(this);
        exit.setForeground(Color.BLACK);
        exit.setBackground(Color.RED);
        exit.setSize(160, 80);
        exit.setLocation(586, 520);
        exit.setFont(new Font("", Font.BOLD, 20));
        add(exit);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.exit(0);
        }
        else if (e.getSource() == nextGame){
            reset(buttons);
        }
        else {
            for (Button[] buttonRow : buttons){
                for (Button singleButton : buttonRow) {
                    if (e.getSource() == singleButton) {
                        if (moveCounter % 2 == 0) {
                            singleButton.setLabel("O");
                            singleButton.setEnabled(false);
                            moveCounter++;
                            if (checkIfMoveWon(singleButton.getLabel())) {
                                win(singleButton.getLabel());
                            };
                            break;
                        }
                        if (moveCounter %2 == 1) {
                            singleButton.setLabel("X");
                            singleButton.setEnabled(false);
                            moveCounter++;
                            if (checkIfMoveWon(singleButton.getLabel())){
                                win(singleButton.getLabel());
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    public void reset(Button[][] buttons){
        for (Button[] buttonRow : buttons){
            for (Button singleButton : buttonRow){
                singleButton.setLabel("");
                singleButton.setEnabled(true);
                singleButton.setBackground(Color.ORANGE);
                singleButton.setForeground(Color.BLACK);
            }
        }
        moveCounter = 0;
    }

    public boolean isWinning(int startingI, int startingJ, int directionI, int directionJ, String label){
        for (int i = 0; i < 5; i++){
            if (!buttons[startingI + i * directionI][startingJ + i * directionJ].getLabel().equals(label)){
                return false;
            }
        }
        return true;
    }

    public boolean checkIfMoveWon(String label){
        for (int i = 0; i < buttons.length; i++){
            for (int j = 0; j < buttons[i].length; j++){
                if (j + 4 < buttons[i].length && isWinning(i, j, 0, 1, label)){
                    return true;
                }
                if (i + 4 < buttons.length && isWinning(i, j, 1, 0, label)){
                    return true;
                }
                if (i + 4 < buttons.length && j + 4 < buttons[i].length && isWinning(i, j, 1, 1, label)){
                    return true;
                }
            }
        }
        return false;
    }

    public void win(String label){
        for(Button[] buttonRow : buttons){
            for(Button singleButton : buttonRow){
                singleButton.setEnabled(false);
                singleButton.setLabel(":)");
            }
        }
        for(int i = 2; i < 9; i++){
            buttons[5][i].setForeground(Color.YELLOW);
            buttons[5][i].setBackground(Color.GREEN);
        }
        for(int i = 4; i < 7; i++){
            buttons[7][i].setForeground(Color.YELLOW);
            buttons[7][i].setBackground(Color.GREEN);
        }
        buttons[5][2].setLabel("P");
        buttons[5][3].setLabel("L");
        buttons[5][4].setLabel("A");
        buttons[5][5].setLabel("Y");
        buttons[5][6].setLabel("E");
        buttons[5][7].setLabel("R");
        buttons[5][8].setLabel(":");
        buttons[6][5].setLabel(label);
        buttons[6][5].setForeground(Color.YELLOW);
        buttons[6][5].setBackground(Color.GREEN);
        buttons[7][4].setLabel("W");
        buttons[7][5].setLabel("I");
        buttons[7][6].setLabel("N");
    }
}





public class Main {

    public static void main(String[] args) {
        TicTacToe f = new TicTacToe();

    }
}
