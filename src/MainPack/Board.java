package MainPack;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;


public class Board {
    private JFrame frame;
    public static Cell arrayOfCells[][];
    public static Cell orderedArrayOfCells[][];
    public static int numberOfMoves;

    public Board() {
        arrayOfCells = new Cell[4][4];
        orderedArrayOfCells = new Cell[4][4];
        numberOfMoves = 0;
        makeFrame();
    }

    private void makeFrame() {
        this.frame = new JFrame("Game Of Fifteen");

        final JPanel contentPane = (JPanel) this.frame.getContentPane();
        contentPane.setBackground(Color.pink);

        Border lineBorder = BorderFactory.createLineBorder(Color.DARK_GRAY);

        contentPane.setLayout(new GridLayout(4, 4, 3, 3));

        GameGenerator generator = new GameGenerator(4);

        int rows = 0;
        int columns = 0;

        for(int i : generator) {

            Cell a = new Cell(i,rows,columns);
            a.setBorder(lineBorder);
            contentPane.add(a);
            arrayOfCells[rows][columns] =(a);
            columns++;
            if(columns > 3) {
                rows++;
                columns = 0;
            }
        }

        rows = 0;
        columns = 0;


        for(int j = 1; j < 17; j++) {
            if(j == 16) {
                orderedArrayOfCells[rows][columns] = new Cell(0, rows, columns);
            } else {
                orderedArrayOfCells[rows][columns] = new Cell(j,rows,columns);
            }
            columns++;
            if(columns > 3) {
                rows++;
                columns = 0;
            }
        }

        frame.setSize(500,500);
        frame.setBackground(Color.GRAY);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

}


