package ex03;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Program {

    public LinkedList<Thread> listThreads = new LinkedList<>();
    public static final Object monitor = new Object();

    public static void main(String[] args) throws FileNotFoundException {
        Program program = new Program();
        program.execute();
    }

    public void execute() throws FileNotFoundException {

        int COUNT_OF_THREADS = 10;
        String filename = "src/ex03/files_urls.txt";

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        Path upload = Paths.get("upload");
        if (!Files.exists(upload)) {
            try {
                Files.createDirectory(upload);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < COUNT_OF_THREADS; i++){
            listThreads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){
                            String s = null;
                            synchronized (monitor) {
                                if (reader.ready())
                                    s = reader.readLine();
                            }
                            if (s == null)
                                break;
                            String[] arr = s.split(" ");
                            String path = arr[1];
                            int numberOfFile = Integer.parseInt(arr[0]);

                            System.out.println(Thread.currentThread().getName() + " start download file number " + numberOfFile);
                            String[] in = path.split("/");
                            URL url = new URL(path);
                            InputStream inputStream = url.openStream();
                            Files.copy(inputStream, new File("upload/" + in[in.length - 1]).toPath());
                            inputStream.close();
                            System.out.println(Thread.currentThread().getName() + " finish download file number " + numberOfFile);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }

        for (Thread thread : listThreads){
            thread.start();
        }
    }

}
