package sherlock.framework;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.antlr.v4.runtime.*;

import sherlock.framework.explanations.*;
import sherlock.framework.parser.GrammarAgentLexer;
import sherlock.framework.parser.GrammarAgentParser;
import sherlock.framework.parser.SMTVisitor;



public class Main {

	public static void main(String[] args) 
	{
		if(args.length<2) {
			System.out.println("Nombre d'arguments insuffisant !");
		}
		else {
			ArrayList<String> explanations = new ArrayList<>();
			String file = "";
			for(int i=0;i<args.length;i++) {
				if(i==0) {
					file = args[i];
				}
				if(i>0) {
					explanations.add(args[i]);
				}
			}
			Main.launch(file,explanations);

		}

	}


	private static void launch(String file,ArrayList<String> explanations)

	{
		// Create an input character stream from standard in
		// ANTLRStringStream input = new ANTLRStringStream(inputString); 
		// Create an ExprLexer that feeds from that stream 
		CharStream cS;
		try {
			cS = CharStreams.fromFileName(file);

			//CharStream cS= CharStreams.fromString(inputString);
			GrammarAgentLexer lexer = new GrammarAgentLexer(cS);
			// Create a stream of tokens fed by the lexer 
			CommonTokenStream tokens = new CommonTokenStream(lexer); 
			// Create a parser that feeds off the token stream 
			GrammarAgentParser plParser = new GrammarAgentParser(tokens);

			SMTVisitor<Object> smtVisit = new SMTVisitor<Object>();
			plParser.parse().accept(smtVisit);
			SMTBuild build = new SMTBuild(smtVisit);
			// System.out.println(build.generateModel());
			SMT smt = new SMT("input.txt","output.txt",build.getModel());
			//System.out.println(build.getScenario().getPropositionSet(0).set);
			//build.getScenario().timeLimit = 2;
			Diagnostic diag = new Diagnostic(build,smt,Main.createInstanceBias(explanations, build, smt));
			diag.launchDiag();
			diag.getOutputXML("outputXML.xml");

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static void serializeDataOut(ArrayList<ArrayList<PropositionSet>> ish)throws IOException{
		String fileName= "Test.txt";
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(ish);
		oos.close();
	}

	public static ArrayList<ArrayList<PropositionSet>> serializeDataIn()
	{
		String fileName= "Test.txt";
		FileInputStream fin;
		try {
			fin = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fin);
			ArrayList<ArrayList<PropositionSet>> iHandler= (ArrayList<ArrayList<PropositionSet>>) ois.readObject();
			ois.close();
			return iHandler;
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	private static ArrayList<CoExp> createInstanceBias(ArrayList<String> exp,SMTBuild build,SMT smt)
	{
		ArrayList<CoExp> bias = new ArrayList<>();
		for(String e : exp) {
			switch(e) 
			{
			case "Anchor" :
				bias.add(new AnchorBias(build,smt));
				break;
			case "Avoid" :
				bias.add(new AvoidBias(build,smt));
				break;
			case "Confirmation" :
				bias.add(new ConfirmationBias(build,smt));
				break;
			case "Control" :
				bias.add(new ControlIllusion(build,smt));
				break;
			case "Correlation" :
				bias.add(new CorrelationIllusion(build,smt));
				break;
			case "Desengagement" :
				bias.add(new DesengagementBias(build,smt));
				break;
			case "Facilitation" :
				bias.add(new FacilitationBias(build,smt));
				break;
			case "Loss" :
				bias.add(new LossAversion(build,smt));
				break;
			case "Optimisme" :
				bias.add(new OptimismeBias(build,smt));
				break;
			case "Overconfidence" :
				bias.add(new Overconfidence(build,smt));
				break;
			case "Risk" :
				bias.add(new RiskAversion(build,smt));
				break;
			case "Truth" :
				bias.add(new TruthIllusion(build,smt));
				break;
			case "Wishful" :
				bias.add(new WishfulThinking(build,smt));
				break;
			}
		}

		return bias;
	}


}
