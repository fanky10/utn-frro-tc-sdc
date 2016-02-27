package ar.edu.utn.frro.tc.sdc.closedloop;

import org.jfree.data.xy.XYSeries;

public abstract class Curva { protected XYSeries dibujoCurva;
  protected XYSeries dibujoPeriodo;
  protected double KCritico;
  protected double PCritico;
  protected double puntoXInicio;
  protected double puntoXFin;
  protected double puntoYInicio;
  protected double puntoYFin;
  protected String[] title;
  protected org.jfree.chart.title.TextTitle comment;
  private static XYSeries entrada;
  protected double[] step;
  protected double[] ktest;
  protected org.jfree.chart.plot.ValueMarker[] marcadores;
  
  public XYSeries getDibujoPeriodo() { return this.dibujoPeriodo; }
  
  public double getPuntoYInicio()
  {
    return this.puntoYInicio;
  }
  
  public double getPuntoYFin() {
    return this.puntoYFin;
  }
  
  public double getPuntoXInicio() {
    return this.puntoXInicio;
  }
  
  public double getPuntoXFin() {
    return this.puntoXFin;
  }
  

  public org.jfree.chart.plot.ValueMarker[] getMarcadores()
  {
    return this.marcadores;
  }
  
  public Curva() {
    this.dibujoCurva = new XYSeries(Integer.valueOf(0));
    this.dibujoPeriodo = new XYSeries(Integer.valueOf(1));
    this.comment = new org.jfree.chart.title.TextTitle();
  }
  
  public static void generarEntrada(double t) {
    entrada = new XYSeries("Entrada");
    for (double x = 0.0D; x <= t; x += 0.01D)
      entrada.add(x, 1.0D);
  }
  
  public static XYSeries getEntrada() {
    return entrada;
  }
  
  public org.jfree.chart.title.TextTitle getComment() {
    return this.comment;
  }
  
  public void setComment(int i) {}
  
  public void setComment() { this.comment.setText(this.title[0]); }
  
  public XYSeries getDibujoCurva()
  {
    return this.dibujoCurva;
  }
  
  public double getKCritico() {
    return this.KCritico;
  }
  
  public double getPCritico() {
    return this.PCritico;
  }
  
  public double getStep(int i) {
    return this.step[i];
  }
  
  public double getKtest(int i) { return this.ktest[i]; }
  
  public abstract void dibujarCurva(double paramDouble);
  
  public abstract void dibujarPeriodo(double paramDouble);
  
  public abstract int getTipoCurva();
}
