
public class Latches {
	String Opcode;
	String Dest;
	String S1;
	Integer InstNum;
	String S2;
	Latches(){
		Opcode=null;
		Dest=null;
		S1=null;
		S2=null;
		InstNum=0;
	}
	public void setOp(String op){
		Opcode=op;
	}
	public void transfer(Latches B){
		this.Opcode=B.Opcode;
		this.Dest=B.Dest;
		this.S1=B.S1;
		this.InstNum=B.InstNum;
		this.S2=B.S2;
		B.Opcode=null;
		B.Dest=null;
		B.S1=null;
		B.InstNum=0;
		B.S2=null;
	}
}
