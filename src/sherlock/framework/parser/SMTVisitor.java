package sherlock.framework.parser;
// Generated from GrammarAgent.g4 by ANTLR 4.8
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import sherlock.framework.Proposition;
import sherlock.framework.PropositionSet;
import sherlock.framework.SetTime;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarAgentParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class SMTVisitor<T> implements GrammarAgentVisitor<T>{


	//public LinkedHashMap<String,PropositionFactory> allProposition;
	
	private HashMap<String,Factory> allProp;
	private RulesFactory currentRFactory;
	private ArrayList<String> maxargs;
	public PropositionSet beliefs;
	public PropositionSet coherence;
	public PropositionSet rules;
	public ArrayList<String> actions;
	public SetTime observations;
	public SetTime scenario;
	public int limitArgs;
	public ArrayList<Proposition> desires;
	
	public static char RULEID = 'R';
	
	public SMTVisitor() {
		super();
		//this.allProposition = new LinkedHashMap<>();
		this.allProp = new HashMap<>();
		this.maxargs = new ArrayList<>();
		this.beliefs = new PropositionSet();
		this.actions = new ArrayList<>();
		this.limitArgs = 0;
		this.currentRFactory = null;
		this.desires = new ArrayList<>();
		this.rules = new PropositionSet();
		this.coherence = new PropositionSet();
		
	}

	@Override
	public T visit(ParseTree arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitChildren(RuleNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitErrorNode(ErrorNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitTerminal(TerminalNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitParse(GrammarAgentParser.ParseContext ctx) {
		// TODO Auto-generated method stub
		ctx.allLimits().accept(this);
		ctx.allFacts().accept(this);
		ctx.allActions().accept(this);
		ctx.allPRules().accept(this);
		ctx.allRules().accept(this);
		ctx.allDesires().accept(this);
		ctx.allInitBeliefs().accept(this);
		ctx.allObservations().accept(this);
		ctx.allRuntime().accept(this);
		return null;
	}

	@Override
	public T visitExpression(GrammarAgentParser.ExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitLogicOperator(GrammarAgentParser.LogicOperatorContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitBool(GrammarAgentParser.BoolContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitPredicat(GrammarAgentParser.PredicatContext ctx) { // On ne rentre ici que pour les prédicats instancier
		// TODO Auto-generated method stub
		
		return null;
	}

	

	@Override
	public T visitSignature(GrammarAgentParser.SignatureContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitOperator(GrammarAgentParser.OperatorContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitAllFacts(GrammarAgentParser.AllFactsContext ctx) {
		// TODO Auto-generated method stub
		for(GrammarAgentParser.DefinitionFactContext fact : ctx.definitionFact()) {
			fact.accept(this);
		}
		
		return null;
	}

	@Override
	public T visitDefinitionFact(GrammarAgentParser.DefinitionFactContext ctx) {
		// TODO Auto-generated method stub
		String name = ctx.ID().getText();
		if(ctx.parent instanceof GrammarAgentParser.AllActionsContext) {
			this.actions.add(name);
		}
		PropFactory current = new PropFactory(name);
		//PropositionFactory current = new PropositionFactory(name,false);
		//allProposition.put(name, current);
		allProp.put(name,current);
		//ctx.signatureDef().accept(this);
		return null;
	}


	
	@Override
	public T visitAllPRules(GrammarAgentParser.AllPRulesContext ctx) {
		int i = 1;
		for(GrammarAgentParser.ExpressionContext rule : ctx.expression()) {
			String name = "C"+i;
			RulesFactory current = new RulesFactory(name);
			current.setIsRule(true);
			current.setCoherence(true);
			currentRFactory = current;
			for(int j=0;j<=this.limitArgs;j++) {
				Proposition c = new Proposition(name+"_"+j,true);
				this.coherence.addProposition(c);
			}
			allProp.put(name, current);
			//current.addPred(new PredicatToSMT(name));
			this.getExprSignature(rule, false);
			i++;
		}
		return null;
	}
	

	@Override
	public T visitAllActions(GrammarAgentParser.AllActionsContext ctx) {
		// TODO Auto-generated method stub
		for(GrammarAgentParser.DefinitionFactContext fact : ctx.definitionFact()) {
			fact.accept(this);
		}
		return null;
	}

	@Override
	public T visitAllRules(GrammarAgentParser.AllRulesContext ctx) {
		int index = this.createRule(ctx.allStrongRules().defRules(), true, 1);
		this.createRule(ctx.allWeakRules().defRules(), false, index);
		return null;
	}
	
	public int createRule(List<GrammarAgentParser.DefRulesContext> listRules,boolean isStrong,int index) {
		int i = index;
		for(GrammarAgentParser.DefRulesContext belief : listRules) {
			String name = Character.toString(RULEID)+i;
			RulesFactory current = new RulesFactory(name);
			current.setIsRule(true);
			current.setStrong(isStrong);
			currentRFactory = current;
			for(int j=0;j<=this.limitArgs;j++) {
				Proposition r = new Proposition(name+"_"+j,true);
				if(isStrong)
					r.isRuleStrong = true;
				else
					r.isRuleWeak = true;
				this.rules.addProposition(r);
			}
			
			allProp.put(current.name, current);
			this.getExprSignature(belief.expression(), false);
			i++;
		}
		return i;
	}
	@Override
	public T visitAllStrongRules(GrammarAgentParser.AllStrongRulesContext ctx) 
	{
		return null;
	}
	
	

	public T visitAllWeakRules(GrammarAgentParser.AllWeakRulesContext ctx) 
	{
		return null;
	}
	
	public void getExprSignature(GrammarAgentParser.ExpressionContext expr,boolean causal)
	{
		if(expr.NOT()!=null) {
			currentRFactory.addSign("(not ");
			this.getExprSignature(expr.notexp, causal);
			currentRFactory.addSign(")");
		}
		else if(expr.logicOperator()!=null) {
			if(expr.logicOperator().AND()!=null)
				currentRFactory.addSign("(and ");
			if(expr.logicOperator().OR()!=null)
				currentRFactory.addSign("(or ");
			if(expr.logicOperator().IMPLIES()!=null)
				currentRFactory.addSign("(=> ");
			if(expr.logicOperator().EFFECT()!=null) {
				currentRFactory.addSign("(=> ");
			}
			causal = true;
			this.getExprSignature(expr.left, causal);
			this.getExprSignature(expr.right, causal);
			currentRFactory.addSign(")");
		}
		else if(expr.bool()!=null) {
			if(expr.bool().TRUE()!=null)
				currentRFactory.addSign(" true ");
			else
				currentRFactory.addSign(" false ");
		}
		else if(expr.LPRE()!=null) {
			currentRFactory.addSign("(=> % ");
			this.generatePred(expr.predicat());
			this.getExprSignature(expr.prec, true);
			currentRFactory.addSign(" )");
			currentRFactory.setPremisse(expr.predicat().ID().getText());
		}
		else if(expr.predicat()!=null) {
			currentRFactory.addSign(" % ");
			this.generatePred(expr.predicat());
			
		}
		else if(expr.LParen()!=null) {
			this.getExprSignature(expr.parenexp, causal);
		}
		
	}
	
	
	public void generatePred(GrammarAgentParser.PredicatContext predicat)
	{
		Factory pred = allProp.get(predicat.ID().getText());
		
		currentRFactory.addProp(pred);
		if(predicat.signature().operator()!=null) {
			if(predicat.signature().operator().ADD()!=null) {
				currentRFactory.addTimeProp(Integer.parseInt(predicat.signature().NUMBER().getText()));
			}
			else {
				currentRFactory.addTimeProp(-Integer.parseInt(predicat.signature().NUMBER().getText()));
			}
		}
		else {
			currentRFactory.addTimeProp(0);
		}
	}
	

	private boolean isAction(String action)
	{
		return this.actions.contains(action);
	}
	

	@Override
	public T visitDefRules(GrammarAgentParser.DefRulesContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitAllDesires(GrammarAgentParser.AllDesiresContext ctx) {
		// TODO Auto-generated method stub
		for(GrammarAgentParser.DefInstancePredContext desire : ctx.defInstancePred()) {
			boolean value = true;
			if(desire.NOT()!=null) {
				value = false;
			}
			desires.add(new Proposition(desire.predicat().ID().getText(),value));
		}
		return null;
	}

	@Override
	public T visitAllObservations(GrammarAgentParser.AllObservationsContext ctx) {
		// TODO Auto-generated method stub
		for(GrammarAgentParser.DefObservationContext observation : ctx.defObservation()) {
			observation.accept(this);
		}
		return null;
	}

	@Override
	public T visitDefObservation(GrammarAgentParser.DefObservationContext ctx) {
		// TODO Auto-generated method stub
		for(GrammarAgentParser.DefInstancePredContext predicat : ctx.defInstancePred()) {
			String pred = this.getPredicatInstance(predicat.predicat());
			int time = Integer.parseInt(ctx.NUMBER().getText());
			if(predicat.NOT() != null) 
				this.observations.addProposition(new Proposition(pred,false),time);
			else
				this.observations.addProposition(new Proposition(pred,true),time);
		}
		return null;
	}

	@Override
	public T visitAllInitBeliefs(GrammarAgentParser.AllInitBeliefsContext ctx) {
		// TODO Auto-generated method stub
		for(GrammarAgentParser.DefInstancePredContext initBel : ctx.defInstancePred()) {
			String pred = this.getPredicatInstance(initBel.predicat());
			if(initBel.NOT() != null) 
				this.beliefs.addProposition(new Proposition(pred,false));
			else
				this.beliefs.addProposition(new Proposition(pred,true));
		}
		return null;
	}
	
	@Override
	public T visitDefInstancePred(GrammarAgentParser.DefInstancePredContext ctx) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public T visitAllRuntime(GrammarAgentParser.AllRuntimeContext ctx) {
		// TODO Auto-generated method stub
		for(GrammarAgentParser.RuntimeDefContext runtime : ctx.runtimeDef()) {
			runtime.accept(this);
		}
		return null;
	}

	@Override
	public T visitRuntimeDef(GrammarAgentParser.RuntimeDefContext ctx) {
		// TODO Auto-generated method stub
		int time = Integer.parseInt(ctx.NUMBER().getText());
		this.scenario.addProposition(new Proposition(this.getPredicatInstance(ctx.predicat()),true), time);
		return null;
	}
	
	private String getPredicatInstance(GrammarAgentParser.PredicatContext ctx) {
		String pred = ctx.ID().getText();
		pred+="_"+ctx.signature().NUMBER().getText();
		return pred;
	}

	@Override
	public T visitAllLimits(GrammarAgentParser.AllLimitsContext ctx) {
		// TODO Auto-generated method stub
		ctx.defLimits().accept(this);
		this.observations = new SetTime(this.limitArgs); //TODO change t
		this.scenario = new SetTime(this.limitArgs); //TODO change t
		return null;
	}

	@Override
	public T visitDefLimits(GrammarAgentParser.DefLimitsContext ctx) {
		// TODO Auto-generated method stub
		this.limitArgs = Integer.parseInt(ctx.NUMBER().getText());
		return null;
	}
	
	public ArrayList<Factory> getFactories()
	{
		return new ArrayList<Factory>(this.allProp.values());
	}

	
	
	

	


}