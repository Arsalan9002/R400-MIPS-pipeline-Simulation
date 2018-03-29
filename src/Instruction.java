import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Instruction {

	ArrayList<String> Inst;
	static String[] opcode= {"LD","DADD","DSUB","MUL","DIV","SD","BR","OR","AND"};
	String Op;
	String S;
	String T;
	String D;
	Integer IC;	//instruction counter
	Integer pointer;
	
	Instruction(){
		/*try {
			Scanner s;
			File file = new File("C:/Users/Behroz/Desktop/JavaWorkspace/Simulator/src/Input.txt");
			s = new Scanner(file);
			Inst = new ArrayList<String>();
			while (s.hasNext()){
				Inst.add(s.next());
			}
			s.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
		pointer = 0;
		IC=0;
		Inst=InstFrame.Inst;
		
	}
	
	public ArrayList<String> getNextInst(int i){
		ArrayList<String> str = new ArrayList<String>();
		ArrayList<String> op = new ArrayList<String>(Arrays.asList(opcode));
		pointer = i;
		if(Inst.size()<=pointer){
			pointer=-1;
			return null;
		}
		Op = Inst.get(i);
		IC++;
		str.add(IC.toString());
		str.add(Op);
	//	System.out.print("Op="+Op);
		i++;
		if(i>=Inst.size()){
			pointer=-1;
			System.out.println();
			return str;
		}
		while(!op.contains(Inst.get(i)))
		{
			while(Inst.get(i)==" "){
				i++;
				if(i>=Inst.size()){
					pointer=-1;
					System.out.println();
					return str;
				}
			}
			
			D=Inst.get(i);
			str.add(D);
		//	System.out.print(" D="+D);
			i++;
			if(i>=Inst.size()){
				pointer=-1;
				return str;
			}
			while(Inst.get(i)==" "){
				i++;
				if(i>=Inst.size()){
					pointer=-1;
					return str;
				}
			}
			
			if(!op.contains(Inst.get(i))){
				S=Inst.get(i);	
				int j=0,k=0;
				for(j=0;j<S.length();j++){
					char c = S.charAt(j);
					if(c=='('){
						k=j+1;
						j=S.length();
					}
				}
				if(S.charAt(S.length()-1)==')')
					S=S.substring(k,S.length()-1);
				//Operands.add(S);
				str.add(S);
		//		System.out.print(" S="+S);
				i++;
				if(i>=Inst.size()){
					pointer=-1;
					System.out.println();
					return str;
				}
			}
			while(Inst.get(i)==" "){
				i++;
				if(i>=Inst.size()){
					pointer=-1;
					return str;
				}
			}
			
			if(!op.contains(Inst.get(i))){
				T=Inst.get(i);
				//Operands.add(T);
				str.add(T);
	//			System.out.print(" T="+T);
				i++;
				if(i>=Inst.size()){
					pointer=-1;
					System.out.println();
					return str;
				}
			}
			while(Inst.get(i)==" "){
				i++;
				if(i>=Inst.size()){
					pointer=-1;
					return str;
				}
			}
			pointer = i;
			System.out.println();
		}
		return str;
	}
	
}
