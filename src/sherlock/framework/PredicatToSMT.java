package sherlock.framework;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class PredicatToSMT 
{
	public String name;
	public LinkedHashMap<String,Integer> idArgs;
	private boolean isAction;
	
	public PredicatToSMT(String name,LinkedHashMap<String,Integer> idArgs)
	{
		this.name = name;
		this.idArgs = idArgs;
		this.isAction = false;
	}
	
	public PredicatToSMT(String name)
	{
		this.name = name;
		this.idArgs = new LinkedHashMap<>();
	}
	
	public void setIsAction(boolean isAction)
	{
		this.isAction = isAction;
	}
	
	public void setArgs(LinkedHashMap<String,Integer> args)
	{
		this.idArgs = args;
	}
	
	
	public LinkedHashMap<String, Integer> getIdArgs() {
		return idArgs;
	}

	public String getPredToSMT(HashMap<String,Integer> args,boolean causal)
	{
		String pred = this.name;
		for(String arg : idArgs.keySet()) {
			int valueArg = (args.get(arg)+idArgs.get(arg));
			if(valueArg<0) 
				pred+="_m"+Math.abs(valueArg);
			else
				pred+="_"+valueArg;
			
		}
		//if(causal && !isAction) 
			//pred = "(and "+pred+" known_"+pred+")"; 
		return pred;
	}

	@Override
	public String toString() {
		String result = this.name+"(";
		for(String arg : this.idArgs.keySet()) {
			result+=arg+" = "+this.idArgs.get(arg).toString()+", ";
		}
		return result+=")";
	}
	
	
	
}
