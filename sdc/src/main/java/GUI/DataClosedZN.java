package GUI;

import javax.swing.table.DefaultTableModel;

public abstract class DataClosedZN {
	public static DefaultTableModel getKPModel() {
		return new DefaultTableModel(new Object[][] { new Object[2] },
				new String[] { "K último", "P último" }) {
			private static final long serialVersionUID = 1L;

			Class[] columnTypes = { Double.class, Double.class };

			public Class getColumnClass(int columnIndex) {
				return this.columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[2];

			public boolean isCellEditable(int row, int column) {
				return this.columnEditables[column];
			}
		};
	}

	public static DefaultTableModel getKPModel(double vK, double vP) {
		return new DefaultTableModel(new Object[][] { {
				Double.valueOf(round(vK)), Double.valueOf(round(vP)) } },
				new String[] { "K último", "P último" }) {
			private static final long serialVersionUID = 1L;

			Class[] columnTypes = { Double.class, Double.class };

			public Class getColumnClass(int columnIndex) {
				return this.columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[2];

			public boolean isCellEditable(int row, int column) {
				return this.columnEditables[column];
			}
		};
	}

	public static DefaultTableModel getControllerModel() {
		return new DefaultTableModel(new Object[][] { { "P", "0.5 Ku" },
				{ "PI", "0.45 Ku", "(1/1.2) Pu" },
				{ "PID", "0.6 Ku", "0.5 Pu", "0.125 Pu" } }, new String[] {
				"Tipo de controlador", "Kc", "Ti", "Td" }) {
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

	public static DefaultTableModel getControllerModel(double criticK,
			double criticP) {
		return new DefaultTableModel(new Object[][] {
				{ "P", Double.valueOf(round(0.5D * criticK)) },
				{ "PI", Double.valueOf(round(0.45D * criticK)),
						Double.valueOf(round(0.8333333333333334D * criticP)) },
				{ "PID", Double.valueOf(round(0.6D * criticK)),
						Double.valueOf(round(0.5D * criticP)),
						Double.valueOf(round(0.125D * criticP)) } },

		new String[] { "Tipo de controlador", "Kc", "Ti", "Td" }) {
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

	private static double round(double numero) {
		return Math.rint(numero * 100.0D) / 100.0D;
	}

	private static String description = "Un procedimiento de sintonización a lazo cerrado implica deshabilitar cualquier acción integral o derivativa del controlador.\n\nConsiste en ir aumentando la ganancia del controlador hasta que la variable de proceso empiece a oscilar. La cantidad de ganancia necesaria para generar oscilaciones sostenidas (amplitud constante) es llamada “ganancia ultima” del proceso, mientras que el tiempo (período) entre cada pico de oscilación es llamado “período último” del proceso.\n\nZiegler y Nichols indican cómo se debe configurar la ganancia del controlador con un valor de la mitad de la ganancia última.  Kp = 0.5 Ku \n\nKp: Ganancia del controlador \n\nKu: Ganancia última, determinada incrementando la ganancia del controlador hasta lograr oscilación auto-sostenibles.\n\nUna ganancia del controlador a la mitad de la “ganancia última” determinada experimentalmente resulta una razonable respuesta rápida ante cambios de setpoint y cambios de carga.\n\nLas oscilaciones de la variable de proceso a cambios de setpoint y de carga son atenuadas con cada pico de onda siendo aproximadamente un cuarto de la amplitud del pico anterior.\n\nLas reglas dadas por Ziegler y Nichols describen una relación real entre los parámetros de sintonización y las características operacionales del proceso. Para que el proceso oscile constantemente la ganancia del controlador debería ser una fracción de la ganancia última y la constante de tiempo integral debería ser proporcional a la constante de tiempo del proceso. \n\n";

	public static String getDescription() {
		return description;
	}
}
