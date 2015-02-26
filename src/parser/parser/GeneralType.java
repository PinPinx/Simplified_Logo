package parser.parser;

public enum GeneralType {
	COMMENT, CONSTANT, COMMAND, VARIABLE, LISTSTART, LISTEND, GROUPSTART, GROUPEND, OTHER;
	
	public static GeneralType findType(String str){
		for(GeneralType type : GeneralType.values()){
			if(str.equalsIgnoreCase(type.toString())){
				return type;
			}
		}
		return GeneralType.OTHER;
	}
}
