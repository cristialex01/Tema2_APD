import java.io.*;

class ThrFile1 implements Runnable {
    FileWriter fw1;
    FileWriter fw2;
    Integer id;

    public ThrFile1(FileWriter fw1, FileWriter fw2, Integer id) {
        this.fw1 = fw1;
        this.fw2 = fw2;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread[] thread = new Thread[Global.p];
            int start = (int)(id * (double)Global.users.size() / Global.p);
            int end = (int)(Math.min((id + 1) * (double)Global.users.size() / Global.p, Global.users.size()));

            for(int j = start; j < end; j++) {
                if (Global.users.get(j).productNo.equals("0"))
                    continue;
                for (int i = 0; i < Global.p; i++)
                    thread[i] = new Thread(new ThrFile2(Global.users.get(j).name,
                            Integer.parseInt(Global.users.get(j).productNo), fw2, i));
                for (int i = 0; i < Global.p; i++)
                    thread[i].start();
                for (int i = 0; i < Global.p; i++)
                    thread[i].join();

                Sem.semnal1.acquire();
                fw1.write(Global.users.get(j).name + "," + Global.users.get(j).productNo + ",shipped\n");
                Sem.semnal1.release();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
