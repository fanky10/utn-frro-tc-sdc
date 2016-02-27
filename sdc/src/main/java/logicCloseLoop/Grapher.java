package logicCloseLoop;

import GUI.DataClosedZN;
import GUI.PanelClosedZieglerNichols;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeriesCollection;

public class Grapher
{
  private XYSeriesCollection conjuntoDatos;
  private Curva curvaActual;
  private JFreeChart diagrama;
  private double t = 0.0D;
  private double tiempoDeCurva;
  private Timer timer;
  private int band = 0;
  private boolean bandera;
  private JTable tabla1;
  private JTable tabla2;
  private JButton boton;
  private JComboBox<String> combo;
  private PanelClosedZieglerNichols view;
  
  public Grapher(PanelClosedZieglerNichols view) {
    this.view = view;
    this.conjuntoDatos = new XYSeriesCollection();
    this.curvaActual = null;
    this.bandera = true;
    this.tabla1 = null;
    

    this.timer = new Timer(20, new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if ((Grapher.this.t <= Grapher.this.tiempoDeCurva) && (Grapher.this.bandera)) {
          Grapher.this.curvaActual.dibujarCurva(Grapher.this.t);
          if (Grapher.this.curvaActual.getTipoCurva() != 3) {
            Grapher.this.t += 0.04D;
          } else {
            Grapher.this.t += 0.04D;
          }
        } else {
          if (Grapher.this.bandera)
            Grapher.this.t = Grapher.this.curvaActual.getPuntoXInicio();
          Grapher.this.bandera = false;
        }
        
        if ((Grapher.this.t <= Grapher.this.curvaActual.getStep(1)) && (Grapher.this.band == 0))
        {



          Grapher.this.band = 1;
        } else if ((Grapher.this.t >= Grapher.this.curvaActual.getStep(1)) && (Grapher.this.t <= Grapher.this.curvaActual.getStep(2)) && (Grapher.this.band == 1)) {
          Grapher.this.curvaActual.setComment(1);
          



          Grapher.this.band = 2;
        } else if ((Grapher.this.t > Grapher.this.curvaActual.getStep(2)) && (Grapher.this.band == 2)) {
          Grapher.this.curvaActual.setComment(2);
          Grapher.this.band = 3;
        }
        
        if ((Grapher.this.t <= Grapher.this.curvaActual.getPuntoXFin()) && (!Grapher.this.bandera))
        {
          Grapher.this.curvaActual.dibujarPeriodo(Grapher.this.t);
          
          if (Grapher.this.curvaActual.getTipoCurva() != 3) {
            Grapher.this.t += 0.03D;
          } else {
            Grapher.this.t += 0.03D;
          }
        } else if ((!Grapher.this.bandera) && (Grapher.this.t < Grapher.this.tiempoDeCurva))
        {
          Grapher.this.tabla2.setModel(DataClosedZN.getKPModel(Grapher.this.curvaActual.getKCritico(), Grapher.this.curvaActual.getPCritico()));
          
          Grapher.this.boton.setEnabled(true);
          Grapher.this.combo.setEnabled(true);
          
          Grapher.this.timer.setDelay(500);
          




          Grapher.this.band += 1;
        }
        

        if (Grapher.this.band == 14)
        {
          System.out.println("STOP");
          Grapher.this.tabla1.setModel(DataClosedZN.getControllerModel(Grapher.this.curvaActual.getKCritico(), Grapher.this.curvaActual.getPCritico()));
          Grapher.this.tabla1.getColumnModel().getColumn(0).setPreferredWidth(106);
          
          Grapher.this.stop();
        }
      }
    });
  }
  
  public JPanel getDiagrama()
  {
    this.diagrama = null;
    this.diagrama = org.jfree.chart.ChartFactory.createXYLineChart(
      "", 
      "t", 
      "Y(t)", 
      this.conjuntoDatos, 
      PlotOrientation.VERTICAL, 
      false, 
      false, 
      false);
    
    this.diagrama.getXYPlot().getRenderer().setSeriesStroke(2, new BasicStroke(1.5F));
    this.diagrama.getXYPlot().getRenderer().setSeriesStroke(3, new BasicStroke(5.0F));
    this.diagrama.getXYPlot().getRenderer().setSeriesStroke(4, new BasicStroke(2.5F));
    if (this.curvaActual != null) {
      this.diagrama.getXYPlot().getRangeAxis().setLowerBound(this.curvaActual.getPuntoYInicio());
      this.diagrama.getXYPlot().getRangeAxis().setUpperBound(this.curvaActual.getPuntoYFin());
    }
    this.diagrama.getXYPlot().getRenderer().setSeriesPaint(3, Color.BLACK);
    this.diagrama.getXYPlot().getRenderer().setSeriesPaint(4, Color.BLACK);
    return new org.jfree.chart.ChartPanel(this.diagrama);
  }
  
  public void insertarCurva(int curva) {
    limpiar();
    switch (curva) {
    case 1:  this.curvaActual = new Curva1();this.tiempoDeCurva = 30.0D; break;
    case 2:  this.curvaActual = new Curva2();this.tiempoDeCurva = 37.5D; break;
    case 3:  this.curvaActual = new Curva3();this.tiempoDeCurva = 12.0D;
    }
    Curva.generarEntrada(this.tiempoDeCurva);
    this.conjuntoDatos.addSeries(Curva.getEntrada());
    this.conjuntoDatos.addSeries(this.curvaActual.getDibujoCurva());
    this.conjuntoDatos.addSeries(this.curvaActual.getDibujoPeriodo());
  }
  
  public void iniciarGraficoCurva(JTable tabla1, JTable tabla2, JButton boton, JComboBox<String> combo) {
    this.tabla1 = tabla1;
    this.tabla2 = tabla2;
    this.boton = boton;
    this.combo = combo;
    this.band = 0;
    this.diagrama.addSubtitle(this.curvaActual.getComment());
    ValueMarker[] arrayOfValueMarker; int j = (arrayOfValueMarker = this.curvaActual.getMarcadores()).length; for (int i = 0; i < j; i++) { ValueMarker marcador = arrayOfValueMarker[i];
      this.diagrama.getXYPlot().addDomainMarker(marcador);
    }
    this.timer.stop();
    this.timer.start();
  }
  
  public Curva getCurvaActual()
  {
    return this.curvaActual;
  }
  
  public void limpiar()
  {
    this.curvaActual = null;
    this.conjuntoDatos.removeAllSeries();
    this.t = 0.0D;
    this.bandera = true;
  }
  
  public void stop() {
    this.timer.stop();
  }
  
  public void start() {
    this.timer.start();
  }
  
  public void getImage() throws IOException
  {
    JFileChooser fc = new JFileChooser();
    
    fc.setFileFilter(new FileFilter()
    {
      public String getDescription()
      {
        return "*.png, *.jpg";
      }
      
      public boolean accept(File f)
      {
        return (f.isDirectory()) || (f.getName().endsWith(".png")) || (f.getName().endsWith(".jpg"));
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
      

      java.io.OutputStream in = new FileOutputStream(file.getPath() + ".png");
      ChartUtilities.writeChartAsPNG(in, this.diagrama, 800, 600);
    }
  }
  
  private static double round(double numero)
  {
    return Math.rint(numero * 100.0D) / 100.0D;
  }
}
