package logicOpenLoop;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.PrintStream;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;

public class CurveGenerator
{
  private double KCritico;
  private double PCritico;
  private double puntoXInicio;
  private double puntoXFin;
  private double puntoYInicio;
  private double puntoYFin;
  private TextTitle comentario;
  private XYSeries entrada;
  private XYSeries dibujoCurva;
  private XYSeries rectaTangente;
  private XYSeries dibujoK;
  private XYSeries tiempoMuerto;
  
  public CurveGenerator(double k, double tau)
  {
    this.puntoXInicio = 0.0D;
    
    this.puntoXFin = (this.puntoXInicio + tau);
    this.puntoYInicio = 0.0D;
    this.puntoYFin = (k + k * 0.3D);
    

    this.dibujoCurva = new XYSeries(Integer.valueOf(0));
    this.rectaTangente = new XYSeries(Integer.valueOf(1));
    this.dibujoK = new XYSeries(Integer.valueOf(2));
    this.tiempoMuerto = new XYSeries(Integer.valueOf(3));
    this.retardo = new XYSeries(Integer.valueOf(4));
    









    this.k = k;
    this.tau = tau;
    this.tiempoFin = (getTiempoAsentamiento() + 2.0D);
    

    setMarkers(k, tau);
  }
  








  public XYSeries getDibujoPeriodo()
  {
    return this.dibujoPeriodo;
  }
  
  public double getPuntoYInicio() {
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
  
  public ValueMarker[] getMarcadoresCurva()
  {
    return this.marcadoresCurva;
  }
  
  public ValueMarker[] getMarcadoresInd() {
    return this.marcadoresInd;
  }
  
  public XYSeries getEntrada() {
    return this.entrada;
  }
  
  public TextTitle getComentario() {
    return this.comentario;
  }
  
  public double getPCritico()
  {
    return this.PCritico;
  }
  




  public double funcionCurva(double t)
  {
    return this.k * (1.0D - (1.0D + t / this.tau) * Math.pow(2.718281828459045D, -1.0D * t / this.tau));
  }
  
  private double funcionDerivadaPrimera(double puntoInflexionY)
  {
    return this.k * puntoInflexionY * Math.pow(2.718281828459045D, -1.0D * puntoInflexionY / this.tau) / Math.pow(this.tau, 2.0D);
  }
  
  private double funcionCurva3(double t)
  {
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
  
  public int getTipoCurva() {
    return 1;
  }
  



  public XYSeries getDibujoCurva()
  {
    return this.dibujoCurva;
  }
  
  public XYSeries getDibujoTangente() {
    return this.rectaTangente;
  }
  
  public void dibujarCurva(double t)
  {
    this.dibujoCurva.add(t, funcionCurva(t));
  }
  

  public void dibujarTangente(double t)
  {
    double m = funcionDerivadaPrimera(this.tau);
    double h = funcionCurva(this.tau) - this.tau * m;
    
    this.T = ((this.k - h) / m);
    this.L = (-1.0D * h / m);
    
    double esCeroEn = this.tau * 0.2817181715409549D;
    this.rectaTangente.add(t, m * t + h);
  }
  

  public void dibujarK(double t)
  {
    this.dibujoK.add(t, this.k);
  }
  
  public XYSeries getDibujoK()
  {
    return this.dibujoK;
  }
  
  public void dibujaLT(double t)
  {
    if (t < this.L) {
      this.tiempoMuerto.add(t, 0.0D);
    }
    else if ((t > this.L) && (t < this.T)) {
      this.retardo.add(t, 0.0D);
    }
  }
  
  public XYSeries getTiempoMuerto() {
    return this.tiempoMuerto;
  }
  
  public XYSeries getRetardo() { return this.retardo; }
  



  public void setMarkers(double k, double tau)
  {
    this.marcadoresCurva = new ValueMarker[2];
    
    double m = funcionDerivadaPrimera(tau);
    double h = funcionCurva(tau) - tau * m;
    
    this.T = ((k - h) / m);
    this.L = (-1.0D * h / m);
    
    double esCeroEn = tau * 0.2817181715409549D;
    
    System.out.println(esCeroEn);
    System.out.println(this.T - this.L);
    
    this.marcadoresCurva[0] = new ValueMarker(esCeroEn);
    this.marcadoresCurva[1] = new ValueMarker(this.T);
    ValueMarker[] arrayOfValueMarker;
    int j = (arrayOfValueMarker = this.marcadoresCurva).length; for (int i = 0; i < j; i++) { ValueMarker marcador = arrayOfValueMarker[i];
      marcador.setStroke(new BasicStroke(1.0F));
      marcador.setPaint(Color.BLACK);
    } }
  
  private XYSeries retardo;
  private XYSeries dibujoPeriodo;
  private XYSeries dibujoLY;
  private XYSeries dibujoTY;
  private XYSeries dibujoLX;
  private XYSeries dibujoTX;
  private ValueMarker[] marcadoresCurva;
  private ValueMarker[] marcadoresInd;
  private double k;
  private double tau;
  private double tiempoFin;
  private double L;
  private double T;
  public void generarEntrada(double t) { this.entrada = new XYSeries("Entrada");
    for (double x = 0.0D; x <= t; x += 0.01D)
      this.entrada.add(x, 1.0D);
  }
  
  private void dibujarCteTiempo() {
    for (double x = 0.0D; x <= this.k; x += 0.001D)
      this.dibujoTY.add(this.T, x);
    for (double x = this.L + 1.0E-5D; x <= this.T; x += 0.001D)
      this.dibujoTX.add(x, 0.0D);
  }
  
  private void dibujarRetardo() {
    for (double x = 0.0D; x <= this.k; x += 0.001D)
      this.dibujoLY.add(this.L, x);
    for (double x = 0.0D; x <= this.L; x += 0.001D)
      this.dibujoLX.add(x, 0.0D);
  }
  
  private void dibujarDerivada() {
    double m = funcionDerivadaPrimera(this.tau);
    double h = funcionCurva(this.tau) - this.tau * m;
    this.T = ((this.k - h) / m);
    this.L = (-1.0D * h / m);
    double esCeroEn = this.tau * 0.2817181715409549D;
    for (double t = esCeroEn; m * t + h <= this.k + this.k * 0.15D; t += 0.01D) {
      this.rectaTangente.add(t, m * t + h);
    }
  }
  
  public XYSeries getTangente() {
    return this.rectaTangente;
  }
  
  public double getT() {
    return this.T - this.L;
  }
  
  public double getL() {
    return this.L;
  }
  
  public double getRealT() {
    return this.T;
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
}
