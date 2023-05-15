package MainPack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import static javax.swing.JOptionPane.showMessageDialog;


public class Cell extends JButton implements ActionListener{
    private int row;
    private int column;
    private int value;

    public Cell(int value, int row, int column) {
        super();

        this.row = row;
        this.column = column;
        this.setValue(value);


        setBorder(new EmptyBorder(3,3,3,3));
        setFont(new Font("Arial", Font.PLAIN, 50));
        setForeground(Color.DARK_GRAY);

        addActionListener(this);

        if(value == 0) {
            setText(" ");
        }
        else {
            setText("" + value);
        }
    }

    public boolean isTheWinner() {
        for(int r = 0; r < Board.arrayOfCells.length; r++) {
            for(int c = 0; c < Board.arrayOfCells[r].length; c++) {
                if(Board.arrayOfCells[row][column].getValue() != Board.arrayOfCells[row][column].getValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void swapNeighboringCells(int row, int column) {

        int currentRow = row;
        int currentColumn = column;

        int emptyColumn = 0;
        int emptyRow = 0;

        for (int r = 0; r < Board.arrayOfCells.length; r++) {
            for (int c = 0; c < Board.arrayOfCells[r].length; c++) {
                if(Board.arrayOfCells[r][c].getValue() == 0) {
                    emptyRow = Board.arrayOfCells[r][c].row;
                    emptyColumn = Board.arrayOfCells[r][c].column;
                }
            }
        }

        if((currentRow == emptyRow && Math.abs(currentColumn - emptyColumn) == 1) || (currentColumn == emptyColumn && Math.abs(currentRow - emptyRow) == 1)) {

            Board.arrayOfCells[emptyRow][emptyColumn].setText(Board.arrayOfCells[row][column].getText());
            Board.arrayOfCells[emptyRow][emptyColumn].setValue(Board.arrayOfCells[row][column].getValue());


            Board.arrayOfCells[row][column].setText(" ");
            Board.arrayOfCells[row][column].setValue(0);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Board.numberOfMoves++;
        swapNeighboringCells(row, column);

        if (Board.arrayOfCells[3][3].getValue() == 0) {
            if (isTheWinner()) {
                showMessageDialog(null, "Success! Game over!");
            }
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}