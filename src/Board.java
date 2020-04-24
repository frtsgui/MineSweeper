import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel {

    private final int N_IMG = 13;
    public static final int TAM_CELULA = 17;


    private final int DESENHA_COBERTURA = 10;
    private final int DESENHA_FLAG = 11;
    private final int DESENHA_FLAG_ERRADA = 12;

    public static int N_MINAS = 40;
    public static int N_LINHAS = 16;
    public static int N_COLS = 16;

    private int[][] campo;
    private boolean fimJogo;
    private int minas_sobrando;
    private Image[] img;

    private JLabel statusbar;


    public Board(JLabel statusbar) {

        this.statusbar = statusbar;

        img = new Image[N_IMG];

        for (int i = 0; i < N_IMG; i++) {
            img[i] = (new ImageIcon(getClass().getResource("img/" + i + ".png"))).getImage();
        }

        setDoubleBuffered(true);

        addMouseListener(new MinesAdapter());
        newGame();

    }

    private void newGame() {        
        fimJogo = false;
        minas_sobrando = N_MINAS;
        campo = CampoMinadoUtils.geraCampo(N_MINAS, N_LINHAS, N_COLS);
        statusbar.setText(Integer.toString(minas_sobrando));
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        int cell = 0;
        fimJogo = minas_sobrando == 0 || CampoMinadoUtils.fimJogo(campo);
        boolean perdeu = false;
        minas_sobrando = N_MINAS;
        for (int i = 0; i < N_LINHAS; i++) {
            for (int j = 0; j < N_COLS; j++) {
                cell = campo[i][j];
                if (fimJogo){
                    perdeu = perdeu || cell == 19;
                    if(cell >= 10 && cell <= 19) cell -= 10;
                    if(cell >= 20){
                        if (cell < 29){
                            cell = DESENHA_FLAG_ERRADA;
                            perdeu = true;
                        }else{
                            cell = DESENHA_FLAG;
                        }
                    }
                }else{
                    if(cell < 10){
                        cell = DESENHA_COBERTURA;
                    }else if(cell >= 10 && cell <= 19){
                        cell -= 10;
                    }else{
                        --minas_sobrando;
                        cell = DESENHA_FLAG;
                    }
                }
                g.drawImage(img[cell], (j * TAM_CELULA),
                    (i * TAM_CELULA), this);
            }
        }

        if (fimJogo) {
            if(perdeu)
                statusbar.setText("Perdeu!");
            else
                statusbar.setText("Ganhou!");
        }else
            statusbar.setText("Faltam "+minas_sobrando);
    }

    public void chamaJogada(){
        int [][]campoOculto = new int[campo.length][campo[0].length];
        for(int i=0;i<campo.length;++i){
            for(int j=0;j<campo[0].length;++j){
                if(campo[i][j] < 10){
                    campoOculto[i][j] = -1;
                }else if(campo[i][j] >= 20){
                    campoOculto[i][j] = 29;
                }else{
                    campoOculto[i][j] = campo[i][j];
                }
            }
        }

        int []escolha = CampoMinadoUtils.escolheAuto(campoOculto);
        CampoMinadoUtils.escolheu(campo, escolha[0], escolha[1]);
        repaint();
    }


    class MinesAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();

            int cCol = x / TAM_CELULA;
            int cRow = y / TAM_CELULA;

            if (fimJogo) {
                newGame();
                repaint();
                return;
            }


            if ((x < N_COLS * TAM_CELULA) && (y < N_LINHAS * TAM_CELULA)) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    CampoMinadoUtils.marcou(campo, cRow, cCol);
                } else {
                    CampoMinadoUtils.escolheu(campo, cRow, cCol);
                }
                repaint();
            }
        }
    }
}
