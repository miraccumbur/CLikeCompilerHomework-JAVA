

public class Token {

	public TokenType type;
	public String text;
	private final ProgramText source;
	
	Token(ProgramText source){
		this.source = source;
	}
	public TokenType getTokenType() {
		return type;
	}
	public String getText() {
		return text;
	}
}

