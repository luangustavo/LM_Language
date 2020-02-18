package lexical;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LexicalAnalyzer {
    private String line;
    private String filePath;
    private int currentLine=-1, currentColumn = -1;
    private int tokenBeginColumn, tokenBeginLine = 0;
    private String tokenValue;
    private char currentChar;
    private final char LINE_BREAK = '\n';
    BufferedReader reader;

    public LexicalAnalyzer(String filePath) {
        this.filePath = filePath;

        try {
            this.reader = new BufferedReader(new FileReader(filePath));

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public boolean hasMoreTokens() {

        if(currentLine == -1 && currentColumn == -1 ) {
            currentLine++;
            currentColumn++;
            try {
                line = this.reader.readLine();
                System.out.format("%4d  %s\n", currentLine+1, line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(line !=null) {
            if (line.substring(currentColumn).matches("\\s*")) { //Se a linha so possui espacos em branco
                currentLine++; //Pega a proxima linha
                currentColumn = 0; //Reinicializa a coluna

                while (line != null) {

                    try {
                        line = this.reader.readLine();
                        if(line != null){
                            if (line.matches("\\s*")) {//Percorre enquanto houver linhas vazias
                                System.out.format("%4d  %s\n", currentLine+1, line);
                                currentLine++;
                                currentColumn=0;
                            } else {
                                System.out.format("%4d  %s\n", currentLine+1, line);
                                return true;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            } else if (currentColumn < line.length()) { //Se a linha possui algum caractere e a coluna n seja o final
                return true;
            } else { //Se a linha atual ja foi analisada
                currentLine++;

                currentColumn = 0;

                while (line != null) {

                    try {
                        line = this.reader.readLine();
                        if(line != null){
                            if (line.matches("\\s*")) {//Percorre enquanto houver linhas vazias
                                System.out.format("%4d  %s\n", currentLine+1, line);
                                currentLine++;

                            } else {
                                System.out.format("%4d  %s\n", currentLine+1, line);
                                return true;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        try {
            this.reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /*Acessando o proximo token*/
    public Token nextToken() throws Exception {
        /*Inicializando o token e seus atributos*/
        Token token;
        tokenValue = "";

        tokenBeginColumn = currentColumn;
        tokenBeginLine = currentLine;
        currentChar = line.charAt(currentColumn);

        /*Percorrendo a linha ate encontrar um caracter nao vazio/tab*/
        while (currentChar == ' ' || currentChar == '\t') {
            currentChar = nextChar();
            tokenBeginColumn++;
        }

        /*Se o caracter eh um digito*/
        if (Character.toString(currentChar).matches("\\d")) {

            appendTokenValue();
            currentChar = nextChar();
            if (currentChar == '.') {
                appendTokenValue();
                currentChar = nextChar();
                while (Character.toString(currentChar).matches("\\d")) {
                    appendTokenValue();
                    currentChar = nextChar();
                }
            }
            while (Character.toString(currentChar).matches("\\d")) {
                appendTokenValue();
                currentChar = nextChar();
                /*Caso de flutuante*/
                if (currentChar == '.') {
                    appendTokenValue();
                    currentChar = nextChar();
                    while (Character.toString(currentChar).matches("\\d")) {
                        appendTokenValue();
                        currentChar = nextChar();
                    }
                }
                if (currentChar != ' ') {
                    buildIdentifier();
                }
            }
        } else {
            buildIdentifier();
        }

        if (tokenValue == "") {
            switch (currentChar) {
                case '"':
                    appendTokenValue();
                    currentChar = nextChar();

                    if (currentChar == '"') {
                        appendTokenValue();
                        currentColumn++;
                        break;
                    }
                    while (currentChar != LINE_BREAK) {
                        appendTokenValue();
                        currentChar = nextChar();

                        if (currentChar == '"') {
                            appendTokenValue();
                            currentColumn++;
                            break;
                        }
                    }
                    break;
                case '\'':
                    appendTokenValue();
                    currentChar = nextChar();

                    if (currentChar != LINE_BREAK) {
                        appendTokenValue();
                    }

                    currentChar = nextChar();
                    if (currentChar == '\'') {
                        appendTokenValue();
                        currentColumn++;
                    }
                    break;
                case '<':
                case '>':
                case '!':
                case '=':
                    appendTokenValue();
                    currentChar = nextChar();
                    if (currentChar == '=') {
                        appendTokenValue();
                        currentColumn++;
                    }
                    break;
                case '+':
                    appendTokenValue();
                    currentChar = nextChar();

                    if (currentChar == '+') {
                        appendTokenValue();
                        currentChar = nextChar();
                    }
                    break;
                default:
                    appendTokenValue();
                    currentColumn++;
                    break;
            }
        }
        tokenValue = tokenValue.trim();

        token = new Token(tokenValue, tokenBeginLine, tokenBeginColumn, analyzeCategory(tokenValue));
        return token;
    }

    /*Concatenar o valor do token*/
    private void appendTokenValue() {
        tokenValue += currentChar;
    }

    private void buildIdentifier() {
        while (!LexicalTable.symbolList.contains(currentChar)) {
            tokenValue += currentChar;
            currentChar = nextChar();
            if (currentChar == LINE_BREAK) {
                break;
            }
        }
    }

    private TokenCategory analyzeCategory(String tokenValue) throws Exception {
        if (isOpeUnary(tokenValue)) {
            return TokenCategory.OPU;
        } else if (LexicalTable.lexemesMap.containsKey(tokenValue)) {
            return LexicalTable.lexemesMap.get(tokenValue);
        } else if (isCteString(tokenValue)) {
            return TokenCategory.CTESTRING;
        } else if (isCteChar(tokenValue)) {
            return TokenCategory.CTECHAR;
        } else if (isCteInt(tokenValue)) {
            return TokenCategory.CTEINT;
        } else if (isCteFloat(tokenValue)) {
            return TokenCategory.CTEFLOAT;
        } else if (isIdentifier(tokenValue)) {
            return TokenCategory.ID;
        }
        return TokenCategory.NULL;
    }

    private Character nextChar() {
        currentColumn++;
        if (currentColumn < line.length()) {
            return line.charAt(currentColumn);
        } else {
            return LINE_BREAK;
        }
    }



    private boolean isIdentifier(String tokenValue) {
        if (tokenValue.matches("[a-zA-Z][a-zA-Z0-9]*")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isCteInt(String tokenValue) {
        if (tokenValue.matches("(\\d)+")) {
            return true;
        }
        return false;
    }

    private boolean isCteFloat(String tokenValue) throws Exception {
        if (tokenValue.matches("(\\d)+\\.(\\d)+")) {
            return true;
        } else if (tokenValue.matches("(\\d)+\\.")) {
            throw new Exception("Erro na linha: " + currentLine + ", coluna: " + currentColumn + ", token: '" + tokenValue + "'" + ", msg: " + "Constante float em formato errado." );
        }
        return false;
    }

    private boolean isCteChar(String tokenValue) throws Exception {
        if (tokenValue.matches("'(.?)'")) {
            return true;
        } else if (tokenValue.startsWith("'")) {
            throw new Exception("Erro na linha: " + currentLine + ", coluna: " + currentColumn + ", token: '" + tokenValue + "'" + ", msg: " + "Caracter nao fechado corretamente com '." );
        }
        return false;
    }

    private boolean isCteString(String tokenValue) throws Exception {
        if (tokenValue.startsWith("\"") && tokenValue.endsWith("\"")) {
            return true;
        } else if (tokenValue.startsWith("\"")) {
            throw new Exception("Erro na linha: " + currentLine + ", coluna: " + currentColumn + ", token: '" + tokenValue + "'" + ", msg: " + "Cadeia de caracteres nao fechada corretamente com '\"'." );
        }
        return false;
    }
    private Character previousNotBlankChar() {
        int previousColumn = tokenBeginColumn - 1;
        char previousChar;

        while (previousColumn >= 0) {
            previousChar = line.charAt(previousColumn);
            if (previousChar != ' ' && previousChar != '\t') {
                return previousChar;
            }
            previousColumn--;
        }
        return null;
    }
    private boolean isOpeUnary(String tokenValue) {
        if (tokenValue.equals("-")) {
            Character previousChar = previousNotBlankChar();
            /*Se o caractere anterior (diferente de ' ') é um operando*/
            if ((previousChar != null) && Character.toString(previousChar).matches("[a-zA-Z0-9]*")) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

public static class LexicalTable {
    public static Map<String, TokenCategory> lexemesMap = new HashMap<String, TokenCategory>();
    public static List<Character> symbolList = new ArrayList<Character>();

    static {

        // Palavras-reservadas

        lexemesMap.put("void", TokenCategory.VOID);
        lexemesMap.put("int", TokenCategory.INT);
        lexemesMap.put("float", TokenCategory.FLOAT);
        lexemesMap.put("char", TokenCategory.CHAR);
        lexemesMap.put("string", TokenCategory.STRING);
        lexemesMap.put("bool", TokenCategory.BOOL);
        lexemesMap.put("return", TokenCategory.RETURN);
        lexemesMap.put("main", TokenCategory.MAIN);
        lexemesMap.put("read", TokenCategory.READ);
        lexemesMap.put("print", TokenCategory.PRINT);
        lexemesMap.put("if", TokenCategory.IF);
        lexemesMap.put("elif", TokenCategory.ELIF);
        lexemesMap.put("else", TokenCategory.ELSE);
        lexemesMap.put("when", TokenCategory.WHEN);
        lexemesMap.put("repeater", TokenCategory.REPEATER);
        lexemesMap.put("true", TokenCategory.CTEBOOL);
        lexemesMap.put("false", TokenCategory.CTEBOOL);
        lexemesMap.put("null", TokenCategory.NULL);

        // Operadores

        lexemesMap.put("+", TokenCategory.OPA);
        lexemesMap.put("-", TokenCategory.OPA);
        lexemesMap.put("*", TokenCategory.OPM);
        lexemesMap.put("/", TokenCategory.OPM);
        lexemesMap.put("^", TokenCategory.OPE);

        lexemesMap.put("<", TokenCategory.ORC);
        lexemesMap.put(">", TokenCategory.ORC);
        lexemesMap.put("<=", TokenCategory.ORC);
        lexemesMap.put(">=", TokenCategory.ORC);
        lexemesMap.put("==", TokenCategory.ORE);
        lexemesMap.put("!=", TokenCategory.ORE);

        lexemesMap.put("and", TokenCategory.AND);
        lexemesMap.put("or", TokenCategory.OR);
        lexemesMap.put("not", TokenCategory.NOT);

        lexemesMap.put("=", TokenCategory.ATR);
        lexemesMap.put("++", TokenCategory.CONCAT);

        // Definidores de escopo

        lexemesMap.put("{", TokenCategory.OK);
        lexemesMap.put("}", TokenCategory.CK);

        // Delimitadores

        lexemesMap.put("(", TokenCategory.OP);
        lexemesMap.put(")", TokenCategory.CP);
        lexemesMap.put("[", TokenCategory.OB);
        lexemesMap.put("]", TokenCategory.CB);

        // Separadores

        lexemesMap.put(",", TokenCategory.SPTR);

        // Terminador de instrucao

        lexemesMap.put(";", TokenCategory.SCO);

        // Lista de simbolos

        symbolList.add(' ');
        symbolList.add('.');
        symbolList.add(',');
        symbolList.add(':');
        symbolList.add(';');
        symbolList.add('!');
        symbolList.add('?');
        symbolList.add('+');
        symbolList.add('-');
        symbolList.add('*');
        symbolList.add('/');
        symbolList.add('\\');
        symbolList.add('_');
        symbolList.add('<');
        symbolList.add('>');
        symbolList.add('=');
        symbolList.add('(');
        symbolList.add(')');
        symbolList.add('[');
        symbolList.add(']');
        symbolList.add('{');
        symbolList.add('}');
        symbolList.add('\'');
        symbolList.add('"');
        symbolList.add('#');
        symbolList.add('@');
        symbolList.add('%');
        symbolList.add('&');
        symbolList.add('$');
        symbolList.add('^');
        symbolList.add('|');
    }
}public static class Syntatic {
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
                try {
                    token = lexicalAnalyzer.nextToken();
                    System.out.println(token.toString());
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(1);
                }

            }
        //}

    }

}public static class Token {
    private String value;
    private TokenCategory category;
    private int line;
    private int column;

    public Token(String tokenValue, int tokenBeginLine, int tokenBeginColumn, TokenCategory tokenCategory) {
        this.value = tokenValue;
        this.category = tokenCategory;
        this.line = tokenBeginLine;
        this.column = tokenBeginColumn;
    }
    @Override
    public String toString() {
        return String.format("          [%04d, %04d] (%04d, %20s) {%s}", line+1, column+1, category.getCategoryValue(), category.name(), value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TokenCategory getCategory() {
        return category;
    }

    public void setCategory(TokenCategory category) {
        this.category = category;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}public enum TokenCategory {
    MAIN(1),
    ID(2),
    VOID(3),
    INT(4),
    BOOL(5),
    CHAR(6),
    STRING(7),
    FLOAT(8),
    OK(9),
    CK(10),
    OP(11),
    CP(12),
    OB(13),
    CB(14),
    SCO(15),
    SPTR(16),
    CTEINT(17),
    CTEFLOAT(18),
    CTEBOOL(19),
    CTECHAR(20),
    CTESTRING(21),
    IF(22),
    ELSE(23),
    ELIF(24),
    REPEATER(25),
    WHEN(26),
    PRINT(27),
    READ(28),
    RETURN(29),
    AND(30),
    OR(31),
    NOT(32),
    OPA(33),
    OPM(34),
    OPE(35),
    OPU(36),
    ORC(37),
    ORE(38),
    ATR(39),
    CONCAT(40),
    NULL(41);

    private int value;

    private TokenCategory(int value) {
        this.value = value;
    }

    public int getCategoryValue() {
        return value;
    }
}}