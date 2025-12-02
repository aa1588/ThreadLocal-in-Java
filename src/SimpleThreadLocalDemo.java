public class SimpleThreadLocalDemo {

    private static final ThreadLocal<String> userNameHolder = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                userNameHolder.set("Amrit");
                sleep(100);
                System.out.println("Thread-1 username: " + userNameHolder.get());
            } finally {
                userNameHolder.remove();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                userNameHolder.set("Alex");
                sleep(50);
                System.out.println("Thread-2 username: " + userNameHolder.get());
            } finally {
                userNameHolder.remove();
            }
        });

        t1.start();
        t2.start();
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}
