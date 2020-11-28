package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BoothTestDrive {

    private byte bit = 0; // 비트수
    private long multiplicand = 0; // 피승수
    private long multiplier = 0; // 승수
    private StringBuilder binaryMultiplicand = new StringBuilder();
    private StringBuilder binaryMultiplier = new StringBuilder();

    public long getMultiplicand() {
        return multiplicand;
    }

    public long getMultiplier() {
        return multiplier;
    }

    public String getBinaryMultiplicand() {
        return binaryMultiplicand.toString();
    }

    public String getBinaryMultiplier() {
        return binaryMultiplier.toString();
    }

    // 사용자에게 입력받음
    public boolean getUserInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Enter a bit(4, 8, 16, 32, 64): ");
            bit = Byte.parseByte(br.readLine());
            System.out.print("Enter a multiplicand: ");
            multiplicand = Long.parseLong(br.readLine());
            System.out.print("Enter a multiplier: ");
            multiplier = Long.parseLong(br.readLine());
        } catch (IOException e) {
            System.out.println("입력에 문제가 생겼습니다. 다시 입력해주세요.");
            return false;
        }
        return true;
    }

    // 음수일 경우 2의 보수로 변환
    public StringBuilder convertToNegative(String inputString) {
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == '0') {
                tmp.append('1');
            }
            else {
                tmp.append('0');
            }
        }

        int lastZeroIndex = 0;
        for (int i = tmp.length() - 1; i >= 0; i--) {
            if (tmp.charAt(i) == '0') {
                tmp.setCharAt(i, '1');
                lastZeroIndex = i;
                break;
            }
        }
        // lastZeroIndex가 LSB가 아닐 경우
        if (lastZeroIndex + 1 != tmp.length()) {
            for (int i = lastZeroIndex + 1; i < tmp.length(); i++) {
                tmp.setCharAt(i, '0');
            }
        }
        return tmp;
    }

    // 입력받은 값을 2진수로 변환
    public void convertToBinary() {
        // 변환 과정에서 나머지 값을 저장하는 변수
        String rest = "";

        // 음수일 경우 일단 양수로 변환 후 나중에 보수를 취해준다.
        boolean isNegativeMultiplicand = false;
        boolean isNegativeMultiplier = false;

        if (multiplicand < 0) {
            multiplicand *= -1;
            isNegativeMultiplicand = true;
        }
        if (multiplier < 0) {
            multiplier *= -1;
            isNegativeMultiplier = true;
        }

        // 2진수로 변환
        while (multiplicand > 0) {
            rest = Long.toString(multiplicand % 2);
            binaryMultiplicand.append(rest);
            multiplicand /= 2;
        }
        // bit수 맞추기 위해 0추가
        while (binaryMultiplicand.length() < bit) {
            binaryMultiplicand.append('0');
        }

        // multiplier에도 위와 동일한 연산 수행
        while (multiplier > 0) {
            rest = Long.toString(multiplier % 2);
            binaryMultiplier.append(rest);
            multiplier /= 2;
        }
        while (binaryMultiplier.length() < bit) {
            binaryMultiplier.append('0');
        }

        // 문자열 뒤집기
        binaryMultiplicand.reverse();
        binaryMultiplier.reverse();

        if (isNegativeMultiplicand) {
            StringBuilder tmp = convertToNegative(binaryMultiplicand.toString());
            binaryMultiplicand.setLength(0);
            binaryMultiplicand = tmp;
        }
        if (isNegativeMultiplier) {
            StringBuilder tmp = convertToNegative(binaryMultiplier.toString());
            binaryMultiplier.setLength(0);
            binaryMultiplier = tmp;
        }
    }

    public static void main(String[] args) {

        BoothTestDrive booth = new BoothTestDrive();

        // 올바른 입력이 나올 때 까지 반복
        while (true) {
            if (booth.getUserInput()) break;
        }

        booth.convertToBinary();

        System.out.println(booth.getBinaryMultiplicand());
        System.out.println(booth.getBinaryMultiplier());

    }

}