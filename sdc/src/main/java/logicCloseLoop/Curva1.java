package logicCloseLoop;

import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;

public class Curva1 extends Curva
{
  private double[] step = { 0.0D, 10.0D, 20.0D };
  private double[] ktest = { 1.0D, 4.0D, 10.0D };
  private String[] title = { "  K = 1                                                                                      ", 
    "  K = 1                                   K = 4                                           ", 
    "                   K = 1                                  K = 4                                K último = 10      " };
  
  public Curva1()
  {
    this.KCritico = 10.0D;
    this.PCritico = 1.895D;
    this.puntoXInicio = 26.53D;
    this.puntoXFin = (this.puntoXInicio + this.PCritico);
    this.puntoYInicio = -0.5D;
    this.puntoYFin = 2.25D;
    this.comment.setText(this.title[0]);
    this.marcadores = new ValueMarker[2];
    this.marcadores[0] = new ValueMarker(this.step[1]);
    this.marcadores[1] = new ValueMarker(this.step[2]);
    ValueMarker[] arrayOfValueMarker; int j = (arrayOfValueMarker = this.marcadores).length; for (int i = 0; i < j; i++) { ValueMarker marcador = arrayOfValueMarker[i];
      marcador.setStroke(new java.awt.BasicStroke(1.0F));
      marcador.setPaint(java.awt.Color.BLACK);
    }
  }
  
  public void dibujarCurva(double t) {
    if (t <= this.step[1]) {
      this.dibujoCurva.add(t, funcionCurva1(t));
    }
    else if (t <= this.step[2]) {
      this.dibujoCurva.add(t, funcionCurva2(t - this.step[1]));
    }
    else {
      this.dibujoCurva.add(t, funcionCurva3(t - this.step[2]));
    }
  }
  
  public double getStep(int i)
  {
    return this.step[i];
  }
  
  public void setComment(int i)
  {
    if (i == 0) {
      this.comment.setText(this.title[0]);
    } else if (i == 1) {
      this.comment.setText(this.title[1]);
    } else if (i == 2) {
      this.comment.setText(this.title[2]);
    }
  }
  
  public double getKtest(int i)
  {
    return this.ktest[i];
  }
  
  private double funcionCurva3(double t) {
    return 10.0D * Math.pow(2.718281828459045D, -6.0D * t) / 47.0D - 580.0D * Math.cos(Math.sqrt(11.0D) * t) / 517.0D + 60.0D * Math.sqrt(11.0D) * Math.sin(Math.sqrt(11.0D) * t) / 517.0D + 0.9090909090909091D;
  }
  
  private double funcionCurva2(double t) {
    return 8.0D * Math.pow(2.718281828459045D, -5.0D * t) / 65.0D - 12.0D * Math.pow(2.718281828459045D, -t / 2.0D) * (Math.cos(Math.sqrt(23.0D) * t / 2.0D) - Math.sqrt(23.0D) * Math.sin(Math.sqrt(23.0D) * t / 2.0D) / 69.0D) / 13.0D + 0.8D;
  }
  
  private double funcionCurva1(double t) {
    return Math.pow(2.718281828459045D, -4.0D * t) / 22.0D - 6.0D * Math.pow(2.718281828459045D, -1.0D * t) * (Math.cos(Math.sqrt(2.0D) * t) + Math.sqrt(2.0D) * Math.sin(Math.sqrt(2.0D) * t) / 3.0D) / 11.0D + 0.5D;
  }
  
  public void dibujarPeriodo(double t) {
    this.dibujoPeriodo.add(t, funcionCurva3(this.puntoXInicio - 20.0D));
  }
  


  public int getTipoCurva()
  {
    return 1;
  }
}
