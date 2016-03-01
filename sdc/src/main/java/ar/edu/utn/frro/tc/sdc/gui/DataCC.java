package ar.edu.utn.frro.tc.sdc.gui;

import javax.swing.table.DefaultTableModel;

public abstract class DataCC {
	public static DefaultTableModel getModelValuesControllers(double vL,
			double vT, double kp, double tau) {
		double[][] result = new double[3][3];
		result[0][0] = redondear(vT / vL * (1.0D + vL / (3.0D * tau)));
		result[1][0] = redondear(vT / vL * (0.9D + vL / (12.0D * tau)));
		result[1][1] = redondear(vL
				* ((32.0D + 6.0D * (vL / tau)) / (9.0D + 20.0D * (vL / tau))));
		result[2][0] = redondear(4.0D * vT / 3.0D * vL + 1.0D / (4.0D * kp));
		result[2][1] = redondear(vL * (32.0D + 6.0D * (vL / tau))
				/ (13.0D + 8.0D * (vL / tau)));
		result[2][2] = redondear(vL * (4.0D / (11.0D + 2.0D * (vL / tau))));
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
		return "/icons/equationCohenCoon.png";
	}

	public static String getTitle() {
		return headTitle;
	}

	public static String getDescription() {
		return description;
	}

	private static String headTitle = "Método de Cohen y Coon - Sistema de lazo abierto";

	private static String description = "El método de Cohen y Coon o método de la curva de reacción del proceso introduce un índice de auto regulación definido como μ = L/τ.\n\nDonde:\nL: Tiempo muerto aparente (tm)\nτ: Constante de tiempo\n\nCohen y Coon plantearon nuevas ecuaciones de sintonización basadas en el mejor modelo de primer orden más un tiempo muerto que se pueda obtener para lazos de control que funcionan como regulador. \n\nEste método aproxima la respuesta del sistema real a un sistema equivalente. Para el cálculo de los parámetros se aplica un pequeño cambio escalón al lazo abierto y se grafica la curva de la variable medida.\n\nSe puede observar que la sintonía mediante éste método tiene una acción de control proporcional más intensa que la obtenida por el método de Ziegler-Nichols.\n\nPara compensar el método de Ziegler-Nichols tiene una constante de tiempo integral menor para hacer al lazo de control más robusto. \n\nLa acción de control derivativa es más intensa.";

	private static double redondear(double numero) {
		return Math.rint(numero * 100.0D) / 100.0D;
	}
}
