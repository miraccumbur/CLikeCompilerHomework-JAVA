

public class NumberToken extends Token{
	
	NumberToken(ProgramText source,String text,TokenType type) {
		super(source);
		this.text=text;
		this.type=type;
	}
}
