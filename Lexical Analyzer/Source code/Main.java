import java.io.File;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        URL Input_url = ClassLoader.getSystemResource("test.txt");
        File file = null;

        try {
            file = new File("sample.txt");
            Lexeme lexeme = new Lexeme(file);
            List<Token> tokenList = lexeme.generateTokens();
            for (int i = 0; i < tokenList.size(); i++) {
                System.out.println(tokenList.get(i).toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
