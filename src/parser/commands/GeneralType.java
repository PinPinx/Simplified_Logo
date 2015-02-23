package parser.commands;

public enum GeneralType {
	COMMENT, CONSTANT, COMMAND, LISTSTART, LISTEND, GROUPSTART, GROUPEND, OTHER;
	
	public static GeneralType findType(String str){
		for(GeneralType type : GeneralType.values()){
			if(str.equalsIgnoreCase(type.toString())){
				return type;
			}
		}
		return GeneralType.OTHER;
	}
}
