public class ErrorDetectionCorrection {

    // ===== CRC Error Detection =====
    static String xor(String a, String b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length(); i++)
            sb.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        return sb.toString();
    }

    static String crc(String data, String gen) {
        int gl = gen.length();
        String padded = data + "0".repeat(gl - 1);
        String rem = padded.substring(0, gl);
        for (int i = gl; i <= padded.length(); i++) {
            rem = (rem.charAt(0) == '1') ? xor(rem, gen) : xor(rem, "0".repeat(gl));
            rem = rem.substring(1);
            if (i < padded.length()) rem += padded.charAt(i);
        }
        return rem;
    }

    // ===== Hamming Code Error Correction =====
    static int[] hammingEncode(int[] data) {
        int[] code = {0, 0, data[0], 0, data[1], data[2], data[3]};
        code[0] = code[2] ^ code[4] ^ code[6]; // P1
        code[1] = code[2] ^ code[5] ^ code[6]; // P2
        code[3] = code[4] ^ code[5] ^ code[6]; // P4
        return code;
    }

    static void hammingCheck(int[] code) {
        int p1 = code[0] ^ code[2] ^ code[4] ^ code[6];
        int p2 = code[1] ^ code[2] ^ code[5] ^ code[6];
        int p4 = code[3] ^ code[4] ^ code[5] ^ code[6];
        int errPos = p1 * 1 + p2 * 2 + p4 * 4;
        if (errPos == 0) System.out.println("Hamming: No error detected.");
        else {
            System.out.println("Hamming: Error at bit position " + errPos + ". Correcting...");
            code[errPos - 1] ^= 1;
            System.out.print("Corrected Code: ");
            for (int b : code) System.out.print(b);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // CRC Demo
        System.out.println("=== CRC Error Detection ===");
        String data = "10110011", gen = "1011";
        String crcBits = crc(data, gen);
        String frame = data + crcBits;
        System.out.println("Data: " + data + "  CRC: " + crcBits + "  Frame: " + frame);
        String check = crc(frame, gen);
        System.out.println("Check: " + (check.contains("1") ? "Error!" : "No Error"));

        // Hamming Demo
        System.out.println("\n=== Hamming Code Error Correction ===");
        int[] databits = {1, 0, 1, 1};
        int[] encoded = hammingEncode(databits);
        System.out.print("Encoded: ");
        for (int b : encoded) System.out.print(b);
        System.out.println();

        // Introduce error at bit 5
        encoded[4] ^= 1;
        System.out.print("With Error: ");
        for (int b : encoded) System.out.print(b);
        System.out.println();
        hammingCheck(encoded);
    }
}
