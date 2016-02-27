package ar.edu.utn.frro.tc.sdc.openedloop;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeriesCollection;

public class Graficador
{
  private XYSeriesCollection conjuntoDatos;
  private Curva curvaActual;
  private JFreeChart diagrama;
  
  public Graficador()
  {
    this.conjuntoDatos = new XYSeriesCollection();
    this.curvaActual = null;
  }
  
  public JPanel getDiagrama() {
    this.diagrama = null;
    this.diagrama = ChartFactory.createXYLineChart(
      "", 
      "t", 
      "Y(t)", 
      this.conjuntoDatos, 
      PlotOrientation.VERTICAL, 
      false, 
      false, 
      false);
    
    XYPlot plot = this.diagrama.getXYPlot();
    if (this.curvaActual != null)
      plot.getRangeAxis().setLowerBound(-0.05D * this.curvaActual.getk());
    XYItemRenderer renderer = plot.getRenderer();
    renderer.setSeriesPaint(0, Color.RED);
    renderer.setSeriesPaint(1, Color.BLUE);
    renderer.setSeriesPaint(2, Color.WHITE);
    renderer.setSeriesPaint(3, Color.BLACK);
    renderer.setSeriesPaint(4, Color.BLACK);
    renderer.setSeriesPaint(5, Color.YELLOW);
    renderer.setSeriesPaint(6, Color.GREEN);
    return new org.jfree.chart.ChartPanel(this.diagrama);
  }
  

  public void insertarCurva(double k, double tau)
  {
    limpiar();
    this.curvaActual = new Curva(k, tau);
    this.conjuntoDatos.addSeries(this.curvaActual.getDibujoCurva());
    this.conjuntoDatos.addSeries(this.curvaActual.getDibujoDerivada());
    this.conjuntoDatos.addSeries(this.curvaActual.getDibujoK());
    this.conjuntoDatos.addSeries(this.curvaActual.getdibujoLY());
    this.conjuntoDatos.addSeries(this.curvaActual.getdibujoTY());
    this.conjuntoDatos.addSeries(this.curvaActual.getdibujoLX());
    this.conjuntoDatos.addSeries(this.curvaActual.getdibujoTX());
  }
  
  public Curva getCurvaActual() {
    return this.curvaActual;
  }
  
  public void limpiar() {
    this.curvaActual = null;
    this.conjuntoDatos.removeAllSeries();
  }
  
  public void getImage() throws IOException
  {
    JFileChooser fc = new JFileChooser();
    
    int returnVal = fc.showSaveDialog(fc);
    
    if (returnVal == 0) {
      File file = fc.getSelectedFile();
      

      OutputStream in = new FileOutputStream(file.getPath() + ".png");
      ChartUtilities.writeChartAsPNG(in, this.diagrama, 800, 600);
    }
  }
}
