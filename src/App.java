import dao.*;
import Model.*;
import View.*;
import Controller.*;

public class App {
    public static void main(String[] args) {
        SQLConnect sqlConnect = new SQLConnect();
        sqlConnect.Connect();

        View view = new View();
        Model model = new Model();
        new Controller(model, view);
    }
}
