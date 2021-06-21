


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//the purpose of the ProgramText class is to abstract away
//from where the program is coming. ProgramText provides a
//single character to the Scanner class when asked for.
//it reads the program (from a file or as String) line by line
//from top to bottom
public class ProgramText {

    //private BufferedReader reader;
    public String progText;
    private int curPos, rez = 0;
    public static char EOF = '₺';

    ProgramText() {

        curPos = -1;

        try {
            progText = readWholeProgram();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private String readWholeProgram() throws IOException {
        return new String(Files.readAllBytes(Paths.get("program2.txt")));

    }

    char curChar() {
        if (curPos == -1)
            curPos++;

        if (curPos == progText.length())
            return EOF;
        if (rez <= progText.length()) {
            return progText.charAt(curPos);
        }
        return EOF;
    }

    char nextChar() {
        curPos++;
        rez = curPos;
        if (rez == progText.length())
            return EOF;

        for (int i = rez; i < progText.length(); i++) {
            if (Character.isWhitespace(progText.charAt(rez))) {
                rez++;
                if (rez == progText.length()) {
                    return EOF;
                }

            }
        }

        if (rez == progText.length()) {
            return EOF;
        }
        if (rez <= progText.length()) {
            return progText.charAt(rez);
        }


        return EOF;

    }


}
