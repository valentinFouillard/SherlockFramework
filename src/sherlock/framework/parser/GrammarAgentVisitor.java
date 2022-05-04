// Generated from GrammarAgent.g4 by ANTLR 4.4
package sherlock.framework.parser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarAgentParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarAgentVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#allFacts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllFacts(@NotNull GrammarAgentParser.AllFactsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#allLimits}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllLimits(@NotNull GrammarAgentParser.AllLimitsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#defRules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefRules(@NotNull GrammarAgentParser.DefRulesContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(@NotNull GrammarAgentParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(@NotNull GrammarAgentParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#allPRules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllPRules(@NotNull GrammarAgentParser.AllPRulesContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#signature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignature(@NotNull GrammarAgentParser.SignatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#defLimits}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefLimits(@NotNull GrammarAgentParser.DefLimitsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#definitionFact}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinitionFact(@NotNull GrammarAgentParser.DefinitionFactContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#runtimeDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuntimeDef(@NotNull GrammarAgentParser.RuntimeDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#defInstancePred}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefInstancePred(@NotNull GrammarAgentParser.DefInstancePredContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#allStrongRules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllStrongRules(@NotNull GrammarAgentParser.AllStrongRulesContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(@NotNull GrammarAgentParser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#allRuntime}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllRuntime(@NotNull GrammarAgentParser.AllRuntimeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#logicOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicOperator(@NotNull GrammarAgentParser.LogicOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(@NotNull GrammarAgentParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#allRules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllRules(@NotNull GrammarAgentParser.AllRulesContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#allDesires}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllDesires(@NotNull GrammarAgentParser.AllDesiresContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#allWeakRules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllWeakRules(@NotNull GrammarAgentParser.AllWeakRulesContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#allActions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllActions(@NotNull GrammarAgentParser.AllActionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#defObservation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefObservation(@NotNull GrammarAgentParser.DefObservationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#allInitBeliefs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllInitBeliefs(@NotNull GrammarAgentParser.AllInitBeliefsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#predicat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicat(@NotNull GrammarAgentParser.PredicatContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarAgentParser#allObservations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllObservations(@NotNull GrammarAgentParser.AllObservationsContext ctx);
}