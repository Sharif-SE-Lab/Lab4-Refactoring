package codeGenerator;

/**
 * Created by mohammad hosein on 6/28/2015.
 */

public class Address {
    private final int num;
    public int getNum() {
        return num;
    }

    private final TypeAddress Type;

    private final varType varType;
    public varType getVarType() {
        return varType;
    }

    public Address(int num, varType varType, TypeAddress Type) {
        this.num = num;
        this.Type = Type;
        this.varType = varType;
    }

    public Address(int num, varType varType) {
        this.num = num;
        this.Type = TypeAddress.Direct;
        this.varType = varType;
    }

    public String toString() {
        switch (Type) {
            case Direct:
                return getNum() + "";
            case Indirect:
                return "@" + getNum();
            case Imidiate:
                return "#" + getNum();
        }
        return getNum() + "";
    }
}
