package CaseBaseCreation;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.jLOAF.casebase.Case;
import org.jLOAF.casebase.CaseBase;
import org.jLOAF.inputs.AtomicInput;
import org.jLOAF.inputs.Feature;
import org.jLOAF.sim.AtomicSimilarityMetricStrategy;
import org.jLOAF.sim.ComplexSimilarityMetricStrategy;
import org.jLOAF.sim.StateBasedSimilarity;
import org.jLOAF.sim.StateBased.KOrderedSimilarity;
import org.jLOAF.sim.atomic.EuclideanDistance;
import org.jLOAF.sim.complex.Mean;

import AgentModules.CPAction;
import AgentModules.CPInput;

public class LogFile2CaseBase {
	
	public void logParser(String logfile, String outfile) throws IOException {
		AtomicSimilarityMetricStrategy Atomic_strat = new EuclideanDistance();
		ComplexSimilarityMetricStrategy complex_strat = new Mean();
		StateBasedSimilarity stateBasedSim = new KOrderedSimilarity(1);
		
		CPInput mcinput;
		CPAction action;
		
		CaseBase cb = new CaseBase();
		
		BufferedReader br = new BufferedReader(new FileReader(logfile),'r');
		String Line;
		String [] input = new String [5];
		System.out.println("Creating casebase...");
		while ((Line = br.readLine()) != null){
			mcinput = new CPInput("Observation",complex_strat);
			
			input = Line.split(",");
			
			mcinput.add(new AtomicInput("position",new Feature(Double.parseDouble(input[0])),Atomic_strat));
			mcinput.add(new AtomicInput("velocity",new Feature(Double.parseDouble(input[1])),Atomic_strat));
			mcinput.add(new AtomicInput("angle",new Feature(Double.parseDouble(input[2])),Atomic_strat));
			mcinput.add(new AtomicInput("vtip",new Feature(Double.parseDouble(input[3])),Atomic_strat));
			action = new CPAction(input[4]);
			
			cb.createThenAdd(mcinput, action, stateBasedSim);	
		}
		
		System.out.println("CaseBase created.");
		br.close();
		System.out.println("Writing to file: " + outfile);
		CaseBase.save(cb, outfile);
		System.out.println("Done!");
	}
}
