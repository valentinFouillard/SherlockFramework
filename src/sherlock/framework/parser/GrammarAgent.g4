// define a grammar called GrammarAgent
grammar GrammarAgent;
parse
	:  allLimits allFacts allActions allPRules  allRules allDesires allInitBeliefs allObservations allRuntime EOF
	;

// Expression logique boolean
expression
	: LParen parenexp=expression RParent
	| NOT notexp=expression
	| left=expression logicOperator right=expression
	| LPRE prec=expression RPRE predicat
	| bool
	| predicat
	;

//Opérateur logique
logicOperator 
	: AND
	| OR
	| IMPLIES
	| CAUSAL
	;
	
// Boolean
bool
	: TRUE
	| FALSE
	;

// Définition d'un prédicat	
predicat
	: ID LParen signature RParent
	| ID
	;

//Signature d'un prédicat
signature
    :TIME operator NUMBER 
    | TIME 
    | NUMBER
    ;
    
// Opérateur de signature
operator
	: ADD
	| MINUS
	;

// Ensemble de tous les faits
allFacts
	:FACTSSET'==='BREAKLINE (definitionFact)+ '===';
	
// Définition d'un fait
definitionFact
	: ID BREAKLINE
	;

	
allPRules 
	: PRULES'==='BREAKLINE (expression BREAKLINE)*'==='
	;
	
allActions
	: ACTIONS'==='BREAKLINE (definitionFact)* '==='
	;
	

allRules
	: RULES'==='BREAKLINE '===' allStrongRules allWeakRules
	;	
allStrongRules 
	: STRONGRULES'==='BREAKLINE (defRules)+ '==='
	;
	
allWeakRules
	: WEAKRULES'==='BREAKLINE (defRules)* '==='
	;

defRules 
	: expression BREAKLINE
	;
allDesires
	: DESIRES '==='BREAKLINE (defInstancePred BREAKLINE)* '==='
	;		
allObservations 
	: OBSERVATIONS'==='BREAKLINE(defObservation BREAKLINE)+'==='
	;

defObservation 
	: NUMBER':'(defInstancePred COMMA|defInstancePred)+
	;

allInitBeliefs
	: INITBELIEFS'==='BREAKLINE(defInstancePred BREAKLINE)*'==='
	;

defInstancePred
	: predicat
	| NOT predicat
	;

allRuntime
	: RUNTIME'==='BREAKLINE(runtimeDef BREAKLINE)*;

runtimeDef
	: NUMBER ':' predicat;

allLimits
	: '==='LIMITS'==='BREAKLINE defLimits BREAKLINE'===';
defLimits
	: TIME EQUALS NUMBER;

AND 			: 'and';
OR  			: 'or';
IMPLIES 		: '=>';
EQUALS			: '=';
CAUSAL			: '::>';
TRUE 			: 'true';
FALSE 			: 'false';
NOT 			: 'not';
COMMA 			: ',';
ADD 			: '+';
MINUS 			: '-';
LParen 			: '(';
RParent 		: ')';
LPRE			: '[';
RPRE			: ']';
TIME			: 't';
DEF				: '::';
X				: 'x';
NAT 			: 'nat';
FACTSSET 		: 'Facts';
DFACTSSET		: 'Derived Facts';
PRULES			: 'PermanentRules';
ACTIONS			: 'Actions';
PACTIONSRULES	: 'PermanentActionRules';
AGENT			: '===Agent===';
RULES 			: 'Rules';
STRONGRULES		: 'Strong';
WEAKRULES		: 'Weak';
DESIRES			: 'Desires';
OBSERVATIONS	: 'Observations';
INITBELIEFS		: 'InitialWorldBeliefs';
RUNTIME			: 'Runtime';
LIMITS			: 'Limits';
WORLD			: '===World===';
SCENARIO		: '===Scenario===';
ID			    : [a-zA-Z]+;
NUMBER 			: [0-9]+;
BREAKLINE 		: [\r\n]+;
WS				: [ \t]+ -> skip;