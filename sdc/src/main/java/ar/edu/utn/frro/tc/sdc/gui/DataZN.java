package ar.edu.utn.frro.tc.sdc.gui;

import javax.swing.table.DefaultTableModel;

public abstract class DataZN {
	public static DefaultTableModel getConstants() {
		double[][] constantesM = {
				{ 1.435D, -0.921D, 0.878D, -0.749D, 0.482D, 1.137D },
				{ 1.357D, -0.947D, 0.842D, -0.738D, 0.381D, 0.995D },
				{ 1.495D, -0.945D, 1.101D, -0.771D, 0.56D, 1.006D } };

		return new DefaultTableModel(new Object[][] {
				{ "IAE", Double.valueOf(constantesM[0][0]),
						Double.valueOf(constantesM[0][1]),
						Double.valueOf(constantesM[0][2]),
						Double.valueOf(constantesM[0][3]),
						Double.valueOf(constantesM[0][4]),
						Double.valueOf(constantesM[0][5]) },
				{ "ITAE", Double.valueOf(constantesM[1][0]),
						Double.valueOf(constantesM[1][1]),
						Double.valueOf(constantesM[1][2]),
						Double.valueOf(constantesM[1][3]),
						Double.valueOf(constantesM[1][4]),
						Double.valueOf(constantesM[1][5]) },
				{ "ISE", Double.valueOf(constantesM[2][0]),
						Double.valueOf(constantesM[2][1]),
						Double.valueOf(constantesM[2][2]),
						Double.valueOf(constantesM[2][3]),
						Double.valueOf(constantesM[2][4]),
						Double.valueOf(constantesM[2][5]) } },

		new String[] { "Criterion", "a", "b", "c", "d", "e", "f" });
	}

	public static DefaultTableModel getModelValuesControllers(double vL,
			double vT) {
		double[][] result = new double[3][3];
		result[0][0] = redondear(vT / vL);
		result[1][0] = redondear(0.9D * (vT / vL));
		result[1][1] = redondear(vL / 0.03D);
		result[2][0] = redondear(1.2D * (vT / vL));
		result[2][1] = redondear(2.0D * vL);
		result[2][2] = redondear(0.5D * vL);

		return new DefaultTableModel(new Object[][] {
				{ "P", Double.valueOf(result[0][0]) },
				{ "PI", Double.valueOf(result[1][0]),
						Double.valueOf(result[1][1]) },
				{ "PID", Double.valueOf(result[2][0]),
						Double.valueOf(result[2][1]),
						Double.valueOf(result[2][2]) } }, new String[] {
				"Controller Type", "Kc", "Ti", "Td" }) {
			private static final long serialVersionUID = 1L;

			Class[] columnTypes = { String.class, String.class, String.class,
					String.class };

			public Class getColumnClass(int columnIndex) {
				return this.columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[4];

			public boolean isCellEditable(int row, int column) {
				return this.columnEditables[column];
			}
		};
	}

	public static String getURLEquationImage() {
		return "/icons/equationZieglerNichols.png";
	}

	public static String getTitle() {
		return headTitle;
	}

	public static String getDescription() {
		return description;
	}

	private static String headTitle = "Ziegler and Nichols - Open-Loop Method";

	private static String description = "The method involves obtaining a measure the response to a step input signal an open loop system .\n\nIf  the plant contains no dominant poles integrators or complex conjugates , the curve may take the form of a letter S. If the response does not exhibit this manner this method is not relevant .\n\nThat response curves are experimentally generated or from a dynamic simulation of the plant and are characterized by two parameters:\n\nL: delay .\n\nT: time constant.\n\n These parameters are determined by drawing a tangent line at the turning point of the curve and determining the intersections of the same with the time axis and the line Y ( t) = K ;  be K gain applied.";

	private static double redondear(double numero) {
		return Math.rint(numero * 100.0D) / 100.0D;
	}
}
