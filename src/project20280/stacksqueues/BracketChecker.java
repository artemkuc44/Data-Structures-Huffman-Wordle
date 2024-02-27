package project20280.stacksqueues;


import java.util.Stack;

class BracketChecker {
    private final String input;

    public BracketChecker(String in) {
        input = in;
    }

    public void check() {
        //TODO
        Stack<Character> stack = new Stack<>();

        for(int i = 0;i<input.length();i++){
            char ch = input.charAt(i);
            switch(ch){
                case '(':
                case '{':
                case '[':
                    stack.push(ch);
                    break;
                case ')':
                case '}':
                case ']':
                    if(stack.isEmpty() || getMatchingBracket(stack.pop()) != ch){
                        System.out.println("incorrect bracketing at position index: " + i);
                        return;
                }
            }
        }

        if(stack.isEmpty()){
            System.out.println("Correct");
        }else{
            System.out.println("incomplete bracketing");
        }
    }

    public static char getMatchingBracket(char ch){
        return switch (ch) {
            case '(' -> ')';
            case '{' -> '}';
            case '[' -> ']';
            default -> ' ';
        };
    }


    public static void main(String[] args) {
        String[] inputs = {
                "[]]()()", // not correct
                "c[d]", // correct\n" +
                "a{b[c]d}e", // correct\n" +
                "a{b(c]d}e", // not correct; ] doesn't match (\n" +
                "a[b{c}d]e}", // not correct; nothing matches final }\n" +
                "a{b(c) ", // // not correct; Nothing matches opening {
                "{[()]}",//correct
                "{[(])}",//incorrect
                "{{[[(())]]}}",//correct
                "][]][][[]][]][][[[",//incorrect
                "(((abc))((d)))))"//incorrect
        };

        for (String input : inputs) {
            BracketChecker checker = new BracketChecker(input);
            System.out.println("checking: " + input);
            checker.check();
        }
    }
}