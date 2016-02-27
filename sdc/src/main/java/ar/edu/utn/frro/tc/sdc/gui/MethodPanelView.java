package ar.edu.utn.frro.tc.sdc.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import ar.edu.utn.frro.tc.sdc.App;

public class MethodPanelView extends JPanel {
	private static final long serialVersionUID = 1L;
	private String method;
	private JPanel mainPanel;
	private ar.edu.utn.frro.tc.sdc.openedloop.Grapher graficador;
	private InformationExpert dataTables;
	private JTable inputTable;
	private JTable vTableControllers;
	private JTable tableTL;
	int band = 0;
	private MethodPanelView globalMethodView = this;

	public MethodPanelView(final App mainView, String paramM) {
		this.method = paramM;

		this.dataTables = new InformationExpert();

		int marginTop = 30;
		int marginRight = 5;
		int marginRight2 = 645;

		this.graficador = new ar.edu.utn.frro.tc.sdc.openedloop.Grapher(this.globalMethodView);

		String headTitle = this.dataTables.getTitle(this.method);
		final String description = this.dataTables.getDescription(this.method);

		this.mainPanel = new JPanel();
		this.mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

		final JPanel graphicPanel = new JPanel();
		graphicPanel.setBounds(marginRight, marginTop, 625, 530);
		graphicPanel.setLayout(new BorderLayout(0, 0));
		graphicPanel.add(this.graficador.getDiagrama(), "Center");

		this.mainPanel.add(graphicPanel);

		JPanel referencePanel = new JPanel();
		referencePanel.setBorder(new TitledBorder(null, "Referencias", 4, 2,
				null, null));
		referencePanel.setBounds(645, 312, 342, 86);
		this.mainPanel.add(referencePanel);

		JPanel aditionalInfo = new JPanel();
		aditionalInfo.setBorder(new TitledBorder(null, "Información Adicional",
				4, 2, null, null));
		aditionalInfo.setBounds(645, 395, 342, 165);
		aditionalInfo.setAlignmentX(0.0F);
		this.mainPanel.add(aditionalInfo);

		JPanel panelLT = new JPanel();
		panelLT.setBorder(new TitledBorder(null, "Valores de L y T", 4, 2,
				null, null));
		panelLT.setBounds(marginRight2, 124, 200, 70);
		panelLT.setLayout(new BorderLayout(0, 0));

		JScrollPane paneltableTL = new JScrollPane();
		panelLT.add(paneltableTL, "Center");
		this.mainPanel.add(panelLT);

		JPanel vControllerPanel = new JPanel();
		vControllerPanel.setBorder(new TitledBorder(null,
				"Valores Sintonización", 4, 2, null, null));
		vControllerPanel.setBounds(645, 198, 349, 110);
		vControllerPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		vControllerPanel.add(scrollPane, "Center");
		this.mainPanel.add(vControllerPanel);

		JPanel jpInput = new JPanel();
		jpInput.setBorder(new TitledBorder(null, "Valores de Entrada", 4, 2,
				null, null));
		jpInput.setBounds(marginRight2, marginTop, 200, 70);
		jpInput.setLayout(new BorderLayout(0, 0));

		this.mainPanel.add(jpInput, "Center");
		JScrollPane spInputTable = new JScrollPane();
		spInputTable.setHorizontalScrollBarPolicy(31);
		jpInput.add(spInputTable);

		this.vTableControllers = new JTable();
		this.vTableControllers.setFocusTraversalKeysEnabled(false);
		this.vTableControllers.setFocusable(false);
		this.vTableControllers.setModel(this.dataTables
				.getModelValuesControllers());
		this.vTableControllers.getColumnModel().getColumn(0)
				.setPreferredWidth(106);

		scrollPane.setViewportView(this.vTableControllers);

		this.tableTL = new JTable();
		this.tableTL.setFocusTraversalKeysEnabled(false);
		this.tableTL.setFocusable(false);
		this.tableTL.setModel(this.dataTables.getModelLT());
		this.tableTL.setRowSelectionAllowed(false);
		this.tableTL.setCellSelectionEnabled(true);
		paneltableTL.setViewportView(this.tableTL);

		this.inputTable = new JTable();
		this.inputTable.setModel(getInputTableModel());

		this.inputTable.setRowSelectionAllowed(false);
		this.inputTable.setCellSelectionEnabled(true);

		spInputTable.setViewportView(this.inputTable);

		JLabel lblMtodoDeLa = new JLabel(headTitle);
		lblMtodoDeLa.setFont(new Font("Tahoma", 1, 16));
		lblMtodoDeLa.setBounds(marginRight, 5, 500, 20);
		this.mainPanel.add(lblMtodoDeLa);

		this.mainPanel.setLayout(null);

		JButton btnDraw = new JButton("  Graficar", new ImageIcon(
				MethodPanelView.class.getResource("/icons/icon_graficar.png")));
		btnDraw.setHorizontalAlignment(2);
		btnDraw.setBounds(850, marginTop, 140, 40);

		JButton btnClean = new JButton("  Limpiar", new ImageIcon(
				MethodPanelView.class.getResource("/icons/icon_limpiar.png")));
		btnClean.setHorizontalAlignment(2);
		btnClean.setBounds(850, marginTop + 40, 140, 40);

		JButton btnSave = new JButton("  Guardar", new ImageIcon(
				MethodPanelView.class.getResource("/icons/icon_guardar.png")));
		btnSave.setHorizontalAlignment(2);
		btnSave.setBounds(850, marginTop + 80, 140, 40);

		final JButton btnStop = new JButton("  Parar", new ImageIcon(
				MethodPanelView.class.getResource("/icons/icon_cancelar.png")));
		btnStop.setHorizontalAlignment(2);
		btnStop.setBounds(850, marginTop + 120, 140, 40);

		JButton btnAssumedModel = new JButton("Modelo asumido", new ImageIcon(
				MethodPanelView.class.getResource("/icons/icon_formula.png")));
		btnAssumedModel.setHorizontalAlignment(2);
		btnAssumedModel.setBounds(10, 22, 150, 40);

		JButton btnEquations = new JButton("Ecuaciones", new ImageIcon(
				MethodPanelView.class.getResource("/icons/icon_formula.png")));
		btnEquations.setHorizontalAlignment(2);
		btnEquations.setBounds(180, 22, 150, 40);

		JButton btnConstants = new JButton("Constantes", new ImageIcon(
				MethodPanelView.class.getResource("/icons/icon_formula.png")));
		btnConstants.setHorizontalAlignment(2);
		btnConstants.setBounds(10, 115, 150, 40);

		JButton btnDescription = new JButton("Descripción método",
				new ImageIcon(
						MethodPanelView.class
								.getResource("/icons/icon_formula.png")));
		btnDescription.setHorizontalAlignment(2);
		btnDescription.setBounds(180, 69, 150, 40);

		JButton controllerSchema = new JButton("Esquema del Controlador",
				new ImageIcon(
						MethodPanelView.class
								.getResource("/icons/icon_formula.png")));
		controllerSchema.setHorizontalAlignment(2);
		controllerSchema.setBounds(10, 69, 150, 40);

		this.mainPanel.add(btnClean);
		this.mainPanel.add(btnDraw);
		this.mainPanel.add(btnSave);
		this.mainPanel.add(btnStop);
		aditionalInfo.setLayout(null);
		aditionalInfo.add(btnAssumedModel);
		aditionalInfo.add(btnEquations);
		if ((this.method == "lopez") || (this.method == "ks")) {
			aditionalInfo.add(btnConstants);
		}

		aditionalInfo.add(btnDescription);
		aditionalInfo.add(controllerSchema);

		JLabel lblGanancia = new JLabel("Ganancia");
		JLabel lblRespuesta = new JLabel("Respuesta");
		JLabel lblRectaTangenteAl = new JLabel(
				"Recta tangente al punto de inflexión");
		JLabel lblRetardol = new JLabel("Retardo (L)");
		JLabel lblCteDeTiempo = new JLabel("Cte. de tiempo (T)");

		JLabel blanco = new JLabel("Ganancia");
		blanco.setIcon(new ImageIcon(App.class
				.getResource("/icons/blanco.png")));
		JLabel rojo = new JLabel("Red");
		rojo.setIcon(new ImageIcon(App.class
				.getResource("/icons/rojo.png")));
		JLabel azul = new JLabel("Blue");
		azul.setIcon(new ImageIcon(App.class
				.getResource("/icons/azul.png")));
		JLabel amarillo = new JLabel("Yelow");
		amarillo.setIcon(new ImageIcon(App.class
				.getResource("/icons/amarillo.png")));
		JLabel green = new JLabel("Green");
		green.setIcon(new ImageIcon(App.class
				.getResource("/icons/verde.png")));

		int widthColor = 15;
		int heightColor = 10;
		int positionColor = 20;
		int colorC1 = 15;
		int colorC2 = 191;

		blanco.setBounds(colorC1, positionColor, widthColor, heightColor);
		rojo.setBounds(colorC1, positionColor * 2, widthColor, heightColor);
		azul.setBounds(colorC1, positionColor * 3, widthColor, heightColor);
		amarillo.setBounds(colorC2, positionColor, widthColor, heightColor);
		green.setBounds(colorC2, positionColor + 25, widthColor, heightColor);

		colorC1 += 20;
		colorC2 += 20;

		lblGanancia.setBounds(42, 18, 100, 14);
		lblRespuesta.setBounds(42, 38, 150, 14);
		lblCteDeTiempo.setBounds(220, 37, 100, 16);
		lblRetardol.setBounds(220, 18, 100, 14);
		lblRectaTangenteAl.setBounds(42, 57, 300, 16);

		referencePanel.setLayout(null);
		referencePanel.add(blanco);
		referencePanel.add(azul);
		referencePanel.add(rojo);
		referencePanel.add(amarillo);
		referencePanel.add(green);

		referencePanel.add(lblGanancia);
		referencePanel.add(lblRespuesta);
		referencePanel.add(lblRectaTangenteAl);
		referencePanel.add(lblRetardol);
		referencePanel.add(lblCteDeTiempo);

		JLabel lblModeloAsumidoDe = new JLabel("Modelo asumido de la planta:");
		lblModeloAsumidoDe.setFont(new Font("Tahoma", 0, 15));
		lblModeloAsumidoDe.setBounds(695, 243, 350, 19);
		this.mainPanel.add(lblModeloAsumidoDe);

		if ((this.method == "lopez") || (this.method == "ks")) {

			btnConstants.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog dialog = new JDialog(mainView, true);
					dialog.setMinimumSize(new Dimension(500, 200));
					if (MethodPanelView.this.method == "lopez") {
						dialog.setTitle("Constantes Metodo de Lopez");
					} else {
						dialog.setTitle("Constantes Metodo de Kaya y Sheib");
					}

					dialog.getContentPane()
							.add(new JScrollPane(
									MethodPanelView.this.dataTables
											.getConstantTable(MethodPanelView.this.method)));
					dialog.setVisible(true);

					dialog.setBounds(100, 100, 500, 200);
				}
			});
		}

		btnAssumedModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = "Modelo asumido";

				ModalEquationView dialog = new ModalEquationView(mainView,
						title, "/icons/equationFirstOrder.png");
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(mainView);
			}

		});
		btnEquations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = "Ecuaciones del método";

				ModalEquationView dialog = new ModalEquationView(
						mainView,
						title,
						MethodPanelView.this.dataTables
								.getURLEquationImage(MethodPanelView.this.method));
				dialog.setVisible(true);
				dialog.setBounds(100, 100, 500, 200);
				dialog.setLocationRelativeTo(mainView);

			}

		});
		btnDescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModalMethodDescription dialog = new ModalMethodDescription(
						mainView, description);
				dialog.setVisible(true);
				dialog.setBounds(100, 100, 500, 200);
				dialog.setLocationRelativeTo(mainView);

			}

		});
		controllerSchema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = "Esquema del Controlador";

				ModalEquationView dialog = new ModalEquationView(mainView,
						title, "/icons/controllerSchemaOpenLoop.png");

				String sourceText = "http://blog.opticontrols.com/archives/477";
				dialog.setSource(sourceText);
				dialog.repaint();

				dialog.setVisible(true);
				dialog.setLocationRelativeTo(mainView);

			}

		});
		btnDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (MethodPanelView.this.inputTable.isEditing()) {
					MethodPanelView.this.inputTable.getCellEditor()
							.stopCellEditing();
				}
				MethodPanelView.this.inputTable.clearSelection();

				TableModel modelo = MethodPanelView.this.inputTable.getModel();

				System.out.println("a value:" + modelo.getValueAt(0, 0));
				System.out.println("b value:" + modelo.getValueAt(0, 1));

				if ((modelo.getValueAt(0, 1) != null)
						&& (modelo.getValueAt(0, 0) != null)) {
					String val1 = ((String) modelo.getValueAt(0, 0)).replace(
							",", ".");
					String val2 = ((String) modelo.getValueAt(0, 1)).replace(
							",", ".");

					Pattern pat = Pattern.compile("^\\d+|^\\d+\\.?\\d+");
					Matcher mat1 = pat.matcher(val1);
					Matcher mat2 = pat.matcher(val2);

					if ((mat1.matches()) && (mat2.matches())) {
						System.out.println("Are Numbers");

						double kp = Double.parseDouble("0" + val1);
						double tau = Double.parseDouble("0" + val2);

						if ((kp > 0.0D) && (tau > 0.0D)) {
							if ((kp < 20.0D) && (tau < 20.0D)) {
								MethodPanelView.this.band = 1;

								MethodPanelView.this.limpiar(graphicPanel);

								MethodPanelView.this.graficador = new ar.edu.utn.frro.tc.sdc.openedloop.Grapher(
										MethodPanelView.this.globalMethodView);
								MethodPanelView.this.graficador.insertCurve(kp,
										tau);

								graphicPanel.removeAll();
								graphicPanel.add(
										MethodPanelView.this.graficador
												.getDiagrama(), "Center");
								graphicPanel.validate();

								MethodPanelView.this.graficador
										.iniciarGraficoCurva();
								MethodPanelView.this.graficador
										.insertarDerivada();

							} else {

								JOptionPane
										.showMessageDialog(
												null,
												"Valor de constantes muy grande. El valor puede tomar una constante como máximo es 20",
												"Error", 0, null);
							}
						} else {
							JOptionPane
									.showMessageDialog(
											null,
											"Las constantes deben ser valores mayores que cero",
											"Error", 0, null);
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Debe ingresar valores numéricos", "Error", 0,
								null);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Debe ingresar constantes de tiempo y ganancia",
							"Error", 0, null);

				}

			}

		});
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MethodPanelView.this.limpiarTodo(graphicPanel);
				MethodPanelView.this.band = 0;

			}

		});
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MethodPanelView.this.graficador.getImage();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (MethodPanelView.this.band == 1) {
					btnStop.setIcon(new ImageIcon(MethodPanelView.class
							.getResource("/icons/icon_aceptar.png")));
					btnStop.setText(" Continuar");
					MethodPanelView.this.graficador.stop();
					MethodPanelView.this.band = 2;
				} else if (MethodPanelView.this.band == 2) {
					btnStop.setIcon(new ImageIcon(MethodPanelView.class
							.getResource("/icons/icon_cancelar.png")));
					btnStop.setText(" Parar");
					MethodPanelView.this.graficador.start();
					MethodPanelView.this.band = 1;
				}
			}
		});
	}

	public void completeTables(ar.edu.utn.frro.tc.sdc.openedloop.Grapher graficador, double kp, double tau) {
		if (graficador.getCurvaActual() != null) {
			ar.edu.utn.frro.tc.sdc.openedloop.CurveGenerator curvaActual = graficador.getCurvaActual();

			double vT = curvaActual.getT();
			double vL = curvaActual.getL();

			this.tableTL.setModel(this.dataTables.getModelLT(vL, vT));
		}
	}

	public void completeTableController(ar.edu.utn.frro.tc.sdc.openedloop.Grapher graph, double kp, double tau) {
		if (this.graficador.getCurvaActual() != null) {
			ar.edu.utn.frro.tc.sdc.openedloop.CurveGenerator curvaActual = this.graficador.getCurvaActual();

			double vT = curvaActual.getT();
			double vL = curvaActual.getL();

			this.vTableControllers.setModel(this.dataTables
					.getModelControllers(this.method, vL, vT, kp, tau));
			this.vTableControllers.getColumnModel().getColumn(0)
					.setPreferredWidth(106);
		}
	}

	private void limpiarTodo(JPanel panel) {
		this.inputTable.setModel(getInputTableModel());
		this.graficador.clean();

		panel.removeAll();
		panel.add(this.graficador.getDiagrama(), "Center");
		panel.validate();

		this.vTableControllers.setModel(this.dataTables
				.getModelValuesControllers());
		this.vTableControllers.getColumnModel().getColumn(0)
				.setPreferredWidth(106);

		this.tableTL.setModel(this.dataTables.getModelLT());
	}

	private void limpiar(JPanel panel) {
		this.graficador.clean();

		panel.removeAll();
		panel.add(this.graficador.getDiagrama(), "Center");
		panel.validate();

		this.vTableControllers.setModel(this.dataTables
				.getModelValuesControllers());
		this.vTableControllers.getColumnModel().getColumn(0)
				.setPreferredWidth(106);

		this.tableTL.setModel(this.dataTables.getModelLT());
	}

	private DefaultTableModel getInputTableModel() {
		return new DefaultTableModel(new Object[][] { new Object[2] },
				new String[] { "Ganancia", "Cte. Tiempo" });
	}

	public JPanel getMainPanel() {
		return this.mainPanel;
	}

	public JTable getInputTable() {
		return this.inputTable;
	}
}
