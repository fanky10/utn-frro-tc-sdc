package ar.edu.utn.frro.tc.sdc.closedloop;

import java.awt.BasicStroke;
import java.awt.Color;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;

public class Curva3
  extends Curva
{
  double[] step = { 0.0D, 4.0D, 8.0D };
  private double[] ktest = { 1.0D, 6.0D, 10.0D };
  private String[] title = { "  K = 1                                                                                      ", 
    "  K = 1                                   K = 6                                           ", 
    "                   K = 1                                  K = 6                                K last = 10      " };
  
  public Curva3()
  {
    this.KCritico = 10.0D;
    this.PCritico = 0.9472D;
    this.puntoXInicio = 9.495D;
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
    } else {
      this.dibujoCurva.add(t, funcionCurva3(t - this.step[2]));
    }
  }
  
  public double getStep(int i)
  {
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
    return 0.9090909090909091D - 360.0D * Math.cos(2.0D * Math.sqrt(11.0D) * t) / 517.0D - 60.0D * Math.sqrt(11.0D) * Math.sin(2.0D * Math.sqrt(11.0D) * t) / 517.0D - 10.0D * Math.exp(-12.0D * t) / 47.0D;
  }
  
  private double funcionCurva2(double t) {
    return 1.0D - 1.0D / Math.sqrt(1.0D - Math.pow(0.115D, 2.0D)) * Math.exp(-0.115D * t / 0.1D) * Math.sin(Math.sqrt(1.0D - Math.pow(0.115D, 2.0D)) * (t / 0.1D) + Math.atan(Math.sqrt(1.0D - Math.pow(0.115D, 2.0D)) / 0.115D));
  }
  
  private double funcionCurva1(double t) {
    return 0.5D - 4.0D * Math.exp(-2.0D * t) * (Math.cos(2.0D * Math.sqrt(2.0D) * t) + 5.0D * Math.sqrt(2.0D) * Math.sin(2.0D * Math.sqrt(2.0D) * t) / 4.0D) / 11.0D - 3.0D * Math.exp(-8.0D * t) / 22.0D;
  }
  
  public void dibujarPeriodo(double t)
  {
    this.dibujoPeriodo.add(t, funcionCurva3(this.puntoXInicio - 8.0D));
  }
  

  public int getTipoCurva()
  {
    return 3;
  }
}
