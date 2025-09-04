import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FrmJuego extends JFrame {
  private final JPanel pnlJugador1;
  private final JPanel pnlJugador2;
  private final Jugador jugador1;
  private final Jugador jugador2;

  public FrmJuego() {
    setSize(600, 300);
    setTitle("Juego de Cartas");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLayout(null);

    JButton btnRepartir = new JButton("Repartir");
    btnRepartir.setBounds(10, 10, 100, 25);
    getContentPane().add(btnRepartir);

    JButton btnVerificar = new JButton("Verificar");
    btnVerificar.setBounds(120, 10, 100, 25);
    getContentPane().add(btnVerificar);

    pnlJugador1 = new JPanel();
    pnlJugador1.setBackground(new Color(50, 255, 0));
    pnlJugador1.setLayout(null);
    pnlJugador2 = new JPanel();
    pnlJugador2.setBackground(new Color(0, 255, 255));
    pnlJugador2.setLayout(null);

    JTabbedPane tpJugadores = new JTabbedPane();
    tpJugadores.addTab("Martin Estrada Contreras", pnlJugador1);
    tpJugadores.addTab("Raul Vidal", pnlJugador2);
    tpJugadores.setBounds(10, 40, 550, 200);

    getContentPane().add(tpJugadores);

    btnRepartir.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            repartir();
          }
        });
    btnVerificar.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            verificar();
          }
        });
    jugador1 = new Jugador();
    jugador2 = new Jugador();
  }

  private void repartir() {
    jugador1.repartir();
    jugador2.repartir();

    jugador1.mostrar(pnlJugador1);
    jugador2.mostrar(pnlJugador2);
  }

  private void verificar() {}
}
