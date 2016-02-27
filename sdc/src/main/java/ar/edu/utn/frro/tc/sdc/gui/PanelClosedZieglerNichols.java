package ar.edu.utn.frro.tc.sdc.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import ar.edu.utn.frro.tc.sdc.App;
import ar.edu.utn.frro.tc.sdc.closedloop.Grapher;












public class PanelClosedZieglerNichols
  extends JPanel
{
  private static final long serialVersionUID = 1L;
  int band = 0;
  
  public JLabel progress;
  
  private JPanel mainPanel;
  private Grapher graficador;
  private JTable valoresZieglerNichols;
  private JTable valoresKP;
  
  public PanelClosedZieglerNichols(final App mainView)
  {
    int marginTop = 30;
    int marginRight = 5;
    int marginRight2 = 635;
    
    String headTitle = "Método de Ziegler y Nichols de oscilaciones sostenidas";
    
    this.graficador = new Grapher(this);
    

    this.mainPanel = new JPanel();
    this.mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
    




    JPanel panel = new JPanel();
    panel.setBorder(new TitledBorder(null, "Referencias", 4, 2, null, null));
    panel.setBounds(marginRight2, 386, 390, 47);
    this.mainPanel.add(panel);
    
    final JPanel grafico = new JPanel();
    grafico.setBounds(5, marginTop, 625, 530);
    grafico.setLayout(new BorderLayout(0, 0));
    grafico.add(this.graficador.getDiagrama(), "Center");
    this.mainPanel.add(grafico);
    
    JPanel ftPanel = new JPanel();
    ftPanel.setBorder(new TitledBorder(null, "Función de transferencia", 4, 2, null, null));
    ftPanel.setBounds(marginRight2, 70, 250, 90);
    this.mainPanel.add(ftPanel);
    ftPanel.setLayout(null);
    
    JPanel ziegerynichols = new JPanel();
    ziegerynichols.setBorder(new TitledBorder(null, "Valores propuestos por Ziegler y Nichols", 4, 2, null, null));
    ziegerynichols.setBounds(marginRight2, 206, 390, 178);
    this.mainPanel.add(ziegerynichols);
    ziegerynichols.setLayout(null);
    

    JPanel aditionalInfo = new JPanel();
    aditionalInfo.setBorder(new TitledBorder(null, "Información Adicional", 4, 2, null, null));
    aditionalInfo.setBounds(marginRight2, 430, 390, 130);
    aditionalInfo.setAlignmentX(0.0F);
    this.mainPanel.add(aditionalInfo);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(10, 85, 370, 81);
    ziegerynichols.add(scrollPane);
    
    this.valoresZieglerNichols = new JTable();
    this.valoresZieglerNichols.setModel(DataClosedZN.getControllerModel());
    this.valoresZieglerNichols.getColumnModel().getColumn(0).setPreferredWidth(106);
    
    scrollPane.setViewportView(this.valoresZieglerNichols);
    
    JScrollPane scrollPane_1 = new JScrollPane();
    scrollPane_1.setBounds(99, 23, 154, 50);
    ziegerynichols.add(scrollPane_1);
    
    this.valoresKP = new JTable();
    this.valoresKP.setModel(DataClosedZN.getKPModel());
    this.valoresKP.setFocusTraversalKeysEnabled(false);
    this.valoresKP.setFocusable(false);
    this.valoresKP.setRowSelectionAllowed(false);
    this.valoresKP.setCellSelectionEnabled(true);
    this.valoresKP.getColumnModel().getColumn(0).setPreferredWidth(51);
    this.valoresKP.getColumnModel().getColumn(1).setPreferredWidth(51);
    
    scrollPane_1.setViewportView(this.valoresKP);
    






    final JLabel ftLabel = new JLabel("");
    ftLabel.setBounds(0, 15, 250, 70);
    ftPanel.add(ftLabel);
    
    JLabel labelTitle = new JLabel(headTitle);
    labelTitle.setFont(new Font("Tahoma", 1, 16));
    labelTitle.setBounds(marginRight, 5, 500, 20);
    this.mainPanel.add(labelTitle);
    





    JLabel rojo = new JLabel("New label");
    rojo.setBounds(120, 26, 24, 7);
    rojo.setIcon(new ImageIcon(App.class.getResource("/icons/azul.png")));
    
    JLabel lblRespuesta = new JLabel("Respuesta");
    lblRespuesta.setBounds(152, 18, 70, 23);
    panel.setLayout(null);
    panel.add(rojo);
    panel.add(lblRespuesta);
    
    JLabel lblEntrada = new JLabel("Entrada");
    lblEntrada.setBounds(44, 22, 60, 14);
    panel.add(lblEntrada);
    
    JLabel lblPerodoCrtico = new JLabel("Período crítico");
    lblPerodoCrtico.setBounds(272, 22, 90, 14);
    panel.add(lblPerodoCrtico);
    
    JLabel label = new JLabel("");
    label.setIcon(new ImageIcon(App.class.getResource("/icons/rojo.png")));
    label.setBounds(10, 26, 24, 7);
    panel.add(label);
    
    JLabel label_1 = new JLabel("");
    label_1.setIcon(new ImageIcon(App.class.getResource("/icons/verde.png")));
    label_1.setBounds(238, 26, 24, 7);
    panel.add(label_1);
    




    String[] valoresComboBox = { "Seleccionar FT", "Función transf. 1", "Función transf. 2", "Función transf. 3" };
    
    final JComboBox<String> funcionesTransferencias = new JComboBox(valoresComboBox);
    funcionesTransferencias.setBounds(marginRight2, marginTop, 170, 30);
    this.mainPanel.add(funcionesTransferencias);
    
    final JButton btnDibujar = new JButton("  Graficar", new ImageIcon(MethodPanelView.class.getResource("/icons/icon_graficar.png")));
    btnDibujar.setHorizontalAlignment(2);
    btnDibujar.setBounds(marginRight2 + 250, marginTop, 140, 40);
    this.mainPanel.setLayout(null);
    this.mainPanel.add(btnDibujar);
    
    JButton btnSave = new JButton("  Guardar", new ImageIcon(MethodPanelView.class.getResource("/icons/icon_guardar.png")));
    btnSave.setHorizontalAlignment(2);
    btnSave.setBounds(marginRight2 + 250, marginTop + 45, 140, 40);
    this.mainPanel.setLayout(null);
    this.mainPanel.add(btnSave);
    
    final JButton btnStop = new JButton("  Parar", new ImageIcon(MethodPanelView.class.getResource("/icons/icon_cancelar.png")));
    btnStop.setHorizontalAlignment(2);
    btnStop.setBounds(marginRight2 + 250, marginTop + 89, 140, 40);
    this.mainPanel.setLayout(null);
    this.mainPanel.add(btnStop);
    

    JButton btnDescripcinDelMtodo = new JButton("Descripción método", new ImageIcon(MethodPanelView.class.getResource("/icons/icon_formula.png")));
    btnDescripcinDelMtodo.setHorizontalAlignment(2);
    btnDescripcinDelMtodo.setBounds(647, 452, 240, 40);
    aditionalInfo.add(btnDescripcinDelMtodo);
    
    JButton controllerSchema = new JButton("Esquema del Controlador", new ImageIcon(MethodPanelView.class.getResource("/icons/icon_formula.png")));
    controllerSchema.setHorizontalAlignment(2);
    controllerSchema.setBounds(647, 452, 240, 40);
    aditionalInfo.add(controllerSchema);
    

    btnDescripcinDelMtodo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ModalMethodDescription dialog = new ModalMethodDescription(mainView, DataClosedZN.getDescription());
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(mainView);

      }
      


    });
    funcionesTransferencias.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0) {
        PanelClosedZieglerNichols.this.graficador = new Grapher(PanelClosedZieglerNichols.this);
        
        PanelClosedZieglerNichols.this.graficador.stop();
        switch (funcionesTransferencias.getSelectedIndex()) {
        case 0:  ftLabel.setIcon(null);
          PanelClosedZieglerNichols.this.valoresZieglerNichols.setModel(DataClosedZN.getControllerModel());
          PanelClosedZieglerNichols.this.valoresZieglerNichols.getColumnModel().getColumn(0).setPreferredWidth(106);
          PanelClosedZieglerNichols.this.valoresKP.setModel(DataClosedZN.getKPModel());
          PanelClosedZieglerNichols.this.valoresKP.getColumnModel().getColumn(0).setPreferredWidth(51);
          PanelClosedZieglerNichols.this.valoresKP.getColumnModel().getColumn(1).setPreferredWidth(51);
          break;
        case 1: 
          ftLabel.setIcon(new ImageIcon(App.class.getResource("/icons/ft1.png"))); break;
        case 2:  ftLabel.setIcon(new ImageIcon(App.class.getResource("/icons/ft2.png"))); break;
        case 3:  ftLabel.setIcon(new ImageIcon(App.class.getResource("/icons/ft3.png")));
        


        }
        
      }
    });
    btnDibujar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (funcionesTransferencias.getSelectedIndex() != 0)
        {
          PanelClosedZieglerNichols.this.graficador = new Grapher(PanelClosedZieglerNichols.this);
          
          PanelClosedZieglerNichols.this.band = 1;
          
          PanelClosedZieglerNichols.this.valoresZieglerNichols.setModel(DataClosedZN.getControllerModel());
          PanelClosedZieglerNichols.this.valoresZieglerNichols.getColumnModel().getColumn(0).setPreferredWidth(106);
          PanelClosedZieglerNichols.this.valoresKP.setModel(DataClosedZN.getKPModel());
          
          PanelClosedZieglerNichols.this.valoresKP.getColumnModel().getColumn(0).setPreferredWidth(51);
          PanelClosedZieglerNichols.this.valoresKP.getColumnModel().getColumn(1).setPreferredWidth(51);
          btnDibujar.setEnabled(false);
          funcionesTransferencias.setEnabled(false);
          PanelClosedZieglerNichols.this.graficador.insertarCurva(funcionesTransferencias.getSelectedIndex());
          


          grafico.removeAll();
          grafico.add(PanelClosedZieglerNichols.this.graficador.getDiagrama(), "Center");
          grafico.validate();
          PanelClosedZieglerNichols.this.graficador.iniciarGraficoCurva(PanelClosedZieglerNichols.this.valoresZieglerNichols, PanelClosedZieglerNichols.this.valoresKP, btnDibujar, funcionesTransferencias);
        }
        
      }
      

    });
    btnSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        try {
          PanelClosedZieglerNichols.this.graficador.getImage();
        } catch (IOException e) {
          e.printStackTrace();
        }
        
      }
      
    });
    btnStop.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (PanelClosedZieglerNichols.this.band == 1) {
          btnStop.setIcon(new ImageIcon(MethodPanelView.class.getResource("/icons/icon_aceptar.png")));
          btnStop.setText(" Continuar");
          PanelClosedZieglerNichols.this.graficador.stop();
          PanelClosedZieglerNichols.this.band = 2;
        } else if (PanelClosedZieglerNichols.this.band == 2) {
          btnStop.setIcon(new ImageIcon(MethodPanelView.class.getResource("/icons/icon_cancelar.png")));
          btnStop.setText(" Parar");
          PanelClosedZieglerNichols.this.graficador.start();
          PanelClosedZieglerNichols.this.band = 1;
        }
        
      }
      
    });
    controllerSchema.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        String title = "Esquema del Controlador";
        
        ModalEquationView dialog = new ModalEquationView(mainView, title, "/icons/controllerSchemaClosedLoop.png");
        
        String sourceText = "http://kurser.iha.dk/m/mtpri1/control/3_PID/f_ziegler/ziegler_nichols_method.html";
        dialog.setSource(sourceText);
        dialog.repaint();
        
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(mainView);
      }
    });
  }
  






  public JPanel getMainPanel()
  {
    return this.mainPanel;
  }
}
