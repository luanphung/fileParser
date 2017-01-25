	import java.util.ArrayList;
	import java.util.List;
	
public class doubleInput {

		//initiate a few variables
		public String table;
		public String name;
		public List<String> isFoundin = new ArrayList<String>();
		
		//basic constructor
		public doubleInput(String table, String name){

			this.table = table;	
			this.name = name;
		}
		//get functions;
		public String getName(){
			return name;
		}
		public String getTable(){
			return table;
		}
		public List<String> getisFoundin(){
			return isFoundin;
		}
		
		//add functions;
		public void add(String file, int line){
			isFoundin.add(file+";"+line);
		}
		public String getInfo(int location){
			return isFoundin.get(location);
		}
	}

