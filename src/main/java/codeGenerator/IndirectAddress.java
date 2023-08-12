package codeGenerator;

public class IndirectAddress extends Address {

    public IndirectAddress(int num, varType varType) {
        super(num, varType);
    }

    @Override
    public String toString() {
        return "@" + getNum();
    }
}