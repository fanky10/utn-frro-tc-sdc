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

		new String[] { "Criterios", "a", "b", "c", "d", "e", "f" });
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
				"Tipo controlador", "Kc", "Ti", "Td" }) {
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

	private static String headTitle = "Método de Ziegler y Nichols - Sistema de lazo abierto";

	private static String description = "El Método consiste en obtener la respuesta de una señal medida ante una entrada escalón en un sistema de lazo abierto.\n\nSi la planta no contiene integradores ni polos dominantes complejos conjugados, la curva puede tener la forma de una letra S. Si la respuesta no exhibe dicha forma este método no es pertinente.\n\nTales curvas de respuesta se generan experimentalmente o a partir de una simulación dinámica de la planta y están caracterizadas por dos parámetros:\n\nL: Tiempo de retardo.\n\nT: Constante de tiempo.\n\nEstos parámetros se determinan dibujando una recta tangente en el punto de inflexión de la curva y determinando las intersecciones de la misma con el eje del tiempo y la línea Y(t) = K; siendo K la ganancia aplicada. \n\n";

	private static double redondear(double numero) {
		return Math.rint(numero * 100.0D) / 100.0D;
	}
}
