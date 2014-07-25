package json;

import java.util.ArrayList;

public class PlugInConfiguration {
	public String moduleName = "";
	public String type = "";
	public String exeFile = "";
    public ArrayList<InuptFiles> inputFiles;
    public ArrayList<InputParams> inputParams;
    public ArrayList<OutputFiles> outputFiles;
    
    public void print (){

    	System.out.println("moduleName 	= " + moduleName);
    	System.out.println("type 		= " + type);
    	System.out.println("exeFile		= " + exeFile);
    	
    	for (InuptFiles i  : inputFiles){
    		System.out.println("Describles =" + i.inputFileDescribes + ", paramPos=" + i.paramPos);
    	}
    	
    	for (InputParams i  : inputParams){
    		System.out.println("paramDescribles =" + i.paramDescribe +" , defaultValue ="+ i.defaultValue + ", paramPos=" + i.paramPos);
    	}
    	
    	for (OutputFiles i  : outputFiles){
    		System.out.println("outputFileName =" + i.outputFileName + ", paramPos=" + i.paramPos);
    	}
    }
}
