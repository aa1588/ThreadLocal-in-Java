public class ThreadLocalUserDataDemo {

    private static final ThreadLocal<UserData> sessionHolder = new ThreadLocal<>();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            try {
                sessionHolder.set(new UserData("Amrit", "Amrit_Pass", true));
                sleep(100);
                System.out.println("Thread-1 session: " + sessionHolder.get());
            } finally {
                sessionHolder.remove();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                sessionHolder.set(new UserData("Alex", "Alex_Pass", false));
                sleep(50);
                System.out.println("Thread-2 session: " + sessionHolder.get());
            } finally {
                sessionHolder.remove();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                sessionHolder.set(new UserData("John", "John_Pass", true));
                sleep(80);
                System.out.println("Thread-3 session: " + sessionHolder.get());
            } finally {
                sessionHolder.remove();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}

record UserData(String userName, String password, boolean isLoggedIn) {}
