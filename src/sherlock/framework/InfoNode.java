package sherlock.framework;
import java.util.ArrayList;

public class InfoNode 
{
	private ArrayList<String> emotions;
	private ArrayList<String> bias;
	private ArrayList<String> families;
	
	public InfoNode()
	{
		this.emotions = new ArrayList<>();
		this.bias = new ArrayList<>();
		this.families = new ArrayList<>();
	}

	public ArrayList<String> getEmotions() {
		return emotions;
	}

	public ArrayList<String> getBias() {
		return bias;
	}

	public ArrayList<String> getFamilyBias() {
		return families;
	}
	
	public void addEmotion(String emotion)
	{
		this.emotions.add(emotion);
	}
	
	public void addBias(String bias)
	{
		this.bias.add(bias);
	}
	
	public void addFamily(String family)
	{
		this.families.add(family);
	}
	
}
