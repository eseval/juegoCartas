import java.util.Random;
import javax.swing.*;

public class Jugador {
  private final int TOTAL_CARTAS = 10;
  private final int MARGEN = 10;
  private final int SEPARACION = 40;

  private final Carta[] cartas = new Carta[TOTAL_CARTAS];
  private final Random r = new Random();

  public void repartir() {
    for (int i = 0; i < TOTAL_CARTAS; i++) {
      cartas[i] = new Carta(r);
    }
  }

  public void mostrar(JPanel pnl) {
    pnl.removeAll();
    int posicion = MARGEN;
    JLabel[] lblCartas = new JLabel[TOTAL_CARTAS];
    int z = 0;
    for (Carta carta : cartas) {
      carta.mostrar(pnl, posicion, MARGEN);
      posicion += SEPARACION;
      z++;
    }

    z = lblCartas.length - 1;
    for (JLabel lbl : lblCartas) {
      System.out.println("PNL :"+ pnl + "pnl");
      pnl.setComponentZOrder(lbl, z);
      z--;
    }

    pnl.repaint();
  }
}
