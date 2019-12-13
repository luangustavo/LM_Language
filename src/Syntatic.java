public class Syntatic {
    private static LexicalAnalyzer lexicalAnalyzer;

    public Syntatic (){
    }

    public static void main(String[] args) {
        if(args.length>0) {

            /*Criando o analisador e passando o arquivo*/
            lexicalAnalyzer = new LexicalAnalyzer(args[0]);

            Token token;
            while(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    System.out.println(token.toString());
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }

            }
        }

    }

}