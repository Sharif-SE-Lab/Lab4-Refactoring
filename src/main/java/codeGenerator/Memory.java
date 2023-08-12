package codeGenerator;

import java.util.ArrayList;

/**
 * Created by mohammad hosein on 6/27/2015.
 */
public class Memory {
    private ArrayList<_3AddressCode> codeBlock;
    private int lastTempIndex;
    private int lastDataAddress;
    private final int stratTempMemoryAddress = 500;
    private final int stratDataMemoryAddress = 200;
    private final int dataSize = 4;
    private final int tempSize = 4;

    public Memory() {
        codeBlock = new ArrayList<_3AddressCode>();
        lastTempIndex = stratTempMemoryAddress;
        lastDataAddress = stratDataMemoryAddress;
    }

    public int addTemp() {
        lastTempIndex += tempSize;
        return getTemp();
    }

    public int getTemp() {
        return lastTempIndex - tempSize;
    }

    public int addData() {
        lastDataAddress += dataSize;
        return getDataAddress();
    }
    public int getDataAddress() {
        return lastDataAddress - dataSize;
    }

    public int saveMemory() {
        codeBlock.add(new _3AddressCode());
        return codeBlock.size() - 1;
    }

    public void add3AddressCode(Operation op, Address opr1, Address opr2, Address opr3) {
        codeBlock.add(new _3AddressCode(op, opr1, opr2, opr3));
    }

    public void add3AddressCode(int i, Operation op, Address opr1, Address opr2, Address opr3) {
        codeBlock.remove(i);
        codeBlock.add(i, new _3AddressCode(op, opr1, opr2, opr3));
    }

    public int getCurrentCodeBlockAddress() {
        return codeBlock.size();
    }

    public String getCodeBlockString() {
        StringBuilder s = new StringBuilder("Code Block");
        for (int i = 0; i < codeBlock.size(); i++) {
            s.append("\r\n");
            s.append(i).append(" : ").append(codeBlock.get(i).toString());
        }
        return s.toString();
    }

    public void pintCodeBlock() {
        System.out.println(getCodeBlockString());
    }

    public void testCodeBlock(String expectedOutput) {
        String blockString = getCodeBlockString();
        if (!expectedOutput.equals(blockString)) {
            System.out.println("Wrong Parse");
        } else {
            System.out.println("Successful Parse");
        }
    }
}

class _3AddressCode {
    private Operation operation;
    private Address Operand1;
    private Address Operand2;
    private Address Operand3;

    public _3AddressCode() {

    }

    public _3AddressCode(Operation op, Address opr1, Address opr2, Address opr3) {
        operation = op;
        Operand1 = opr1;
        Operand2 = opr2;
        Operand3 = opr3;
    }

    public String toString() {
        if (operation == null) return "";
        StringBuffer res = new StringBuffer("(");
        res.append(operation.toString()).append(",");
        if (Operand1 != null) res.append(Operand1.toString());
        res.append(",");
        if (Operand2 != null) res.append(Operand2.toString());
        res.append(",");
        if (Operand3 != null) res.append(Operand3.toString());
        res.append(")");

        return res.toString();
    }
}
