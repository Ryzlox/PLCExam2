public class Token {
    String token;
    String lexeme;

    public Token(String token, String lexeme) { //constructor for tokens
        this.token = token;
        this.lexeme = lexeme;
    }

    public String toString() {
        return output(lexeme,token);
    }
    String output(String lex, String token) { //output of each lexeme and token
        String output = lex;
        for(int i = lex.length() ; i < 16 ; i++) {
            output+=' ';
        }
        return output + token;
    }

}