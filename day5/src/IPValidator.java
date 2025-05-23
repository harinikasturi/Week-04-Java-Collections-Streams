public class IPValidator {
    public static void main(String[] args) {
        String[] ips = {"192.168.1.1", "256.0.0.1", "127.0.0.1", "1.2.3.4.5"};

        for (String ip : ips) {
            System.out.println(ip + " is " + (isValidIP(ip) ? "Valid" : "Invalid"));
        }
    }

    public static boolean isValidIP(String ip) {
        return ip.matches("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
    }
}