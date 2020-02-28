package syntatic;

import lexical.LexicalAnalyzer;
import lexical.Token;

public class Compiler {

    private static LexicalAnalyzer lexicalAnalyzer;
    private static SyntaticAnalyzer syntaticAnalyzer;


    public Compiler (){
    }

    public static void main(String[] args) {
        if(args.length>0) {

        /*Criando o analisador e passando o arquivo*/
        lexicalAnalyzer = new LexicalAnalyzer(args[0]);

        syntaticAnalyzer = new SyntaticAnalyzer(lexicalAnalyzer);

        syntaticAnalyzer.MODULE();

        }

    }
}
