import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Lexeme {
    BufferedReader buffReader;
    char currentChar;
    List<Token> tokenList = new ArrayList<>();
    public static final String KEY_WORDS[] = new String[]{
            "import","return", "class", "public", "while", "if", "else", "private",
			"protected", "switch", "case", "super", "boolean", "package", "static", "implements", "interface",
			"new", "continue", "this", "final", "int", "char", "byte", "String", "float", "double", "try"
			};

    public Lexeme(File file) { //reading from file to obtain input to determine lexemes

        try {
            buffReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        currentChar = readNextChar();
    }

    public List<Token> generateTokens() { //obtaining token list
        Token token = readNextToken();
        while (token != null) {
            tokenList.add(token);
            token = readNextToken();
        }
        return tokenList;
    }

    public Token readNextToken() { //checking the current token's type and evaluating what token it is, assigning it by type and moving to the next token
        int condition = 1;
        while (true) {
            if (currentChar == (char) (-1)) {
                try {
                    buffReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            switch (condition) {
                case 1: {
                    if (currentChar == '\n' || currentChar == ' ' || currentChar == '\b' || currentChar == '\f' || currentChar == '\r' ||  currentChar == '\t') {
                        currentChar = readNextChar();
                        continue;

                    } else if (currentChar == '(') {
                        currentChar = readNextChar();
                        return new Token("LEFT PARENTHESES", "(");
                    } else if (currentChar == ')') {
                        currentChar = readNextChar();
                        return new Token("RIGHT PARENTHESES", ")");
                    } else if (currentChar == '{') {
                        currentChar = readNextChar();
                        return new Token("LEFT BRACE", "{");
                    } else if (currentChar == '}') {
                        currentChar = readNextChar();
                        return new Token("RIGHT BRACE", "}");


                    } else if (currentChar == ';') {
                        currentChar = readNextChar();
                        return new Token("Semicolon", ";");

                    } else if (currentChar == '+') {
                        currentChar = readNextChar();
                        return new Token("PLUS", "+");
                    } else if (currentChar == '-') {
                        currentChar = readNextChar();
                        return new Token("MINUS", "-");
                    } else if (currentChar == '*') {
                        currentChar = readNextChar();
                        return new Token("MULTIPLICATION", "*");
                    } else if (currentChar == '/') {
                        currentChar = readNextChar();
                        return new Token("DIVISION", "/");
                    } else if (currentChar == '%') {
                        currentChar = readNextChar();
                        return new Token("MODULUS DIVISION", "%");


                    } else if (currentChar == ',') {
                        currentChar = readNextChar();
                        return new Token("COMMA", ",");
                    } else if (currentChar == '=') {
                        currentChar = readNextChar();
                        if (currentChar == '=') {
                            currentChar = readNextChar();
                            return new Token("EQUALS SIGN, CONDITIONAL", "==");
                        } else {
                            return new Token("EQUALS SIGN, ASSIGNING VALUE", "=");
                        }

                    } else if (currentChar == '!') {
                        currentChar = readNextChar();
                        if (currentChar == '=') {
                            currentChar = readNextChar();
                            return new Token("NOT EQUALS", "!=");
                        } else return new Token("NOT", "!");

                    }else if(currentChar == '&'){
                        currentChar = readNextChar();
                        if(currentChar == '&'){
                            currentChar = readNextChar();
                            return new Token("AND","&&");
                        }else return new Token("UNDEFINED","&");
  
                    }else if(currentChar == '|') {
                        currentChar = readNextChar();
                        if (currentChar == '|') {
                            currentChar = readNextChar();
                            return new Token("OR", "||");
                        } else return new Token("UNDEFINED", "|");

                    } else {
                        condition = 2;
                        continue;
                    }

                }
                case 2: { //checking if currentChar is a number and assigning it a token of decimal or integer
                    if (isNumber(currentChar)) {
                        String number = String.valueOf(currentChar);
                        for (; ; ) {
                            currentChar = readNextChar();
                            if (isNumber(currentChar) || currentChar == '.') {
                                number += String.valueOf(currentChar);
                            } else {
                                if (number.contains("."))
                                    return new Token("DECIMAL", number);
                                else return new Token("INTEGER", number);
                            }
                        }
                    } else condition = 3;
                }
                case 3: {//checking if currentChar is a number and assigning it a token of decimal or integer
                    if (isLetter(currentChar) || currentChar == '_') {
                        String word = String.valueOf(currentChar);
                        for (; ; ) {
                            currentChar = readNextChar();
                            if (isLetter(currentChar) || currentChar == '_' || isNumber(currentChar)) {
                                word += String.valueOf(currentChar);
                            } else {
                                List key_words = Arrays.asList(KEY_WORDS);

                                if (key_words.contains(word))
                                    return new Token("KEYWORD", word);
                                else return new Token("IDENTIFYING TERM", word);
                            }
                        }
                    } else {
                        currentChar = readNextChar();
                        return new Token("ERROR: ", "UNDEFINED " + currentChar);
                    }
                }
            }
        }


    }

    char readNextChar() {
        try {
            return (char) buffReader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (char) (-1);
    }

    boolean isNumber(char c) {
        if (c >= '0' && c <= '9')
            return true;

        return false;
    }

    boolean isLetter(char c) {
        if (c >= 'a' && c <= 'z')
            return true;
        if (c >= 'A' && c <= 'Z')
            return true;

        return false;

    }
}