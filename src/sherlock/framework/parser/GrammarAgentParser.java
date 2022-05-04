// Generated from GrammarAgent.g4 by ANTLR 4.4
package sherlock.framework.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarAgentParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__1=1, T__0=2, AND=3, OR=4, IMPLIES=5, EQUALS=6, CAUSAL=7, TRUE=8, FALSE=9, 
		NOT=10, COMMA=11, ADD=12, MINUS=13, LParen=14, RParent=15, LPRE=16, RPRE=17, 
		TIME=18, DEF=19, X=20, NAT=21, FACTSSET=22, DFACTSSET=23, PRULES=24, ACTIONS=25, 
		PACTIONSRULES=26, AGENT=27, RULES=28, STRONGRULES=29, WEAKRULES=30, DESIRES=31, 
		OBSERVATIONS=32, INITBELIEFS=33, RUNTIME=34, LIMITS=35, WORLD=36, SCENARIO=37, 
		ID=38, NUMBER=39, BREAKLINE=40, WS=41;
	public static final String[] tokenNames = {
		"<INVALID>", "':'", "'==='", "'and'", "'or'", "'=>'", "'='", "'::>'", 
		"'true'", "'false'", "'not'", "','", "'+'", "'-'", "'('", "')'", "'['", 
		"']'", "'t'", "'::'", "'x'", "'nat'", "'Facts'", "'Derived Facts'", "'PermanentRules'", 
		"'Actions'", "'PermanentActionRules'", "'===Agent==='", "'Rules'", "'Strong'", 
		"'Weak'", "'Desires'", "'Observations'", "'InitialWorldBeliefs'", "'Runtime'", 
		"'Limits'", "'===World==='", "'===Scenario==='", "ID", "NUMBER", "BREAKLINE", 
		"WS"
	};
	public static final int
		RULE_parse = 0, RULE_expression = 1, RULE_logicOperator = 2, RULE_bool = 3, 
		RULE_predicat = 4, RULE_signature = 5, RULE_operator = 6, RULE_allFacts = 7, 
		RULE_definitionFact = 8, RULE_allPRules = 9, RULE_allActions = 10, RULE_allRules = 11, 
		RULE_allStrongRules = 12, RULE_allWeakRules = 13, RULE_defRules = 14, 
		RULE_allDesires = 15, RULE_allObservations = 16, RULE_defObservation = 17, 
		RULE_allInitBeliefs = 18, RULE_defInstancePred = 19, RULE_allRuntime = 20, 
		RULE_runtimeDef = 21, RULE_allLimits = 22, RULE_defLimits = 23;
	public static final String[] ruleNames = {
		"parse", "expression", "logicOperator", "bool", "predicat", "signature", 
		"operator", "allFacts", "definitionFact", "allPRules", "allActions", "allRules", 
		"allStrongRules", "allWeakRules", "defRules", "allDesires", "allObservations", 
		"defObservation", "allInitBeliefs", "defInstancePred", "allRuntime", "runtimeDef", 
		"allLimits", "defLimits"
	};

	@Override
	public String getGrammarFileName() { return "GrammarAgent.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammarAgentParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ParseContext extends ParserRuleContext {
		public AllInitBeliefsContext allInitBeliefs() {
			return getRuleContext(AllInitBeliefsContext.class,0);
		}
		public AllObservationsContext allObservations() {
			return getRuleContext(AllObservationsContext.class,0);
		}
		public AllFactsContext allFacts() {
			return getRuleContext(AllFactsContext.class,0);
		}
		public AllDesiresContext allDesires() {
			return getRuleContext(AllDesiresContext.class,0);
		}
		public AllLimitsContext allLimits() {
			return getRuleContext(AllLimitsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(GrammarAgentParser.EOF, 0); }
		public AllPRulesContext allPRules() {
			return getRuleContext(AllPRulesContext.class,0);
		}
		public AllRuntimeContext allRuntime() {
			return getRuleContext(AllRuntimeContext.class,0);
		}
		public AllActionsContext allActions() {
			return getRuleContext(AllActionsContext.class,0);
		}
		public AllRulesContext allRules() {
			return getRuleContext(AllRulesContext.class,0);
		}
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitParse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitParse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48); allLimits();
			setState(49); allFacts();
			setState(50); allActions();
			setState(51); allPRules();
			setState(52); allRules();
			setState(53); allDesires();
			setState(54); allInitBeliefs();
			setState(55); allObservations();
			setState(56); allRuntime();
			setState(57); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext left;
		public ExpressionContext notexp;
		public ExpressionContext parenexp;
		public ExpressionContext prec;
		public ExpressionContext right;
		public TerminalNode NOT() { return getToken(GrammarAgentParser.NOT, 0); }
		public TerminalNode RParent() { return getToken(GrammarAgentParser.RParent, 0); }
		public LogicOperatorContext logicOperator() {
			return getRuleContext(LogicOperatorContext.class,0);
		}
		public PredicatContext predicat() {
			return getRuleContext(PredicatContext.class,0);
		}
		public TerminalNode LParen() { return getToken(GrammarAgentParser.LParen, 0); }
		public TerminalNode LPRE() { return getToken(GrammarAgentParser.LPRE, 0); }
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode RPRE() { return getToken(GrammarAgentParser.RPRE, 0); }
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			switch (_input.LA(1)) {
			case NOT:
				{
				setState(60); match(NOT);
				setState(61); ((ExpressionContext)_localctx).notexp = expression(5);
				}
				break;
			case LParen:
				{
				setState(62); match(LParen);
				setState(63); ((ExpressionContext)_localctx).parenexp = expression(0);
				setState(64); match(RParent);
				}
				break;
			case LPRE:
				{
				setState(66); match(LPRE);
				setState(67); ((ExpressionContext)_localctx).prec = expression(0);
				setState(68); match(RPRE);
				setState(69); predicat();
				}
				break;
			case TRUE:
			case FALSE:
				{
				setState(71); bool();
				}
				break;
			case ID:
				{
				setState(72); predicat();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(81);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpressionContext(_parentctx, _parentState);
					_localctx.left = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(75);
					if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
					setState(76); logicOperator();
					setState(77); ((ExpressionContext)_localctx).right = expression(5);
					}
					} 
				}
				setState(83);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LogicOperatorContext extends ParserRuleContext {
		public TerminalNode CAUSAL() { return getToken(GrammarAgentParser.CAUSAL, 0); }
		public TerminalNode IMPLIES() { return getToken(GrammarAgentParser.IMPLIES, 0); }
		public TerminalNode AND() { return getToken(GrammarAgentParser.AND, 0); }
		public TerminalNode OR() { return getToken(GrammarAgentParser.OR, 0); }
		public LogicOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterLogicOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitLogicOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitLogicOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicOperatorContext logicOperator() throws RecognitionException {
		LogicOperatorContext _localctx = new LogicOperatorContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_logicOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << IMPLIES) | (1L << CAUSAL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolContext extends ParserRuleContext {
		public TerminalNode FALSE() { return getToken(GrammarAgentParser.FALSE, 0); }
		public TerminalNode TRUE() { return getToken(GrammarAgentParser.TRUE, 0); }
		public BoolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitBool(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitBool(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolContext bool() throws RecognitionException {
		BoolContext _localctx = new BoolContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_bool);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicatContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(GrammarAgentParser.ID, 0); }
		public TerminalNode RParent() { return getToken(GrammarAgentParser.RParent, 0); }
		public TerminalNode LParen() { return getToken(GrammarAgentParser.LParen, 0); }
		public SignatureContext signature() {
			return getRuleContext(SignatureContext.class,0);
		}
		public PredicatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterPredicat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitPredicat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitPredicat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicatContext predicat() throws RecognitionException {
		PredicatContext _localctx = new PredicatContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_predicat);
		try {
			setState(94);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(88); match(ID);
				setState(89); match(LParen);
				setState(90); signature();
				setState(91); match(RParent);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(93); match(ID);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SignatureContext extends ParserRuleContext {
		public TerminalNode TIME() { return getToken(GrammarAgentParser.TIME, 0); }
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public TerminalNode NUMBER() { return getToken(GrammarAgentParser.NUMBER, 0); }
		public SignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterSignature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitSignature(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitSignature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SignatureContext signature() throws RecognitionException {
		SignatureContext _localctx = new SignatureContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_signature);
		try {
			setState(102);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(96); match(TIME);
				setState(97); operator();
				setState(98); match(NUMBER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(100); match(TIME);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(101); match(NUMBER);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(GrammarAgentParser.ADD, 0); }
		public TerminalNode MINUS() { return getToken(GrammarAgentParser.MINUS, 0); }
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			_la = _input.LA(1);
			if ( !(_la==ADD || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllFactsContext extends ParserRuleContext {
		public TerminalNode FACTSSET() { return getToken(GrammarAgentParser.FACTSSET, 0); }
		public DefinitionFactContext definitionFact(int i) {
			return getRuleContext(DefinitionFactContext.class,i);
		}
		public TerminalNode BREAKLINE() { return getToken(GrammarAgentParser.BREAKLINE, 0); }
		public List<DefinitionFactContext> definitionFact() {
			return getRuleContexts(DefinitionFactContext.class);
		}
		public AllFactsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allFacts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterAllFacts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitAllFacts(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitAllFacts(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllFactsContext allFacts() throws RecognitionException {
		AllFactsContext _localctx = new AllFactsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_allFacts);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106); match(FACTSSET);
			setState(107); match(T__0);
			setState(108); match(BREAKLINE);
			setState(110); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(109); definitionFact();
				}
				}
				setState(112); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(114); match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefinitionFactContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(GrammarAgentParser.ID, 0); }
		public TerminalNode BREAKLINE() { return getToken(GrammarAgentParser.BREAKLINE, 0); }
		public DefinitionFactContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definitionFact; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterDefinitionFact(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitDefinitionFact(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitDefinitionFact(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefinitionFactContext definitionFact() throws RecognitionException {
		DefinitionFactContext _localctx = new DefinitionFactContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_definitionFact);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116); match(ID);
			setState(117); match(BREAKLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllPRulesContext extends ParserRuleContext {
		public List<TerminalNode> BREAKLINE() { return getTokens(GrammarAgentParser.BREAKLINE); }
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode PRULES() { return getToken(GrammarAgentParser.PRULES, 0); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(GrammarAgentParser.BREAKLINE, i);
		}
		public AllPRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allPRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterAllPRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitAllPRules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitAllPRules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllPRulesContext allPRules() throws RecognitionException {
		AllPRulesContext _localctx = new AllPRulesContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_allPRules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119); match(PRULES);
			setState(120); match(T__0);
			setState(121); match(BREAKLINE);
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << NOT) | (1L << LParen) | (1L << LPRE) | (1L << ID))) != 0)) {
				{
				{
				setState(122); expression(0);
				setState(123); match(BREAKLINE);
				}
				}
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(130); match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllActionsContext extends ParserRuleContext {
		public DefinitionFactContext definitionFact(int i) {
			return getRuleContext(DefinitionFactContext.class,i);
		}
		public TerminalNode ACTIONS() { return getToken(GrammarAgentParser.ACTIONS, 0); }
		public TerminalNode BREAKLINE() { return getToken(GrammarAgentParser.BREAKLINE, 0); }
		public List<DefinitionFactContext> definitionFact() {
			return getRuleContexts(DefinitionFactContext.class);
		}
		public AllActionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allActions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterAllActions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitAllActions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitAllActions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllActionsContext allActions() throws RecognitionException {
		AllActionsContext _localctx = new AllActionsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_allActions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132); match(ACTIONS);
			setState(133); match(T__0);
			setState(134); match(BREAKLINE);
			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(135); definitionFact();
				}
				}
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(141); match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllRulesContext extends ParserRuleContext {
		public TerminalNode RULES() { return getToken(GrammarAgentParser.RULES, 0); }
		public AllStrongRulesContext allStrongRules() {
			return getRuleContext(AllStrongRulesContext.class,0);
		}
		public AllWeakRulesContext allWeakRules() {
			return getRuleContext(AllWeakRulesContext.class,0);
		}
		public TerminalNode BREAKLINE() { return getToken(GrammarAgentParser.BREAKLINE, 0); }
		public AllRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterAllRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitAllRules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitAllRules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllRulesContext allRules() throws RecognitionException {
		AllRulesContext _localctx = new AllRulesContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_allRules);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143); match(RULES);
			setState(144); match(T__0);
			setState(145); match(BREAKLINE);
			setState(146); match(T__0);
			setState(147); allStrongRules();
			setState(148); allWeakRules();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllStrongRulesContext extends ParserRuleContext {
		public DefRulesContext defRules(int i) {
			return getRuleContext(DefRulesContext.class,i);
		}
		public List<DefRulesContext> defRules() {
			return getRuleContexts(DefRulesContext.class);
		}
		public TerminalNode BREAKLINE() { return getToken(GrammarAgentParser.BREAKLINE, 0); }
		public TerminalNode STRONGRULES() { return getToken(GrammarAgentParser.STRONGRULES, 0); }
		public AllStrongRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allStrongRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterAllStrongRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitAllStrongRules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitAllStrongRules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllStrongRulesContext allStrongRules() throws RecognitionException {
		AllStrongRulesContext _localctx = new AllStrongRulesContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_allStrongRules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150); match(STRONGRULES);
			setState(151); match(T__0);
			setState(152); match(BREAKLINE);
			setState(154); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(153); defRules();
				}
				}
				setState(156); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << NOT) | (1L << LParen) | (1L << LPRE) | (1L << ID))) != 0) );
			setState(158); match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllWeakRulesContext extends ParserRuleContext {
		public DefRulesContext defRules(int i) {
			return getRuleContext(DefRulesContext.class,i);
		}
		public List<DefRulesContext> defRules() {
			return getRuleContexts(DefRulesContext.class);
		}
		public TerminalNode BREAKLINE() { return getToken(GrammarAgentParser.BREAKLINE, 0); }
		public TerminalNode WEAKRULES() { return getToken(GrammarAgentParser.WEAKRULES, 0); }
		public AllWeakRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allWeakRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterAllWeakRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitAllWeakRules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitAllWeakRules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllWeakRulesContext allWeakRules() throws RecognitionException {
		AllWeakRulesContext _localctx = new AllWeakRulesContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_allWeakRules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160); match(WEAKRULES);
			setState(161); match(T__0);
			setState(162); match(BREAKLINE);
			setState(166);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << NOT) | (1L << LParen) | (1L << LPRE) | (1L << ID))) != 0)) {
				{
				{
				setState(163); defRules();
				}
				}
				setState(168);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(169); match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefRulesContext extends ParserRuleContext {
		public TerminalNode BREAKLINE() { return getToken(GrammarAgentParser.BREAKLINE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DefRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterDefRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitDefRules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitDefRules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefRulesContext defRules() throws RecognitionException {
		DefRulesContext _localctx = new DefRulesContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_defRules);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171); expression(0);
			setState(172); match(BREAKLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllDesiresContext extends ParserRuleContext {
		public TerminalNode DESIRES() { return getToken(GrammarAgentParser.DESIRES, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(GrammarAgentParser.BREAKLINE); }
		public DefInstancePredContext defInstancePred(int i) {
			return getRuleContext(DefInstancePredContext.class,i);
		}
		public TerminalNode BREAKLINE(int i) {
			return getToken(GrammarAgentParser.BREAKLINE, i);
		}
		public List<DefInstancePredContext> defInstancePred() {
			return getRuleContexts(DefInstancePredContext.class);
		}
		public AllDesiresContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allDesires; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterAllDesires(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitAllDesires(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitAllDesires(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllDesiresContext allDesires() throws RecognitionException {
		AllDesiresContext _localctx = new AllDesiresContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_allDesires);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174); match(DESIRES);
			setState(175); match(T__0);
			setState(176); match(BREAKLINE);
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NOT || _la==ID) {
				{
				{
				setState(177); defInstancePred();
				setState(178); match(BREAKLINE);
				}
				}
				setState(184);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(185); match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllObservationsContext extends ParserRuleContext {
		public TerminalNode OBSERVATIONS() { return getToken(GrammarAgentParser.OBSERVATIONS, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(GrammarAgentParser.BREAKLINE); }
		public DefObservationContext defObservation(int i) {
			return getRuleContext(DefObservationContext.class,i);
		}
		public TerminalNode BREAKLINE(int i) {
			return getToken(GrammarAgentParser.BREAKLINE, i);
		}
		public List<DefObservationContext> defObservation() {
			return getRuleContexts(DefObservationContext.class);
		}
		public AllObservationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allObservations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterAllObservations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitAllObservations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitAllObservations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllObservationsContext allObservations() throws RecognitionException {
		AllObservationsContext _localctx = new AllObservationsContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_allObservations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187); match(OBSERVATIONS);
			setState(188); match(T__0);
			setState(189); match(BREAKLINE);
			setState(193); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(190); defObservation();
				setState(191); match(BREAKLINE);
				}
				}
				setState(195); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NUMBER );
			setState(197); match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefObservationContext extends ParserRuleContext {
		public List<TerminalNode> COMMA() { return getTokens(GrammarAgentParser.COMMA); }
		public TerminalNode NUMBER() { return getToken(GrammarAgentParser.NUMBER, 0); }
		public DefInstancePredContext defInstancePred(int i) {
			return getRuleContext(DefInstancePredContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(GrammarAgentParser.COMMA, i);
		}
		public List<DefInstancePredContext> defInstancePred() {
			return getRuleContexts(DefInstancePredContext.class);
		}
		public DefObservationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defObservation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterDefObservation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitDefObservation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitDefObservation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefObservationContext defObservation() throws RecognitionException {
		DefObservationContext _localctx = new DefObservationContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_defObservation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199); match(NUMBER);
			setState(200); match(T__1);
			setState(205); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(205);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(201); defInstancePred();
					setState(202); match(COMMA);
					}
					break;
				case 2:
					{
					setState(204); defInstancePred();
					}
					break;
				}
				}
				setState(207); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NOT || _la==ID );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllInitBeliefsContext extends ParserRuleContext {
		public TerminalNode INITBELIEFS() { return getToken(GrammarAgentParser.INITBELIEFS, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(GrammarAgentParser.BREAKLINE); }
		public DefInstancePredContext defInstancePred(int i) {
			return getRuleContext(DefInstancePredContext.class,i);
		}
		public TerminalNode BREAKLINE(int i) {
			return getToken(GrammarAgentParser.BREAKLINE, i);
		}
		public List<DefInstancePredContext> defInstancePred() {
			return getRuleContexts(DefInstancePredContext.class);
		}
		public AllInitBeliefsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allInitBeliefs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterAllInitBeliefs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitAllInitBeliefs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitAllInitBeliefs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllInitBeliefsContext allInitBeliefs() throws RecognitionException {
		AllInitBeliefsContext _localctx = new AllInitBeliefsContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_allInitBeliefs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209); match(INITBELIEFS);
			setState(210); match(T__0);
			setState(211); match(BREAKLINE);
			setState(217);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NOT || _la==ID) {
				{
				{
				setState(212); defInstancePred();
				setState(213); match(BREAKLINE);
				}
				}
				setState(219);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(220); match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefInstancePredContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(GrammarAgentParser.NOT, 0); }
		public PredicatContext predicat() {
			return getRuleContext(PredicatContext.class,0);
		}
		public DefInstancePredContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defInstancePred; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterDefInstancePred(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitDefInstancePred(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitDefInstancePred(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefInstancePredContext defInstancePred() throws RecognitionException {
		DefInstancePredContext _localctx = new DefInstancePredContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_defInstancePred);
		try {
			setState(225);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(222); predicat();
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(223); match(NOT);
				setState(224); predicat();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllRuntimeContext extends ParserRuleContext {
		public List<RuntimeDefContext> runtimeDef() {
			return getRuleContexts(RuntimeDefContext.class);
		}
		public List<TerminalNode> BREAKLINE() { return getTokens(GrammarAgentParser.BREAKLINE); }
		public TerminalNode RUNTIME() { return getToken(GrammarAgentParser.RUNTIME, 0); }
		public TerminalNode BREAKLINE(int i) {
			return getToken(GrammarAgentParser.BREAKLINE, i);
		}
		public RuntimeDefContext runtimeDef(int i) {
			return getRuleContext(RuntimeDefContext.class,i);
		}
		public AllRuntimeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allRuntime; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterAllRuntime(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitAllRuntime(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitAllRuntime(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllRuntimeContext allRuntime() throws RecognitionException {
		AllRuntimeContext _localctx = new AllRuntimeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_allRuntime);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227); match(RUNTIME);
			setState(228); match(T__0);
			setState(229); match(BREAKLINE);
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NUMBER) {
				{
				{
				setState(230); runtimeDef();
				setState(231); match(BREAKLINE);
				}
				}
				setState(237);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuntimeDefContext extends ParserRuleContext {
		public PredicatContext predicat() {
			return getRuleContext(PredicatContext.class,0);
		}
		public TerminalNode NUMBER() { return getToken(GrammarAgentParser.NUMBER, 0); }
		public RuntimeDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_runtimeDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterRuntimeDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitRuntimeDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitRuntimeDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuntimeDefContext runtimeDef() throws RecognitionException {
		RuntimeDefContext _localctx = new RuntimeDefContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_runtimeDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238); match(NUMBER);
			setState(239); match(T__1);
			setState(240); predicat();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllLimitsContext extends ParserRuleContext {
		public TerminalNode LIMITS() { return getToken(GrammarAgentParser.LIMITS, 0); }
		public List<TerminalNode> BREAKLINE() { return getTokens(GrammarAgentParser.BREAKLINE); }
		public DefLimitsContext defLimits() {
			return getRuleContext(DefLimitsContext.class,0);
		}
		public TerminalNode BREAKLINE(int i) {
			return getToken(GrammarAgentParser.BREAKLINE, i);
		}
		public AllLimitsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allLimits; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterAllLimits(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitAllLimits(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitAllLimits(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllLimitsContext allLimits() throws RecognitionException {
		AllLimitsContext _localctx = new AllLimitsContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_allLimits);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242); match(T__0);
			setState(243); match(LIMITS);
			setState(244); match(T__0);
			setState(245); match(BREAKLINE);
			setState(246); defLimits();
			setState(247); match(BREAKLINE);
			setState(248); match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefLimitsContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(GrammarAgentParser.EQUALS, 0); }
		public TerminalNode TIME() { return getToken(GrammarAgentParser.TIME, 0); }
		public TerminalNode NUMBER() { return getToken(GrammarAgentParser.NUMBER, 0); }
		public DefLimitsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defLimits; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).enterDefLimits(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarAgentListener ) ((GrammarAgentListener)listener).exitDefLimits(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarAgentVisitor ) return ((GrammarAgentVisitor<? extends T>)visitor).visitDefLimits(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefLimitsContext defLimits() throws RecognitionException {
		DefLimitsContext _localctx = new DefLimitsContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_defLimits);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250); match(TIME);
			setState(251); match(EQUALS);
			setState(252); match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1: return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3+\u0101\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3L\n\3\3\3\3\3\3\3\3\3\7\3R\n\3\f\3\16"+
		"\3U\13\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6a\n\6\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\5\7i\n\7\3\b\3\b\3\t\3\t\3\t\3\t\6\tq\n\t\r\t\16\tr\3\t\3"+
		"\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u0080\n\13\f\13\16\13"+
		"\u0083\13\13\3\13\3\13\3\f\3\f\3\f\3\f\7\f\u008b\n\f\f\f\16\f\u008e\13"+
		"\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\6\16\u009d"+
		"\n\16\r\16\16\16\u009e\3\16\3\16\3\17\3\17\3\17\3\17\7\17\u00a7\n\17\f"+
		"\17\16\17\u00aa\13\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\7\21\u00b7\n\21\f\21\16\21\u00ba\13\21\3\21\3\21\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\6\22\u00c4\n\22\r\22\16\22\u00c5\3\22\3\22\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\6\23\u00d0\n\23\r\23\16\23\u00d1\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\7\24\u00da\n\24\f\24\16\24\u00dd\13\24\3\24\3\24\3\25\3\25"+
		"\3\25\5\25\u00e4\n\25\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u00ec\n\26\f"+
		"\26\16\26\u00ef\13\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\31\3\31\3\31\3\31\3\31\2\3\4\32\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"$&(*,.\60\2\5\4\2\5\7\t\t\3\2\n\13\3\2\16\17\u00fc\2\62"+
		"\3\2\2\2\4K\3\2\2\2\6V\3\2\2\2\bX\3\2\2\2\n`\3\2\2\2\fh\3\2\2\2\16j\3"+
		"\2\2\2\20l\3\2\2\2\22v\3\2\2\2\24y\3\2\2\2\26\u0086\3\2\2\2\30\u0091\3"+
		"\2\2\2\32\u0098\3\2\2\2\34\u00a2\3\2\2\2\36\u00ad\3\2\2\2 \u00b0\3\2\2"+
		"\2\"\u00bd\3\2\2\2$\u00c9\3\2\2\2&\u00d3\3\2\2\2(\u00e3\3\2\2\2*\u00e5"+
		"\3\2\2\2,\u00f0\3\2\2\2.\u00f4\3\2\2\2\60\u00fc\3\2\2\2\62\63\5.\30\2"+
		"\63\64\5\20\t\2\64\65\5\26\f\2\65\66\5\24\13\2\66\67\5\30\r\2\678\5 \21"+
		"\289\5&\24\29:\5\"\22\2:;\5*\26\2;<\7\2\2\3<\3\3\2\2\2=>\b\3\1\2>?\7\f"+
		"\2\2?L\5\4\3\7@A\7\20\2\2AB\5\4\3\2BC\7\21\2\2CL\3\2\2\2DE\7\22\2\2EF"+
		"\5\4\3\2FG\7\23\2\2GH\5\n\6\2HL\3\2\2\2IL\5\b\5\2JL\5\n\6\2K=\3\2\2\2"+
		"K@\3\2\2\2KD\3\2\2\2KI\3\2\2\2KJ\3\2\2\2LS\3\2\2\2MN\f\6\2\2NO\5\6\4\2"+
		"OP\5\4\3\7PR\3\2\2\2QM\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2T\5\3\2\2"+
		"\2US\3\2\2\2VW\t\2\2\2W\7\3\2\2\2XY\t\3\2\2Y\t\3\2\2\2Z[\7(\2\2[\\\7\20"+
		"\2\2\\]\5\f\7\2]^\7\21\2\2^a\3\2\2\2_a\7(\2\2`Z\3\2\2\2`_\3\2\2\2a\13"+
		"\3\2\2\2bc\7\24\2\2cd\5\16\b\2de\7)\2\2ei\3\2\2\2fi\7\24\2\2gi\7)\2\2"+
		"hb\3\2\2\2hf\3\2\2\2hg\3\2\2\2i\r\3\2\2\2jk\t\4\2\2k\17\3\2\2\2lm\7\30"+
		"\2\2mn\7\4\2\2np\7*\2\2oq\5\22\n\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2"+
		"\2\2st\3\2\2\2tu\7\4\2\2u\21\3\2\2\2vw\7(\2\2wx\7*\2\2x\23\3\2\2\2yz\7"+
		"\32\2\2z{\7\4\2\2{\u0081\7*\2\2|}\5\4\3\2}~\7*\2\2~\u0080\3\2\2\2\177"+
		"|\3\2\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082"+
		"\u0084\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0085\7\4\2\2\u0085\25\3\2\2"+
		"\2\u0086\u0087\7\33\2\2\u0087\u0088\7\4\2\2\u0088\u008c\7*\2\2\u0089\u008b"+
		"\5\22\n\2\u008a\u0089\3\2\2\2\u008b\u008e\3\2\2\2\u008c\u008a\3\2\2\2"+
		"\u008c\u008d\3\2\2\2\u008d\u008f\3\2\2\2\u008e\u008c\3\2\2\2\u008f\u0090"+
		"\7\4\2\2\u0090\27\3\2\2\2\u0091\u0092\7\36\2\2\u0092\u0093\7\4\2\2\u0093"+
		"\u0094\7*\2\2\u0094\u0095\7\4\2\2\u0095\u0096\5\32\16\2\u0096\u0097\5"+
		"\34\17\2\u0097\31\3\2\2\2\u0098\u0099\7\37\2\2\u0099\u009a\7\4\2\2\u009a"+
		"\u009c\7*\2\2\u009b\u009d\5\36\20\2\u009c\u009b\3\2\2\2\u009d\u009e\3"+
		"\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0"+
		"\u00a1\7\4\2\2\u00a1\33\3\2\2\2\u00a2\u00a3\7 \2\2\u00a3\u00a4\7\4\2\2"+
		"\u00a4\u00a8\7*\2\2\u00a5\u00a7\5\36\20\2\u00a6\u00a5\3\2\2\2\u00a7\u00aa"+
		"\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00ab\3\2\2\2\u00aa"+
		"\u00a8\3\2\2\2\u00ab\u00ac\7\4\2\2\u00ac\35\3\2\2\2\u00ad\u00ae\5\4\3"+
		"\2\u00ae\u00af\7*\2\2\u00af\37\3\2\2\2\u00b0\u00b1\7!\2\2\u00b1\u00b2"+
		"\7\4\2\2\u00b2\u00b8\7*\2\2\u00b3\u00b4\5(\25\2\u00b4\u00b5\7*\2\2\u00b5"+
		"\u00b7\3\2\2\2\u00b6\u00b3\3\2\2\2\u00b7\u00ba\3\2\2\2\u00b8\u00b6\3\2"+
		"\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bb\3\2\2\2\u00ba\u00b8\3\2\2\2\u00bb"+
		"\u00bc\7\4\2\2\u00bc!\3\2\2\2\u00bd\u00be\7\"\2\2\u00be\u00bf\7\4\2\2"+
		"\u00bf\u00c3\7*\2\2\u00c0\u00c1\5$\23\2\u00c1\u00c2\7*\2\2\u00c2\u00c4"+
		"\3\2\2\2\u00c3\u00c0\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5"+
		"\u00c6\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\7\4\2\2\u00c8#\3\2\2\2"+
		"\u00c9\u00ca\7)\2\2\u00ca\u00cf\7\3\2\2\u00cb\u00cc\5(\25\2\u00cc\u00cd"+
		"\7\r\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00d0\5(\25\2\u00cf\u00cb\3\2\2\2\u00cf"+
		"\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2"+
		"\2\2\u00d2%\3\2\2\2\u00d3\u00d4\7#\2\2\u00d4\u00d5\7\4\2\2\u00d5\u00db"+
		"\7*\2\2\u00d6\u00d7\5(\25\2\u00d7\u00d8\7*\2\2\u00d8\u00da\3\2\2\2\u00d9"+
		"\u00d6\3\2\2\2\u00da\u00dd\3\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2"+
		"\2\2\u00dc\u00de\3\2\2\2\u00dd\u00db\3\2\2\2\u00de\u00df\7\4\2\2\u00df"+
		"\'\3\2\2\2\u00e0\u00e4\5\n\6\2\u00e1\u00e2\7\f\2\2\u00e2\u00e4\5\n\6\2"+
		"\u00e3\u00e0\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4)\3\2\2\2\u00e5\u00e6\7"+
		"$\2\2\u00e6\u00e7\7\4\2\2\u00e7\u00ed\7*\2\2\u00e8\u00e9\5,\27\2\u00e9"+
		"\u00ea\7*\2\2\u00ea\u00ec\3\2\2\2\u00eb\u00e8\3\2\2\2\u00ec\u00ef\3\2"+
		"\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee+\3\2\2\2\u00ef\u00ed"+
		"\3\2\2\2\u00f0\u00f1\7)\2\2\u00f1\u00f2\7\3\2\2\u00f2\u00f3\5\n\6\2\u00f3"+
		"-\3\2\2\2\u00f4\u00f5\7\4\2\2\u00f5\u00f6\7%\2\2\u00f6\u00f7\7\4\2\2\u00f7"+
		"\u00f8\7*\2\2\u00f8\u00f9\5\60\31\2\u00f9\u00fa\7*\2\2\u00fa\u00fb\7\4"+
		"\2\2\u00fb/\3\2\2\2\u00fc\u00fd\7\24\2\2\u00fd\u00fe\7\b\2\2\u00fe\u00ff"+
		"\7)\2\2\u00ff\61\3\2\2\2\22KS`hr\u0081\u008c\u009e\u00a8\u00b8\u00c5\u00cf"+
		"\u00d1\u00db\u00e3\u00ed";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}