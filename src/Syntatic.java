public class Syntatic {
    private static LexicalAnalyzer lexicalAnalyzer;

    public Syntatic (){
    }

    public static void main(String[] args) {
        if(args.length>0) {

            /*Criando o analisador e passando o arquivo*/
            lexicalAnalyzer = new LexicalAnalyzer(args[0]);
            /*Lendo o arquivo e executando o analisador lexico*/
            lexicalAnalyzer.readFile();
        }

    }

    public void Analyzer(){
        Token token;
        token = this.lexicalAnalyzer.nextToken();
        System.out.println(token.toString());

    }

    /*Tratando erros Lexicos*/
    public void printError(int type, String token, int currentLine, int currentColumn) {
        String msg_error = "";
        if(type == 1){
            msg_error = "Identificador muito longo.";
        }else if(type == 2){
            msg_error = "Identificador nao iniciado com letra.";
        }else if(type == 3){
            msg_error = "Constante float em formato errado.";
        }else if(type == 4){
            msg_error = "Caracter nao fechado corretamente com '.";
        }else if(type == 5){
            msg_error = "Cadeia de caracteres nao fechada corretamente com '\"'.";
        }

        System.err.println("Erro na linha: " + currentLine + ", coluna: " + currentColumn + ", token: '" + token
                + "'" + ", msg: " + msg_error);
        System.exit(1);
    }
}