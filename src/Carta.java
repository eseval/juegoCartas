import java.util.Random;
import javax.swing.*;

public class Carta {
  private final int indice;

  public Carta(Random r) {
    indice = r.nextInt(52) + 1;
  }

  public JLabel mostrar(JPanel pnl, int x, int y) {
    String archivoImagen = "img/CARTA" + indice + ".jpg";
    ImageIcon imgCarta = new ImageIcon(getClass().getResource(archivoImagen));
    JLabel lblCarta = new JLabel();
    lblCarta.setIcon(imgCarta);
    lblCarta.setBounds(x, y, imgCarta.getIconWidth(), imgCarta.getIconHeight());
    pnl.add(lblCarta);
    return lblCarta;
  }
}
