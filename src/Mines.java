import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;


public class Mines extends JFrame implements ActionListener {

    private int FRAME_WIDTH;
    private int FRAME_HEIGHT;

    private JLabel statusbar;
    private Board board;

    public Mines() {
        statusbar = new JLabel("");
        board = new Board(statusbar);

        FRAME_WIDTH = Board.TAM_CELULA * Board.N_COLS;
        FRAME_HEIGHT = Board.TAM_CELULA * (Board.N_LINHAS + 6);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Minesweeper");


        JButton auto = new JButton("Jogada sugerida");
        auto.addActionListener(this);

        JButton config = new JButton("Mudar configuração");
        config.addActionListener(this);

        JPanel botoes = new JPanel(new BorderLayout());
        botoes.add(config, BorderLayout.NORTH);
        botoes.add(auto, BorderLayout.SOUTH);

        add(botoes, BorderLayout.NORTH);
        add(board);
        add(statusbar, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Jogada sugerida")){
            board.chamaJogada();
        }else{
            String nlin = JOptionPane.showInputDialog("Quantas linhas deve ter o campo?", Integer.toString(Board.N_LINHAS));
            if (nlin == null) return;
            String ncol = JOptionPane.showInputDialog("Quantas colunas deve ter o campo?", Integer.toString(Board.N_COLS));
            if (ncol == null) return;
            String nminas = JOptionPane.showInputDialog("Quantas minas deve ter o campo?", Integer.toString(Board.N_MINAS));
            if(nminas == null) return;

            Board.N_LINHAS = Integer.parseInt(nlin);
            Board.N_COLS = Integer.parseInt(ncol);
            Board.N_MINAS = Integer.parseInt(nminas);

            JFrame ex = new Mines();
            ex.setVisible(true);
            this.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame ex = new Mines();
                ex.setVisible(true);
            }
        });
    }
}
