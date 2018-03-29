
public class Pipeline {

	boolean IF;
	boolean IS;
	boolean RF;
	boolean EX;
	boolean DF;
	boolean DS;
	boolean TC;
	boolean WB;
	Integer Clock;
	boolean Stall;
	Integer StallCycles;
	Integer p;
	Integer[][] Cycletime;
	
	Pipeline(){
		IF=false;
		IS=false;
		RF=false;
		EX=false;
		DF=false;
		DS=false;
		TC=false;
		WB=false;
		Clock=1;
		StallCycles=0;
		Stall=false;
	};
	
	public Latches IF(OutFrame output,Instruction i,Latches IF_IS,Latches temp){
		
		/*if(IF==true){
			System.out.println("IF: ");
			System.out.println("Instruction Number:"+i.IC);
			System.out.println("Opcode:"+i.Op);
			System.out.println("Dest:"+ i.D);
			System.out.println("Source(S):"+i.S);
			if(i.T !=null)
				System.out.println("Source(T):"+i.T);
			System.out.println();
			return IF_IS;
		}
		else if(i.pointer!=-1){
			i.getNextInst(i.pointer);
			IF_IS.InstNum= i.IC;
			System.out.println("IF: ");
			System.out.println("Instruction Number:"+IF_IS.InstNum);
			IF_IS.Opcode = i.Op;
			System.out.println("Opcode:"+IF_IS.Opcode);
			IF_IS.Dest = i.D;
			System.out.println("Dest:"+IF_IS.Dest);
			IF_IS.S1 = i.S;
			System.out.println("Source(S):"+IF_IS.S1);
			if(i.T!=null){
				IF_IS.S2 = i.T;
				System.out.println("Source(T):"+IF_IS.S2);
			}
			p=i.pointer;
			System.out.println();
			return IF_IS;
		}
		else{
			System.out.println("IF: ");
			System.out.println("Instruction Number:"+0);
			System.out.println("Opcode:"+null);
			System.out.println("Dest:"+null);
			System.out.println("Source(S):"+null);
			System.out.println("Source(T):"+null);
			System.out.println();
			return IF_IS;
		}*/
		if(IF==true){
			temp.InstNum= i.IC;
			temp.Opcode = i.Op;
			temp.Dest = i.D;
			temp.S1 = i.S;
			temp.S2 = i.T;
			output.Display(temp,"IF");
			if(i.IC!=0 && Cycletime[i.IC-1][0]==null)
				Cycletime[i.IC-1][0]=Clock;
			/*output.SeqOutput.append( String.format( "IF:\nInstruction Number: %d\nOpcode: %s\nDest: %s\nSource(S): %s\n",i.IC,i.Op,i.D,i.S ));
			if(i.T !=null)
				output.SeqOutput.append( String.format( "Source(T): %s\n\n",i.T ));*/
			return temp;
		}
		else if(i.pointer!=-1){
			i.getNextInst(i.pointer);
			IF_IS.InstNum= i.IC;
			IF_IS.Opcode = i.Op;
			IF_IS.Dest = i.D;
			IF_IS.S1 = i.S;
			output.Display(IF_IS,"IF");
			if(IF_IS.InstNum!=0 && Cycletime[IF_IS.InstNum-1][0]==null)
				Cycletime[IF_IS.InstNum-1][0]=Clock;
		/*	output.SeqOutput.append( String.format( "IF:\nInstruction Number: %d\nOpcode: %s\nDest: %s\nSource(S): %s\n",IF_IS.InstNum,IF_IS.Opcode,IF_IS.Dest,IF_IS.S1));
			if(i.T!=null){
				IF_IS.S2 = i.T;
				output.SeqOutput.append( String.format( "Source(T): %s\n\n",IF_IS.S2));
			}*/
			return IF_IS;
		}
		else{
			temp.InstNum= 0;
			temp.Opcode = null;
			temp.Dest = null;
			temp.S1 = null;
			temp.S2 = null;
			output.Display(temp,"IF");
			if(temp.InstNum!=0 && Cycletime[temp.InstNum-1][0]==null)
				Cycletime[temp.InstNum-1][0]=Clock;
			//output.SeqOutput.append( String.format( "IF:\nInstruction Number: %d\nOpcode: %s\nDest: %s\nSource(S): %s\nSource(T): %s\n\n",0,null,null,null,null));
			return IF_IS;
		}
		
	}
	public Latches IS(OutFrame output,Latches IF_IS,Latches IS_RF){
		/*System.out.println("IS: ");
		System.out.println("Instruction Number:"+IS_RF.InstNum);
		System.out.println("Opcode:"+IS_RF.Opcode);
		System.out.println("Dest:"+IS_RF.Dest);
		System.out.println("Source(S):"+IS_RF.S1);
		if(IS_RF.S2 !=null)
			System.out.println("Source(T):"+IS_RF.S2);
		System.out.println();*/
		output.Display(IS_RF,"IS");
		if(IS_RF.InstNum!=0 && Cycletime[IS_RF.InstNum-1][1]==null)
			Cycletime[IS_RF.InstNum-1][1]=Clock;
		return IS_RF;
	}
	public Latches RF(OutFrame output,Latches IS_RF,Latches RF_EX){
		/*System.out.println("RF: ");
		System.out.println("Instruction Number:"+RF_EX.InstNum);
		System.out.println("Opcode:"+RF_EX.Opcode);
		System.out.println("Dest:"+RF_EX.Dest);
		System.out.println("Source(S):"+RF_EX.S1);
		if(RF_EX.S2 !=null)
			System.out.println("Source(T):"+RF_EX.S2);
		System.out.println();*/
		output.Display(RF_EX,"RF");
		if(RF_EX.InstNum!=0 && Cycletime[RF_EX.InstNum-1][2]==null)
			Cycletime[RF_EX.InstNum-1][2]=Clock;
		return RF_EX;
	}
	public Latches EX(OutFrame output,Latches RF_EX,Latches EX_DF){

		/*System.out.println("EX: ");
		System.out.println("Instruction Number:"+EX_DF.InstNum);
		System.out.println("Opcode:"+EX_DF.Opcode);
		System.out.println("Dest:"+EX_DF.Dest);
		System.out.println("Source(S):"+EX_DF.S1);
		if(EX_DF.S2 !=null)
			System.out.println("Source(T):"+EX_DF.S2);
		System.out.println();
		return EX_DF;*/
		output.Display(EX_DF,"EX");
		if(EX_DF.InstNum!=0 && Cycletime[EX_DF.InstNum-1][3]==null)
			Cycletime[EX_DF.InstNum-1][3]=Clock;
		return EX_DF;
	}
	public Latches DF(OutFrame output,Latches EX_DF,Latches DF_DS){

		/*System.out.println("DF: ");
		System.out.println("Instruction Number:"+DF_DS.InstNum);
		System.out.println("Opcode:"+DF_DS.Opcode);
		System.out.println("Dest:"+DF_DS.Dest);
		System.out.println("Source(S):"+DF_DS.S1);
		if(DF_DS.S2 !=null)
			System.out.println("Source(T):"+DF_DS.S2);
		System.out.println();
		return DF_DS;*/
		output.Display(DF_DS,"DF");
		if(DF_DS.InstNum!=0 && Cycletime[DF_DS.InstNum-1][4]==null)
			Cycletime[DF_DS.InstNum-1][4]=Clock;
		return DF_DS;
	}
	public Latches DS(OutFrame output,Latches DF_DS,Latches DS_TC){
		
		/*System.out.println("DS: ");
		System.out.println("Instruction Number:"+DS_TC.InstNum);
		System.out.println("Opcode:"+DS_TC.Opcode);
		System.out.println("Dest:"+DS_TC.Dest);
		System.out.println("Source(S):"+DS_TC.S1);
		if(DS_TC.S2 !=null)
			System.out.println("Source(T):"+DS_TC.S2);
		System.out.println();
		return DS_TC;*/
		output.Display(DS_TC,"DS");
		if(DS_TC.InstNum!=0 && Cycletime[DS_TC.InstNum-1][5]==null)
			Cycletime[DS_TC.InstNum-1][5]=Clock;
		return DS_TC;
	}
	public Latches TC(OutFrame output,Latches DS_TC,Latches TC_WB){
		
		/*System.out.println("TC: ");
		System.out.println("Instruction Number:"+TC_WB.InstNum);
		System.out.println("Opcode:"+TC_WB.Opcode);
		System.out.println("Dest:"+TC_WB.Dest);
		System.out.println("Source(S):"+TC_WB.S1);
		if(TC_WB.S2 !=null)
			System.out.println("Source(T):"+TC_WB.S2);
		System.out.println();
		return TC_WB;*/
		output.Display(TC_WB,"TC");
		if(TC_WB.InstNum!=0 && Cycletime[TC_WB.InstNum-1][6]==null)
			Cycletime[TC_WB.InstNum-1][6]=Clock;
		return TC_WB;
	}
	public Latches WB(OutFrame output,Latches TC_WB,Latches Done){
		
		/*System.out.println("WB: ");
		System.out.println("Instruction Number:"+Done.InstNum);
		System.out.println("Opcode:"+Done.Opcode);
		System.out.println("Dest:"+Done.Dest);
		System.out.println("Source(S):"+Done.S1);
		if(Done.S2 !=null)
			System.out.println("Source(T):"+Done.S2);
		System.out.println();
		return Done;*/
		output.Display(Done,"WB");
		if(Done.InstNum!=0 && Cycletime[Done.InstNum-1][7]==null)
			Cycletime[Done.InstNum-1][7]=Clock;
		return Done;
	}
	
	public boolean isStall(Latches RF_EX,Latches EX_DF,Latches DF_DS){
		if(EX_DF.Opcode!=null && RF_EX.Opcode!=null){

			//System.out.println("EX_DF="+EX_DF.Opcode+" "+EX_DF.InstNum+" RF_EX="+RF_EX.Opcode+" "+RF_EX.InstNum+" DF_DS="+DF_DS.Opcode+" "+DF_DS.InstNum);
			if(EX_DF.Opcode.equals("LD") && RF_EX.Opcode.equals("LD"))
				return false;
			
			if(DF_DS.Opcode!=null)
				if(EX_DF.Opcode.equals("LD") && DF_DS.Opcode.equals("LD"))
					return false;
			

			if((EX_DF.Opcode.equals("LD")) && (RF_EX.S1.equals(EX_DF.Dest)|| RF_EX.S2.equals(EX_DF.Dest))){
				StallCycles=2;
				IF=true;
				IS=true;
				RF=true;
				EX=true;
				Stall=true;
				return true;
			}
			else if(DF_DS.Opcode!=null &&((DF_DS.Opcode.equals("LD") ) && ( RF_EX.S1.equals(DF_DS.Dest) || RF_EX.S2.equals(DF_DS.Dest))))
			{
				StallCycles=1;
				IF=true;
				IS=true;
				RF=true;
				EX=true;
				Stall=true;
				return true;
			}
		}
		return false;
	}
	public void simulate(OutFrame output,Instruction i,Latches temp,Latches IF_IS,Latches IS_RF,Latches RF_EX,Latches EX_DF,Latches DF_DS,Latches DS_TC,Latches TC_WB,Latches Done){

		
		if(StallCycles==0)
			LatchTransfer(IF_IS, IS_RF, RF_EX, EX_DF, DF_DS, DS_TC, TC_WB, Done);
		else
			StallTransfer(EX_DF, DF_DS, DS_TC, TC_WB, Done);

		//System.out.println("ClockCycle:"+Clock);
		output.showClock(Clock);
		
		IF(output,i,IF_IS,temp);
		
		IS(output,IF_IS,IS_RF);
		
		RF(output,IS_RF,RF_EX);
		
		EX(output,RF_EX,EX_DF);
		
		DF(output,EX_DF,DF_DS);
		
		DS(output,DF_DS,DS_TC);
		
		TC(output,DS_TC,TC_WB);
		
		WB(output,TC_WB,Done);
		isStall(RF_EX,EX_DF,DF_DS);
		Stall=nextclock();
	}
	
	public boolean nextclock(){
		Clock++;
		if(StallCycles>0){
			StallCycles--;
			if(StallCycles==0)
				Stall=false;
			return true;
		}
		IF=false;
		IS=false;
		RF=false;
		EX=false;
		DF=false;
		DS=false;
		TC=false;
		WB=false;
		return false;
	}
	public void LatchTransfer(Latches IF_IS,Latches IS_RF,Latches RF_EX,Latches EX_DF,Latches DF_DS,Latches DS_TC,Latches TC_WB,Latches Done){
		Done.transfer(TC_WB);
		TC_WB.transfer(DS_TC);
		DS_TC.transfer(DF_DS);
		DF_DS.transfer(EX_DF);
		if(IF==false&&IS==false&&RF==false&&EX==false){
		EX_DF.transfer(RF_EX);
		RF_EX.transfer(IS_RF);
		IS_RF.transfer(IF_IS);
		}
	}
	public void StallTransfer(Latches EX_DF,Latches DF_DS,Latches DS_TC,Latches TC_WB,Latches Done){
			Done.transfer(TC_WB);
			TC_WB.transfer(DS_TC);
			DS_TC.transfer(DF_DS);
			DF_DS.transfer(EX_DF);
	}
	public Integer[][] initialize(OutFrame output, Integer InstCount){
		Instruction i = new Instruction();
		Cycletime = new Integer[InstCount][8];
		for(int k=0;k<InstCount;k++)
			for(int j=0;j<8;j++)
				Cycletime[k][j]=null;
			
		Latches temp = new Latches();
		Latches IF_IS = new Latches();
		Latches IS_RF = new Latches();
		Latches RF_EX = new Latches();
		Latches EX_DF = new Latches();
		Latches DF_DS = new Latches();
		Latches DS_TC = new Latches();
		Latches TC_WB = new Latches();
		Latches Done = new Latches();
		do{
			simulate(output,i,temp,IF_IS,IS_RF,RF_EX,EX_DF,DF_DS,DS_TC,TC_WB,Done);
		}
		while(i.pointer!=-1 || IF_IS.Opcode!=null || IS_RF.Opcode!=null || RF_EX.Opcode!=null || EX_DF.Opcode!=null || DF_DS.Opcode!=null || DS_TC.Opcode!=null || TC_WB.Opcode!=null);
		/*for(int k=0;k<InstCount;k++){
			for(int j=0;j<8;j++){
				System.out.println("InstNum="+(k+1)+" Stage="+(j+1)+" ClockCycle="+Cycletime[k][j]);
			}
		}
		System.out.println(Cycletime.length);*/
		return Cycletime;
	}

}

