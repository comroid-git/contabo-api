import org.comroid.contabo.ContaboConnection;

public class MainTest {
    private static ContaboConnection contabo;

    public static void main(String[] args) {
        contabo = new ContaboConnection(args[0]);
    }
}
