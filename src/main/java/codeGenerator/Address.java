package codeGenerator;

/**
 * Created by mohammad hosein on 6/28/2015.
 */

public abstract class Address {
    private final int num;
    public int getNum() {
        return num;
    }

    private final varType varType;
    public varType getVarType() {
        return varType;
    }

    public Address(int num, varType varType) {
        this.num = num;
        this.varType = varType;
    }

    public abstract String toString();
}