package ar.edu.utn.frro.tc.sdc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ModalEquationView
  extends JDialog
{
  private static final long serialVersionUID = 1L;
  private final JPanel contentPanel = new JPanel();
  JLabel source = null;
  private String sourceText = "";
  
  double minW;
  
  double minH;
  

  public ModalEquationView(JFrame frame, String title, String url)
  {
    super(frame, true);
    
    ImageIcon img = new ImageIcon(ModalEquationView.class.getResource(url));
    this.minW = img.getIconWidth();
    this.minH = img.getIconHeight();
    
    setTitle(title);
    
    setBounds(100, 100, (int)this.minW + 30, (int)this.minH + 50);
    
    setResizable(false);
    
    getContentPane().setLayout(new BorderLayout());
    this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.contentPanel, "Center");
    this.contentPanel.setLayout(null);
    

    JLabel label = new JLabel();
    label.setIcon(img);
    label.setBounds(10, 10, (int)this.minW, (int)this.minH);
    this.contentPanel.add(label);
  }
  

  public void setSource(String sourceText)
  {
    this.sourceText = sourceText;
    
    this.source = new JLabel("Source: " + this.sourceText);
    this.source.setBounds(10, (int)this.minH + 10, (int)this.minW, 15);
    
    this.source.setForeground(Color.BLUE);
    this.source.setCursor(new Cursor(12));
    this.source.addMouseListener(new URLOpenAdapter());
    


    this.contentPanel.add(this.source);
    setBounds(100, 100, (int)this.minW + 30, (int)this.minH + 60);
  }
  
  private class URLOpenAdapter extends MouseAdapter
  {
    private URLOpenAdapter() {}
    
    public void mouseClicked(MouseEvent e) {
      if (Desktop.isDesktopSupported()) {
        try {
          Desktop.getDesktop().browse(new URI(ModalEquationView.this.sourceText));
        }
        catch (Throwable localThrowable) {}
      }
    }
  }
}
