// Generated from GrammarAgent.g4 by ANTLR 4.4
package sherlock.framework.parser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarAgentParser}.
 */
public interface GrammarAgentListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#allFacts}.
	 * @param ctx the parse tree
	 */
	void enterAllFacts(@NotNull GrammarAgentParser.AllFactsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#allFacts}.
	 * @param ctx the parse tree
	 */
	void exitAllFacts(@NotNull GrammarAgentParser.AllFactsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#allLimits}.
	 * @param ctx the parse tree
	 */
	void enterAllLimits(@NotNull GrammarAgentParser.AllLimitsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#allLimits}.
	 * @param ctx the parse tree
	 */
	void exitAllLimits(@NotNull GrammarAgentParser.AllLimitsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#defRules}.
	 * @param ctx the parse tree
	 */
	void enterDefRules(@NotNull GrammarAgentParser.DefRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#defRules}.
	 * @param ctx the parse tree
	 */
	void exitDefRules(@NotNull GrammarAgentParser.DefRulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull GrammarAgentParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull GrammarAgentParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(@NotNull GrammarAgentParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(@NotNull GrammarAgentParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#allPRules}.
	 * @param ctx the parse tree
	 */
	void enterAllPRules(@NotNull GrammarAgentParser.AllPRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#allPRules}.
	 * @param ctx the parse tree
	 */
	void exitAllPRules(@NotNull GrammarAgentParser.AllPRulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#signature}.
	 * @param ctx the parse tree
	 */
	void enterSignature(@NotNull GrammarAgentParser.SignatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#signature}.
	 * @param ctx the parse tree
	 */
	void exitSignature(@NotNull GrammarAgentParser.SignatureContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#defLimits}.
	 * @param ctx the parse tree
	 */
	void enterDefLimits(@NotNull GrammarAgentParser.DefLimitsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#defLimits}.
	 * @param ctx the parse tree
	 */
	void exitDefLimits(@NotNull GrammarAgentParser.DefLimitsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#definitionFact}.
	 * @param ctx the parse tree
	 */
	void enterDefinitionFact(@NotNull GrammarAgentParser.DefinitionFactContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#definitionFact}.
	 * @param ctx the parse tree
	 */
	void exitDefinitionFact(@NotNull GrammarAgentParser.DefinitionFactContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#runtimeDef}.
	 * @param ctx the parse tree
	 */
	void enterRuntimeDef(@NotNull GrammarAgentParser.RuntimeDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#runtimeDef}.
	 * @param ctx the parse tree
	 */
	void exitRuntimeDef(@NotNull GrammarAgentParser.RuntimeDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#defInstancePred}.
	 * @param ctx the parse tree
	 */
	void enterDefInstancePred(@NotNull GrammarAgentParser.DefInstancePredContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#defInstancePred}.
	 * @param ctx the parse tree
	 */
	void exitDefInstancePred(@NotNull GrammarAgentParser.DefInstancePredContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#allStrongRules}.
	 * @param ctx the parse tree
	 */
	void enterAllStrongRules(@NotNull GrammarAgentParser.AllStrongRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#allStrongRules}.
	 * @param ctx the parse tree
	 */
	void exitAllStrongRules(@NotNull GrammarAgentParser.AllStrongRulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(@NotNull GrammarAgentParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(@NotNull GrammarAgentParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#allRuntime}.
	 * @param ctx the parse tree
	 */
	void enterAllRuntime(@NotNull GrammarAgentParser.AllRuntimeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#allRuntime}.
	 * @param ctx the parse tree
	 */
	void exitAllRuntime(@NotNull GrammarAgentParser.AllRuntimeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#logicOperator}.
	 * @param ctx the parse tree
	 */
	void enterLogicOperator(@NotNull GrammarAgentParser.LogicOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#logicOperator}.
	 * @param ctx the parse tree
	 */
	void exitLogicOperator(@NotNull GrammarAgentParser.LogicOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(@NotNull GrammarAgentParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(@NotNull GrammarAgentParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#allRules}.
	 * @param ctx the parse tree
	 */
	void enterAllRules(@NotNull GrammarAgentParser.AllRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#allRules}.
	 * @param ctx the parse tree
	 */
	void exitAllRules(@NotNull GrammarAgentParser.AllRulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#allDesires}.
	 * @param ctx the parse tree
	 */
	void enterAllDesires(@NotNull GrammarAgentParser.AllDesiresContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#allDesires}.
	 * @param ctx the parse tree
	 */
	void exitAllDesires(@NotNull GrammarAgentParser.AllDesiresContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#allWeakRules}.
	 * @param ctx the parse tree
	 */
	void enterAllWeakRules(@NotNull GrammarAgentParser.AllWeakRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#allWeakRules}.
	 * @param ctx the parse tree
	 */
	void exitAllWeakRules(@NotNull GrammarAgentParser.AllWeakRulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#allActions}.
	 * @param ctx the parse tree
	 */
	void enterAllActions(@NotNull GrammarAgentParser.AllActionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#allActions}.
	 * @param ctx the parse tree
	 */
	void exitAllActions(@NotNull GrammarAgentParser.AllActionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#defObservation}.
	 * @param ctx the parse tree
	 */
	void enterDefObservation(@NotNull GrammarAgentParser.DefObservationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#defObservation}.
	 * @param ctx the parse tree
	 */
	void exitDefObservation(@NotNull GrammarAgentParser.DefObservationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#allInitBeliefs}.
	 * @param ctx the parse tree
	 */
	void enterAllInitBeliefs(@NotNull GrammarAgentParser.AllInitBeliefsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#allInitBeliefs}.
	 * @param ctx the parse tree
	 */
	void exitAllInitBeliefs(@NotNull GrammarAgentParser.AllInitBeliefsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#predicat}.
	 * @param ctx the parse tree
	 */
	void enterPredicat(@NotNull GrammarAgentParser.PredicatContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#predicat}.
	 * @param ctx the parse tree
	 */
	void exitPredicat(@NotNull GrammarAgentParser.PredicatContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarAgentParser#allObservations}.
	 * @param ctx the parse tree
	 */
	void enterAllObservations(@NotNull GrammarAgentParser.AllObservationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarAgentParser#allObservations}.
	 * @param ctx the parse tree
	 */
	void exitAllObservations(@NotNull GrammarAgentParser.AllObservationsContext ctx);
}