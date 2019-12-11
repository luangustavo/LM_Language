public class Syntatic {
    private static LexicalAnalyzer lexicalAnalyzer;

    public Syntatic (){
    }

    public static void main(String[] args) {
        //if(args.length>0) {

            /*Criando o analisador e passando o arquivo*/
            //lexicalAnalyzer = new LexicalAnalyzer(args[0]);
            lexicalAnalyzer = new LexicalAnalyzer("files/alomundo.lm");

            Token token;
            while(lexicalAnalyzer.hasMoreTokens()){
                token = lexicalAnalyzer.nextToken();
                System.out.println(token.toString());
            }
        //}

    }

    /*Tratando erros Lexicos*/
    public void printError(int type, String token, int currentLine, int currentColumn) {
        String[] msg_error = {"Identificador nao iniciado com letra.",
                "Constante float em formato errado.",
                "Caracter nao fechado corretamente com '.",
                "Cadeia de caracteres nao fechada corretamente com '\"'."};

        System.err.println("Erro na linha: " + currentLine + ", coluna: " + currentColumn + ", token: '" + token
                + "'" + ", msg: " + msg_error[type-1]);
        System.exit(1);
    }
}