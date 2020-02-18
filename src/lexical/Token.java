package lexical;

public class Token {
    private String value;
    private LexicalAnalyzer.TokenCategory category;
    private int line;
    private int column;

    public Token(String tokenValue, int tokenBeginLine, int tokenBeginColumn, LexicalAnalyzer.TokenCategory tokenCategory) {
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

    public LexicalAnalyzer.TokenCategory getCategory() {
        return category;
    }

    public void setCategory(LexicalAnalyzer.TokenCategory category) {
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
}
