package syntatic;

import lexical.LexicalAnalyzer;
import lexical.Token;

public class SyntaticAnalyzer {
    private static LexicalAnalyzer.Token token = null;
    private static LexicalAnalyzer lexicalAnalyzer;
    

    public SyntaticAnalyzer (){
    }

    public static void analyzer(String[] args) {
        if(args.length>0) {

            /*Criando o analisador e passando o arquivo*/
            lexicalAnalyzer = new LexicalAnalyzer(args[0]);

            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    MODULE();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }

            }

        }

    }


    public static void MODULE() {
        System.out.println("          FUNCTIONS MAIN");

        FUNCTIONS();
        MAIN();

    }

    public static void FUNCTIONS(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ID)){

            System.out.println("          'ID' PARAMS RETURNTYPE ESCOPE FUNCTIONS");

            System.out.println(token.toString());

            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    PARAMS();
                    RETURNTYPE();
                    ESCOPE();
                    FUNCTIONS();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }

            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }
        }
        else{
            System.out.println("          ε");
        }
    }

    public static void MAIN(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.MAIN)){

            System.out.println("          'MAIN' 'OP' 'CP' 'VOID' ESCOPE");

            System.out.println(token.toString());

            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();

                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)) {


                        System.out.println(token.toString());

                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){
                            try {
                                token = lexicalAnalyzer.nextToken();

                                if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)) {

                                    System.out.println(token.toString());

                                    /*Next Token*/
                                    if(lexicalAnalyzer.hasMoreTokens()){
                                        try {
                                            token = lexicalAnalyzer.nextToken();

                                            if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.VOID)) {

                                                System.out.println(token.toString());

                                                /*Next Token*/
                                                if(lexicalAnalyzer.hasMoreTokens()){
                                                    try {
                                                        token = lexicalAnalyzer.nextToken();
                                                        ESCOPE();
                                                    } catch (Exception e) {
                                                        System.err.println(e);
                                                        System.exit(1);
                                                    }

                                                }
                                                else{
                                                    System.out.println("          ERROR: EOF inesperado.");
                                                }

                                            }
                                            else{
                                                System.out.println("          ERROR: Token 'VOID' esperado. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                                            }
                                        } catch (Exception e) {
                                            System.err.println(e);
                                            System.exit(1);
                                        }

                                    }
                                    else{
                                        System.out.println("          ERROR: EOF inesperado.");
                                    }
                                }
                                else{
                                    System.out.println("          ERROR: Token 'CP' esperado. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                                }
                            } catch (Exception e) {
                                System.err.println(e);
                                System.exit(1);
                            }

                        }
                        else{
                            System.out.println("          ERROR: EOF inesperado.");
                        }

                    }
                    else{
                        System.out.println("          ERROR: Token 'OP' esperado. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                    }

                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }

            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }
        }
        else{
            System.out.println("          ERROR: Token 'MAIN' esperado. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }
    }

    public static void PARAMS(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

            System.out.println("          'OP' PARAMSEXT");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    PARAMSEXT();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }

            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }
        }
        else{
            System.out.println("          ERROR: Token 'OP' esperado. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }


    }

    public static void PARAMSEXT() {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)){

            System.out.println("          'CP'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();

                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }

            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          LISTPARAMS 'CP'");
            LISTPARAMS();
            if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)) {
                System.out.println(token.toString());

                /*Next Token*/
                if(lexicalAnalyzer.hasMoreTokens()){
                    try {
                        token = lexicalAnalyzer.nextToken();

                    } catch (Exception e) {
                        System.err.println(e);
                        System.exit(1);
                    }

                }
                else{
                    System.out.println("          ERROR: EOF inesperado.");
                }


            }
            else{
                System.out.println("          ERROR: Token 'CP' esperado. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
            }


        }

    }

    public static void LISTPARAMS(){

        System.out.println("          TYPE NAME LISTPARAMSEXT");

        TYPE();
        NAME();
        LISTPARAMSEXT();

    }

    public static void LISTPARAMSEXT(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.SPTR)){

            System.out.println("          'SPTR' LISTPARAMS");

            System.out.println(token.toString());

            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    LISTPARAMS();

                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }

            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          ε");
        }

    }

    public static void TYPE(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.INT)){
            System.out.println("          'INT'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }
        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.BOOL)){
            System.out.println("          'BOOL'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }
        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CHAR)){
            System.out.println("          'CHAR'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }
        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.STRING)){
            System.out.println("          'STRING'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }
        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.FLOAT)){
            System.out.println("          'FLOAT'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }
        }else{
            System.out.println("          ERROR: Tokens 'INT', 'BOOL', 'CHAR', 'STRING', 'FLOAT' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }

    }

    public static void RETURNTYPE(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.VOID)){
            System.out.println("          'VOID'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          TYPE RETURNTYPEEXT");

            TYPE();
            RETURNTYPEEXT();
        }

    }

    public static void RETURNTYPEEXT(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OB)){
            System.out.println("          'OB' 'CB'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();

                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CB)){
                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){
                            try {
                                token = lexicalAnalyzer.nextToken();
                            } catch (Exception e) {
                                System.err.println(e);
                                System.exit(1);
                            }
                        }
                        else{
                            System.out.println("          ERROR: EOF inesperado.");
                        }

                    }
                    else{
                        System.out.println("          ERROR: Tokens 'CB' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                    }


                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          ε");
        }

    }

    public static void NAME(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ID)){
            System.out.println("          'ID' NAMEEXT");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    NAMEEXT();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          ERROR: Tokens 'ID' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }

    }

    public static void NAMEEXT(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OB)){
            System.out.println("          'OB' EA 'CB'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    EA();

                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CB)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){
                            try {
                                token = lexicalAnalyzer.nextToken();

                            } catch (Exception e) {
                                System.err.println(e);
                                System.exit(1);
                            }
                        }
                        else{
                            System.out.println("          ERROR: EOF inesperado.");
                        }

                    }
                    else{
                        System.out.println("          ERROR: Tokens 'CB' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                    }
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          ε");
        }

    }

    /*public static void INDEX() {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ID)){
            System.out.println("          'ID'");

            System.out.println(token.toString());
            //Next Token
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }
        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CTEINT)){
            System.out.println("          'CTEINT'");

            System.out.println(token.toString());
            //Next Token
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }
        }else{
            System.out.println("          ERROR: Tokens 'ID', 'CTEINT' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }

    }*/

    public static void ESCOPE(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OK)){
            System.out.println("          'OK' COMMANDS 'CK' 'SCO' ");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    COMMANDS();

                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CK)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){
                            try {
                                token = lexicalAnalyzer.nextToken();

                                if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.SCO)){

                                    System.out.println(token.toString());
                                    /*Next Token*/
                                    if(lexicalAnalyzer.hasMoreTokens()){
                                        try {
                                            token = lexicalAnalyzer.nextToken();
                                        } catch (Exception e) {
                                            System.err.println(e);
                                            System.exit(1);
                                        }
                                    }
                                    else{
                                        //System.out.println("          ERROR: EOF inesperado.");
                                    }

                                }
                                else{
                                    System.out.println("          ERROR: Tokens 'SCO' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                                }
                            } catch (Exception e) {
                                System.err.println(e);
                                System.exit(1);
                            }
                        }
                        else{
                            System.out.println("          ERROR: EOF inesperado.");
                        }

                    }
                    else{
                        System.out.println("          ERROR: Tokens 'CK' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                    }
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }


        }
        else{
            System.out.println("          ERROR: Tokens 'OK' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }

    }

    public static void COMMANDS(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.INT)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.BOOL)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.CHAR)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.STRING)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.FLOAT)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.ID)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.PRINT)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.READ)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.IF)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.WHEN)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.REPEATER)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.RETURN)){

            System.out.println("          CMD 'SCO' COMMANDS");

            CMD();

            if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.SCO)){

                System.out.println(token.toString());
                /*Next Token*/
                if(lexicalAnalyzer.hasMoreTokens()){
                    try {
                        token = lexicalAnalyzer.nextToken();
                        COMMANDS();
                    } catch (Exception e) {
                        System.err.println(e);
                        System.exit(1);
                    }
                }
                else{
                    System.out.println("          ERROR: EOF inesperado.");
                }

            }
            else{
                System.out.println("          ERROR: Tokens 'SCO' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
            }

        }
        else{

            System.out.println("          ε");

        }

    }

    public static void CMD(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.INT)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.BOOL)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.CHAR)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.STRING)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.FLOAT)) {

            System.out.println("          DECLARATION");

            DECLARATION();

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ID)){

            System.out.println("          'ID' CMDEXT");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    CMDEXT();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.PRINT)){

            System.out.println("          PRINT");

            PRINT();

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.READ)){

            System.out.println("          READ");

            READ();

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.IF)){

            System.out.println("          IFELSE");

            IFELSE();

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.WHEN)){

            System.out.println("          WHEN");

            WHEN();

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.REPEATER)){

            System.out.println("          REPEATER");

            REPEATER();

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.RETURN)){

            System.out.println("          RETURN");

            RETURN();

        }

    }

    public static void DECLARATION(){

        System.out.println("          TYPE NAME");

        TYPE();
        NAME();
    }

    public static void CMDEXT(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

            System.out.println("          FUNCTIONCALL");


            FUNCTIONCALL();

        }else{

            System.out.println("          ATTRIBUTION");


            ATTRIBUTION();
        }
    }

    public static void ATTRIBUTION(){

        System.out.println("          NAMEEXT 'ATR' VALUE");

        NAMEEXT();

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ATR)){

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    VALUE();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{

            System.out.println("          ERROR: Tokens 'ATR' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

        }
    }

    public static void VALUE(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OB)){

            System.out.println("          ARRAY");

            ARRAY();

        }
        else{

            System.out.println("          EA");

            EA();

        }
    }

    public static void ARRAY(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OB)){

            System.out.println("          'OB' ARRAYEXT");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    ARRAYEXT();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          ERROR: Tokens 'OB' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }
    }

    public static void ARRAYEXT(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CB)){

            System.out.println("          'CB'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{

            System.out.println("          ELEMENTS 'CB'");


            ELEMENTS();
            if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CB)){

                System.out.println(token.toString());
                /*Next Token*/
                if(lexicalAnalyzer.hasMoreTokens()){
                    try {
                        token = lexicalAnalyzer.nextToken();
                    } catch (Exception e) {
                        System.err.println(e);
                        System.exit(1);
                    }
                }
                else{
                    System.out.println("          ERROR: EOF inesperado.");
                }

            }
        }
    }

    public static void ELEMENTS(){

        System.out.println("          CONSTANT ELEMENTSEXT");


        CONSTANT();
        ELEMENTSEXT();
    }

    public static void ELEMENTSEXT(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.SPTR)){

            System.out.println("          'SPTR' ELEMENTS");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    ELEMENTS();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          ε");
        }
    }

    public static void FUNCTIONCALL(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

            System.out.println("          'OP' LISTPARAMSCALL 'CP'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    LISTPARAMSCALL();

                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){
                            try {
                                token = lexicalAnalyzer.nextToken();
                            } catch (Exception e) {
                                System.err.println(e);
                                System.exit(1);
                            }
                        }
                        else{
                            System.out.println("          ERROR: EOF inesperado.");
                        }

                    }
                    else{
                        System.out.println("          ERROR: Tokens 'CP' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                    }
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          ERROR: Tokens 'OP' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }
    }

    public static void LISTPARAMSCALL(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CTEINT)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.CTEBOOL)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.CTECHAR)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.CTESTRING)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.CTEFLOAT)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.ID)){
            System.out.println("          PARAMITEM LISTPARAMSCALLEXT");

            PARAMITEM();
            LISTPARAMSCALLEXT();
        }
        else{

            System.out.println("          ε");

        }


    }

    public static void LISTPARAMSCALLEXT(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.SPTR)){

            System.out.println("          'SPTR' LISTPARAMSCALL");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    LISTPARAMSCALL();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }



        }
        else{
            System.out.println("          ε");
        }
    }

    public static void PARAMITEM(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ID)){

            System.out.println("          NAME");

            NAME();

        }
        else{
            System.out.println("          CONSTANT");

            CONSTANT();
        }
    }

    public static void PRINT(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.PRINT)){

            System.out.println("          'PRINT' 'OP' MESSAGE PRINTEXT 'CP'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();


                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){
                            try {
                                token = lexicalAnalyzer.nextToken();
                                MESSAGE();
                                PRINTEXT();

                                if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)){

                                    System.out.println(token.toString());
                                    /*Next Token*/
                                    if(lexicalAnalyzer.hasMoreTokens()){
                                        try {
                                            token = lexicalAnalyzer.nextToken();

                                        } catch (Exception e) {
                                            System.err.println(e);
                                            System.exit(1);
                                        }
                                    }
                                    else{
                                        System.out.println("          ERROR: EOF inesperado.");
                                    }

                                }
                                else{
                                    System.out.println("          ERROR: Tokens 'CP' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                                }
                            } catch (Exception e) {
                                System.err.println(e);
                                System.exit(1);
                            }
                        }
                        else{
                            System.out.println("          ERROR: EOF inesperado.");
                        }

                    }
                    else{

                        System.out.println("          ERROR: Tokens 'OP' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                    }
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          ERROR: Tokens 'PRINT' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

        }
    }

    public static void PRINTEXT(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.SPTR)){

            System.out.println("          'SPTR' NAME PRINTEXT");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    NAME();
                    PRINTEXT();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{

            System.out.println("          ε");

        }

    }

    public static void MESSAGE(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CTESTRING)){

            System.out.println("          'CTESTRING' MESSAGEEXT");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    MESSAGEEXT();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else {

            System.out.println("          NAME MESSAGEEXT");

            NAME();
            MESSAGEEXT();

        }
    }

    public static void MESSAGEEXT(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CONCAT)){

            System.out.println("          'CONCAT' MESSAGE");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    MESSAGE();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          ε");
        }
    }

    public static void READ(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.READ)){

            System.out.println("          'READ' 'OP' READEXT 'CP'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();


                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){
                            try {
                                token = lexicalAnalyzer.nextToken();
                                READEXT();

                                if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)){

                                    System.out.println(token.toString());
                                    /*Next Token*/
                                    if(lexicalAnalyzer.hasMoreTokens()){
                                        try {
                                            token = lexicalAnalyzer.nextToken();
                                        } catch (Exception e) {
                                            System.err.println(e);
                                            System.exit(1);
                                        }
                                    }
                                    else{
                                        System.out.println("          ERROR: EOF inesperado.");
                                    }

                                }
                                else{
                                    System.out.println("          ERROR: Tokens 'CP' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                                }
                            } catch (Exception e) {
                                System.err.println(e);
                                System.exit(1);
                            }
                        }
                        else{
                            System.out.println("          ERROR: EOF inesperado.");
                        }
                    }
                    else{

                        System.out.println("          ERROR: Tokens 'OP' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                    }
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{

            System.out.println("          ERROR: Tokens 'READ' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

        }
    }

    public static void READEXT(){

        System.out.println("          NAME READEXTR");

        NAME();
        READEXTR();
    }

    public static void READEXTR(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.SPTR)){

            System.out.println("          'SPTR' READEXT");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    READEXT();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          ε");
        }
    }

    public static void IFELSE(){

        System.out.println("          IF ELIF ELSE");

        IF();
        ELIF();
        ELSE();
    }

    public static void IF(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.IF)){

            System.out.println("          'IF' 'OP' EB 'CP' 'OK' COMMANDS 'CK'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();


                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){
                            try {
                                token = lexicalAnalyzer.nextToken();
                                EB();

                                if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)){

                                    System.out.println(token.toString());
                                    /*Next Token*/
                                    if(lexicalAnalyzer.hasMoreTokens()){
                                        try {
                                            token = lexicalAnalyzer.nextToken();


                                            if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OK)){

                                                System.out.println(token.toString());
                                                /*Next Token*/
                                                if(lexicalAnalyzer.hasMoreTokens()){
                                                    try {
                                                        token = lexicalAnalyzer.nextToken();
                                                        COMMANDS();

                                                        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CK)){

                                                            System.out.println(token.toString());
                                                            /*Next Token*/
                                                            if(lexicalAnalyzer.hasMoreTokens()){
                                                                try {
                                                                    token = lexicalAnalyzer.nextToken();

                                                                } catch (Exception e) {
                                                                    System.err.println(e);
                                                                    System.exit(1);
                                                                }
                                                            }
                                                            else{
                                                                System.out.println("          ERROR: EOF inesperado.");
                                                            }

                                                        }
                                                        else{

                                                            System.out.println("          ERROR: Tokens 'CK' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                                                        }

                                                    } catch (Exception e) {
                                                        System.err.println(e);
                                                        System.exit(1);
                                                    }
                                                }
                                                else{
                                                    System.out.println("          ERROR: EOF inesperado.");
                                                }

                                            }
                                            else{

                                                System.out.println("          ERROR: Tokens 'OK' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                                            }
                                        } catch (Exception e) {
                                            System.err.println(e);
                                            System.exit(1);
                                        }
                                    }
                                    else{
                                        System.out.println("          ERROR: EOF inesperado.");
                                    }

                                }
                                else{

                                    System.out.println("          ERROR: Tokens 'CP' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                                }
                            } catch (Exception e) {
                                System.err.println(e);
                                System.exit(1);
                            }
                        }
                        else{
                            System.out.println("          ERROR: EOF inesperado.");
                        }

                    }
                    else{

                        System.out.println("          ERROR: Tokens 'OP' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                    }
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{

            System.out.println("          ERROR: Tokens 'IF' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

        }
    }

    public static void ELIF(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ELIF)){

            System.out.println("          'ELIF' 'OP' EB 'CP' 'OK' COMMANDS 'CK' ELIF");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();


                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){
                            try {
                                token = lexicalAnalyzer.nextToken();
                                EB();

                                if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)){

                                    System.out.println(token.toString());
                                    /*Next Token*/
                                    if(lexicalAnalyzer.hasMoreTokens()){
                                        try {
                                            token = lexicalAnalyzer.nextToken();
                                            if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OK)){

                                                System.out.println(token.toString());
                                                /*Next Token*/
                                                if(lexicalAnalyzer.hasMoreTokens()){
                                                    try {
                                                        token = lexicalAnalyzer.nextToken();
                                                        COMMANDS();

                                                        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CK)){

                                                            System.out.println(token.toString());
                                                            /*Next Token*/
                                                            if(lexicalAnalyzer.hasMoreTokens()){
                                                                try {
                                                                    token = lexicalAnalyzer.nextToken();

                                                                } catch (Exception e) {
                                                                    System.err.println(e);
                                                                    System.exit(1);
                                                                }
                                                            }
                                                            else{
                                                                System.out.println("          ERROR: EOF inesperado.");
                                                            }

                                                        }
                                                        else{

                                                            System.out.println("          ERROR: Tokens 'CK' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                                                        }

                                                    } catch (Exception e) {
                                                        System.err.println(e);
                                                        System.exit(1);
                                                    }
                                                }
                                                else{
                                                    System.out.println("          ERROR: EOF inesperado.");
                                                }

                                            }
                                            else{

                                                System.out.println("          ERROR: Tokens 'OK' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                                            }
                                        } catch (Exception e) {
                                            System.err.println(e);
                                            System.exit(1);
                                        }
                                    }
                                    else{
                                        System.out.println("          ERROR: EOF inesperado.");
                                    }

                                }
                                else{

                                    System.out.println("          ERROR: Tokens 'CP' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                                }
                            } catch (Exception e) {
                                System.err.println(e);
                                System.exit(1);
                            }
                        }
                        else{
                            System.out.println("          ERROR: EOF inesperado.");
                        }

                    }
                    else{

                        System.out.println("          ERROR: Tokens 'OP' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                    }
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{

            System.out.println("          ε");

        }
    }

    public static void ELSE(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ELSE)){

            System.out.println("          'ELSE' 'OK' COMMANDS 'CK'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();


                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OK)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){
                            try {
                                token = lexicalAnalyzer.nextToken();
                                COMMANDS();

                                if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CK)){

                                    System.out.println(token.toString());
                                    /*Next Token*/
                                    if(lexicalAnalyzer.hasMoreTokens()){
                                        try {
                                            token = lexicalAnalyzer.nextToken();

                                        } catch (Exception e) {
                                            System.err.println(e);
                                            System.exit(1);
                                        }
                                    }
                                    else{
                                        System.out.println("          ERROR: EOF inesperado.");
                                    }

                                }
                                else{

                                    System.out.println("          ERROR: Tokens 'CK' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                                }
                            } catch (Exception e) {
                                System.err.println(e);
                                System.exit(1);
                            }
                        }
                        else{
                            System.out.println("          ERROR: EOF inesperado.");
                        }

                    }
                    else{

                        System.out.println("          ERROR: Tokens 'OK' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                    }
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{

            System.out.println("          ε");

        }
    }

    public static void WHEN(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.WHEN)){

            System.out.println("          'WHEN' 'OP' EB 'CP' 'OK' COMMANDS 'CK'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();


                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){
                            try {
                                token = lexicalAnalyzer.nextToken();
                                EB();

                                if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)){

                                    System.out.println(token.toString());
                                    /*Next Token*/
                                    if(lexicalAnalyzer.hasMoreTokens()){
                                        try {
                                            token = lexicalAnalyzer.nextToken();
                                            if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OK)){

                                                System.out.println(token.toString());
                                                /*Next Token*/
                                                if(lexicalAnalyzer.hasMoreTokens()){
                                                    try {
                                                        token = lexicalAnalyzer.nextToken();
                                                        COMMANDS();

                                                        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CK)){

                                                            System.out.println(token.toString());
                                                            /*Next Token*/
                                                            if(lexicalAnalyzer.hasMoreTokens()){
                                                                try {
                                                                    token = lexicalAnalyzer.nextToken();

                                                                } catch (Exception e) {
                                                                    System.err.println(e);
                                                                    System.exit(1);
                                                                }
                                                            }
                                                            else{
                                                                System.out.println("          ERROR: EOF inesperado.");
                                                            }

                                                        }
                                                        else{

                                                            System.out.println("          ERROR: Tokens 'CK' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                                                        }

                                                    } catch (Exception e) {
                                                        System.err.println(e);
                                                        System.exit(1);
                                                    }
                                                }
                                                else{
                                                    System.out.println("          ERROR: EOF inesperado.");
                                                }

                                            }
                                            else{

                                                System.out.println("          ERROR: Tokens 'OK' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                                            }
                                        } catch (Exception e) {
                                            System.err.println(e);
                                            System.exit(1);
                                        }
                                    }
                                    else{
                                        System.out.println("          ERROR: EOF inesperado.");
                                    }

                                }
                                else{

                                    System.out.println("          ERROR: Tokens 'CP' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                                }
                            } catch (Exception e) {
                                System.err.println(e);
                                System.exit(1);
                            }
                        }
                        else{
                            System.out.println("          ERROR: EOF inesperado.");
                        }

                    }
                    else{

                        System.out.println("          ERROR: Tokens 'OP' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                    }
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{

            System.out.println("          ERROR: Tokens 'WHEN' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

        }
    }

    public static void REPEATER(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.REPEATER)){

            System.out.println("          'REPEATER' 'OP' 'ID' ATTRIBUTION 'SCO' PARAMITEM 'SCO' PARAMITEM 'CP' 'OK' COMMANDS 'CK'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();

                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){
                            try {
                                token = lexicalAnalyzer.nextToken();

                                if (token.getCategory().equals(LexicalAnalyzer.TokenCategory.ID)) {

                                    System.out.println("          'SPTR' READEXT");

                                    System.out.println(token.toString());
                                    /*Next Token*/
                                    if(lexicalAnalyzer.hasMoreTokens()){
                                        try {
                                            token = lexicalAnalyzer.nextToken();

                                            ATTRIBUTION();

                                            if (token.getCategory().equals(LexicalAnalyzer.TokenCategory.SCO)) {

                                                System.out.println(token.toString());
                                                /*Next Token*/
                                                if (lexicalAnalyzer.hasMoreTokens()) {
                                                    try {
                                                        token = lexicalAnalyzer.nextToken();

                                                        PARAMITEM();


                                                        if (token.getCategory().equals(LexicalAnalyzer.TokenCategory.SCO)) {

                                                            System.out.println(token.toString());
                                                            /*Next Token*/
                                                            if (lexicalAnalyzer.hasMoreTokens()) {
                                                                try {
                                                                    token = lexicalAnalyzer.nextToken();

                                                                    PARAMITEM();

                                                                    if (token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)) {

                                                                        System.out.println(token.toString());
                                                                        /*Next Token*/
                                                                        if (lexicalAnalyzer.hasMoreTokens()) {
                                                                            try {
                                                                                token = lexicalAnalyzer.nextToken();

                                                                                if (token.getCategory().equals(LexicalAnalyzer.TokenCategory.OK)) {

                                                                                    System.out.println(token.toString());
                                                                                    /*Next Token*/
                                                                                    if (lexicalAnalyzer.hasMoreTokens()) {
                                                                                        try {
                                                                                            token = lexicalAnalyzer.nextToken();

                                                                                            COMMANDS();

                                                                                            if (token.getCategory().equals(LexicalAnalyzer.TokenCategory.CK)) {

                                                                                                System.out.println(token.toString());
                                                                                                /*Next Token*/
                                                                                                if (lexicalAnalyzer.hasMoreTokens()) {
                                                                                                    try {
                                                                                                        token = lexicalAnalyzer.nextToken();

                                                                                                    } catch (Exception e) {
                                                                                                        System.err.println(e);
                                                                                                        System.exit(1);
                                                                                                    }
                                                                                                } else {
                                                                                                    System.out.println("          ERROR: EOF inesperado.");
                                                                                                }

                                                                                            } else {
                                                                                                System.out.println("          ERROR: Tokens 'CK' esperados. L:" + token.getLine() + " C:" + token.getColumn() + " Tk:" + token.getValue());
                                                                                            }

                                                                                        } catch (Exception e) {
                                                                                            System.err.println(e);
                                                                                            System.exit(1);
                                                                                        }
                                                                                    } else {
                                                                                        System.out.println("          ERROR: EOF inesperado.");
                                                                                    }

                                                                                } else {
                                                                                    System.out.println("          ERROR: Tokens 'OK' esperados. L:" + token.getLine() + " C:" + token.getColumn() + " Tk:" + token.getValue());
                                                                                }

                                                                            } catch (Exception e) {
                                                                                System.err.println(e);
                                                                                System.exit(1);
                                                                            }
                                                                        } else {
                                                                            System.out.println("          ERROR: EOF inesperado.");
                                                                        }

                                                                    } else {
                                                                        System.out.println("          ERROR: Tokens 'CP' esperados. L:" + token.getLine() + " C:" + token.getColumn() + " Tk:" + token.getValue());
                                                                    }

                                                                } catch (Exception e) {
                                                                    System.err.println(e);
                                                                    System.exit(1);
                                                                }
                                                            } else {
                                                                System.out.println("          ERROR: EOF inesperado.");
                                                            }

                                                        } else {
                                                            System.out.println("          ERROR: Tokens 'SCO' esperados. L:" + token.getLine() + " C:" + token.getColumn() + " Tk:" + token.getValue());
                                                        }

                                                    } catch (Exception e) {
                                                        System.err.println(e);
                                                        System.exit(1);
                                                    }
                                                } else {
                                                    System.out.println("          ERROR: EOF inesperado.");
                                                }

                                            } else {
                                                System.out.println("          ERROR: Tokens 'SCO' esperados. L:" + token.getLine() + " C:" + token.getColumn() + " Tk:" + token.getValue());
                                            }

                                        } catch (Exception e) {
                                            System.err.println(e);
                                            System.exit(1);
                                        }
                                    }
                                    else{
                                        System.out.println("          ERROR: EOF inesperado.");
                                    }


                                }
                                else{
                                    System.out.println("          ERROR: Tokens 'ID' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                                }

                            } catch(Exception e){
                                System.err.println(e);
                                System.exit(1);
                            }

                        }
                        else{
                            System.out.println("          ERROR: EOF inesperado.");
                        }

                    }
                    else{
                        System.out.println("          ERROR: Tokens 'OP' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                    }

                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          ERROR: Tokens 'REPEATER' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }
    }

    public static void RETURN(){
        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.RETURN)){

            System.out.println("          'RETURN' RETURNEXT");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    RETURNEXT();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          ERROR: Tokens 'RETURN' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }
    }

    public static void RETURNEXT(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ID)){

            System.out.println("          NAME");

            NAME();

        }
        else{
            System.out.println("          CONSTANT");

            CONSTANT();
        }
    }

    public static void EB(){

        System.out.println("          TB EBR");

        TB();
        EBR();
    }

    public static void EBR(){
        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OR)){

            System.out.println("          'OR' TB EBR");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    TB();
                    EBR();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          ε");
        }
    }

    public static void TB(){

        System.out.println("          FB TBR");

        FB();
        TBR();
    }

    public static void TBR(){
        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.AND)){

            System.out.println("          'AND' FB TBR");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    FB();
                    TBR();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          ε");
        }
    }

    public static void FB(){
        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.NOT)){

            System.out.println("          'NOT' FB");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    FB();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{

            System.out.println("          EA EREL");
            EA();
            EREL();
        }
    }

    public static void EREL(){
        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ORC)){

            System.out.println("          'ORC' EA");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    EA();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ORE)){

            System.out.println("          'ORE' EA");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    EA();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }else{
            System.out.println("          ERROR: Tokens 'ORC', 'ORE' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }
    }

    public static void EA(){

        System.out.println("          TA EAR");

        TA();
        EAR();
    }

    public static void EAR(){
        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OPA)){

            System.out.println("          'OPA' TA EAR");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    TA();
                    EAR();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          ε");
        }
    }

    public static void TA(){

        System.out.println("          PA TAR");

        PA();
        TAR();
    }

    public static void TAR(){
        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OPM)){

            System.out.println("          'OPM' PA TAR");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    PA();
                    TAR();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          ε");
        }
    }

    public static void PA(){

        System.out.println("          FA PAFAT");

        FA();
        PAFAT();
    }

    public static void PAFAT(){
        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OPM)){

            System.out.println("          'OPE' PA");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    PA();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          ε");
        }
    }

    public static void FA(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OPU)){

            System.out.println("          'OPU' FA");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    FA();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

            System.out.println("          'OP' EB 'CP'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    EB();

                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){
                            try {
                                token = lexicalAnalyzer.nextToken();

                            } catch (Exception e) {
                                System.err.println(e);
                                System.exit(1);
                            }
                        }
                        else{
                            System.out.println("          ERROR: EOF inesperado.");
                        }

                    }
                    else{

                        System.out.println("          ERROR: Tokens 'CP' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                    }
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ID)){

            System.out.println("          ID");

            ID();

        }else{
            System.out.println("          CONSTANT");

            CONSTANT();
        }
    }

    public static void ID(){
        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ID)){

            System.out.println("          'ID' IDFAT");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    IDFAT();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          ERROR: Tokens 'ID' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }
    }

    public static void IDFAT(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OB)){

            System.out.println("          'OB' EA 'CB'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    EA();

                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CB)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){
                            try {
                                token = lexicalAnalyzer.nextToken();

                            } catch (Exception e) {
                                System.err.println(e);
                                System.exit(1);
                            }
                        }
                        else{
                            System.out.println("          ERROR: EOF inesperado.");
                        }

                    }
                    else{

                        System.out.println("          ERROR: Tokens 'CB' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                    }
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

            System.out.println("          'OP' LISTPARAMSCALL 'CP'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                    LISTPARAMSCALL();

                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){
                            try {
                                token = lexicalAnalyzer.nextToken();

                            } catch (Exception e) {
                                System.err.println(e);
                                System.exit(1);
                            }
                        }
                        else{
                            System.out.println("          ERROR: EOF inesperado.");
                        }

                    }
                    else{

                        System.out.println("          ERROR: Tokens 'CP' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                    }
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }

        }else{

            System.out.println("          ε");
        }
    }

    public static void CONSTANT(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CTEINT)){
            System.out.println("          'CTEINT'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }
        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CTEFLOAT)){
            System.out.println("          'CTEFLOAT'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }
        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CTEBOOL)){
            System.out.println("          'CTEBOOL'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }
        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CTECHAR)){
            System.out.println("          'CTECHAR'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }
        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CTESTRING)){
            System.out.println("          'CTESTRING'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                try {
                    token = lexicalAnalyzer.nextToken();
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
            else{
                System.out.println("          ERROR: EOF inesperado.");
            }
        }else{
            System.out.println("          ERROR: Tokens 'CTEINT', 'CTEFLOAT', 'CTEBOOL', 'CTECHAR', 'CTESTRING' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }

    }
}