import java.io.*;
import java.util.concurrent.Semaphore;

class Tema2{
    public static void main(String[] argv) throws IOException, InterruptedException {
        Global.p = Integer.parseInt(argv[1]);
        Sem.semnal2 = new Semaphore(Global.p);

        File in1 = new File(argv[0] + "/orders.txt");
        File in2 = new File(argv[0] + "/order_products.txt");
        BufferedReader br1 = new BufferedReader(new FileReader(in1));
        BufferedReader br2 = new BufferedReader(new FileReader(in2));

        File out1 = new File("orders_out.txt");
        File out2 = new File("order_products_out.txt");
        FileWriter fw1 = new FileWriter(out1);
        FileWriter fw2 = new FileWriter(out2);

        String str;
        while((str = br1.readLine()) != null)
            Global.users.add(new Users(str.split(",")[0], str.split(",")[1]));
        while((str = br2.readLine()) != null)
            Global.products.add(new Products(str.split(",")[0], str.split(",")[1]));
        br1.close();
        br2.close();

        Thread[] thread = new Thread[Global.p];
        for(int i = 0; i < Global.p; i++)
            thread[i] = new Thread(new ThrFile1(fw1, fw2, i));
        for(int i = 0 ; i < Global.p; i++)
            thread[i].start();
        for(int i = 0; i < Global.p; i++)
            thread[i].join();
        fw1.close();
        fw2.close();
    }
}