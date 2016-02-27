package ar.edu.utn.frro.tc.sdc.openedloop;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.filechooser.FileFilter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeriesCollection;

import ar.edu.utn.frro.tc.sdc.gui.MethodPanelView;

public class Grapher {
	private XYSeriesCollection conjuntoDatos;
	private CurveGenerator curvaActual;
	private JFreeChart diagrama;
	double t = 0.0D;
	private double curveTime = 0.0D;
	private double kPlant = 0.0D;
	private double systemTime = this.curveTime * 7.0D;
	private Timer timerCurve;
	int flag = 0;
	public MethodPanelView globalPanelView;
	private Grapher graph = this;

	public Grapher() {
		this.conjuntoDatos = new XYSeriesCollection();
		resetTimer();
	}

	public Grapher(MethodPanelView view) {
		this.globalPanelView = view;

		this.conjuntoDatos = new XYSeriesCollection();
		resetTimer();
	}

	public JPanel getDiagrama() {
		this.diagrama = ChartFactory.createXYLineChart("", "t", "Y(t)",
				this.conjuntoDatos, PlotOrientation.VERTICAL, false, false,
				false);

		XYPlot plot = this.diagrama.getXYPlot();
		XYItemRenderer renderer = plot.getRenderer();
		renderer.setSeriesPaint(0, Color.WHITE);
		renderer.setSeriesPaint(1, Color.RED);
		renderer.setSeriesPaint(2, Color.BLUE);
		renderer.setSeriesPaint(3, Color.YELLOW);
		renderer.setSeriesPaint(4, Color.GREEN);
		renderer.setSeriesPaint(5, Color.BLACK);
		renderer.setSeriesPaint(6, Color.BLACK);

		if (this.curvaActual != null) {
			this.diagrama.getXYPlot().getRangeAxis()
					.setLowerBound(this.curvaActual.getPuntoYInicio());
			this.diagrama.getXYPlot().getRangeAxis()
					.setUpperBound(this.curvaActual.getPuntoYFin());
		}

		return new ChartPanel(this.diagrama);
	}

	public void resetTimer() {
		this.flag = 0;
		this.t = 0.0D;

		this.timerCurve = new Timer(20, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Grapher.this.t <= Grapher.this.curveTime * 7.0D)
						&& (Grapher.this.flag == 0)) {
					Grapher.this.curvaActual.dibujarK(Grapher.this.t);

					Grapher.this.t += 0.5D;
				} else if (Grapher.this.flag == 0) {
					Grapher.this.t = 0.0D;
					Grapher.this.flag = 1;
				}

				if ((Grapher.this.t < Grapher.this.curveTime * 7.0D)
						&& (Grapher.this.flag == 1)) {
					Grapher.this.curvaActual.dibujarCurva(Grapher.this.t);
					Grapher.this.t += 0.05D;
				} else if (Grapher.this.flag == 1) {
					Grapher.this.t = 0.0D;
					Grapher.this.flag = 2;
				}

				if ((Grapher.this.t < Grapher.this.curveTime * 7.0D)
						&& (Grapher.this.flag == 2)) {
					Grapher.this.curvaActual.dibujarTangente(Grapher.this.t);
					Grapher.this.t += 0.2D;

				} else if (Grapher.this.flag == 2) {
					Grapher.this.t = 0.0D;
					Grapher.this.flag = 3;
					ValueMarker[] arrayOfValueMarker;
					int j = (arrayOfValueMarker = Grapher.this.curvaActual
							.getMarcadoresCurva()).length;
					for (int i = 0; i < j; i++) {
						ValueMarker marcador = arrayOfValueMarker[i];
						Grapher.this.diagrama.getXYPlot().addDomainMarker(
								marcador);
					}
				}

				if ((Grapher.this.t < Grapher.this.curvaActual.getRealT())
						&& (Grapher.this.flag == 3)) {
					Grapher.this.curvaActual.dibujaLT(Grapher.this.t);
					Grapher.this.t += 0.05D;

				} else if (Grapher.this.flag == 3) {
					Grapher.this.t = 0.0D;
					Grapher.this.flag = 4;
				}

				if (Grapher.this.flag >= 4) {
					Grapher.this.globalPanelView.completeTables(
							Grapher.this.graph, Grapher.this.kPlant,
							Grapher.this.curveTime);

					Grapher.this.timerCurve.setDelay(500);

					Grapher.this.flag += 1;

					System.out.println(Grapher.this.flag);
					if (Grapher.this.flag == 15) {
						Grapher.this.globalPanelView.completeTableController(
								Grapher.this.graph, Grapher.this.kPlant,
								Grapher.this.curveTime);
						Grapher.this.stop();
					}
				}
			}
		});
	}

	public void insertCurve(double k, double tau) {
		this.curveTime = tau;
		this.kPlant = k;

		this.curvaActual = new CurveGenerator(k, tau);

		this.conjuntoDatos.addSeries(this.curvaActual.getDibujoK());
		this.conjuntoDatos.addSeries(this.curvaActual.getDibujoCurva());
		this.conjuntoDatos.addSeries(this.curvaActual.getDibujoTangente());
		this.conjuntoDatos.addSeries(this.curvaActual.getTiempoMuerto());
		this.conjuntoDatos.addSeries(this.curvaActual.getRetardo());
	}

	public void insertarDerivada() {
	}

	public void iniciarGraficoCurva() {
		this.diagrama.getXYPlot().getRangeAxis().setLowerBound(-0.5D);

		this.timerCurve.stop();
		this.timerCurve.start();
	}

	public CurveGenerator getCurvaActual() {
		return this.curvaActual;
	}

	public void clean() {
		this.curvaActual = null;
		this.conjuntoDatos.removeAllSeries();
		this.t = 0.0D;
		this.timerCurve = null;
		resetTimer();
	}

	public void stop() {
		this.timerCurve.stop();
	}

	public void start() {
		this.timerCurve.start();
	}

	public void getImage() throws IOException {
		JFileChooser fc = new JFileChooser();

		fc.setFileFilter(new FileFilter() {
			public String getDescription() {
				return "*.png, *.jpg";
			}

			public boolean accept(File f) {
				return (f.isDirectory()) || (f.getName().endsWith(".png"))
						|| (f.getName().endsWith(".jpg"));
			}
		});
		stop();

		String fileDefaultName = "Graphic" + System.currentTimeMillis() / 1000L;

		File defaultFile = new File(fileDefaultName);

		fc.setSelectedFile(defaultFile);
		int returnVal = fc.showSaveDialog(fc);
		start();

		if (returnVal == 0) {
			System.out.println(fc.getSelectedFile().getPath());
			File file = fc.getSelectedFile();

			OutputStream in = new FileOutputStream(file.getPath() + ".png");
			ChartUtilities.writeChartAsPNG(in, this.diagrama, 800, 600);
		}
	}
}
