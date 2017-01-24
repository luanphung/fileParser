import java.util.ArrayList;
import java.util.List;

public class input {
	//initiate a few variables
	public String name;
	public List<String> isFoundin = new ArrayList<String>();
	
	//basic constructor
	public input(String name){
		this.name = name;
	}
	//get functions;
	public String getName(){
		return name;
	}
	public List<String> getisFoundin(){
		return isFoundin;
	}
	
	//add functions;
	public void add(String file, int line){
		isFoundin.add(this.name +";"+file+";"+line);
	}
	public String getInfo(int location){
		return isFoundin.get(location);
	}
}
