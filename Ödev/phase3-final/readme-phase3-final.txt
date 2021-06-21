In Token class, token object has a feature.This is program text object. And this class two method getTokenType and getText. Gettext return string value and gettokentype return token type.

IdentiferToken, keywordToken, numbertoken, specialToken class extends Token class. This object class get text and tokentype. And Token class superclass for these class.

EOFTOKEN class extend Token class. And token class superclass.

In token type enum and booleanoperationtype files has our values.

In programtext class curios value is -1 and there his a try catch block in try block run readWjoleProgram method and this method return string this string value is progText.In curChar method if curios equal -1 curPos++; and return return in progtext string value in chart curpos. In nextChar method curpos++ and rez value equal curpos value. If rez equal protest length return EOf. After that, there is a for loop. i equal rez and if i smaller than protest length run this loop. In this loop has a if block. If progtext chart rez value is white space enter this block.In this block rez++; After that, again If rez equal protest length return EOf. And finally return progtExt chart rez value.

In OurScanner Class, Scanner object has a feature. This are Programtext object. In these  class has a isSpecial method. This method returns boolean value. This method get a char value. In this method I created a Boolean control. This boolean is false for now.There is a if block. If this char value is not a white space enter this block. After that, there is a for each loop . This loop go around TokenType enum. And if this char values equals this value enter this block. And in here control boolean values is be true and break. In this class has a nextToken method. This method return Token.There are chVur and chNext. These are char value. And these value get forum program text class.There is a if block. If this char value is not a white space enter this block.After that, there is a for each loop . This loop go around TokenType enum. And if this char values equals this value enter this block. In here create a SpecialToken object and return this. If not enter the second if block we controller char value is digit. If is digit add string value and we controlled nextChar isSpeacil if true enter a new if block but it is false not enter in this block. If enter these block create a NumberToken object, then string value be empty and return this token.If char value is not a digit, it is a letter. Now enter the other else if block.char value add again string value. Again if block controlled by isSpeacila method.In this if block if block and else block. If string value is while enter the if block and create keyword token and return this.But is not enter the else block create IdentiferToken and return this token.

In the Parser class, I first defined;
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

I checked the absence of eoftoken in the parse method. I assign the node value to curnode. I find out which child the parent is with the for each loop. and curnode becomes that child. Then I go to method S. we'll check the eof and go to the s1 Tavern. Here we do a syntax check, and if there's no problem, we add children to our tree using the childadder method. We use Expression and boolean methods to check syntax accuracy. after touring all our statements, we leave the parse method loop. we're doing curlycontroller. If he enters this control, the error occurs and the program closes. It prints an error message to the console. Then we create our treversal object. We use the postorder and preorder methots of this object. And from here, we print the geleln ArrayList on the console. Then we send the arraylist with our values from the preorder to the eval method. In this method, we travel our ArrayList in the for loop. if our value is"=", we throw the left and right child into the values of rez and rez1. and then we send it to the expressionsolver method. If there is an identifer value in our expression in this method,it will find it, get the value from the HashMap, and solve our expression. Unfortunately, I couldn't complete this method because I didn't have enough time. if our value is while or if, this time we send it to our boolean method. Again, I couldn't do this method because I didn't have enough time.if our value is in, hashmape saves the input value entered by the user from the console using the nextline method from the scanner library. if the value is out, System.out.using the println method, it takes the value equivalent to hashmpate and prints it to the screen.

We created the Node class. Instead of using left and right children here. I used a list of children. Because a parent can have more than one child.

in the treversal class, we first defined the following values;
Stack<Pair> stack = new Stack<Pair>();
    ArrayList<String> postorderTraversal = new ArrayList<String>();
    ArrayList<String> preorderTraversal = new ArrayList<String>();

postorder and preorder methods are methods that I can find ready on the internet. Geeksforgeek had these codes. I took these codes and made them suitable for my own program and ran them.