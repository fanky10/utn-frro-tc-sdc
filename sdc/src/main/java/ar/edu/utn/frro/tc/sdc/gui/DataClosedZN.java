package ar.edu.utn.frro.tc.sdc.gui;

import javax.swing.table.DefaultTableModel;

public abstract class DataClosedZN {
	public static DefaultTableModel getKPModel() {
		return new DefaultTableModel(new Object[][] { new Object[2] },
				new String[] { "K Last", "P Last" }) {
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
				new String[] { "K Last", "P Last" }) {
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

	public static DefaultTableModel getControllerModel(double criticK,
			double criticP) {
		return new DefaultTableModel(new Object[][] {
				{ "P", Double.valueOf(round(0.5D * criticK)) },
				{ "PI", Double.valueOf(round(0.45D * criticK)),
						Double.valueOf(round(0.8333333333333334D * criticP)) },
				{ "PID", Double.valueOf(round(0.6D * criticK)),
						Double.valueOf(round(0.5D * criticP)),
						Double.valueOf(round(0.125D * criticP)) } },

		new String[] { "Controller Type", "Kc", "Ti", "Td" }) {
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

	private static String description = "A tuning procedure involves closed-loop or disable any integral derivative action controller.\n\nConsists to gradually increase the controller gain until the process variable starts to oscillate.The amount of gain required to generate sustained oscillations (constant amplitude) is called ‘last gain’ process, while time (period) between each peak oscillation is called ''last period'' process.\n\nZiegler and Nichols indicate how to configure the controller gain with a value of half the ultimate gain. Kp = 0.5 Ku \n\nKp: Gain Controller\n\nKu:Last gain, determined by increasing the controller gain to achieve self-sustaining oscillation \n\nA controller gain half of the ''ultimate gain'' determined experimentally is a reasonable fast response to changes in set point and load changes.\n\n The oscillations of the process variable setpoint changes and load are attenuated with each wave peak being approximately a quarter of the width of previous peak.\n\nThe rules given by Ziegler and Nichols describes a real relationship between the tuning parameters and operational characteristics of the process. For the process to constantly swing the controller gain should be a fraction of the ultimate gain and integral time constant should be proportional to the time constant of the process.\n\n";

	public static String getDescription() {
		return description;
	}
}
