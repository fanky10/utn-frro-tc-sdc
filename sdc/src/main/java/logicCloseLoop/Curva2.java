package logicCloseLoop;

import java.awt.BasicStroke;
import java.awt.Color;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;

public class Curva2
  extends Curva
{
  double[] step = { 0.0D, 12.5D, 25.0D };
  private double[] ktest = { 1.0D, 5.0D, 8.0D };
  private String[] title = { "  K = 1                                                                                      ", 
    "  K = 1                                   K = 5                                           ", 
    "                   K = 1                                  K = 5                                K último = 8       " };
  
  public Curva2()
  {
    this.KCritico = 8.0D;
    this.PCritico = 3.63D;
    this.puntoXInicio = 30.745D;
    this.puntoXFin = (this.puntoXInicio + this.PCritico);
    this.puntoYInicio = 0.0D;
    this.puntoYFin = 1.8D;
    this.comment.setText(this.title[0]);
    this.marcadores = new ValueMarker[2];
    this.marcadores[0] = new ValueMarker(this.step[1]);
    this.marcadores[1] = new ValueMarker(this.step[2]);
    ValueMarker[] arrayOfValueMarker; int j = (arrayOfValueMarker = this.marcadores).length; for (int i = 0; i < j; i++) { ValueMarker marcador = arrayOfValueMarker[i];
      marcador.setStroke(new BasicStroke(1.0F));
      marcador.setPaint(Color.BLACK);
    }
  }
  
  public void dibujarCurva(double t) {
    if (t <= this.step[1]) {
      this.dibujoCurva.add(t, funcionCurva1(t));
    } else if (t <= this.step[2]) {
      this.dibujoCurva.add(t, funcionCurva2(t - this.step[1]));
    } else
      this.dibujoCurva.add(t, funcionCurva3(t - this.step[2]));
  }
  
  public double getStep(int i) {
    return this.step[i];
  }
  
  public double getKtest(int i)
  {
    return this.ktest[i];
  }
  
  public void setComment(int i) {
    if (i == 0) {
      this.comment.setText(this.title[0]);
    } else if (i == 1) {
      this.comment.setText(this.title[1]);
    } else if (i == 2) {
      this.comment.setText(this.title[2]);
    }
  }
  
  private double funcionCurva3(double t) {
    return 0.8888888888888888D - 2.0D * Math.cos(Math.sqrt(3.0D) * t) / 3.0D - 2.0D * Math.sqrt(3.0D) * Math.sin(Math.sqrt(3.0D) * t) / 9.0D - 2.0D * Math.pow(2.718281828459045D, -3.0D * t) / 9.0D;
  }
  
  private double funcionCurva2(double t) {
    return 1.0D - 1.0D / Math.sqrt(1.0D - Math.pow(0.4D, 2.0D)) * Math.exp(-0.4D * t) * Math.sin(Math.sqrt(1.0D - Math.pow(0.4D, 2.0D)) * t + Math.atan(Math.sqrt(1.0D - Math.pow(0.4D, 2.0D)) / 0.4D));
  }
  
  private double funcionCurva1(double t) {
    return 0.5D - Math.pow(2.718281828459045D, -0.5D * t) * (Math.cos(Math.sqrt(3.0D) * t / 2.0D) + Math.sqrt(3.0D) * Math.sin(Math.sqrt(3.0D) * t / 2.0D)) / 3.0D - Math.pow(2.718281828459045D, -2.0D * t) / 6.0D;
  }
  
  public void dibujarPeriodo(double t)
  {
    this.dibujoPeriodo.add(t, funcionCurva3(this.puntoXInicio - 25.0D));
  }
  

  public int getTipoCurva()
  {
    return 2;
  }
}
