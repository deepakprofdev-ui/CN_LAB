import java.net.*;
import java.io.*;

public class FileServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(9000);
        System.out.println("File Server running on port 9000...");
        Socket s = ss.accept();

        // Read requested file name
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String fileName = in.readLine();
        System.out.println("Client requested: " + fileName);

        File f = new File(fileName);
        OutputStream os = s.getOutputStream();
        PrintWriter pw = new PrintWriter(os, true);

        if (!f.exists()) {
            pw.println("ERROR: File not found");
        } else {
            pw.println("OK:" + f.length());
            FileInputStream fis = new FileInputStream(f);
            byte[] buf = new byte[1024]; int len;
            while ((len = fis.read(buf)) != -1) os.write(buf, 0, len);
            fis.close();
            System.out.println("File sent: " + fileName);
        }
        s.close(); ss.close();
    }
}
