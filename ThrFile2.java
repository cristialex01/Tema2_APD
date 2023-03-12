import java.io.*;

class ThrFile2 implements Runnable {
    String name;
    Integer prodsLeft;
    FileWriter fw;
    Integer id;

    public ThrFile2(String name, Integer prodsLeft, FileWriter fw, Integer id) {
        this.name = name;
        this.prodsLeft = prodsLeft;
        this.fw = fw;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Sem.semnal2.acquire();
            int start = (int)(id * (double)Global.products.size() / Global.p);
            int end = (int)(Math.min((id + 1) * (double)Global.products.size() / Global.p, Global.products.size()));
            for(int i = start; i < end; i++) {
                Sem.semnal1.acquire();
                if (Global.products.get(i).name.equals(name)) {
                    fw.write(name + "," + Global.products.get(i).product + ",shipped\n");
                    prodsLeft--;
                }
                Sem.semnal1.release();

                if (prodsLeft == 0)
                    break;
            }
            Sem.semnal2.release();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
