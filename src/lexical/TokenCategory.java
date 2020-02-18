package lexical;

public enum TokenCategory {
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
}