public enum TokenCategory {
    MAIN(1),
    ID(2),
    VOID(3),
    INT(4),
    BOOL(6),
    CHAR(7),
    STRING(8),
    FLOAT(9),
    OK(10),
    CK(11),
    OP(12),
    CP(13),
    OB(14),
    CB(15),
    COMMENT(16),
    SCO(17),
    SPTR(18),
    CTEINT(20),
    CTEFLOAT(21),
    CTEBOOL(22),
    CTECHAR(23),
    CTESTRING(24),
    IF(25),
    ELSE(26),
    ELIF(27),
    REPEATER(28),
    WHEN(30),
    PRINT(31),
    READ(32),
    RETURN(33),
    AND(34),
    OR(35),
    NOT(36),
    OPA(37),
    OPM(38),
    OPE(39),
    OPU(40),
    ORC(41),
    ORE(42),
    ATR(43),
    CONCAT(44),
    NULL(45);

    private int value;

    private TokenCategory(int value) {
        this.value = value;
    }

    public int getCategoryValue() {
        return value;
    }
}