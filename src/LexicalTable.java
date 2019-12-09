import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LexicalTable {
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
}