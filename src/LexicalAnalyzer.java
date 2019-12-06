import java.io.BufferedReader;
import java.io.FileReader;

public class LexicalAnalyzer {
    private String line;
    private String filePath;
    private int currentLine, currentColumn = 0;
    private int tokenBeginColumn, tokenBeginLine = 0;
    private String tokenValue;
    private char currentChar;
    private final char LINE_BREAK = '\n';
    Syntatic syntatic;

    public LexicalAnalyzer(String filePath) {
        this.filePath = filePath;
        this.syntatic = new Syntatic ();
    }

    /*Lendo o arquivo de entrada*/
    public void readFile() {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(filePath));
            line = reader.readLine();
            int i=0;
            while (line != null) {

                System.out.format("%4d  %s\n", i+1, line);

                while (hasMoreTokens()) {

                    syntatic.Analyzer();
                }
                i++;
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }



    public boolean hasMoreTokens() {

        if (line.substring(currentColumn).matches("\\s*")) { //Se a linha so possui espacos em branco
            currentLine++; //Pega a proxima linha
            currentColumn = 0; //Reinicializa a coluna

        } else if (currentColumn < line.length()) { //Se a linha possui algum caractere e a coluna n seja o final
            return true;
        } else { //Se a linha atual ja foi analisada
            currentLine++;
            currentColumn = 0;
        }
        return false;
    }

    /*Acessando o proximo token*/
    public Token nextToken() {
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

            while (Character.toString(currentChar).matches("\\d")) {
                appendTokenValue();
                currentChar = nextChar();

                /*Caso de flutuante*/
                if (currentChar == '.') {
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

    private TokenCategory analyzeCategory(String tokenValue) {
        if (isOpeUnary(tokenValue)) {
            return TokenCategory.OPU;
        } else if (LexicalTable.lexemesMap.containsKey(tokenValue)) {
            return LexicalTable.lexemesMap.get(tokenValue);
        } else if (isCteString(tokenValue)) {
            return TokenCategory.CTESTRING;
        } else if (isCteChar(tokenValue)) {
            return TokenCategory.CHAR;
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
            if (tokenValue.length() < 32) {
                return true;
            } else {
                /*Esse erro sera tratado no Analizador sintatico*/
                syntatic.printError(1, tokenValue, currentLine, currentColumn);

            }
        } else {
            /*Esse erro sera tratado no Analizador sintatico*/
            syntatic.printError(2, tokenValue, currentLine, currentColumn);

        }
        return false;
    }

    private boolean isCteInt(String tokenValue) {
        if (tokenValue.matches("(\\d)+")) {
            return true;
        }
        return false;
    }

    private boolean isCteFloat(String tokenValue) {
        if (tokenValue.matches("(\\d)+\\.(\\d)+")) {
            return true;
        } else if (tokenValue.matches("(\\d)+\\.")) {
            /*Esse erro sera tratado no Analizador sintatico*/
            syntatic.printError(3, tokenValue, currentLine, currentColumn);
        }
        return false;
    }

    private boolean isCteChar(String tokenValue) {
        if (tokenValue.matches("'(.?)'")) {
            return true;
        } else if (tokenValue.startsWith("'")) {
            /*Esse erro sera tratado no Analizador sintatico*/
            syntatic.printError(4, tokenValue, currentLine, currentColumn);
        }
        return false;
    }

    private boolean isCteString(String tokenValue) {
        if (tokenValue.startsWith("\"") && tokenValue.endsWith("\"")) {
            return true;
        } else if (tokenValue.startsWith("\"")) {
            /*Esse erro sera tratado no Analizador sintatico*/
            syntatic.printError(5, tokenValue, currentLine, currentColumn);
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
            if ((previousChar != null) && Character.toString(previousChar).matches("[a-zA-Z][a-zA-Z0-9]*")) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public String getLine() {
        return line;
    }
}