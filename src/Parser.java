import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;
import java.util.stream.Collectors;

public class Parser {

    private final OurScanner scanner;
    public ProgramText programText;
    private Token curToken,nextToken;
    private int rightCurly = 0, leftCurly = 0;
    public Node node = new Node("Program"),nodeEXP=new Node("EXP");
    public Node curNode;
    public String curParentLeftChild;
    public ArrayList<String> arrayListControl = new ArrayList<String>();
    public HashMap<String,String> map = new HashMap<String,String>();
    public Scanner scan=new Scanner(System.in);

    // eğer parantez kapanırsa current nodeyi parent yap

    Parser(OurScanner scanner, ProgramText programText, Token token) {

        this.scanner = scanner;
        this.programText = programText;
        this.curToken = token;
    }

    void parse() {
        curToken = scanner.nextToken();
        while (!(curToken instanceof EOFToken)) {
            if (!(curToken instanceof EOFToken)) {

                if (curToken != null) {
                    //System.out.println(curToken.getText());
                    curNode=node;
                    if(arrayListControl.size()>0){
                        for(int i=0;i<arrayListControl.size();i++){
                            for(Node s : curNode.children){
                                if(s.data.equals(arrayListControl.get(i))){
                                    curNode=s.children.get(1);

                                }
                            }

                        }
                    }
                    //System.out.printf("Type: %s, text: %s\n", curToken.getTokenType(), curToken.getText())
                    S();
                }
            }
            curToken = scanner.nextToken();
        }
        if (!curlyController()) {
            //System.out.println("1Something is wrong.. " + curToken.getTokenType());
            System.exit(0);
        }
        Treversal treversal = new Treversal();
        ArrayList<String>  treePreOrder=treversal.preorder(node);
        ArrayList<String> treePostOrder=treversal.postorder(node);
        System.out.println("Preorder: "+treePreOrder);
        System.out.println("Postorder: "+treePostOrder);
        eval(treePreOrder);


        

    }

    void S() {
        if (curToken.getTokenType().equals(TokenType.END_OF_FILE)) {
            if (curlyController()) {
                return;
            } else {
                System.out.println("2Something is wrong.. " + curToken.getTokenType());
                System.exit(0);
            }
        }

        S1();
    }
    // bi değişken belirleriz adı while olursa while içinde if olursa if içinde
    //bu sayede onu kontrol ederken ne içinde anlarız
    //hatta bunu arraylist yaparız sonuncuyu sileriz en sonuncu içinde olduğumuz olur
    //ya da map te tutarız konumunu while mı if mi olduğunu daha iyi olur

    void S1() {
        curParentLeftChild=curToken.getText();
        if (curToken.getTokenType().equals(TokenType.END_OF_FILE)) {
            System.out.println("end of file");
            if (curlyController()) {
                System.exit(0);
            } else {
                System.out.println("3Something is wrong.. " + curToken.getTokenType());
                System.exit(0);
            }
            return;
        } else if (curToken.getTokenType().equals(TokenType.RIGHT_CURLY)) {
            arrayListControl.remove(arrayListControl.size()-1);
            rightCurly++;
        } else if (curToken.getTokenType().equals(TokenType.LEFT_CURLY)) {
            leftCurly++;
        }

        //WHILE

        else if (curToken.getTokenType().equals(TokenType.WHILE)) {
            childAdder(curNode,curToken.getText());
            for(Node s : curNode.children){
                if(s.data.equals(curToken.getText())){
                    curNode=s;
                }
            }
            curToken = scanner.nextToken();
            while (curToken == null) {
                curToken = scanner.nextToken();
            }
            if (curToken.getTokenType().equals(TokenType.END_OF_FILE)) {
                if (curlyController()) {
                    System.exit(0);
                } else {
                    System.out.println("4Something is wrong.. " + curToken.getTokenType());
                    System.exit(0);
                }
            } else if (curToken.getTokenType().equals(TokenType.LEFT_PAR)) {
                //System.out.println(curNode.children.stream().filter(f-> f.data.equals(curToken.getText())).collect(Collectors.toList()));

                String s=Boolean();
                childAdder(curNode,s);
                childAdder(curNode,"body");
                curToken = scanner.nextToken();
                while (curToken == null) {
                    curToken = scanner.nextToken();
                }

                if (curToken.getTokenType().equals(TokenType.END_OF_FILE)) {
                    if (curlyController()) {
                        System.exit(0);
                    } else {
                        System.out.println("5Something is wrong.. " + curToken.getTokenType());
                        System.exit(0);
                    }
                    System.exit(0);
                } else if (curToken.getTokenType().equals(TokenType.RIGHT_PAR)) {

                    curToken = scanner.nextToken();
                    while (curToken == null) {
                        curToken = scanner.nextToken();
                    }
                    if (curToken.getTokenType().equals(TokenType.END_OF_FILE)) {
                        if (curlyController()) {
                            System.exit(0);
                        } else {
                            System.out.println("6Something is wrong.. " + curToken.getTokenType());
                            System.exit(0);
                        }
                        System.exit(0);
                    } else if (curToken.getTokenType().equals(TokenType.LEFT_CURLY)) {
                        arrayListControl.add("while");
                        leftCurly++;
                    } else {
                        System.out.println("7Something is wrong.. " + curToken.getTokenType());
                        System.exit(0);
                    }
                } else {
                    System.out.println("8Something is wrong.. " + curToken.getTokenType());
                    System.exit(0);
                }
            } else {
                System.out.println("9Something is wrong.. " + curToken.getTokenType());
                System.exit(0);
            }
        }

        //IF

        else if (curToken.getTokenType().equals(TokenType.IF)) {
            childAdder(curNode,curToken.getText());
            for(Node s : curNode.children){
                if(s.data.equals(curToken.getText())){
                    curNode=s;
                }
            }
            curToken = scanner.nextToken();
            while (curToken == null) {
                curToken = scanner.nextToken();
            }

            if (curToken.getTokenType().equals(TokenType.END_OF_FILE)) {
                if (curlyController()) {
                    System.exit(0);
                } else {
                    System.out.println("10Something is wrong.. " + curToken.getTokenType());
                    System.exit(0);
                }
            } else if (curToken.getTokenType().equals(TokenType.LEFT_PAR)) {
                String s=Boolean();
                childAdder(curNode,s);
                childAdder(curNode,"body");
                //Exp();
                curToken = scanner.nextToken();
                while (curToken == null) {
                    curToken = scanner.nextToken();
                }

                if (curToken.getTokenType().equals(TokenType.END_OF_FILE)) {
                    if (curlyController()) {
                        System.exit(0);
                    } else {
                        System.out.println("11Something is wrong.. " + curToken.getTokenType());
                        System.exit(0);
                    }
                    System.exit(0);
                } else if (curToken.getTokenType().equals(TokenType.RIGHT_PAR)) {

                    curToken = scanner.nextToken();
                    while (curToken == null) {
                        curToken = scanner.nextToken();
                    }
                    if (curToken.getTokenType().equals(TokenType.END_OF_FILE)) {
                        if (curlyController()) {
                            System.exit(0);
                        } else {
                            System.out.println("12Something is wrong.. " + curToken.getTokenType());
                            System.exit(0);
                        }
                        System.exit(0);
                    } else if (curToken.getTokenType().equals(TokenType.LEFT_CURLY)) {
                        arrayListControl.add("if");
                        leftCurly++;
                    } else {
                        System.out.println("13Something is wrong.. " + curToken.getTokenType());
                        System.exit(0);
                    }
                } else {
                    System.out.println("14Something is wrong.. " + curToken.getTokenType());
                    System.exit(0);
                }
            } else {
                System.out.println("15Something is wrong.. " + curToken.getTokenType());
                System.exit(0);
            }
        }

        //IDENITIFIER

        else if (curToken.getTokenType().equals(TokenType.IDENITIFIER)) {
            curParentLeftChild=curToken.getText();
            curToken = scanner.nextToken();
            while (curToken == null) {
                curToken = scanner.nextToken();
            }
            if (curToken.getTokenType().equals(TokenType.END_OF_FILE)) {
                if (curlyController()) {
                    System.exit(0);
                } else {
                    System.out.println("16Something is wrong.. " + curToken.getTokenType());
                    System.exit(0);
                }
                System.exit(0);
            } else if (curToken.getTokenType().equals(TokenType.EQUAL)) {
                childAdder(curNode,TokenType.EQUAL.getText());
                for(Node s : curNode.children){
                    if(s.data.equals(TokenType.EQUAL.getText())){
                        curNode=s;
                    }
                }
                childAdder(curNode,curParentLeftChild);
                String s=Exp();
                childAdder(curNode,s);
                curToken = scanner.nextToken();
                while (curToken == null) {
                    curToken = scanner.nextToken();
                }
                if (curToken.getTokenType().equals(TokenType.END_OF_FILE)) {
                    if (curlyController()) {
                        System.exit(0);
                    } else {
                        System.out.println("17Something is wrong.. " + curToken.getTokenType());
                        System.exit(0);
                    }
                    System.exit(0);
                } else if (curToken.getTokenType().equals(TokenType.SEMI_COLON)) {

                } else {
                    System.out.println("18Something is wrong.. " + curToken.getTokenType());
                    System.exit(0);
                }
            } else {
                System.out.println("19Something is wrong.. " + curToken.getTokenType());
                System.exit(0);
            }
        }

        //OUT

        else if (curToken.getTokenType().equals(TokenType.OUT)) {
            childAdder(curNode,curToken.getText());
            for(Node s : curNode.children){
                if(s.data.equals(curToken.getText())){
                    curNode=s;
                }
            }
            curToken = scanner.nextToken();
            while (curToken == null) {
                curToken = scanner.nextToken();
            }
            if (curToken.getTokenType().equals(TokenType.END_OF_FILE)) {
                if (curlyController()) {
                    System.exit(0);
                } else {
                    System.out.println("20Something is wrong.. " + curToken.getTokenType());
                    System.exit(0);
                }
            } else if (curToken.getTokenType().equals(TokenType.LEFT_PAR)) {
                String s=Exp();
                childAdder(curNode,s);
                curToken = scanner.nextToken();
                while (curToken == null) {
                    curToken = scanner.nextToken();
                }

                if (curToken.getTokenType().equals(TokenType.END_OF_FILE)) {
                    if (curlyController()) {
                        System.exit(0);
                    } else {
                        System.out.println("21Something is wrong.. " + curToken.getTokenType());
                        System.exit(0);
                    }
                    System.exit(0);
                } else if (curToken.getTokenType().equals(TokenType.RIGHT_PAR)) {

                    curToken = scanner.nextToken();
                    while (curToken == null) {
                        curToken = scanner.nextToken();
                    }

                    if (curToken.getTokenType().equals(TokenType.END_OF_FILE)) {
                        if (curlyController()) {
                            System.exit(0);
                        } else {
                            System.out.println("22Something is wrong.. " + curToken.getTokenType());
                            System.exit(0);
                        }
                        System.exit(0);
                    } else if (curToken.getTokenType().equals(TokenType.SEMI_COLON)) {

                    } else {
                        System.out.println("25Something is wrong.. " + curToken.getTokenType());
                        System.exit(0);
                    }
                } else {
                    System.out.println("26Something is wrong.. " + curToken.getTokenType());
                    System.exit(0);
                }
            } else {
                System.out.println("27Something is wrong.. " + curToken.getTokenType());
                System.exit(0);
            }
        }

        //IN

        else if (curToken.getTokenType().equals(TokenType.IN)) {
            childAdder(curNode,curToken.getText());
            for(Node s : curNode.children){
                if(s.data.equals(curToken.getText())){
                    curNode=s;
                }
            }
            curToken = scanner.nextToken();
            while (curToken == null) {
                curToken = scanner.nextToken();
            }
            if (curToken.getTokenType().equals(TokenType.END_OF_FILE)) {
                if (curlyController()) {
                    System.exit(0);
                } else {
                    System.out.println("20Something is wrong.. " + curToken.getTokenType());
                    System.exit(0);
                }
            } else if (curToken.getTokenType().equals(TokenType.LEFT_PAR)) {
                String s=Exp();
                childAdder(curNode,s);
                curToken = scanner.nextToken();
                while (curToken == null) {
                    curToken = scanner.nextToken();
                }

                if (curToken.getTokenType().equals(TokenType.END_OF_FILE)) {
                    if (curlyController()) {
                        System.exit(0);
                    } else {
                        System.out.println("21Something is wrong.. " + curToken.getTokenType());
                        System.exit(0);
                    }
                    System.exit(0);
                } else if (curToken.getTokenType().equals(TokenType.RIGHT_PAR)) {

                    curToken = scanner.nextToken();
                    while (curToken == null) {
                        curToken = scanner.nextToken();
                    }

                    if (curToken.getTokenType().equals(TokenType.END_OF_FILE)) {
                        if (curlyController()) {
                            System.exit(0);
                        } else {
                            System.out.println("22Something is wrong.. " + curToken.getTokenType());
                            System.exit(0);
                        }
                        System.exit(0);
                    } else if (curToken.getTokenType().equals(TokenType.SEMI_COLON)) {

                    } else {
                        System.out.println("25Something is wrong.. " + curToken.getTokenType());
                        System.exit(0);
                    }
                } else {
                    System.out.println("26Something is wrong.. " + curToken.getTokenType());
                    System.exit(0);
                }
            } else {
                System.out.println("27Something is wrong.. " + curToken.getTokenType());
                System.exit(0);
            }
        }


    }

    //EXPRESSION

    String Exp() {
        String rezerve = "";
        boolean control = true;
        while (control) {
            if (String.valueOf(scanner.chNext).equals(TokenType.RIGHT_PAR.getText()) ||
                    String.valueOf(scanner.chNext).equals(TokenType.SEMI_COLON.getText())) {

                control = false;

            }else{
                curToken = scanner.nextToken();
                while (curToken == null) {
                    curToken = scanner.nextToken();
                }

                if (curToken.getTokenType().equals(TokenType.END_OF_FILE)) {
                    if (curlyController()) {
                        System.exit(0);
                    } else {
                        System.out.println("36Something is wrong.. " + curToken.getTokenType());
                        System.exit(0);
                    }
                    System.exit(0);
                } else if (!(String.valueOf(scanner.chNext).equals(TokenType.RIGHT_PAR.getText()) ||
                        String.valueOf(scanner.chNext).equals(TokenType.SEMI_COLON.getText()))) {
                    rezerve += curToken.getText();
                } else if ((String.valueOf(scanner.chNext).equals(TokenType.RIGHT_PAR.getText()) ||
                        String.valueOf(scanner.chNext).equals(TokenType.SEMI_COLON.getText()))) {
                    rezerve += curToken.getText();
                    //System.out.println(rezerve);
                    break;
                } else {
                    System.out.println(rezerve + " 37Something is wrong.. " + curToken.getTokenType());
                    System.exit(0);
                }

            }

        }
        return rezerve;
    }




    //curlyController

    boolean curlyController() {
        boolean control = false;
        if (rightCurly == leftCurly) {
            control = true;
        }
        return control;
    }

    void childAdder (Node node,String value){
        node.addChild(new Node(value));
    }
    void ExpTail(){
    }

    void Term() {
    }

    void TermTail() {
    }
    void Factor() {
    }

    void FactorTail() {
    }

    void Id() {
    }

    void Char() {
    }

    void Num() {
    }

    String Boolean() {
        boolean control = true,control2=true;
        String booleanValue = "",rezerve="";
        curToken = scanner.nextToken();
        while (curToken == null) {
            curToken = scanner.nextToken();
        }
        if (!(curToken.getTokenType().equals(TokenType.NUMBER) || curToken.getTokenType().equals(TokenType.IDENITIFIER))) {

            System.out.println(" 38Something is wrong.. " + curToken.getTokenType());
            System.exit(0);

        } else {
            if (curToken.getTokenType().equals(TokenType.NUMBER) || curToken.getTokenType().equals(TokenType.IDENITIFIER)) {
                rezerve+=curToken.getText();
                curToken = scanner.nextToken();
                while (curToken == null) {
                    curToken = scanner.nextToken();
                }
                nextToken=scanner.nextToken();
                while (nextToken == null) {
                    nextToken = scanner.nextToken();
                }
                    if (nextToken.getTokenType().equals(TokenType.IDENITIFIER)|| nextToken.getTokenType().equals(TokenType.NUMBER)) {
                        booleanValue+=curToken.getText();
                        rezerve=rezerve+curToken.getText()+nextToken.getText();
                        for (BooleanOperationType type : BooleanOperationType.values()) {
                            if (booleanValue.equals(type.getText())) {
                                //System.out.println(rezerve+" "+type.getText()+" "+type);
                                control2=false;
                            }
                        }
                        if(control2){
                            System.out.println(" 39Something is wrong.. " + curToken.getTokenType());
                            System.exit(0);
                        }
                    } else if(nextToken.getTokenType().equals(TokenType.WHILE)|| nextToken.getTokenType().equals(TokenType.IF)||
                            nextToken.getTokenType().equals(TokenType.OUT)|| nextToken.getTokenType().equals(TokenType.IN)){
                        System.out.println(" 40Something is wrong.. " + curToken.getTokenType());
                        System.exit(0);

                    } else {
                        booleanValue=booleanValue+curToken.getText()+nextToken.getText();
                        rezerve=rezerve+curToken.getText()+nextToken.getText();
                        for (BooleanOperationType type : BooleanOperationType.values()) {
                            if (booleanValue.equals(type.getText())) {
                                curToken= scanner.nextToken();
                                while (curToken == null) {
                                    curToken = scanner.nextToken();
                                }
                                rezerve+=curToken.getText();
                                //System.out.println(rezerve+" "+type.getText()+" "+type);
                                control2=false;
                            }
                        }
                        if(control2){
                            System.out.println(" 41Something is wrong.. " + curToken.getTokenType());
                            System.exit(0);
                        }
                    }
                } else {
                    System.out.println(" 42Something is wrong.. " + curToken.getTokenType());
                    System.exit(0);
                }
            }

        return rezerve;
    }

    void eval(ArrayList<String> tree){
        for(int i=0;i<tree.size();i++){
            //System.out.println(tree.get(i));
            if(tree.get(i).equals("Program")){

            }else if(tree.get(i).equals(TokenType.EQUAL.getText())){
                i++;
                String rez,rez1;
                rez=tree.get(i);
                i++;
                rez1=tree.get(i);
                //System.out.println(rez+" "+rez1);
                expressionSolver(rez1);
            }else if(tree.get(i).equals(TokenType.WHILE.getText())){
                i++;
                booleanSolver(tree.get(i));
            }else if(tree.get(i).equals(TokenType.IF.getText())){
                i++;
                booleanSolver(tree.get(i));
            }else if(tree.get(i).equals(TokenType.IN.getText())){
                i++;
                String valueName=tree.get(i);
                String value=scan.nextLine();
                map.put(valueName,value);
            }else if(tree.get(i).equals(TokenType.OUT.getText())){
                i++;
                System.out.println(map.get(tree.get(i)));

            }
        }

    }
    String expressionSolver(String expression){
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String rez="";
        String rezMain="";
        for (int i=0;i<expression.length();i++) {
            //System.out.println(foo.charAt(i));
            if(Character.isWhitespace(expression.charAt(i))){

            }
            else if(Character.isLetter(expression.charAt(i))||Character.isDigit(expression.charAt(i))){
                rez+=expression.charAt(i);
                rezMain+=expression.charAt(i);
            }
            else if(String.valueOf(expression.charAt(i)).equals("+")||
                    String.valueOf(expression.charAt(i)).equals("-")||
                    String.valueOf(expression.charAt(i)).equals("*")||
                    String.valueOf(expression.charAt(i)).equals("/")){
                rezMain+=expression.charAt(i);
                //System.out.println(rez);
                boolean ctrl=false;
                for(int l=0;l<rez.length();l++){
                    if(Character.isLetter(rez.charAt(l))){
                        ctrl=true;
                    }
                }
                if(ctrl){
                    String value=map.get(rez);/*
                    if(value.isEmpty()){
                        System.out.println("error");
                        System.exit(0);
                    }*/

                    rezMain+=value;
                }else{
                    rezMain+=rez;
                }
                rez="";
                /*
                for (Map.Entry<String,String> entry: map.entrySet()){
                    String key = entry.getKey();
                    if(key.equals(rez)){

                        rez+=map.get(key);
                    }
                    else{
                        System.out.println("error");
                    }
                }
                */



            }

        }//System.out.println(rezMain);
        /*try {

            System.out.println(engine.eval(rezMain));
        } catch (ScriptException e) {
            e.printStackTrace();
        }*/
        return null;
    }

    Boolean booleanSolver(String expression){
        return null;
    }
}
