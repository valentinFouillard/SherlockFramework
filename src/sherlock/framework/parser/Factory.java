package sherlock.framework.parser;

public abstract class Factory 
{
	protected String name;
	protected boolean isRule;
	
	public Factory(String name) {
		this.name = name;
		this.isRule = false;
	}
	
	public boolean isRule() {return this.isRule;}
	public void setIsRule(boolean isRule) {this.isRule = isRule;}
	public abstract String getID(int time);
	
	public abstract void generate(FactoryBuild factBuild,int time);
}
