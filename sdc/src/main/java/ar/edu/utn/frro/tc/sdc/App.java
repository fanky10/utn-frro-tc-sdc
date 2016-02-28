package ar.edu.utn.frro.tc.sdc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import ar.edu.utn.frro.tc.sdc.gui.MethodPanelView;
import ar.edu.utn.frro.tc.sdc.gui.PanelClosedZieglerNichols;
import ar.edu.utn.frro.tc.sdc.gui.PanelCompare;

public class App extends JFrame {
	private double minW = 1060.0D;
	private double minH = 630.0D;

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);

					frame.setExtendedState(6);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public App() {
		Dimension d = new Dimension();
		d.setSize(this.minW, this.minH);
		setMinimumSize(d);

		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		final App frame = this;

		setTitle("Sintonización de controladores");
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						App.class
								.getResource("/icons/imagen de respuessta transitoria.jpg")));
		setDefaultCloseOperation(3);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		this.contentPane = new JPanel();
		this.contentPane.setBackground(Color.LIGHT_GRAY);
		this.contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		final JMenu curvaR = new JMenu("Curva Reacción Z-N");
		curvaR.setHorizontalAlignment(0);
		curvaR.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				App.this.contentPane.removeAll();
				MethodPanelView panel = new MethodPanelView(frame, "zn");

				App.this.contentPane.add(panel.getMainPanel(), "Center");
				App.this.contentPane.updateUI();

				JTable tabla = panel.getInputTable();
				tabla.requestFocus();
				tabla.changeSelection(0, 0, false, false);
			}

			public void mouseExited(MouseEvent e) {
				curvaR.setSelected(false);
			}

			public void mouseEntered(MouseEvent e) {
				curvaR.setSelected(true);
			}

		});
		final JMenu cohenCoon = new JMenu("Cohen - Coon");
		cohenCoon.setHorizontalAlignment(0);
		cohenCoon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				App.this.contentPane.removeAll();
				MethodPanelView panel = new MethodPanelView(frame, "cc");
				App.this.contentPane.add(panel.getMainPanel(), "Center");
				App.this.contentPane.updateUI();

				JTable tabla = panel.getInputTable();
				tabla.requestFocus();
				tabla.changeSelection(0, 0, false, false);
			}

			public void mouseExited(MouseEvent e) {
				cohenCoon.setSelected(false);
			}

			public void mouseEntered(MouseEvent e) {
				cohenCoon.setSelected(true);
			}

		});
		final JMenu lopez = new JMenu("Lopez");
		lopez.setHorizontalAlignment(0);
		lopez.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				App.this.contentPane.removeAll();
				MethodPanelView panel = new MethodPanelView(frame, "lopez");
				App.this.contentPane.add(panel.getMainPanel(), "Center");
				App.this.contentPane.updateUI();

				JTable tabla = panel.getInputTable();
				tabla.requestFocus();
				tabla.changeSelection(0, 0, false, false);
			}

			public void mouseExited(MouseEvent e) {
				lopez.setSelected(false);
			}

			public void mouseEntered(MouseEvent e) {
				lopez.setSelected(true);
			}

		});
		final JMenu kayaSheib = new JMenu("Kaya-Sheib");
		kayaSheib.setHorizontalAlignment(0);
		kayaSheib.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				App.this.contentPane.removeAll();
				MethodPanelView panel = new MethodPanelView(frame, "ks");
				App.this.contentPane.add(panel.getMainPanel(), "Center");
				App.this.contentPane.updateUI();

				JTable tabla = panel.getInputTable();
				tabla.requestFocus();
				tabla.changeSelection(0, 0, false, false);
			}

			public void mouseExited(MouseEvent e) {
				kayaSheib.setSelected(false);
			}

			public void mouseEntered(MouseEvent e) {
				kayaSheib.setSelected(true);

			}

		});
		final JMenu oscilaciones = new JMenu("Oscilaciones Sostenidas Z-N");
		oscilaciones.setHorizontalAlignment(0);
		oscilaciones.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				App.this.contentPane.removeAll();
				PanelClosedZieglerNichols panel = new PanelClosedZieglerNichols(
						frame);
				App.this.contentPane.add(panel.getMainPanel(), "Center");
				App.this.contentPane.updateUI();
			}

			public void mouseExited(MouseEvent e) {
				oscilaciones.setSelected(false);
			}

			public void mouseEntered(MouseEvent e) {
				oscilaciones.setSelected(true);
			}

		});
		final JMenu compare = new JMenu("Comparación");
		compare.setHorizontalAlignment(0);
		compare.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				App.this.contentPane.removeAll();
				PanelCompare panel = new PanelCompare(frame);
				App.this.contentPane.add(panel.getMainPanel(), "Center");

				App.this.contentPane.updateUI();
				panel.getInputField().requestFocus();
			}

			public void mouseExited(MouseEvent e) {
				compare.setSelected(false);
			}

			public void mouseEntered(MouseEvent e) {
				compare.setSelected(true);

			}

		});
		final JMenu mnExit = new JMenu("Salir");
		mnExit.setHorizontalAlignment(0);
		mnExit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}

			public void mouseExited(MouseEvent e) {
				mnExit.setSelected(false);
			}

			public void mouseEntered(MouseEvent e) {
				mnExit.setSelected(true);

			}

		});
		menuBar.add(curvaR);

		JMenu separator = new JMenu("||");
		JMenu separator2 = new JMenu("||");
		separator.setEnabled(false);
		separator2.setEnabled(false);

		menuBar.add(separator);

		menuBar.add(oscilaciones);

		menuBar.add(separator2);

		menuBar.add(mnExit);

		curvaR.setMargin(new Insets(0, 20, 0, 20));
		cohenCoon.setMargin(new Insets(0, 20, 0, 20));
		lopez.setMargin(new Insets(0, 20, 0, 20));
		kayaSheib.setMargin(new Insets(0, 20, 0, 20));
		compare.setMargin(new Insets(0, 20, 0, 20));

		separator.setMargin(new Insets(0, 0, 0, 40));
		separator2.setMargin(new Insets(0, 0, 0, 40));

		oscilaciones.setMargin(new Insets(0, 20, 0, 20));
		mnExit.setMargin(new Insets(0, 20, 0, 20));
	}
}
