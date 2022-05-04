package sherlock.framework;
import java.io.Serializable;
import java.util.ArrayList;

public class Proposition implements Serializable
{
	public String id;
	public boolean instance,isRuleStrong,isRuleWeak;
	public int maxTime;
	public ArrayList<String> precArg;
	private String premisse;
	
	
	public Proposition(String id,boolean instance)
	{
		this.id = id;
		this.instance = instance;
		this.maxTime = 0;
		this.precArg = new ArrayList<>();
		this.isRuleStrong = false;
		this.isRuleWeak = false;
	}
	
	public Proposition(String id,boolean instance,int maxTime)
	{
		this.id = id;
		this.instance = instance;
		this.maxTime = maxTime;
		this.precArg = new ArrayList<>();
		this.isRuleStrong = false;
		this.isRuleWeak = false;
		
	}
	
	public void setPremisse(String prem) {
		this.premisse=prem;
	}
	
	public String getPremisse() {
		return this.premisse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (instance ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proposition other = (Proposition) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (instance != other.instance)
			return false;
		return true;
	}

	@Override
	public String toString() {
		if(instance)
			return this.id;
		else
			return "not "+this.id;
	}
	
	
	
	
	
	
}
