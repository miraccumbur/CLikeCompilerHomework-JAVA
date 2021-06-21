

public class EOFToken extends Token {

    EOFToken(ProgramText source) {
        super(source);
        type = TokenType.END_OF_FILE;

    }

}
