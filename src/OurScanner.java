

//is responsible for scanning for tokens (it will return tokens)
//to the parser.
public class OurScanner {
    private final ProgramText source;
    public String string = "";
    public char chNext, chCur;

    OurScanner(ProgramText source) {
        this.source = source;
    }

    boolean isSpecial(char chNext) {
        boolean control = false;
        if (!Character.isWhitespace(chNext)) {
            for (TokenType type : TokenType.values()) {
                if (String.valueOf(chNext).equals(type.getText())) {
                    control = true;
                    break;
                }
            }
        }
        return control;
    }

    //Scanner will ask the Source for characters and one a sequence of
    //characters form a token it will return immediately.
    //Scanner needs to know some of rules (for example, what constitutes
    //a number, what constitutes an identifier and so forth)
    Token nextToken() {
        Token token;

        chCur = source.curChar();
        chNext = source.nextChar();
        while (Character.isWhitespace(chCur)) {
            chCur = source.curChar();
            chNext = source.nextChar();
        }
        if (!Character.isWhitespace(chCur)) {
            for (TokenType type : TokenType.values()) {
                if (String.valueOf(chCur).equals(type.getText())) {
                    token = new SpecialToken(source, String.valueOf(chCur), type);
                    return token;
                }
            }
            if (Character.isDigit(chCur)) {
                //number token
                //System.out.println(chCur);
                string += chCur;
                if (isSpecial(chNext)) {
                    token = new NumberToken(source, string, TokenType.NUMBER);
                    string = "";
                    return token;
                }

            } else if (Character.isLetter(chCur)) {
                //identifier token
                string += chCur;
                if (isSpecial(chNext)) {
                    if (string.equals(TokenType.WHILE.getText())) {
                        //System.out.println(TokenType.WHILE.getText());
                        token = new KeywordToken(source, string, TokenType.WHILE);
                        string = "";
                        return token;
                    } else if (string.equals(TokenType.IF.getText())) {
                        token = new KeywordToken(source, string, TokenType.IF);
                        string = "";
                        return token;
                    } else if (string.equals((TokenType.OUT.getText()))) {
                        token = new KeywordToken(source, string, TokenType.OUT);
                        string = "";
                        return token;
                    } else if (string.equals((TokenType.IN.getText()))) {
                        token = new KeywordToken(source, string, TokenType.OUT);
                        string = "";
                        return token;
                    } else {
                        token = new IdentifierToken(source, string, TokenType.IDENITIFIER);
                        string = "";
                        return token;
                    }


                }

            } else {
                token = new EOFToken(source);
                return token;
            }
        }

        return null;


    }


}
