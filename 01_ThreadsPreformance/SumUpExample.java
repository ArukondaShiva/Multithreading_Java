public class SumUpExample {

    private long startRange;
    private long endRange;
    private long sum = 0;

    private static long MAX_NUM = Integer.MAX_VALUE;

    public SumUpExample(long startRange,long endRange){
        this.startRange = startRange;
        this.endRange = endRange;
    }


    public void add(){
        for(long i=startRange;i<=endRange;i++){
           sum += i;
        }
    }
    
    static void oneThread(){
        long start = System.currentTimeMillis();
        //   System.out.println(start);
        SumUpExample s = new SumUpExample(1, MAX_NUM);
        s.add();
        long end = System.currentTimeMillis();
        System.out.println("Single Thread sum : "+s.sum+" took "+(end-start)+" MilliSeconds");
    }

    static void twoThreads() throws InterruptedException{
        long start = System.currentTimeMillis();

        SumUpExample s1 = new SumUpExample(1, MAX_NUM/2);
        SumUpExample s2 = new SumUpExample((MAX_NUM/2)+1, MAX_NUM);

        Thread t1 = new Thread(()->{
            s1.add();
        });

        Thread t2 = new Thread(()->{
            s2.add();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        
        long finalSum = s1.sum+s2.sum;
        long end = System.currentTimeMillis();

        System.out.println("Two Threads final sum :"+finalSum+" took "+(end-start)+" MilliSeconds");

    }


    public static void runTest() throws InterruptedException{
        oneThread();
        twoThreads();
    }

}
