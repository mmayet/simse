/*
 * This class is responsible for generating all of the code for the state
 * component of the simulation
 */

package simse.codegenerator.stategenerator;

import simse.codegenerator.CodeGeneratorConstants;

import simse.modelbuilder.ModelOptions;
import simse.modelbuilder.actionbuilder.DefinedActionTypes;
import simse.modelbuilder.objectbuilder.DefinedObjectTypes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class StateGenerator implements CodeGeneratorConstants {
  private ModelOptions options;
  private ADTGenerator adtGen; // generates ADTs
  private RepositoryGenerator repGen; // generates state repositories
  private ClockGenerator clockGen; // generates clock
  private LoggerGenerator loggerGen; // generates logger

  public StateGenerator(ModelOptions options, DefinedObjectTypes objTypes,
      DefinedActionTypes actTypes) {
    this.options = options;
    adtGen = new ADTGenerator(options, objTypes, actTypes);
    repGen = new RepositoryGenerator(options, objTypes, actTypes);
    clockGen = new ClockGenerator(options);
    loggerGen = new LoggerGenerator(options);
  }

  // causes all of this component's sub-components to generate code
  public void generate() {
    adtGen.generate();
    repGen.generate();
    clockGen.generate();
    loggerGen.generate();

    // generate outer state component:
    File stateFile = new File(options.getCodeGenerationDestinationDirectory(), 
        ("simse\\state\\State.java"));
    if (stateFile.exists()) {
      stateFile.delete(); // delete old version of file
    }
    try {
      FileWriter writer = new FileWriter(stateFile);
      writer
          .write("/* File generated by: simse.codegenerator.stategenerator.StateGenerator */");
      writer.write(NEWLINE);
      writer.write("package simse.state;");
      writer.write(NEWLINE);
      writer.write(NEWLINE);
      writer.write("import simse.state.logger.Logger;");
      writer.write(NEWLINE);
      writer.write(NEWLINE);
      writer.write("public class State implements Cloneable");
      writer.write(NEWLINE);
      writer.write(OPEN_BRACK);
      writer.write(NEWLINE);

      // member variables:
      writer.write("private EmployeeStateRepository esr;");
      writer.write(NEWLINE);
      writer.write("private ArtifactStateRepository asr;");
      writer.write(NEWLINE);
      writer.write("private ToolStateRepository tsr;");
      writer.write(NEWLINE);
      writer.write("private ProjectStateRepository psr;");
      writer.write(NEWLINE);
      writer.write("private CustomerStateRepository csr;");
      writer.write(NEWLINE);
      writer.write("private ActionStateRepository actsr;");
      writer.write(NEWLINE);
      writer.write("private Clock clock;");
      writer.write(NEWLINE);
      writer.write("private Logger logger;");
      writer.write(NEWLINE);
      writer.write("private Number score;");
      writer.write(NEWLINE);
      writer.write(NEWLINE);

      // constructor:
      writer.write("public State()");
      writer.write(NEWLINE);
      writer.write(OPEN_BRACK);
      writer.write(NEWLINE);
      writer.write("esr = new EmployeeStateRepository();");
      writer.write(NEWLINE);
      writer.write("asr = new ArtifactStateRepository();");
      writer.write(NEWLINE);
      writer.write("tsr = new ToolStateRepository();");
      writer.write(NEWLINE);
      writer.write("psr = new ProjectStateRepository();");
      writer.write(NEWLINE);
      writer.write("csr = new CustomerStateRepository();");
      writer.write(NEWLINE);
      writer.write("actsr = new ActionStateRepository();");
      writer.write(NEWLINE);
      writer.write("logger = new Logger(this);");
      writer.write(NEWLINE);
      writer.write("clock = new Clock(logger);");
      writer.write(NEWLINE);
      writer.write("score = new Integer(-1);");
      writer.write(NEWLINE);
      writer.write(CLOSED_BRACK);
      writer.write(NEWLINE);
      writer.write(NEWLINE);

      // methods:
      
      // clone method:
      writer.write("public Object clone() {");
      writer.write(NEWLINE);
      writer.write("try {");
      writer.write(NEWLINE);
      writer.write("State cl = (State) (super.clone());");
      writer.write(NEWLINE);
      writer.write("cl.esr = (EmployeeStateRepository) (esr.clone());");
      writer.write(NEWLINE);
      writer.write("cl.asr = (ArtifactStateRepository) (asr.clone());");
      writer.write(NEWLINE);
      writer.write("cl.tsr = (ToolStateRepository) (tsr.clone());");
      writer.write(NEWLINE);
      writer.write("cl.psr = (ProjectStateRepository) (psr.clone());");
      writer.write(NEWLINE);
      writer.write("cl.csr = (CustomerStateRepository) (csr.clone());");
      writer.write(NEWLINE);
      writer.write("cl.actsr = (ActionStateRepository) (actsr.clone());");
      writer.write(NEWLINE);
			writer.write("cl.actsr.refetchParticipants(cl.asr, cl.csr, cl.esr, cl.psr, cl.tsr);");
			writer.write(NEWLINE);
      writer.write("cl.clock = null;");
      writer.write(NEWLINE);
      writer.write("cl.logger = null;");
      writer.write(NEWLINE);
			writer.write("if (score instanceof Integer) {");
			writer.write(NEWLINE);
			writer.write("cl.score = new Integer(score.intValue());");
			writer.write(NEWLINE);
			writer.write(CLOSED_BRACK);
			writer.write(NEWLINE);
			writer.write("else { // Double");
			writer.write(NEWLINE);
			writer.write("cl.score = new Double(score.doubleValue());");
			writer.write(NEWLINE);
			writer.write(CLOSED_BRACK);
      writer.write(NEWLINE);
      writer.write("return cl;");
      writer.write(NEWLINE);
      writer.write("} catch (CloneNotSupportedException c) {");
      writer.write(NEWLINE);
      writer.write("System.out.println(c.getMessage());");
      writer.write(NEWLINE);
      writer.write(CLOSED_BRACK);
      writer.write(NEWLINE);
      writer.write("return null;");
      writer.write(NEWLINE);
      writer.write(CLOSED_BRACK);
      writer.write(NEWLINE);
      writer.write(NEWLINE);

      // getEmployeeStateRepository() method:
      writer
          .write("public EmployeeStateRepository getEmployeeStateRepository()");
      writer.write(NEWLINE);
      writer.write(OPEN_BRACK);
      writer.write(NEWLINE);
      writer.write("return esr;");
      writer.write(NEWLINE);
      writer.write(CLOSED_BRACK);
      writer.write(NEWLINE);
      writer.write(NEWLINE);

      // getArtifactStateRepository() method:
      writer
          .write("public ArtifactStateRepository getArtifactStateRepository()");
      writer.write(NEWLINE);
      writer.write(OPEN_BRACK);
      writer.write(NEWLINE);
      writer.write("return asr;");
      writer.write(NEWLINE);
      writer.write(CLOSED_BRACK);
      writer.write(NEWLINE);
      writer.write(NEWLINE);

      // getToolStateRepository() method:
      writer.write("public ToolStateRepository getToolStateRepository()");
      writer.write(NEWLINE);
      writer.write(OPEN_BRACK);
      writer.write(NEWLINE);
      writer.write("return tsr;");
      writer.write(NEWLINE);
      writer.write(CLOSED_BRACK);
      writer.write(NEWLINE);
      writer.write(NEWLINE);

      // getProjectStateRepository() method:
      writer.write("public ProjectStateRepository getProjectStateRepository()");
      writer.write(NEWLINE);
      writer.write(OPEN_BRACK);
      writer.write(NEWLINE);
      writer.write("return psr;");
      writer.write(NEWLINE);
      writer.write(CLOSED_BRACK);
      writer.write(NEWLINE);
      writer.write(NEWLINE);

      // getCustomerStateRepository() method:
      writer
          .write("public CustomerStateRepository getCustomerStateRepository()");
      writer.write(NEWLINE);
      writer.write(OPEN_BRACK);
      writer.write(NEWLINE);
      writer.write("return csr;");
      writer.write(NEWLINE);
      writer.write(CLOSED_BRACK);
      writer.write(NEWLINE);
      writer.write(NEWLINE);

      // getActionStateRepository() method:
      writer.write("public ActionStateRepository getActionStateRepository()");
      writer.write(NEWLINE);
      writer.write(OPEN_BRACK);
      writer.write(NEWLINE);
      writer.write("return actsr;");
      writer.write(NEWLINE);
      writer.write(CLOSED_BRACK);
      writer.write(NEWLINE);
      writer.write(NEWLINE);

      // getClock() method:
      writer.write("public Clock getClock()");
      writer.write(NEWLINE);
      writer.write(OPEN_BRACK);
      writer.write(NEWLINE);
      writer.write("return clock;");
      writer.write(NEWLINE);
      writer.write(CLOSED_BRACK);
      writer.write(NEWLINE);
      writer.write(NEWLINE);
      
      // setClock method:
    	writer.write("public void setClock(Clock clock) {");
    	writer.write(NEWLINE);
    	writer.write("this.clock = clock;");
    	writer.write(NEWLINE);
    	writer.write(CLOSED_BRACK);
    	writer.write(NEWLINE);
    	writer.write(NEWLINE);

      // getLogger() method:
      writer.write("public Logger getLogger()");
      writer.write(NEWLINE);
      writer.write(OPEN_BRACK);
      writer.write(NEWLINE);
      writer.write("return logger;");
      writer.write(NEWLINE);
      writer.write(CLOSED_BRACK);
      writer.write(NEWLINE);
      writer.write(NEWLINE);

      // setLogger method:
    	writer.write("public void setLogger(Logger logger) {");
    	writer.write(NEWLINE);
    	writer.write("this.logger = logger;");
    	writer.write(NEWLINE);
    	writer.write(CLOSED_BRACK);
    	writer.write(NEWLINE);
    	
    	// getScore method:
    	writer.write("public Number getScore() {");
    	writer.write(NEWLINE);
    	writer.write("return score;");
    	writer.write(NEWLINE);
    	writer.write(CLOSED_BRACK);
    	writer.write(NEWLINE);
    	writer.write(NEWLINE);
    	
    	// setScore method:
    	writer.write("public void setScore(Number score) {");
    	writer.write(NEWLINE);
    	writer.write("this.score = score;");
    	writer.write(NEWLINE);
    	writer.write(CLOSED_BRACK);
    	writer.write(NEWLINE);

      writer.write(CLOSED_BRACK);
      writer.close();
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, ("Error writing file "
          + stateFile.getPath() + ": " + e.toString()), "File IO Error",
          JOptionPane.WARNING_MESSAGE);
    }
  }
}