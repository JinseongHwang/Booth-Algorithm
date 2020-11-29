package boothalgorithm;

import java.util.ArrayList;
import java.util.Collections;

public class Booth {

    public int bit = 0;
    public int operateStartIndex = 0;
    public ArrayList<Long> multiplicand = new ArrayList<>();
    public ArrayList<Long> multiplier = new ArrayList<>();
    public ArrayList<Long> result = new ArrayList<>();
    public ArrayList<Long> multiplicandComplement;
    public ArrayList<String> resultList = new ArrayList<>();

    StringBuilder currLine = new StringBuilder();

    public Booth(int bit, String multiplicandInput, String multiplierInput) {
        this.bit = bit;
        getUserInput(multiplicandInput, multiplierInput);
        initResult();
        printBinaryOperand();
        startOperation();
        printBinaryResult();
    }

    public ArrayList<String> writeInResultArea() {
        return resultList;
    }

    public void writeStr(String str) {
        resultList.add(str);
    }

    public void writeArr(ArrayList<Long> arr, int startIndex, int endIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i <= endIndex; i++) {
            sb.append(arr.get(i));
        }
        resultList.add(sb.toString());
    }

    public void getUserInput(String multiplicandInput, String multiplierInput) {
        long m1 = Long.parseLong(multiplicandInput);
        convertToBinary(true, Math.abs(m1));
        while (multiplicand.size() < 2 * bit) {
            multiplicand.add((long)0);
        }
        if (m1 < 0) {
            convertToComplement(multiplicand);
        }
        Collections.reverse(multiplicand);

        long m2 = Long.parseLong(multiplierInput);
        convertToBinary(false, Math.abs(m2));
        while (multiplier.size() < bit) {
            multiplier.add((long)0);
        }
        if (m2 < 0) {
            convertToComplement(multiplier);
        }
        Collections.reverse(multiplier);
    }

    public void convertToBinary(boolean selector, long d) {
        while (d > 0) {
            long currentRest = d % 2;
            if (selector) {
                multiplicand.add(currentRest);
            } else {
                multiplier.add(currentRest);
            }
            d /= 2;
        }
    }

    public void convertToComplement(ArrayList<Long> arr) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == 0) {
                arr.set(i, (long) 1);
            } else {
                arr.set(i, (long) 0);
            }
        }
        int firstZeroIndex = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == 0) {
                firstZeroIndex = i;
                arr.set(i, (long) 1);
                break;
            }
        }
        for (int i = 0; i < firstZeroIndex; i++) {
            arr.set(i, (long) 0);
        }
    }

    public void initResult() {
        operateStartIndex = bit * 2 - 1;
        for (int i = 0; i < bit * 2; i++) {
            result.add((long) 0);
        }
    }

    public void shiftLeft() {
        operateStartIndex--;
    }

    public void addOperation(ArrayList<Long> operand) {
        boolean carry = false;
        for (int resultIndex = operateStartIndex, multiplicandIndex = operand.size() - 1;
             resultIndex >= 0;
             resultIndex--, multiplicandIndex--) {
            long currResultBit = result.get(resultIndex);
            long currMultiplicandBit = operand.get(multiplicandIndex);
            if (carry) {
                if (currResultBit + currMultiplicandBit == 2) {
                    result.set(resultIndex, (long)1);
                    carry = true;
                } else if (currResultBit + currMultiplicandBit == 1) {
                    result.set(resultIndex, (long)0);
                    carry = true;
                } else {
                    result.set(resultIndex, (long)1);
                    carry = false;
                }
            } else {
                if (currResultBit + currMultiplicandBit == 2) {
                    result.set(resultIndex, (long)0);
                    carry = true;
                } else if (currResultBit + currMultiplicandBit == 1) {
                    result.set(resultIndex, (long)1);
                    carry = false;
                } else {
                    result.set(resultIndex, (long)0);
                    carry = false;
                }
            }
        }
    }

    public void startOperation() {
        multiplicandComplement = new ArrayList<>(multiplicand);
        Collections.reverse(multiplicandComplement);
        convertToComplement(multiplicandComplement);
        Collections.reverse(multiplicandComplement);
        multiplier.add((long)0);

        for (int i = multiplier.size() - 1; i > 0; i--) {
            long preBit = multiplier.get(i - 1);
            long postBit = multiplier.get(i);

            if (preBit == 0 && postBit == 1) {
                addOperation(multiplicand);
            } else if (preBit == 1 && postBit == 0) {
                addOperation(multiplicandComplement);
            }
            shiftLeft();
        }
    }

    public long binaryToDecimal(ArrayList<Long> bin) {
        long sum = 0;
        boolean isNegative = false;
        if (bin.get(0) == 1) {
            isNegative = true;
            Collections.reverse(bin);
            convertToComplement(bin);
            Collections.reverse(bin);
        }
        for (int i = 0; i < bin.size(); i++) {
            if (bin.get(bin.size() - i - 1) == 1) {
                sum += (Math.pow(2, i));
            }
        }
        if (isNegative) return -sum;
        else return sum;
    }

    public void printLine() {
        writeStr("----------------------------------------------------------------------------------------" +
                 "----------------------------------------------------------------------------------------");
    }

    public void printBinaryOperand() {
        currLine.append("Multiplicand   => ");
        //System.out.print("Multiplicand   => ");
        for (int i = bit; i < multiplicand.size(); i++) {
            currLine.append(multiplicand.get(i));
            //System.out.print(multiplicand.get(i));
            if (i > 0 && (i + 1) % 4 == 0) {
                currLine.append(" ");
                //System.out.print(" ");
            }
        }
        //System.out.println();
        writeStr(currLine.toString());
        currLine.setLength(0);

        currLine.append("Multiplier     => ");
        //System.out.print("Multiplier     => ");
        for (int i = 0; i < multiplier.size(); i++) {
            currLine.append(multiplier.get(i));
            //System.out.print(multiplier.get(i));
            if (i > 0 && (i + 1) % 4 == 0) {
                currLine.append(" ");
                //System.out.print(" ");
            }
        }
        //System.out.println();
        writeStr(currLine.toString());
        currLine.setLength(0);
        printLine();
    }

    public void printBinaryResult() {
        currLine.append("Result Binary  => ");
        //System.out.print("Result Binary  => ");
        for (int i = 0; i < result.size(); i++) {
            currLine.append(result.get(i));
            //System.out.print(result.get(i));
            if (i > 0 && (i + 1) % 4 == 0) {
                currLine.append(" ");
                //System.out.print(" ");
            }
        }
        //System.out.println();
        writeStr(currLine.toString());
        currLine.setLength(0);

        currLine.append("Result Decimal => ");
        currLine.append(Long.toString(binaryToDecimal(result)));
        //System.out.println("Result Decimal => " + binaryToDecimal(result));
        writeStr(currLine.toString());
        currLine.setLength(0);
        printLine();
    }
}