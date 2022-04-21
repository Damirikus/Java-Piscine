package ex03;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Program {

    public LinkedList<Thread> listThreads = new LinkedList<>();
    public ArrayList<String> urls = new ArrayList<>();
    public Map<Integer, String> urlsReal = new HashMap<>();

    public static void main(String[] args) {
        Program program = new Program();
        program.execute();
    }

    public void execute(){

        int COUNT_OF_THREADS = 4;
        String filename = "files_urls.txt";

        try( BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
//            Files.createDirectory(Paths.get("upload"));
            while (reader.ready()){
                String str = reader.readLine();

                String[] arr1 = str.split(" ");
                urls.add(arr1[1]);
            }

            for (int i = 0; i < COUNT_OF_THREADS; i++){
                listThreads.add(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            int numberOfFile = 0;
                            String path = null;
                            while (urlsReal.size() == 0);
//                                wait();

                            synchronized (urlsReal){
                                for (Integer i : urlsReal.keySet()){
                                    numberOfFile = i;
                                }
                                path = urlsReal.get(numberOfFile);
                                urlsReal.remove(numberOfFile);
                            }
//                            notify();
                            System.out.println(Thread.currentThread().getName() + " start download file number " + numberOfFile);
                            String[] in = path.split("/");
                            URL url = new URL(path);
                            InputStream inputStream = url.openStream();
                            Files.copy(inputStream, new File("upload/" + in[in.length - 1]).toPath());
                            inputStream.close();
                            System.out.println(Thread.currentThread().getName() + " finish download file number " + numberOfFile);
                            synchronized (listThreads){
                                listThreads.addLast(Thread.currentThread());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }));
            }

            int k = 0;
            for (String u : urls){
                while (urlsReal.size() == 1){
//                    wait();
                }

                synchronized (urlsReal){
                    urlsReal.put(k + 1, u);
                }

                Thread thread;
                synchronized (listThreads){
                    while (listThreads.size() == 0);
                    thread = listThreads.removeFirst();
                }
                thread.start();

                k++;
            }




        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
