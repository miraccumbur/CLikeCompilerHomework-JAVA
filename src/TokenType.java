

public enum TokenType {
    LEFT_CURLY("{"), RIGHT_CURLY("}"), LEFT_PAR("("), RIGHT_PAR(")"),
    EQUAL("="), SEMI_COLON(";"), LESS_THAN("<"),GRATER_THAN(">"),
    MINUS("-"), MULTIPLY("*"), DIVIDE("/"), PLUS("+"),NOT("!"),

    WHILE("while"), IF("if"), OUT("out"), IN("in"),
    IDENITIFIER, NUMBER, END_OF_FILE;

    public String getText() {
        return text;
    }

    private final String text;

    TokenType(String text) {
        this.text = text;
    }

    TokenType() {
        this.text = this.toString();
    }
}


