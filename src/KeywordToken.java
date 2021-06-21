public class KeywordToken extends Token {
    KeywordToken(ProgramText source,String text, TokenType type) {
        super(source);
        this.text=text;
        this.type=type;
    }
}
