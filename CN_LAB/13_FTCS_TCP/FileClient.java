import java.net.*;
import java.io.*;
import java.util.Scanner;

public class FileClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 9000);
        PrintWriter out   = new PrintWriter(s.getOutputStream(), true);
        InputStream is    = s.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter filename to download: ");
        String fileName = sc.nextLine();
        out.println(fileName);

        String response = br.readLine();
        if (response.startsWith("ERROR")) {
            System.out.println("Server: " + response);
        } else {
            FileOutputStream fos = new FileOutputStream("downloaded_" + fileName);
            byte[] buf = new byte[1024]; int len;
            while ((len = is.read(buf)) != -1) fos.write(buf, 0, len);
            fos.close();
            System.out.println("File downloaded as: downloaded_" + fileName);
        }
        s.close();
    }
}
