package sherlock.framework;
import java.io.IOException;
import java.util.ArrayList;

import org.antlr.v4.runtime.*;

import sherlock.framework.explanations.*;
import sherlock.framework.parser.GrammarAgentLexer;
import sherlock.framework.parser.GrammarAgentParser;
import sherlock.framework.parser.SMTVisitor;

/**
 * Main class
 *
 */
@SuppressWarnings("serial")
public class Sherlock {

	// All the explanations
	private final static ArrayList<String> LISTBIAS = new ArrayList<>() {
		{
			add("Anchor");
			add("Avoid");
			add("Confirmation");
			add("Control");
			add("Correlation");
			add("Desengagement");
			add("Facilitation");
			add("Loss");
			add("Optimism");
			add("Overconfidence");
			add("Risk");
			add("Truth");
			add("Wishful");
		}
	};
	
	public static void main(String[] args) 
	{
		if(args.length<3) {
			System.out.println("Not enough arguments !");
		}
		else {
			ArrayList<String> explanations = new ArrayList<>();
			String file = "";
			String output = "";
			for(int i=0;i<args.length;i++) {
				if(i==0) {
					file = args[i];
				}
				if(i==1) {
					output = args[i];
				}
				if(i>1) {
					explanations.add(args[i]);
				}
			}
			Sherlock.launch(file,output,explanations);

		}

	}


	private static void launch(String file,String output,ArrayList<String> explanations)

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
			SMT smt = new SMT("inputZ3.txt","outputZ3.txt",build.getModel());
			//System.out.println(build.getScenario().getPropositionSet(0).set);
			//build.getScenario().timeLimit = 2;
			ArrayList<CoExp> bias = new ArrayList<>();
			for(String exp : explanations) {
				if(exp.equals("All")) {
					for(String b : LISTBIAS) {
						Sherlock.addBias(bias,Sherlock.createInstanceBias(b, build, smt));
					}
				}
				else {
					Sherlock.addBias(bias,Sherlock.createInstanceBias(exp, build, smt));
				}
			}
			Diagnostic diag = new Diagnostic(build,smt,bias);
			diag.launchDiag();
			diag.getOutputXML(output);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	private static void addBias(ArrayList<CoExp> exp,CoExp bias)
	{
		if(!exp.contains(bias)) {
			exp.add(bias);
		}
	}

	

	private static CoExp createInstanceBias(String exp,SMTBuild build,SMT smt)
	{
		
			switch(exp) 
			{
			case "Anchor" :
				return new AnchorBias(build,smt);
			case "Avoid" :
				return new AvoidBias(build,smt);
			case "Confirmation" :
				return new ConfirmationBias(build,smt);
			case "Control" :
				return new ControlIllusion(build,smt);
			case "Correlation" :
				return new CorrelationIllusion(build,smt);
			case "Desengagement" :
				return new DesengagementBias(build,smt);
			case "Facilitation" :
				return new FacilitationBias(build,smt);
			case "Loss" :
				return new LossAversion(build,smt);
			case "Optimism" :
				return new OptimismeBias(build,smt);
			case "Overconfidence" :
				return new Overconfidence(build,smt);
			case "Risk" :
				return new RiskAversion(build,smt);
			case "Truth" :
				return new TruthIllusion(build,smt);
			case "Wishful" :
				return new WishfulThinking(build,smt);
			default :
				return null;
			}

	}


}
