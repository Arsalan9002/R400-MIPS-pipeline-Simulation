import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import java.awt.Window.Type;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import java.awt.Font;

public class InstFrame extends JFrame {

	private JPanel contentPane;
	private JLayeredPane layeredPane;
	static ArrayList<String> Inst;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InstFrame frame = new InstFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InstFrame() {
		setFont(new Font("Arial", Font.PLAIN, 17));
		setTitle("MIPS R4000 Simulator");
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JLabel lbl1 = new JLabel("Enter Instructions Below:");
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setBounds(0, 0, 477, 14);
		layeredPane.add(lbl1);
		
		JLabel lbl2 = new JLabel("Valid Opcodes: LD, DADD, DSUB, MUL, DIV,  SD, BR, OR, AND");
		lbl2.setHorizontalAlignment(SwingConstants.LEFT);
		lbl2.setBounds(0, 25, 477, 14);
		layeredPane.add(lbl2);
		
		JLabel lbl3 = new JLabel("Registers: R1, R2, ..., R32");
		lbl3.setHorizontalAlignment(SwingConstants.LEFT);
		lbl3.setBounds(0, 11, 477, 14);
		layeredPane.add(lbl3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 42, 477, 358);
		layeredPane.add(scrollPane);
		
		JTextArea Input = new JTextArea();
		Input.setText("Format: Opcode D S T");
		scrollPane.setViewportView(Input);
		
		JButton Submit = new JButton("Submit");
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] str=Input.getText().split("\\s+");
				Inst = new ArrayList<String>(Arrays.asList(str));
				String[] Instructions=Input.getText().split("\n");
				//System.out.println(Inst);
				Pipeline p = new Pipeline();
				OutFrame output = new OutFrame(Input.getLineCount());
				//output.setVisible(true);
				output.setTable(p.initialize(output,Input.getLineCount()),Instructions);
				Submit.setEnabled(false);
			}
		});
		contentPane.add(Submit, BorderLayout.SOUTH);
	}
}
