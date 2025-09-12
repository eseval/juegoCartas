import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Jugador {

  private final int TOTAL_CARTAS = 10;
  private final int SEPARACION = 40;
  private final int MARGEN = 10;
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
      lblCartas[z] = carta.mostrar(pnl, posicion, MARGEN);
      posicion += SEPARACION;
      z++;
    }

    z = lblCartas.length - 1;
    for (JLabel lbl : lblCartas) {
      pnl.setComponentZOrder(lbl, z);
      z--;
    }

    pnl.repaint();
  }

  public String getGrupos() {
    String resultado = "No se encontraron grupos";

    // Reiniciar el estado de las cartas
    for (Carta carta : cartas) {
      carta.setUtilizada(false);
    }

    // Contar cartas por nombre
    int[] contadores = new int[NombreCarta.values().length];
    for (Carta carta : cartas) {
      contadores[carta.getNombre().ordinal()]++;
    }

    // Buscar escaleras a partir de 2 cartas
    String escaleras = "";
    for (int i = 0; i < TOTAL_CARTAS - 1; i++) {
      if (!cartas[i].isUtilizada()) {
        int secuencia = 1;
        for (int j = i + 1; j < TOTAL_CARTAS; j++) {
          if (cartas[j].getPinta() == cartas[i].getPinta()
              && cartas[j].getNombre().ordinal() == cartas[i].getNombre().ordinal() + secuencia
              && !cartas[j].isUtilizada()) {
            secuencia++;
            cartas[j].setUtilizada(true);
          }
        }
        if (secuencia >= 2) { // antes era >= 3
          cartas[i].setUtilizada(true);
          escaleras +=
              "\nEscalera de "
                  + cartas[i].getNombre()
                  + " a "
                  + NombreCarta.values()[cartas[i].getNombre().ordinal() + secuencia - 1]
                  + " de "
                  + cartas[i].getPinta();
        }
      }
    }

    // Marcar cartas en pares/ternas como utilizadas
    for (Carta carta : cartas) {
      if (contadores[carta.getNombre().ordinal()] >= 2) {
        carta.setUtilizada(true);
      }
    }

    // Verificar si hay grupos
    boolean hayGrupos = false;
    for (int contador : contadores) {
      if (contador >= 2) {
        hayGrupos = true;
        break;
      }
    }
    if (!escaleras.equals("")) {
      hayGrupos = true;
    }

    if (hayGrupos) {
      resultado = "Grupos encontrados:" + escaleras;

      // Agregar pares y ternas
      for (int i = 0; i < contadores.length; i++) {
        if (contadores[i] >= 2) {
          if (contadores[i] == 2) {
            resultado += "\nPar de " + NombreCarta.values()[i];
          } else if (contadores[i] == 3) {
            resultado += "\nTerna de " + NombreCarta.values()[i];
          } else if (contadores[i] == 4) {
            resultado += "\nCuarta de " + NombreCarta.values()[i];
          }
        }
      }
    }

    // Calcular puntaje de cartas no utilizadas
    int totalPuntos = 0;
    for (Carta carta : cartas) {
      if (!carta.isUtilizada()) {
        int puntos = 0;
        if (carta.getNombre().ordinal() == 0) { // AS
          puntos = 1;
        } else if (carta.getNombre().ordinal() >= 1 && carta.getNombre().ordinal() <= 9) { // 2-10
          puntos = carta.getNombre().ordinal() + 1;
        } else { // J, Q, K
          puntos = 10;
        }
        totalPuntos += puntos;
      }
    }

    resultado += "\n\nPuntaje final: " + totalPuntos;

    return resultado;
  }
}
