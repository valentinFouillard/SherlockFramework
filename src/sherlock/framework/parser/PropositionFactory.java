package sherlock.framework.parser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
/**
 * Classe créeant des propositions pour le SMT
 * @author valentin
 *
 */
public class PropositionFactory 
{
	public String name; // nom de la proposition
	public String signature; // Signature de la proposition de la forme : %s and %s => %s
	public boolean isExpression; // Retourne vrai si expression
	private boolean isCausal; // Retourne vrai si règle de la forme $s ::> %s
	public boolean isCoherence; // Retourne vrai si règle de cohérence
	private boolean isRule; // Retourne vrai si règle
	private boolean isStrong;
	private HashMap<String,Integer> limitArgsMax,limitArgsMin; // Limit minimum et maximum des arguments de la proposition
	public String precondition;
	
	public PropositionFactory(String name,boolean isExpression)
	{
		this.name = name;
		this.isExpression = isExpression;
		this.isCausal = false;
		this.isCoherence = false;
		this.isRule = false;
		this.isStrong = false;
		this.signature = "";
		this.precondition = null;
		this.limitArgsMax = new HashMap<>();
		this.limitArgsMin = new HashMap<>();
		
	}
	
	public void setCausal(boolean causal) {
		this.isCausal = causal;
	}
	
	public void setPrecondition(String prec)
	{
		this.precondition = prec;
	}
	
	public void setCoherence(boolean coherence) {
		this.isCoherence = coherence;
	}
	
	public boolean isRule()
	{
		return this.isRule;
	}
	
	public void setIsRule(boolean isRule)
	{
		this.isRule = isRule;
	}
	
	public boolean getCoherence() {
		return this.isCoherence;
	}
	
	public boolean isStrong()
	{
		return this.isStrong;
	}
	
	public void setStrong(boolean value)
	{
		this.isStrong = value;
	}
	
	
	
	
	public HashMap<String,Integer> getMaxLimit()
	{
		return this.limitArgsMax;
	}
	
	public HashMap<String,Integer> getMinLimit()
	{
		return this.limitArgsMin;
	}
	

	
	
	
}
