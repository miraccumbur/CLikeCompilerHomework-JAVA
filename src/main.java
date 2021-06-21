public class main {
    public static void main(String[] args) {
        ProgramText programText = new ProgramText();
        OurScanner scanner = new OurScanner(programText);
        Token token = new Token(programText);
        Parser parser = new Parser(scanner, programText, token);
        parser.parse();
    }
}
