package ar.edu.utn.frro.tc.sdc.gui;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class InformationExpert {
	DefaultTableModel globalModelLT;
	DefaultTableModel globalModelConstants;

	public void dataZieglerNichols() {
	}

	public void dataZieglerNichols(String typeModel) {
	}

	public JTable getConstantTable(String typeModel) {
		JTable constantsTable = new JTable();

		if (typeModel == "lopez") {
			constantsTable.setModel(DataLopez.getConstants());
		} else if (typeModel == "ks") {
			constantsTable.setModel(DataKS.getConstants());
		} else {
			constantsTable.setModel(null);
		}

		constantsTable.setRowSelectionAllowed(false);
		constantsTable.setCellSelectionEnabled(true);
		return constantsTable;
	}

	public JTable getTableLT(String typeModel, double vL, double vT) {
		JTable tableLT = new JTable();

		if (typeModel == "zn") {
			tableLT.setModel(getModelLT(vL, vT));
		} else if (typeModel == "cc") {
			tableLT.setModel(getModelLT(vL, vT));
		} else if (typeModel == "lopez") {
			tableLT.setModel(getModelLT(vL, vT));
		} else if (typeModel == "ks") {
			tableLT.setModel(getModelLT(vL, vT));
		}

		tableLT.setRowSelectionAllowed(false);
		tableLT.setCellSelectionEnabled(true);
		return tableLT;
	}

	public JTable getTableLT() {
		JTable tableLT = new JTable();
		tableLT.setModel(getModelLT());

		tableLT.setRowSelectionAllowed(false);
		tableLT.setCellSelectionEnabled(true);

		return tableLT;
	}

	public DefaultTableModel getModelLT() {
		return  new DefaultTableModel(new Object[][] { new Object[2] }, new String[] {
				"L", "T" }) {
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

	public DefaultTableModel getModelLT(double vL, double vT) {
		return new DefaultTableModel(new Object[][] { { Double.valueOf(round(vL)),
				Double.valueOf(round(vT)) } }, new String[] { "L", "T" }) {
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

	public DefaultTableModel getModelValuesControllers() {
		return new DefaultTableModel(new Object[][] { { "P" }, { "PI" }, { "PID" } },
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

	public JTable getTableControllers(String typeModel, double vL, double vT,
			double kp, double tau) {
		JTable tableControllers = new JTable();

		if (typeModel == "zn") {
			tableControllers.setModel(DataZN.getModelValuesControllers(vL, vT));
		} else if (typeModel == "cc") {
			tableControllers.setModel(DataCC.getModelValuesControllers(vL, vT,
					kp, tau));
		} else if (typeModel == "lopez") {
			tableControllers.setModel(DataLopez.getModelValuesControllers(vL,
					vT, kp, tau));
		} else if (typeModel == "ks") {
			tableControllers.setModel(DataKS.getModelValuesControllers(vL, vT,
					kp, tau));
		}

		tableControllers.setRowSelectionAllowed(false);
		tableControllers.setCellSelectionEnabled(true);
		return tableControllers;
	}

	public DefaultTableModel getModelControllers(String typeModel, double vL,
			double vT, double kp, double tau) {
		if (typeModel == "zn")
			return DataZN.getModelValuesControllers(vL, vT);
		if (typeModel == "cc")
			return DataCC.getModelValuesControllers(vL, vT, kp, tau);
		if (typeModel == "lopez")
			return DataLopez.getModelValuesControllers(vL, vT, kp, tau);
		if (typeModel == "ks") {
			return DataKS.getModelValuesControllers(vL, vT, kp, tau);
		}
		return null;
	}

	public String getTitle(String typeModel) {
		if (typeModel == "zn")
			return DataZN.getTitle();
		if (typeModel == "cc")
			return DataCC.getTitle();
		if (typeModel == "lopez")
			return DataLopez.getTitle();
		if (typeModel == "ks") {
			return DataKS.getTitle();
		}
		return null;
	}

	public String getDescription(String typeModel) {
		if (typeModel == "zn")
			return DataZN.getDescription();
		if (typeModel == "cc")
			return DataCC.getDescription();
		if (typeModel == "lopez")
			return DataLopez.getDescription();
		if (typeModel == "ks") {
			return DataKS.getDescription();
		}
		return null;
	}

	public String getURLEquationImage(String typeModel) {
		if (typeModel == "zn")
			return DataZN.getURLEquationImage();
		if (typeModel == "cc")
			return DataCC.getURLEquationImage();
		if (typeModel == "lopez")
			return DataLopez.getURLEquationImage();
		if (typeModel == "ks") {
			return DataKS.getURLEquationImage();
		}
		return null;
	}

	public String getAssumedModel() {
		return "/icons/equationFirstOrder.png";
	}

	public double round(double numero) {
		return Math.rint(numero * 100.0D) / 100.0D;
	}
}
