package syntatic;

import lexical.LexicalAnalyzer;

import lexical.Token;

public class SyntaticAnalyzer {
    private static LexicalAnalyzer.Token token = null;
    private static LexicalAnalyzer lexicalAnalyzer;
    

    public SyntaticAnalyzer ( LexicalAnalyzer lexicalAnalyzer){

        this.lexicalAnalyzer = lexicalAnalyzer;
        if(lexicalAnalyzer.hasMoreTokens()){
            try {
                token = lexicalAnalyzer.nextToken();
            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
    }


    public static void MODULE() {
        System.out.println("          MODULE = FUNCTIONS MAIN");

        try {
            FUNCTIONS();
            MAIN();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

    }

    public static void FUNCTIONS() throws Exception {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ID)){

            try {

                System.out.println("          FUNCTIONS = 'ID' PARAMS RETURNTYPE ESCOPE ';' FUNCTIONS");

                System.out.println(token.toString());

                /*Next Token*/
                if(lexicalAnalyzer.hasMoreTokens()){

                        token = lexicalAnalyzer.nextToken();
                        PARAMS();
                        RETURNTYPE();
                        ESCOPE();

                        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.SCO)){

                            System.out.println(token.toString());

                            if(lexicalAnalyzer.hasMoreTokens()){
                                try {
                                    token = lexicalAnalyzer.nextToken();
                                    FUNCTIONS();
                                } catch (Exception e) {
                                    System.err.println(e);
                                    System.exit(1);
                                }

                            }
                            else{
                                //ACEITO
                                System.exit(0);
                                //System.out.println("          ERROR: EOF inesperado.");
                            }
                        }
                        else{
                            throw new Exception("          ERROR: Token ';' esperado. L:"+ token.getLine() + " C:" + token.getColumn() + " Tk:" + token.getValue());
                            //System.out.println("          ERROR: Token 'SCO' esperado. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                        }

                }
                else{
                    throw new Exception("          ERROR: EOF inesperado." );

                    //System.out.println("          ERROR: EOF inesperado.");
                }

            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }
        }
        else{
            System.out.println("          FUNCTIONS = ε");
        }
    }

    public static void MAIN() throws Exception {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.MAIN)){

            try {

            System.out.println("          MAIN = 'main' '(' ')' 'void' ESCOPE ';'");

            System.out.println(token.toString());

            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                    token = lexicalAnalyzer.nextToken();

                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)) {


                        System.out.println(token.toString());

                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){
                                token = lexicalAnalyzer.nextToken();

                                if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)) {

                                    System.out.println(token.toString());

                                    /*Next Token*/
                                    if(lexicalAnalyzer.hasMoreTokens()){

                                            token = lexicalAnalyzer.nextToken();

                                            if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.VOID)) {

                                                System.out.println(token.toString());

                                                /*Next Token*/
                                                if(lexicalAnalyzer.hasMoreTokens()){

                                                        token = lexicalAnalyzer.nextToken();
                                                        ESCOPE();

                                                        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.SCO)){

                                                            System.out.println(token.toString());

                                                            if(lexicalAnalyzer.hasMoreTokens()){

                                                                    token = lexicalAnalyzer.nextToken();


                                                            }
                                                            else{
                                                                //ACEITO
                                                                System.exit(0);
                                                               // System.out.println("          ERROR: EOF inesperado.");
                                                            }
                                                        }
                                                        else{
                                                            throw new Exception("          ERROR: Token ';' esperado. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                                                        }
                                                }
                                                else{
                                                    throw new Exception("          ERROR: EOF inesperado.");
                                                }

                                            }
                                            else{
                                                throw new Exception("          ERROR: Token 'void' esperado. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                                            }
                                    }
                                    else{
                                        throw new Exception("          ERROR: EOF inesperado.");
                                    }
                                }
                                else{
                                    throw new Exception("          ERROR: Token ')' esperado. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                                }
                        }
                        else{
                            throw new Exception("          ERROR: EOF inesperado.");
                        }

                    }
                    else{
                        throw new Exception("          ERROR: Token '(' esperado. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                    }

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }
        }
        else{
            throw new Exception("          ERROR: Token 'main' esperado. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue() );
        }
    }

    public static void PARAMS() throws Exception {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){
            try {
            System.out.println("          PARAMS = '(' PARAMSEXT");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    PARAMSEXT();


            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }
        }
        else{
            throw new Exception("          ERROR: Token '(' esperado. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }


    }

    public static void PARAMSEXT() throws Exception {

        try {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)){

            System.out.println("          PARAMSEXT = ')'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          PARAMSEXT = LISTPARAMS ')'");
            LISTPARAMS();
            if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)) {
                System.out.println(token.toString());

                /*Next Token*/
                if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                }
                else{
                    throw new Exception("          ERROR: EOF inesperado.");
                }


            }
            else{
                throw new Exception("          ERROR: Token ')' esperado. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
            }


        }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

    }

    public static void LISTPARAMS(){

        System.out.println("          LISTPARAMS = TYPE NAME LISTPARAMSEXT");

        try{
            TYPE();
            NAME();
            LISTPARAMSEXT();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

    }

    public static void LISTPARAMSEXT(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.SPTR)){
            try {
                System.out.println("          LISTPARAMSEXT = ';' LISTPARAMS");

                System.out.println(token.toString());

                /*Next Token*/
                if(lexicalAnalyzer.hasMoreTokens()){
                        token = lexicalAnalyzer.nextToken();
                        LISTPARAMS();
                }
                else{
                    throw new Exception("          ERROR: EOF inesperado.");
                }
            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{
            System.out.println("          LISTPARAMSEXT = ε");
        }

    }

    public static void TYPE(){

        try {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.INT)){
            System.out.println("          TYPE = 'int'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.BOOL)){
            System.out.println("          TYPE = 'bool'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CHAR)){
            System.out.println("          TYPE = 'char'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.STRING)){
            System.out.println("          TYPE = 'string'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.FLOAT)){
            System.out.println("          TYPE = 'float'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
        }else{
            throw new Exception("          ERROR: Tokens 'int', 'bool', 'char', 'string', 'float' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

    }

    public static void RETURNTYPE(){

        try {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.VOID)){
            System.out.println("          RETURNTYPE = 'void'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          RETURNTYPE = TYPE RETURNTYPEEXT");

            TYPE();
            RETURNTYPEEXT();
        }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

    }

    public static void RETURNTYPEEXT(){

        try {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OB)){
            System.out.println("          RETURNTYPEEXT = '[' ']'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();

                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CB)){
                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){

                                token = lexicalAnalyzer.nextToken();

                        }
                        else{
                            throw new Exception("          ERROR: EOF inesperado.");
                        }

                    }
                    else{
                        throw new Exception("          ERROR: Tokens ']' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                    }

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

        }
        else{
            System.out.println("          RETURNTYPEEXT = ε");
        }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

    }

    public static void NAME() throws Exception {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ID)){
            try {
            System.out.println("          NAME = 'ID' NAMEEXT");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){
                    token = lexicalAnalyzer.nextToken();
                    NAMEEXT();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{
            throw new Exception("          ERROR: Tokens 'ID' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }

    }

    public static void NAMEEXT(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OB)){
            try {
            System.out.println("          NAMEEXT = '[' EA ']'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    EA();

                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CB)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){

                                token = lexicalAnalyzer.nextToken();

                        }
                        else{
                            throw new Exception("          ERROR: EOF inesperado.");
                        }

                    }
                    else{
                        throw new Exception("          ERROR: Tokens ']' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                    }

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{
            System.out.println("          NAMEEXT = ε");
        }

    }


    public static void ESCOPE() throws Exception {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OK)){
            try {
            System.out.println("          ESCOPE = '{' COMMANDS '}' ");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    COMMANDS();

                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CK)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){

                                token = lexicalAnalyzer.nextToken();

                                /*

                                if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.SCO)){

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
                                        //System.out.println("          ERROR: EOF inesperado.");
                                    }

                                }
                                else{
                                    System.out.println("          ERROR: Tokens 'SCO' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                                }

                                 */

                        }
                        else{
                            throw new Exception("          ERROR: EOF inesperado.");
                        }

                    }
                    else{
                        throw new Exception("          ERROR: Tokens '}' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                    }

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }


        }
        else{
            throw new Exception("          ERROR: Tokens '{' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }

    }

    public static void COMMANDS() throws Exception {



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

            try {

            System.out.println("          COMMANDS = CMD ';' COMMANDS");

            CMD();

            if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.SCO)){

                System.out.println(token.toString());
                /*Next Token*/
                if(lexicalAnalyzer.hasMoreTokens()){

                        token = lexicalAnalyzer.nextToken();
                        COMMANDS();

                }
                else{
                    throw new Exception("          ERROR: EOF inesperado.");
                }

            }
            else{
                throw new Exception("          ERROR: Tokens ';' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
            }
        } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{

            System.out.println("          COMMANDS = ε");

        }

    }

    public static void CMD(){
        try {
        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.INT)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.BOOL)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.CHAR)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.STRING)
                || token.getCategory().equals(LexicalAnalyzer.TokenCategory.FLOAT)) {

            System.out.println("          CMD = DECLARATION");

            DECLARATION();

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ID)){

            System.out.println("          CMD = 'ID' CMDEXT");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    CMDEXT();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.PRINT)){

            System.out.println("          CMD = PRINT");

            PRINT();

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.READ)){

            System.out.println("          CMD = READ");

            READ();

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.IF)){

            System.out.println("          CMD = IFELSE");

            IFELSE();

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.WHEN)){

            System.out.println("          CMD = WHEN");

            WHEN();

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.REPEATER)){

            System.out.println("          CMD = REPEATER");

            REPEATER();

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.RETURN)){

            System.out.println("          CMD = RETURN");

            RETURN();

        }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

    }

    public static void DECLARATION(){

        System.out.println("          DECLARATION = TYPE NAME");

        try{

            TYPE();
            NAME();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void CMDEXT(){

        try{

            if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

                System.out.println("          CMDEXT = FUNCTIONCALL");


                FUNCTIONCALL();

            }else{

                System.out.println("          CMDEXT = ATTRIBUTION");


                ATTRIBUTION();
            }

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void ATTRIBUTION(){

        try {
        System.out.println("          ATTRIBUTION = NAMEEXT '=' VALUE");


        NAMEEXT();

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ATR)){

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    VALUE();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

        }
        else{

            throw new Exception("          ERROR: Tokens '=' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

        }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void VALUE(){

        try{

            if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OB)){

                System.out.println("          VALUE = ARRAY");

                ARRAY();

            }
            else{

                System.out.println("          VALUE = EA");

                EA();

            }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void ARRAY() throws Exception {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OB)){

            try {

            System.out.println("          ARRAY = '[' ARRAYEXT");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    ARRAYEXT();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{
            throw new Exception("          ERROR: Tokens '[' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }
    }

    public static void ARRAYEXT(){

        try {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CB)){

            System.out.println("          ARRAYEXT = ']'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

        }
        else{

            System.out.println("          ARRAYEXT = ELEMENTS ']'");


            ELEMENTS();
            if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CB)){

                System.out.println(token.toString());
                /*Next Token*/
                if(lexicalAnalyzer.hasMoreTokens()){

                        token = lexicalAnalyzer.nextToken();

                }
                else{
                    throw new Exception("          ERROR: EOF inesperado.");
                }

            }
        }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void ELEMENTS(){

        try{

            System.out.println("          ELEMENTS = EB ELEMENTSEXT");


            EB();
            ELEMENTSEXT();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void ELEMENTSEXT(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.SPTR)){

            try {

            System.out.println("          ELEMENTSEXT = ',' ELEMENTS");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    ELEMENTS();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

        }
        else{
            System.out.println("          ELEMENTSEXT = ε");
        }
    }

    public static void FUNCTIONCALL() throws Exception {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

            try {

            System.out.println("          FUNCTIONCALL = '(' FUNCTIONCALLEXT");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    FUNCTIONCALLEXT();


            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{
            throw new Exception("          ERROR: Tokens '(' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }
    }

    public static void FUNCTIONCALLEXT(){

        try {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)){

            System.out.println("          FUNCTIONCALLEXT = ')'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

        }
        else{

            System.out.println("          FUNCTIONCALLEXT = LISTPARAMSCALL ')'");

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
                throw new Exception("          ERROR: Tokens ')' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
            }

        }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

    }

    public static void LISTPARAMSCALL(){

        System.out.println("          LISTPARAMSCALL = PARAMITEM LISTPARAMSCALLEXT");

        try{

            PARAMITEM();
            LISTPARAMSCALLEXT();

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }


    }

    public static void LISTPARAMSCALLEXT(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.SPTR)){

            System.out.println("          LISTPARAMSCALLEXT = ',' LISTPARAMSCALL");

            try {

                System.out.println(token.toString());
                /*Next Token*/
                if(lexicalAnalyzer.hasMoreTokens()){

                        token = lexicalAnalyzer.nextToken();
                        LISTPARAMSCALL();

                }
                else{
                    throw new Exception("          ERROR: EOF inesperado.");
                }
            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{
            System.out.println("          LISTPARAMSCALLEXT = ε");
        }
    }

    public static void PARAMITEM(){

        try{

            if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ID)){

                System.out.println("          PARAMITEM = NAME");

                NAME();

            }
            else{
                System.out.println("          PARAMITEM = EB");

                EB();
            }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void PRINT() throws Exception {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.PRINT)){

            System.out.println("          PRINT = 'print' '(' MESSAGE PRINTEXT ')'");

            try {

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();


                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){

                                token = lexicalAnalyzer.nextToken();
                                MESSAGE();
                                PRINTEXT();

                                if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)){

                                    System.out.println(token.toString());
                                    /*Next Token*/
                                    if(lexicalAnalyzer.hasMoreTokens()){

                                            token = lexicalAnalyzer.nextToken();


                                    }
                                    else{
                                        throw new Exception("          ERROR: EOF inesperado.");
                                    }

                                }
                                else{
                                    throw new Exception("          ERROR: Tokens ')' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                                }

                        }
                        else{
                            throw new Exception("          ERROR: EOF inesperado.");
                        }

                    }
                    else{

                        throw new Exception("          ERROR: Tokens '(' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                    }

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{
            throw new Exception("          ERROR: Tokens 'print' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

        }
    }

    public static void PRINTEXT(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.SPTR)){

            System.out.println("          PRINTEXT = ',' NAME PRINTEXT");

            try {

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    NAME();
                    PRINTEXT();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{

            System.out.println("          PRINTEXT = ε");

        }

    }

    public static void MESSAGE(){

        try {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CTESTRING)){

            System.out.println("          MESSAGE = 'CTESTRING' MESSAGEEXT");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    MESSAGEEXT();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

        }
        else {

            System.out.println("          MESSAGE = NAME MESSAGEEXT");

            NAME();
            MESSAGEEXT();

        }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void MESSAGEEXT(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CONCAT)){

            System.out.println("          MESSAGEEXT = '++' MESSAGE");

            try {

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    MESSAGE();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{
            System.out.println("          MESSAGEEXT = ε");
        }
    }

    public static void READ() throws Exception {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.READ)){

            System.out.println("          READ = 'read' '(' READEXT ')'");

            try {

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();


                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){

                                token = lexicalAnalyzer.nextToken();
                                READEXT();

                                if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)){

                                    System.out.println(token.toString());
                                    /*Next Token*/
                                    if(lexicalAnalyzer.hasMoreTokens()){

                                            token = lexicalAnalyzer.nextToken();

                                    }
                                    else{
                                        throw new Exception("          ERROR: EOF inesperado.");
                                    }

                                }
                                else{
                                    throw new Exception("          ERROR: Tokens ')' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                                }

                        }
                        else{
                            throw new Exception("          ERROR: EOF inesperado.");
                        }
                    }
                    else{

                        throw new Exception("          ERROR: Tokens '(' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                    }

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{

            throw new Exception("          ERROR: Tokens 'read' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

        }
    }

    public static void READEXT(){

        try{

            System.out.println("          READEXT = NAME READEXTR");

            NAME();
            READEXTR();

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void READEXTR(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.SPTR)){

            try {

            System.out.println("          READEXTR = ',' READEXT");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    READEXT();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{
            System.out.println("          READEXTR = ε");
        }
    }

    public static void IFELSE(){

        System.out.println("          IFELSE = IF ELIF ELSE");

        try{

            IF();
            ELIF();
            ELSE();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void IF() throws Exception {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.IF)){

            try {

            System.out.println("          IF = 'if' '(' EB ')' ESCOPE");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();


                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){

                                token = lexicalAnalyzer.nextToken();
                                EB();

                                if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)){

                                    System.out.println(token.toString());
                                    /*Next Token*/
                                    if(lexicalAnalyzer.hasMoreTokens()){

                                            token = lexicalAnalyzer.nextToken();
                                            ESCOPE();

                                    }
                                    else{
                                        throw new Exception("          ERROR: EOF inesperado.");
                                    }

                                }
                                else{

                                    throw new Exception("          ERROR: Tokens ')' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                                }

                        }
                        else{
                            throw new Exception("          ERROR: EOF inesperado.");
                        }

                    }
                    else{

                        throw new Exception("          ERROR: Tokens '(' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                    }

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{

            throw new Exception("          ERROR: Tokens 'if' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

        }
    }

    public static void ELIF(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ELIF)){

            System.out.println("          ELIF = 'elif' '(' EB ')' ESCOPE ELIF");

            try {

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();


                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){

                                token = lexicalAnalyzer.nextToken();
                                EB();

                                if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)){

                                    System.out.println(token.toString());
                                    /*Next Token*/
                                    if(lexicalAnalyzer.hasMoreTokens()){

                                            token = lexicalAnalyzer.nextToken();
                                            ESCOPE();
                                            ELIF();

                                    }
                                    else{
                                        throw new Exception("          ERROR: EOF inesperado.");
                                    }

                                }
                                else{

                                    throw new Exception("          ERROR: Tokens ')' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                                }

                        }
                        else{
                            throw new Exception("          ERROR: EOF inesperado.");
                        }

                    }
                    else{

                        throw new Exception("          ERROR: Tokens '(' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                    }

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{

            System.out.println("          ELIF = ε");

        }
    }

    public static void ELSE(){

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ELSE)){

            try {

            System.out.println("          ELSE = 'else' ESCOPE");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();


                    ESCOPE();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{

            System.out.println("          ELSE = ε");

        }
    }

    public static void WHEN() throws Exception {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.WHEN)){

            try {

            System.out.println("          WHEN = 'when' '(' EB ')' ESCOPE");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();


                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){

                                token = lexicalAnalyzer.nextToken();
                                EB();

                                if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)){

                                    System.out.println(token.toString());
                                    /*Next Token*/
                                    if(lexicalAnalyzer.hasMoreTokens()){

                                            token = lexicalAnalyzer.nextToken();

                                            ESCOPE();

                                    }
                                    else{
                                        throw new Exception("          ERROR: EOF inesperado.");
                                    }

                                }
                                else{

                                    throw new Exception("          ERROR: Tokens ')' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                                }

                        }
                        else{
                            throw new Exception("          ERROR: EOF inesperado.");
                        }

                    }
                    else{

                        throw new Exception("          ERROR: Tokens '(' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                    }

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{

            throw new Exception("          ERROR: Tokens 'when' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

        }
    }

    public static void REPEATER() throws Exception {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.REPEATER)){

            System.out.println("          REPEATER = 'repeater' '(' 'ID' ATTRIBUTION ';' PARAMITEM ';' PARAMITEM ')' ESCOPE");

            try {

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();

                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){

                                token = lexicalAnalyzer.nextToken();

                                if (token.getCategory().equals(LexicalAnalyzer.TokenCategory.ID)) {

                                    System.out.println(token.toString());
                                    /*Next Token*/
                                    if(lexicalAnalyzer.hasMoreTokens()){

                                            token = lexicalAnalyzer.nextToken();

                                            ATTRIBUTION();

                                            if (token.getCategory().equals(LexicalAnalyzer.TokenCategory.SCO)) {

                                                System.out.println(token.toString());
                                                /*Next Token*/
                                                if (lexicalAnalyzer.hasMoreTokens()) {

                                                        token = lexicalAnalyzer.nextToken();

                                                        PARAMITEM();


                                                        if (token.getCategory().equals(LexicalAnalyzer.TokenCategory.SCO)) {

                                                            System.out.println(token.toString());
                                                            /*Next Token*/
                                                            if (lexicalAnalyzer.hasMoreTokens()) {

                                                                    token = lexicalAnalyzer.nextToken();

                                                                    PARAMITEM();

                                                                    if (token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)) {

                                                                        System.out.println(token.toString());
                                                                        /*Next Token*/
                                                                        if (lexicalAnalyzer.hasMoreTokens()) {

                                                                                token = lexicalAnalyzer.nextToken();

                                                                                ESCOPE();


                                                                        } else {
                                                                            throw new Exception("          ERROR: EOF inesperado.");
                                                                        }

                                                                    } else {
                                                                        throw new Exception("          ERROR: Tokens ')' esperados. L:" + token.getLine() + " C:" + token.getColumn() + " Tk:" + token.getValue());
                                                                    }


                                                            } else {
                                                                throw new Exception("          ERROR: EOF inesperado.");
                                                            }

                                                        } else {
                                                            throw new Exception("          ERROR: Tokens ';' esperados. L:" + token.getLine() + " C:" + token.getColumn() + " Tk:" + token.getValue());
                                                        }


                                                } else {
                                                    throw new Exception("          ERROR: EOF inesperado.");
                                                }

                                            } else {
                                                throw new Exception("          ERROR: Tokens ';' esperados. L:" + token.getLine() + " C:" + token.getColumn() + " Tk:" + token.getValue());
                                            }


                                    }
                                    else{
                                        throw new Exception("          ERROR: EOF inesperado.");
                                    }


                                }
                                else{
                                    throw new Exception("          ERROR: Tokens 'ID' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                                }



                        }
                        else{
                            throw new Exception("          ERROR: EOF inesperado.");
                        }

                    }
                    else{
                        throw new Exception("          ERROR: Tokens '(' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
                    }


            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{
            throw new Exception("          ERROR: Tokens 'repeater' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }
    }

    public static void RETURN() throws Exception {
        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.RETURN)){

            try {

            System.out.println("          RETURN = 'return' RETURNEXT");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    RETURNEXT();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{
            throw new Exception("          ERROR: Tokens 'return' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }
    }

    public static void RETURNEXT(){

        try {

            if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ID)){

                System.out.println("          RETURNEXT = NAME");

                NAME();

            }
            else{
                System.out.println("          RETURNEXT = EB");

                EB();
            }

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void EB(){

        try {

            System.out.println("          EB= TB EBR");

            TB();
            EBR();

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void EBR(){
        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OR)){

            try {

            System.out.println("          EBR = 'or' TB EBR");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    TB();
                    EBR();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{
            System.out.println("          EBR = ε");
        }
    }

    public static void TB(){

        try{

            System.out.println("          TB = FB TBR");

            FB();
            TBR();

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void TBR(){
        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.AND)){

            try {

            System.out.println("          TBR = 'and' FB TBR");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    FB();
                    TBR();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

        }
        else{
            System.out.println("          TBR = ε");
        }
    }

    public static void FB(){

        try{
            if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.NOT)){

                System.out.println("          FB = 'not' FB");

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
                    throw new Exception("          ERROR: EOF inesperado.");
                }

            }
            else{

                System.out.println("          FB = EA EREL");
                EA();
                EREL();
            }

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void EREL() throws Exception {

        try {
            if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ORC)){

                System.out.println("          EREL = 'ORC' EA");

                System.out.println(token.toString());
                /*Next Token*/
                if(lexicalAnalyzer.hasMoreTokens()){

                        token = lexicalAnalyzer.nextToken();
                        EA();

                }
                else{
                    throw new Exception("          ERROR: EOF inesperado.");
                }

            }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ORE)){



                System.out.println("          EREL = 'ORE' EA");

                System.out.println(token.toString());
                /*Next Token*/
                if(lexicalAnalyzer.hasMoreTokens()){

                        token = lexicalAnalyzer.nextToken();
                        EA();

                }
                else{
                    throw new Exception("          ERROR: EOF inesperado.");
                }


            }else{
                System.out.println("          EREL = ε");
            }

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void EA(){

        try{

            System.out.println("          EA = TA EAR");

            TA();
            EAR();

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void EAR(){
        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OPA)){

            try {

            System.out.println("          EAR = 'OPA' TA EAR");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    TA();
                    EAR();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

        }
        else{
            System.out.println("          EAR = ε");
        }
    }

    public static void TA(){

        try{

            System.out.println("          TA = PA TAR");

            PA();
            TAR();

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void TAR(){
        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OPM)){

            try {

            System.out.println("          TAR = 'OPM' PA TAR");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    PA();
                    TAR();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{
            System.out.println("          TAR = ε");
        }
    }

    public static void PA(){

        System.out.println("          PA = FA PAFAT");

        FA();
        PAFAT();
    }

    public static void PAFAT(){
        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OPE)){

            try {

            System.out.println("          PAFAT = 'OPE' PA");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    PA();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{
            System.out.println("          PAFAT = ε");
        }
    }

    public static void FA(){

        try {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OPU)){

            System.out.println("          FA = 'OPU' FA");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    FA();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

            System.out.println("          FA = '(' EB ')'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    EB();

                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CP)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){

                                token = lexicalAnalyzer.nextToken();


                        }
                        else{
                            throw new Exception("          ERROR: EOF inesperado.");
                        }

                    }
                    else{

                        throw new Exception("          ERROR: Tokens ')' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                    }

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ID)){

            System.out.println("          FA = ID");

            ID();

        }else{
            System.out.println("          FA = CONSTANT");

            CONSTANT();
        }

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void ID() throws Exception {
        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.ID)){

            try {

            System.out.println("          ID = 'ID' IDFAT");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    IDFAT();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }

        }
        else{
            throw new Exception("          ERROR: Tokens 'ID' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }
    }

    public static void IDFAT(){

        try {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OB)){

            System.out.println("          IDFAT = '[' EA ']'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();
                    EA();

                    if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CB)){

                        System.out.println(token.toString());
                        /*Next Token*/
                        if(lexicalAnalyzer.hasMoreTokens()){

                                token = lexicalAnalyzer.nextToken();


                        }
                        else{
                            throw new Exception("          ERROR: EOF inesperado.");
                        }

                    }
                    else{

                        throw new Exception("          ERROR: Tokens ']' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());

                    }

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }

        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.OP)){

            System.out.println("          IDFAT = FUNCTIONCALL");

            FUNCTIONCALL();

        }else{

            System.out.println("          IDFAT = ε");
        }

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void CONSTANT(){

        try {

        if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CTEINT)){
            System.out.println("          CONSTANT = 'CTEINT'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CTEFLOAT)){
            System.out.println("          CONSTANT = 'CTEFLOAT'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CTEBOOL)){
            System.out.println("          CONSTANT = 'CTEBOOL'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CTECHAR)){
            System.out.println("          CONSTANT = 'CTECHAR'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
        }else if(token.getCategory().equals(LexicalAnalyzer.TokenCategory.CTESTRING)){
            System.out.println("          CONSTANT = 'CTESTRING'");

            System.out.println(token.toString());
            /*Next Token*/
            if(lexicalAnalyzer.hasMoreTokens()){

                    token = lexicalAnalyzer.nextToken();

            }
            else{
                throw new Exception("          ERROR: EOF inesperado.");
            }
        }else{
            throw new Exception("          ERROR: Tokens 'CTEINT', 'CTEFLOAT', 'CTEBOOL', 'CTECHAR', 'CTESTRING' esperados. L:"+ token.getLine() +" C:" + token.getColumn() + " Tk:" + token.getValue());
        }

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

    }
}