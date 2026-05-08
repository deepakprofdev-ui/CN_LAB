public class CRC {
    static String xor(String a, String b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length(); i++)
            sb.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        return sb.toString();
    }

    static String divide(String dividend, String divisor) {
        int dl = divisor.length();
        String rem = dividend.substring(0, dl);
        for (int i = dl; i <= dividend.length(); i++) {
            if (rem.charAt(0) == '1')
                rem = xor(rem, divisor);
            else
                rem = xor(rem, "0".repeat(dl));
            rem = rem.substring(1);
            if (i < dividend.length())
                rem += dividend.charAt(i);
        }
        return rem;
    }

    public static void main(String[] args) {
        String data     = "11010011101100";
        String divisor  = "1011";
        int zeros       = divisor.length() - 1;

        // Encode
        String padded   = data + "0".repeat(zeros);
        String crc      = divide(padded, divisor);
        String frame    = data + crc;

        System.out.println("Original Data   : " + data);
        System.out.println("Divisor (Gen)   : " + divisor);
        System.out.println("CRC Remainder   : " + crc);
        System.out.println("Transmitted Frame: " + frame);

        // Case 1: No Error
        String r1 = divide(frame, divisor);
        System.out.println("\n[Without Error]");
        System.out.println("Remainder: " + r1 + " -> " + (r1.contains("1") ? "Error!" : "No Error"));

        // Case 2: With Error (flip bit 3)
        char[] errFrame = frame.toCharArray();
        errFrame[3] = (errFrame[3] == '0') ? '1' : '0';
        String errorFrame = new String(errFrame);
        String r2 = divide(errorFrame, divisor);
        System.out.println("\n[With Error - bit flipped]");
        System.out.println("Error Frame: " + errorFrame);
        System.out.println("Remainder: " + r2 + " -> " + (r2.contains("1") ? "Error Detected!" : "No Error"));
    }
}
