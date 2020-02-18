package lexical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LexicalTable {
    public static Map<String, LexicalAnalyzer.TokenCategory> lexemesMap = new HashMap<String, LexicalAnalyzer.TokenCategory>();
    public static List<Character> symbolList = new ArrayList<Character>();

    static {

        // Palavras-reservadas

        lexemesMap.put("void", LexicalAnalyzer.TokenCategory.VOID);
        lexemesMap.put("int", LexicalAnalyzer.TokenCategory.INT);
        lexemesMap.put("float", LexicalAnalyzer.TokenCategory.FLOAT);
        lexemesMap.put("char", LexicalAnalyzer.TokenCategory.CHAR);
        lexemesMap.put("string", LexicalAnalyzer.TokenCategory.STRING);
        lexemesMap.put("bool", LexicalAnalyzer.TokenCategory.BOOL);
        lexemesMap.put("return", LexicalAnalyzer.TokenCategory.RETURN);
        lexemesMap.put("main", LexicalAnalyzer.TokenCategory.MAIN);
        lexemesMap.put("read", LexicalAnalyzer.TokenCategory.READ);
        lexemesMap.put("print", LexicalAnalyzer.TokenCategory.PRINT);
        lexemesMap.put("if", LexicalAnalyzer.TokenCategory.IF);
        lexemesMap.put("elif", LexicalAnalyzer.TokenCategory.ELIF);
        lexemesMap.put("else", LexicalAnalyzer.TokenCategory.ELSE);
        lexemesMap.put("when", LexicalAnalyzer.TokenCategory.WHEN);
        lexemesMap.put("repeater", LexicalAnalyzer.TokenCategory.REPEATER);
        lexemesMap.put("true", LexicalAnalyzer.TokenCategory.CTEBOOL);
        lexemesMap.put("false", LexicalAnalyzer.TokenCategory.CTEBOOL);
        lexemesMap.put("null", LexicalAnalyzer.TokenCategory.NULL);

        // Operadores

        lexemesMap.put("+", LexicalAnalyzer.TokenCategory.OPA);
        lexemesMap.put("-", LexicalAnalyzer.TokenCategory.OPA);
        lexemesMap.put("*", LexicalAnalyzer.TokenCategory.OPM);
        lexemesMap.put("/", LexicalAnalyzer.TokenCategory.OPM);
        lexemesMap.put("^", LexicalAnalyzer.TokenCategory.OPE);

        lexemesMap.put("<", LexicalAnalyzer.TokenCategory.ORC);
        lexemesMap.put(">", LexicalAnalyzer.TokenCategory.ORC);
        lexemesMap.put("<=", LexicalAnalyzer.TokenCategory.ORC);
        lexemesMap.put(">=", LexicalAnalyzer.TokenCategory.ORC);
        lexemesMap.put("==", LexicalAnalyzer.TokenCategory.ORE);
        lexemesMap.put("!=", LexicalAnalyzer.TokenCategory.ORE);

        lexemesMap.put("and", LexicalAnalyzer.TokenCategory.AND);
        lexemesMap.put("or", LexicalAnalyzer.TokenCategory.OR);
        lexemesMap.put("not", LexicalAnalyzer.TokenCategory.NOT);

        lexemesMap.put("=", LexicalAnalyzer.TokenCategory.ATR);
        lexemesMap.put("++", LexicalAnalyzer.TokenCategory.CONCAT);

        // Definidores de escopo

        lexemesMap.put("{", LexicalAnalyzer.TokenCategory.OK);
        lexemesMap.put("}", LexicalAnalyzer.TokenCategory.CK);

        // Delimitadores

        lexemesMap.put("(", LexicalAnalyzer.TokenCategory.OP);
        lexemesMap.put(")", LexicalAnalyzer.TokenCategory.CP);
        lexemesMap.put("[", LexicalAnalyzer.TokenCategory.OB);
        lexemesMap.put("]", LexicalAnalyzer.TokenCategory.CB);

        // Separadores

        lexemesMap.put(",", LexicalAnalyzer.TokenCategory.SPTR);

        // Terminador de instrucao

        lexemesMap.put(";", LexicalAnalyzer.TokenCategory.SCO);

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
}