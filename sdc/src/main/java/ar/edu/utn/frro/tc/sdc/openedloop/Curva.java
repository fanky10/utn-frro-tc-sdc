package ar.edu.utn.frro.tc.sdc.openedloop;

import org.jfree.data.xy.XYSeries;

public class Curva { private XYSeries dibujoCurva;
  
  public static enum tipoCurva { PRIMER_METODO,  SEGUNDO_METODO; }
  
  private XYSeries dibujoDerivada;
  private XYSeries dibujoK;
  private XYSeries dibujoLY;
  
  public Curva(double k, double tau) { this.dibujoCurva = new XYSeries(Integer.valueOf(1));
    this.dibujoDerivada = new XYSeries(Integer.valueOf(2));
    this.dibujoK = new XYSeries(Integer.valueOf(3));
    this.dibujoLY = new XYSeries(Integer.valueOf(4));
    this.dibujoTY = new XYSeries(Integer.valueOf(5));
    this.dibujoLX = new XYSeries(Integer.valueOf(6));
    this.dibujoTX = new XYSeries(Integer.valueOf(7));
    this.k = k;
    this.tau = tau;
    this.tiempoFin = (getTiempoAsentamiento() + 2.0D);
    dibujarCurva();
    dibujarDerivada();
    dibujarRetardo();
    dibujarCteTiempo(); }
  
  private XYSeries dibujoTY;
  
  private void dibujarCteTiempo() { for (double x = 0.0D; x <= this.k; x += 0.001D)
      this.dibujoTY.add(this.T, x);
    for (double x = this.L + 1.0E-5D; x <= this.T; x += 0.001D)
      this.dibujoTX.add(x, 0.0D); }
  
  private XYSeries dibujoLX;
  
  private void dibujarRetardo() { for (double x = 0.0D; x <= this.k; x += 0.001D)
      this.dibujoLY.add(this.L, x);
    for (double x = 0.0D; x <= this.L; x += 0.001D)
      this.dibujoLX.add(x, 0.0D); }
  
  private XYSeries dibujoTX;
  
  private void dibujarDerivada() { double m = funcionDerivadaPrimera(this.tau);
    double h = funcionCurva(this.tau) - this.tau * m;
    this.T = ((this.k - h) / m);
    this.L = (-1.0D * h / m);
    double esCeroEn = this.tau * 0.2817181715409549D;
    for (double t = esCeroEn; m * t + h <= this.k + this.k * 0.15D; t += 0.01D)
      this.dibujoDerivada.add(t, m * t + h);
  }
  
  private double k;
  
  private void dibujarCurva() { for (double t = 0.0D; t <= this.tiempoFin; t += 0.01D) {
      this.dibujoCurva.add(t, funcionCurva(t));
    }
    for (double t = 0.0D; t <= this.tiempoFin; t += 0.01D)
      this.dibujoK.add(t, this.k); }
  
  private double tau;
  private double tiempoFin;
  
  private double funcionCurva(double t) { return this.k * (1.0D - (1.0D + t / this.tau) * Math.pow(2.718281828459045D, -1.0D * t / this.tau)); }
  
  private double L;
  private double T;
  private double funcionDerivadaPrimera(double puntoInflexionY) { return this.k * puntoInflexionY * Math.pow(2.718281828459045D, -1.0D * puntoInflexionY / this.tau) / Math.pow(this.tau, 2.0D); }
  
  public XYSeries getDibujoCurva()
  {
    return this.dibujoCurva;
  }
  
  public XYSeries getDibujoDerivada() {
    return this.dibujoDerivada;
  }
  
  public XYSeries getDibujoK() {
    return this.dibujoK;
  }
  
  public double getT() {
    return this.T - this.L;
  }
  
  public double getL() {
    return this.L;
  }
  
  private double getTiempoAsentamiento() {
    double valorMinimo = 0.0D;double valorMaximo = 0.0D;
    for (double x = 0.0D; x <= 200.0D; x += 1.0E-4D) {
      if (funcionCurva(x) > this.k - 5.0E-4D) {
        valorMinimo = x;
        break;
      }
    }
    for (double x = 0.0D; x <= 200.0D; x += 1.0E-4D) {
      if (funcionCurva(x) > this.k + 5.0E-4D) {
        valorMaximo = x;
        break;
      }
    }
    return (valorMinimo + valorMaximo) / 2.0D;
  }
  
  public XYSeries getdibujoLY() {
    return this.dibujoLY;
  }
  
  public XYSeries getdibujoTY() {
    return this.dibujoTY;
  }
  
  public XYSeries getdibujoLX() {
    return this.dibujoLX;
  }
  
  public XYSeries getdibujoTX() {
    return this.dibujoTX;
  }
  
  public double getk() {
    return this.k;
  }
  
  public static void detenerTiempo() {
    try {
      System.out.println("Entro al timer");
      Thread.sleep(1L);
      System.out.println("Salio del timer");
    }
    catch (InterruptedException localInterruptedException) {}
  }
}
