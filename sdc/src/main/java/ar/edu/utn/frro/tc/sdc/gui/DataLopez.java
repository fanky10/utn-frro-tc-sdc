package ar.edu.utn.frro.tc.sdc.gui;

import javax.swing.table.DefaultTableModel;

public abstract class DataLopez {
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
			double vT, double Kp, double tau) {
		double[][] result = new double[3][6];
		result[0][0] = redondear(constantesM[0][0] / Kp
				* Math.pow(vL / tau, constantesM[0][1]));
		result[0][1] = redondear(tau / constantesM[0][2]
				* Math.pow(vL / tau, -constantesM[0][3]));
		result[0][2] = redondear(tau * constantesM[0][4]
				* Math.pow(vL / tau, constantesM[0][5]));
		result[1][0] = redondear(constantesM[1][0] / Kp
				* Math.pow(vL / tau, constantesM[1][1]));
		result[1][1] = redondear(tau / constantesM[1][2]
				* Math.pow(vL / tau, -constantesM[1][3]));
		result[1][2] = redondear(tau * constantesM[1][4]
				* Math.pow(vL / tau, constantesM[1][5]));
		result[2][0] = redondear(constantesM[2][0] / Kp
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
						Double.valueOf(result[2][2]) } },

		new String[] { "Tipo controlador", "Kc", "Ti", "Td" }) {
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

	private static String headTitle = "Método de Lopez - Sistema de lazo abierto";

	static double[][] constantesM = {
			{ 1.435D, -0.921D, 0.878D, -0.749D, 0.482D, 1.137D },
			{ 1.357D, -0.947D, 0.842D, -0.738D, 0.381D, 0.995D },
			{ 1.495D, -0.945D, 1.101D, -0.771D, 0.56D, 1.006D } };

	private static String description = "El primer método basado en criterios integrales que presentó ecuaciones para el cálculo de los parámetros del controlador fue desarrollado por López y es conocido como el método de López.\n\nLópez define una función de costo de la forma: Φ = F[ e(t),t]dt ; donde F es una función del error y del tiempo. Se obtiene un valor que caracteriza la respuesta del sistema.\n\nEntre menor sea el valor de Φ, mejor será el desempeño del sistema de control, por ello, un desempeño óptimo se obtiene cuando Φ es mínimo.\n\nLos criterios de desempeño utilizados por López fueron: integral del error absoluto (IAE),  integral del error absoluto por el tiempo (ITAE) e integral del error cuadrático (ISE).\n\nLa optimización de los criterios de desempeño integrales de López está basada en el mejor modelo de primer orden más tiempo muerto que se pueda obtener, para lazos de control que funcionan como reguladores con un controlador PID-Ideal.\n\nSegún el criterio de López los parámetros del PID se encuentran en base a la minimización de los índices de funcionamiento. Para esto se asume que la respuesta se aproxima por una función de transferencia de primer orden con retardo.\n\nLa sintonia de controladores PID para procesos industriales está basada normalmente en especificaciones nominales sobre determinadas características de la respuesta del sistema en lazo cerrado a cambios bruscos en el punto de consigna o en la carga.\n\nTambién es usual basar el diseño en criterios de optimización sobre la señal de error, tratando de minimizar alguna de las cuatro integrales típicas de la señal de error: la integral del error (IE), la integral del cuadrado del error (ISE), la integral del valor absoluto del error (IAE) y la integral del valor absoluto del error ponderado en el tiempo (ITAE).\n\n";

	private static double redondear(double numero) {
		return Math.rint(numero * 100.0D) / 100.0D;
	}
}
