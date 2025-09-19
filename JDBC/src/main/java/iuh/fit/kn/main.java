package iuh.fit.kn;

public class main {
    public static void main(String[] args) throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        System.out.println("MariaDB driver loaded OK!");
    }

}
