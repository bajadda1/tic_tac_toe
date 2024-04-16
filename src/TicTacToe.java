import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TicTacToe extends JFrame implements ActionListener {

    JButton[][] buttons;
    JLabel label;
    JPanel head;
    JPanel body;
    String player="X";
    public TicTacToe(){
        this.setTitle("Tic Tac Toe");
        this.setSize(500, 500);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        head=new JPanel();
        head.setPreferredSize(new Dimension(500,100));
        head.setBackground(Color.yellow);
        this.add(head,BorderLayout.NORTH);

        label=new JLabel();
        label.setText(player+" :your turn");
        label.setFont(new Font("Ink Free",Font.BOLD,40));
        label.setPreferredSize(new Dimension(500,100));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        head.add(label);

        body=new JPanel();
        body.setPreferredSize(new Dimension(500,400));
        body.setBackground(Color.red);
        body.setLayout(new GridLayout(3,3));

        buttons =new JButton[3][3];
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                buttons[i][j]=new JButton("");
                buttons[i][j].setFocusable(false);
                buttons[i][j].setFont(new Font("Ink Free", Font.BOLD, 50));
                buttons[i][j].addActionListener(this);
                body.add(buttons[i][j]);
            }
        }

        this.add(body,BorderLayout.CENTER);
    }

    public boolean isFull() {
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isEmpty(int i,int j) {
        if(buttons[i][j].getText().equals("")) {
            return true;
        }
        return false;
    }

    public boolean isWin(String player) {
        for(int i=0;i<3;i++) {
            if((buttons[i][0].getText().equals(player) && buttons[i][1].getText().equals(player) && buttons[i][2].getText().equals(player))||
                    (buttons[0][i].getText().equals(player) && buttons[1][i].getText().equals(player) && buttons[2][i].getText().equals(player))
                    ||(buttons[0][0].getText().equals(player) && buttons[1][1].getText().equals(player) && buttons[2][2].getText().equals(player))
                    ||(buttons[0][2].getText().equals(player) && buttons[1][1].getText().equals(player) && buttons[2][0].getText().equals(player))) {
                return true;

            }
        }
        return false;
    }

    private void endGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }

        if (isWin(player)) {
            JOptionPane.showMessageDialog(this, "Player " + player + " wins!");

        } else {
            JOptionPane.showMessageDialog(this, "It's a draw!");
        }
    }

    public void passTurn() {
        player=player.equals("X")?"O":"X";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(!isFull()) {
                    if(e.getSource().equals(buttons[i][j]) && isEmpty(i, j) ) {
                        buttons[i][j].setText(player);
                        if(isWin(player)) {
                            head.setBackground(Color.green);
                            label.setText(player+" is the winner");
                            endGame();
                        }
                        else if(isFull()) {
                            head.setBackground(Color.red);
                            label.setText("it's a draw");
                            endGame();

                        }
                        else {
                            passTurn();
                            label.setText(player+" :your turn");
                        }

                    }
                }

            }
        }


    }

}