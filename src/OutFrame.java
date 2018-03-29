import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JTable;

public class OutFrame {

	JFrame frmMipsRSimulator;
	JTextArea SeqOutput;
	DefaultTableModel model;
	JTable table;
	JPanel panel;

	public OutFrame(Integer InstCount) {
		initialize(InstCount);
	}
	
	public void setTable(Integer[][] Cycletime,String[] Inst){
		for(int i=0;i<Inst.length;i++){
			table.setValueAt(Inst[i], i, 0);
		}
		for(int i=0;i<Cycletime.length;i++){
			for(int j=1;j<9;j++){
				table.setValueAt(Cycletime[i][j-1], i, j);
			}
		}
	}
	
	private void initialize(Integer InstCount) {
		frmMipsRSimulator = new JFrame();
		frmMipsRSimulator.setTitle("MIPS R4000 Simulator");
		frmMipsRSimulator.setBounds(100, 100, 1000, 500);
		frmMipsRSimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmMipsRSimulator.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		String col[] = {"Instruction","IF", "IS", "RF", "EX", "DF", "DS", "TC", "WB"};
        this.model = new DefaultTableModel(col, InstCount);
        this.table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
		tabbedPane.addTab("Table", null, pane, null);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Sequential", null, scrollPane, null);
		
		SeqOutput = new JTextArea();
		scrollPane.setViewportView(SeqOutput);
		SeqOutput.setEditable(false);

		frmMipsRSimulator.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tabbedPane, SeqOutput, scrollPane}));
		frmMipsRSimulator.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tabbedPane, frmMipsRSimulator.getContentPane(), scrollPane, SeqOutput}));
        frmMipsRSimulator.setVisible(true);
        
		
		
        
	}
	public void Display(Latches L,String stage)
	{
		SeqOutput.append( String.format( "%s:\nInstruction Number: %d\nOpcode: %s\nDest: %s\nSource(S): %s\n\n",stage,L.InstNum,L.Opcode,L.Dest,L.S1));
		if(L.S2!=null)
			SeqOutput.append( String.format( "Source(T): %s\n\n",L.S2));
	}
	public void showClock(Integer Clock){
		if(Clock!=1)
			SeqOutput.append( String.format("--------------------------------\n\n",Clock));
		SeqOutput.append( String.format("ClockCycle: %d\n\n",Clock));
	}
}