package ar.edu.utn.frro.tc.sdc.gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

import ar.edu.utn.frro.tc.sdc.App;
import ar.edu.utn.frro.tc.sdc.openedloop.CurveGenerator;
import ar.edu.utn.frro.tc.sdc.openedloop.Grapher;

















public class PanelCompare
  extends JPanel
{
  JPanel containerPanel;
  JPanel panelData;
  JTable tableTL;
  InformationExpert infoExpert;
  JCheckBox checkBoxCurvaReaccinZN;
  JCheckBox checkBoxCC;
  JCheckBox checkBoxLopez;
  JCheckBox checkBoxKS;
  JFrame mainView1;
  private static final long serialVersionUID = 1L;
  private JTextField inputA;
  private JTextField inputB;
  
  public PanelCompare(App mainView)
  {
    this.mainView1 = mainView;
    
    this.infoExpert = new InformationExpert();
    
    setLayout(null);
    
    JLabel lblComparacinDeMetodos = new JLabel("Comparación de metodos - Sistemas de lazo abierto");
    lblComparacinDeMetodos.setFont(new Font("Tahoma", 1, 16));
    lblComparacinDeMetodos.setBounds(12, 0, 578, 34);
    add(lblComparacinDeMetodos);
    
    this.panelData = new JPanel();
    this.panelData.setToolTipText("");
    this.panelData.setBorder(new TitledBorder(null, "Datos de Entrada", 4, 2, null, null));
    this.panelData.setBounds(12, 33, 199, 545);
    add(this.panelData);
    this.panelData.setLayout(null);
    
    this.inputA = new JTextField();
    this.inputA.setBounds(12, 50, 175, 25);
    this.panelData.add(this.inputA);
    this.inputA.setColumns(10);
    
    this.inputB = new JTextField();
    this.inputB.setBounds(12, 90, 175, 25);
    this.panelData.add(this.inputB);
    this.inputB.setColumns(10);
    
    JLabel lblKc = new JLabel("Ganancia");
    lblKc.setBounds(12, 30, 70, 20);
    this.panelData.add(lblKc);
    
    JLabel lblConstateTimpo = new JLabel("Constate de Timpo");
    lblConstateTimpo.setBounds(12, 74, 179, 15);
    this.panelData.add(lblConstateTimpo);
    
    this.checkBoxCurvaReaccinZN = new JCheckBox("Curva Reacción Z-N");
    this.checkBoxCurvaReaccinZN.setBounds(8, 264, 161, 23);
    this.panelData.add(this.checkBoxCurvaReaccinZN);
    
    this.checkBoxCC = new JCheckBox("Cohen-Coon");
    this.checkBoxCC.setBounds(8, 292, 161, 23);
    this.panelData.add(this.checkBoxCC);
    
    this.checkBoxLopez = new JCheckBox("Lopez");
    this.checkBoxLopez.setBounds(8, 319, 161, 23);
    this.panelData.add(this.checkBoxLopez);
    
    this.checkBoxKS = new JCheckBox("Kaya-Sheib");
    this.checkBoxKS.setBounds(8, 346, 161, 23);
    this.panelData.add(this.checkBoxKS);
    
    JButton btnGenerator = new JButton("   Generar", new ImageIcon(MethodPanelView.class.getResource("/icons/icon_aceptar.png")));
    btnGenerator.setBounds(12, 377, 175, 40);
    btnGenerator.setHorizontalAlignment(2);
    this.panelData.add(btnGenerator);
    


    JScrollPane paneltableTL = new JScrollPane();
    paneltableTL.setRequestFocusEnabled(false);
    paneltableTL.setBounds(12, 142, 175, 68);
    paneltableTL.setBorder(new TitledBorder(null, "Valores de L y T", 4, 2, null, null));
    this.panelData.add(paneltableTL, "Center");
    
    this.tableTL = new JTable();
    this.tableTL.setFocusTraversalKeysEnabled(false);
    this.tableTL.setFocusable(false);
    this.tableTL.setRequestFocusEnabled(false);
    this.tableTL.setModel(this.infoExpert.getModelLT());
    this.tableTL.setRowSelectionAllowed(false);
    this.tableTL.setCellSelectionEnabled(true);
    paneltableTL.setViewportView(this.tableTL);
    

    this.containerPanel = new JPanel();
    this.containerPanel.setBorder(new TitledBorder(null, "Métodos", 4, 2, null, null));
    this.containerPanel.setBounds(223, 33, 800, 545);
    add(this.containerPanel);
    this.containerPanel.setLayout(null);
    
    btnGenerator.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        PanelCompare.this.generateComp();
      }
    });
  }
  


  public JPanel getMainPanel()
  {
    return this;
  }
  
  private void generateComp()
  {
    if ((this.inputA.getText() != "") && (this.inputB.getText() != ""))
    {
      String val1 = this.inputA.getText().replace(",", ".");
      String val2 = this.inputB.getText().replace(",", ".");
      
      Pattern pat = Pattern.compile("^\\d+|^\\d+\\.?\\d+");
      Matcher mat1 = pat.matcher(val1);
      Matcher mat2 = pat.matcher(val2);
      

      if ((mat1.matches()) && (mat2.matches())) {
        System.out.println("Are Numbers");
        




        int top = 20;
        int left = 5;
        int height = 120;
        int width = 770;
        
        this.containerPanel.removeAll();
        
        double Kp = Double.parseDouble(val1);
        double tau = Double.parseDouble(val2);
        
        if ((Kp > 0.0D) && (tau > 0.0D)) {
          if ((Kp < 20.0D) && (tau < 20.0D))
          {

            Grapher graph = new Grapher();
            graph.insertCurve(Kp, tau);
            CurveGenerator actualCurve = graph.getCurvaActual();
            double vL = actualCurve.getL();
            double vT = actualCurve.getT();
            
            this.tableTL.getModel().setValueAt(Double.valueOf(this.infoExpert.round(vL)), 0, 0);
            this.tableTL.getModel().setValueAt(Double.valueOf(this.infoExpert.round(vT)), 0, 1);
            
            if (this.checkBoxCurvaReaccinZN.isSelected()) {
              JPanel panelZN = new JPanel();
              panelZN.setBorder(new TitledBorder(null, "Ziegler - Nichols", 4, 2, null, null));
              panelZN.setBounds(left, top, width, height);
              
              JTable table = this.infoExpert.getTableControllers("zn", vL, vT, Kp, tau);
              
              JScrollPane panelTable = new JScrollPane();
              panelTable.add(table);
              panelTable.setBounds(10, 20, 400, 81);
              panelZN.add(panelTable);
              panelTable.setViewportView(table);
              

              generateButtons("zn", panelZN);
              
              panelZN.repaint();
              this.containerPanel.add(panelZN);
              top += height;
            }
            if (this.checkBoxCC.isSelected()) {
              JPanel panelCC = new JPanel();
              panelCC.setBorder(new TitledBorder(null, "Cohen - Coon", 4, 2, null, null));
              panelCC.setBounds(left, top, width, height);
              
              JTable table = this.infoExpert.getTableControllers("cc", vL, vT, Kp, tau);
              
              JScrollPane panelTable = new JScrollPane();
              panelTable.add(table);
              panelTable.setBounds(10, 20, 400, 81);
              panelCC.add(panelTable);
              panelTable.setViewportView(table);
              

              generateButtons("cc", panelCC);
              
              panelCC.repaint();
              this.containerPanel.add(panelCC);
              top += height;
            }
            
            if (this.checkBoxLopez.isSelected()) {
              JPanel panelL = new JPanel();
              panelL.setBorder(new TitledBorder(null, "Lopez", 4, 2, null, null));
              panelL.setBounds(left, top, width, height);
              
              JTable tableL = this.infoExpert.getTableControllers("lopez", vL, vT, Kp, tau);
              
              JScrollPane panelTable = new JScrollPane();
              panelTable.add(tableL);
              panelTable.setBounds(10, 20, 400, 81);
              
              panelL.add(panelTable);
              panelTable.setViewportView(tableL);
              

              generateButtons("lopez", panelL);
              

              this.containerPanel.add(panelL);
              
              panelL.repaint();
              top += height;
            }
            
            if (this.checkBoxKS.isSelected()) {
              JPanel panelKS = new JPanel();
              panelKS.setBorder(new TitledBorder(null, "Kaya - Sheib", 4, 2, null, null));
              panelKS.setBounds(left, top, width, height);
              
              JTable table = this.infoExpert.getTableControllers("ks", vL, vT, Kp, tau);
              
              JScrollPane panelTable = new JScrollPane();
              panelTable.add(table);
              panelTable.setBounds(10, 20, 400, 81);
              panelKS.add(panelTable);
              panelTable.setViewportView(table);
              

              generateButtons("ks", panelKS);
              
              panelKS.repaint();
              this.containerPanel.add(panelKS);
              top += height;
            }
            

            this.containerPanel.repaint();
          }
          else {
            JOptionPane.showMessageDialog(null, "Valor de constantes muy grande. El valor puede tomar una constante como máximo es 20", 
              "Error", 0, null);
          }
        }
        else {
          JOptionPane.showMessageDialog(null, "Las constantes deben ser valores mayores que cero", 
            "Error", 0, null);
        }
      } else {
        JOptionPane.showMessageDialog(null, "Debe ingresar valores numéricos", 
          "Error", 0, null);
      }
    } else {
      JOptionPane.showMessageDialog(null, "Debe ingresar constantes de tiempo y ganancia", 
        "Error", 0, null);
    }
  }
  

  private void generateButtons(final String method, JPanel panel)
  {
    JButton btnEquations = new JButton("Ecuaciones", new ImageIcon(MethodPanelView.class.getResource("/icons/icon_formula.png")));
    btnEquations.setHorizontalAlignment(2);
    btnEquations.setBounds(420, 15, 150, 40);
    
    JButton btnDescription = new JButton("Descripción método", new ImageIcon(MethodPanelView.class.getResource("/icons/icon_formula.png")));
    btnDescription.setHorizontalAlignment(2);
    btnDescription.setBounds(580, 15, 150, 40);
    
    if ((method == "lopez") || (method == "ks"))
    {
      JButton btnConstants = new JButton("Constantes", new ImageIcon(MethodPanelView.class.getResource("/icons/icon_formula.png")));
      btnConstants.setHorizontalAlignment(2);
      btnConstants.setBounds(420, 65, 150, 40);
      


      btnConstants.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e) {
          JDialog dialog = new JDialog(PanelCompare.this.mainView1);
          dialog.setTitle("Constantes Metodo de Lopez");
          dialog.getContentPane().add(new JScrollPane(PanelCompare.this.infoExpert.getConstantTable(method)));
          dialog.setVisible(true);
          dialog.setLocationRelativeTo(PanelCompare.this.mainView1);
          dialog.setBounds(100, 100, 500, 200);
        }
        
      });
      panel.add(btnConstants);
    }
    
    btnEquations.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        String title = "Ecuaciones del método";
        
        ModalEquationView dialog = new ModalEquationView(PanelCompare.this.mainView1, title, PanelCompare.this.infoExpert.getURLEquationImage(method));
        dialog.setVisible(true);
        dialog.setBounds(100, 100, 500, 200);
        dialog.setLocationRelativeTo(PanelCompare.this.mainView1);
      }
      

    });
    btnDescription.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ModalMethodDescription dialog = new ModalMethodDescription(PanelCompare.this.mainView1, PanelCompare.this.infoExpert.getDescription(method));
        dialog.setVisible(true);
        dialog.setBounds(100, 100, 500, 200);
        dialog.setLocationRelativeTo(PanelCompare.this.mainView1);
      }
      

    });
    panel.add(btnEquations);
    panel.add(btnDescription);
  }
  

  public JTextField getInputField()
  {
    return this.inputA;
  }
}
