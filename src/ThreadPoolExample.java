import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    
    private static final ThreadLocal<String> requestId = new ThreadLocal<>();
    
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        // Submit 5 tasks, but only 2 threads in pool
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                try {
                    requestId.set("REQ-" + taskId);
                    System.out.println(Thread.currentThread().getName() + 
                                     " processing: " + requestId.get());
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    requestId.remove();  // Critical!
                }
            });
        }
        
        executor.shutdown();
    }
}