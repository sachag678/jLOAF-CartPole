package PerformanceTesting;

import java.io.IOException;

import org.jLOAF.Agent;
import org.jLOAF.performance.PerformanceEvaluator;

import AgentModules.CPAgent;
import CaseBaseCreation.LogFile2CaseBase;

public class PerformanceTest extends PerformanceEvaluator {
	
	public static void main(String a[]) throws IOException{
		//String [] filenames = {"Data/Carleton_1.lsf","Data/Carleton_2.lsf","Data/Carleton_3.lsf","Data/Carleton_4.lsf","Data/Carleton_5.lsf","Data/University_1.lsf","Data/University_2.lsf","Data/University_3.lsf","Data/University_4.lsf","Data/University_5.lsf"};
		String [] filenames = {"Data/file0.txt","Data/file1.txt","Data/file2.txt","Data/file3.txt","Data/file4.txt","Data/file5.txt","Data/file6.txt","Data/file7.txt","Data/file8.txt","Data/file9.txt"};
		String output_filename = "Results/ctsmodel,bayesian,none,none,none,none,.csv";
	
		//CaseBaseFilter smote = new UnderSampling(standardize);
		//CaseBaseFilter sample = new Sampling(standardize);
		PerformanceTest pt = new PerformanceTest();
		pt.PerformanceEvaluatorMethod(filenames, null, output_filename,"bayesian",null, null);
	}

	@Override
	public String[] createArrayOfCasebaseNames(String[] filenames) throws IOException {
		LogFile2CaseBase lg2cb = new LogFile2CaseBase();
		int count = 0;
		String [] cbnames = new String [filenames.length];
		
		for(String s: filenames){
			String str = "Data/cb"+count+".cb";
			cbnames[count]=str;
			lg2cb.logParser(s,str);
			count++;
		}
		return cbnames;
	}

	@Override
	public Agent createAgent() {
		CPAgent agent = new CPAgent();
		return agent;
	}

}
