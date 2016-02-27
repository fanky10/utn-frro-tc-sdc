package ar.edu.utn.frro.tc.sdc.gui;

import javax.swing.table.DefaultTableModel;

public abstract class DataKS {
	public static DefaultTableModel getConstants() {
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

		new String[] { "Criterios", "a", "b", "c", "d", "e", "f" }) {
			private static final long serialVersionUID = 1L;

			Class[] columnTypes = { String.class, String.class, String.class,
					String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return this.columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[7];

			public boolean isCellEditable(int row, int column) {
				return this.columnEditables[column];
			}
		};
	}

	public static DefaultTableModel getModelValuesControllers(double vL,
			double vT, double kp, double tau) {
		double[][] result = new double[3][6];
		result[0][0] = redondear(constantesM[0][0] / kp
				* Math.pow(vL / tau, constantesM[0][1]));
		result[0][1] = redondear(tau / constantesM[0][2]
				* Math.pow(vL / tau, -constantesM[0][3]));
		result[0][2] = redondear(tau * constantesM[0][4]
				* Math.pow(vL / tau, constantesM[0][5]));
		result[1][0] = redondear(constantesM[1][0] / kp
				* Math.pow(vL / tau, constantesM[1][1]));
		result[1][1] = redondear(tau / constantesM[1][2]
				* Math.pow(vL / tau, -constantesM[1][3]));
		result[1][2] = redondear(tau * constantesM[1][4]
				* Math.pow(vL / tau, constantesM[1][5]));
		result[2][0] = redondear(constantesM[2][0] / kp
				* Math.pow(vL / tau, constantesM[2][1]));
		result[2][1] = redondear(tau / constantesM[2][2]
				* Math.pow(vL / tau, -constantesM[2][3]));
		result[2][2] = redondear(tau * constantesM[2][4]
				* Math.pow(vL / tau, constantesM[2][5]));

		return new DefaultTableModel(new Object[][] {
				{ "PID-IAE", Double.valueOf(result[0][0]),
						Double.valueOf(result[0][1]),
						Double.valueOf(result[0][2]) },
				{ "PID-ITAE", Double.valueOf(result[1][0]),
						Double.valueOf(result[1][1]),
						Double.valueOf(result[1][2]) },
				{ "PID-ISE", Double.valueOf(result[2][0]),
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
		return "/icons/equationLopez.png";
	}

	public static String getTitle() {
		return headTitle;
	}

	public static String getDescription() {
		return description;
	}

	private static String headTitle = "Método de Kaya y Sheib - Sistema de lazo abierto";

	private static double[][] constantesM = {
			{ 0.98089D, -0.76167D, 0.91032D, -1.05211D, 0.59974D, 0.89819D },
			{ 0.77902D, -1.06401D, 1.14311D, -0.70949D, 0.57137D, 1.03826D },
			{ 1.11907D, -0.89711D, 0.7987D, -0.9548D, 0.54766D, 0.87798D } };

	private static String description = "Mientras López desarrolló el método de sintonización para un controlador PID-Ideal, Kaya y Sheib realizaron lo mismo para controladores que se denominaron PID-Clásico (PID-Serie), PID- No Interactuante (una variación del PID-Paralelo) y PID-Industrial.\n\nEl procedimiento de sintonización está basado en el mejor modelo de primer orden más tiempo muerto que se pueda obtener para lazos de control que funcionan como reguladores. El criterio de desempeño corresponde a la minimización de alguno de los criterios integrales y el controlador a uno de los indicados anteriormente. \n\n";

	private static double redondear(double numero) {
		return Math.rint(numero * 100.0D) / 100.0D;
	}
}
