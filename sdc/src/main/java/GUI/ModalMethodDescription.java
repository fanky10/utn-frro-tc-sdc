package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;



public class ModalMethodDescription
  extends JDialog
{
  private static final long serialVersionUID = 1L;
  private final JPanel contentPanel = new JPanel();
  



  public ModalMethodDescription(JFrame frame, String mensaje)
  {
    super(frame, true);
    
    setTitle("Descripción del método");
    setIconImage(Toolkit.getDefaultToolkit().getImage(ModalMethodDescription.class.getResource("/icons/imagen de respuessta transitoria.jpg")));
    setResizable(false);
    setBounds(100, 100, 600, 500);
    setMinimumSize(new Dimension(600, 500));
    getContentPane().setLayout(new BorderLayout());
    this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.contentPanel, "Center");
    this.contentPanel.setLayout(null);
    
    JLabel label = new JLabel();
    label.setIcon(new ImageIcon(ModalMethodDescription.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
    label.setBounds(10, 10, 32, 43);
    this.contentPanel.add(label);
    

    JTextPane textPane = new JTextPane();
    textPane.setText(mensaje);
    JScrollPane scrollPane = new JScrollPane(textPane);
    textPane.setBounds(40, 18, 550, 400);
    scrollPane.setBounds(40, 18, 530, 400);
    textPane.setEditable(false);
    textPane.setFont(new Font("Tahoma", 0, 13));
    textPane.setBackground(SystemColor.control);
    
    this.contentPanel.add(scrollPane);
    textPane.setCaretPosition(0);
    

    JButton btnAceptar = new JButton("Aceptar");
    btnAceptar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ModalMethodDescription.this.dispose();
      }
    });
    btnAceptar.setBounds(250, 420, 100, 40);
    this.contentPanel.add(btnAceptar);
  }
}
