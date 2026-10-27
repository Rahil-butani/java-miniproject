import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TransactionProcessor {
    private ExecutorService executorService;
    
    public TransactionProcessor(int poolSize) {
        executorService = Executors.newFixedThreadPool(poolSize);
    }
    
    public void submit(Runnable task) {
        executorService.execute(task);
    }
    
    public void stop() throws InterruptedException {
        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);
    }
}
