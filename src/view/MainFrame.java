/**
 * 
 */
package view;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.MatrixFactory;
import model.RussianMatrix;
import model.RussianMatrixAdapter;

/**
 * @author Robert
 */
public class MainFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel pnlMatrixA;
    private JButton btnFile;
    private JTextField txtFdSize;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    MainFrame frame = new MainFrame();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    private void setPnl(JPanel pnl, RussianMatrixAdapter rma, JLabel lblC, JLabel lblMa, JLabel lblMb) {
	TitledBorder border = (TitledBorder) pnl.getBorder();
	int ciIndex = rma.getCiIndex();
	int p = rma.getP();
	if (border != null) {
	    if (ciIndex < p) {
		border.setTitle("C[" + (ciIndex + 1) + "]");
	    } else {
		border.setTitle("C");
	    }
	}
	pnl.repaint();
	lblC.setText(rma.ciToString());
	lblMa.setText(rma.aToColoredString());
	lblMb.setText(rma.bToColoredString());
    }

    /**
     * Create the frame.
     */
    public MainFrame() {
	final RussianMatrixAdapter rma = new RussianMatrixAdapter();
	final JFileChooser fc = new JFileChooser();

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 636, 302);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	GridBagLayout gbl_contentPane = new GridBagLayout();
	gbl_contentPane.columnWidths = new int[] { 0, 0 };
	gbl_contentPane.rowHeights = new int[] { 0, 0 };
	gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
	gbl_contentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
	contentPane.setLayout(gbl_contentPane);

	final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
	gbc_tabbedPane.fill = GridBagConstraints.BOTH;
	gbc_tabbedPane.gridx = 0;
	gbc_tabbedPane.gridy = 0;
	contentPane.add(tabbedPane, gbc_tabbedPane);

	JPanel pnlTab1 = new JPanel();
	tabbedPane.addTab("Citire", null, pnlTab1, null);
	pnlTab1.setLayout(null);

	pnlMatrixA = new JPanel();
	pnlMatrixA.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "A", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null,
		null));
	pnlMatrixA.setBounds(10, 12, 200, 200);
	pnlTab1.add(pnlMatrixA);
	pnlMatrixA.setLayout(null);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(12, 19, 176, 176);
	pnlMatrixA.add(scrollPane);

	final JLabel lblA = new JLabel("");
	scrollPane.setViewportView(lblA);
	lblA.setVerticalAlignment(SwingConstants.TOP);
	lblA.setHorizontalAlignment(SwingConstants.LEFT);

	JPanel pnlMatrixB = new JPanel();
	pnlMatrixB.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "B", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null,
		null));
	pnlMatrixB.setBounds(212, 12, 200, 200);
	pnlTab1.add(pnlMatrixB);
	pnlMatrixB.setLayout(null);

	JScrollPane scrollPane_2 = new JScrollPane();
	scrollPane_2.setBounds(12, 19, 176, 176);
	pnlMatrixB.add(scrollPane_2);

	final JLabel lblB = new JLabel("");
	scrollPane_2.setViewportView(lblB);
	lblB.setVerticalAlignment(SwingConstants.TOP);

	JPanel pnlButtons = new JPanel();
	pnlButtons.setBorder(new TitledBorder(null, "Creare", TitledBorder.LEFT, TitledBorder.TOP, null, null));
	pnlButtons.setBounds(413, 0, 192, 212);
	pnlTab1.add(pnlButtons);
	pnlButtons.setLayout(null);

	JButton btnRandom = new JButton("Random");

	btnRandom.setBounds(6, 62, 186, 50);
	pnlButtons.add(btnRandom);

	btnFile = new JButton("Fisier");
	btnFile.setBounds(6, 112, 186, 50);
	pnlButtons.add(btnFile);

	JButton btnKey = new JButton("Tastatura");
	btnKey.setEnabled(false);
	btnKey.setBounds(6, 162, 186, 50);
	pnlButtons.add(btnKey);

	final JRadioButton rdbtnMatriceaB = new JRadioButton("Matricea B");
	rdbtnMatriceaB.setBounds(100, 42, 92, 20);
	pnlButtons.add(rdbtnMatriceaB);

	final JRadioButton rdbtnMatriceaA = new JRadioButton("Matricea A");
	rdbtnMatriceaA.setBounds(6, 42, 90, 20);
	pnlButtons.add(rdbtnMatriceaA);

	txtFdSize = new JTextField();
	txtFdSize.setBounds(83, 15, 100, 20);
	pnlButtons.add(txtFdSize);
	txtFdSize.setColumns(10);

	JLabel lblDimensiune = new JLabel("Dimensiune");
	lblDimensiune.setBounds(10, 21, 77, 14);
	pnlButtons.add(lblDimensiune);

	JPanel pnlTab2 = new JPanel();
	tabbedPane.addTab("Calcul", null, pnlTab2, null);
	pnlTab2.setLayout(null);

	JScrollPane scrlPn1 = new JScrollPane();
	scrlPn1.setToolTipText("Matricea A");
	scrlPn1.setBounds(10, 11, 200, 200);
	pnlTab2.add(scrlPn1);

	final JLabel lblMa = new JLabel("");
	scrlPn1.setViewportView(lblMa);

	JScrollPane scrlPn2 = new JScrollPane();
	scrlPn2.setToolTipText("Matricea B");
	scrlPn2.setBounds(217, 11, 200, 200);
	pnlTab2.add(scrlPn2);

	final JLabel lblMb = new JLabel("");
	scrlPn2.setViewportView(lblMb);

	final JPanel pnlCalcul = new JPanel();
	pnlCalcul.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Calcul", TitledBorder.LEADING, TitledBorder.ABOVE_TOP,
		null, null));
	pnlCalcul.setBounds(427, 0, 178, 211);
	pnlTab2.add(pnlCalcul);
	pnlCalcul.setLayout(null);

	final JButton btnPrev = new JButton("Prev");
	btnPrev.setBounds(10, 21, 79, 20);
	pnlCalcul.add(btnPrev);

	final JButton btnNext = new JButton("Next");
	btnNext.setBounds(88, 21, 79, 20);
	pnlCalcul.add(btnNext);

	JScrollPane scrollPane_1 = new JScrollPane();
	scrollPane_1.setBounds(10, 42, 157, 157);
	pnlCalcul.add(scrollPane_1);

	final JLabel lblC = new JLabel("");
	lblC.setToolTipText("Matricile C");
	lblC.setVerticalAlignment(SwingConstants.TOP);
	scrollPane_1.setViewportView(lblC);

	btnRandom.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		Integer size = 1;
		try {
		    size = Integer.parseInt(txtFdSize.getText());
		    if (rdbtnMatriceaA.isSelected()) {
			rma.setA(MatrixFactory.buildRandomRussianMatrix("square", size));
			lblA.setText(rma.aToString());
		    }
		    if (rdbtnMatriceaB.isSelected()) {
			rma.setB(MatrixFactory.buildRandomRussianMatrix("square", size));
			lblB.setText(rma.bToString());
		    }
		} catch (Exception e) {
		    Logger.getGlobal().log(Level.SEVERE, "input not correct for size");
		}
	    }
	});
	btnFile.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		File file;
		fc.showOpenDialog(MainFrame.this);
		file = fc.getSelectedFile();

		if (file != null) {
		    String filePath = file.getAbsolutePath();
		    try {
			if (rdbtnMatriceaA.isSelected()) {
			    rma.setA(MatrixFactory.buildRussianMatrixFromFile(filePath));
			    lblA.setText(rma.aToString());
			}
			if (rdbtnMatriceaB.isSelected()) {
			    rma.setB(MatrixFactory.buildRussianMatrixFromFile(filePath));
			    lblB.setText(rma.bToString());
			}
		    } catch (Exception e) {
			Logger.getGlobal().log(Level.SEVERE, "input not correct for size");
		    }
		}
	    }
	});

	tabbedPane.addChangeListener(new ChangeListener() {
	    public void stateChanged(ChangeEvent e) {
		int tabIndex = tabbedPane.getSelectedIndex();
		RussianMatrix a = rma.getA();
		RussianMatrix b = rma.getB();
		if (tabIndex == 1 && a != null && b != null) {
		    rma.mul();
		    setPnl(pnlCalcul, rma, lblC, lblMa, lblMb);
		}
		if (a == null || b == null) {
		    btnPrev.setEnabled(false);
		    btnNext.setEnabled(false);
		} else if (a != null && b != null) {
		    btnPrev.setEnabled(true);
		    btnNext.setEnabled(true);
		}
	    }
	});

	btnPrev.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		rma.prev();
		setPnl(pnlCalcul, rma, lblC, lblMa, lblMb);
	    }
	});

	btnNext.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		rma.next();
		setPnl(pnlCalcul, rma, lblC, lblMa, lblMb);
	    }
	});
    }
}
