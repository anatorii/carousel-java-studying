import io.github.cdimascio.dotenv.Dotenv;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class App {
    static Dotenv dotenv = null;
    static Image[] image = new Image[] {null, null, null, null, null};
    static String[] coords = new String[] {"126.636848,45.755647", "116.391430,39.901842", "82.626415,39.157604", "81.314158,31.062542", "127.534047,50.257486"};
    static String[] titles = new String[] {"Харбин", "Пекин", "Такла-Макан", "Гора Кайлас", "Благовещенск"};

    public static void main(String[] args) {
        dotenv = Dotenv.configure()
                .directory("assets")
                .filename(".env")
                .load();

        try {
            String apikey = App.dotenv.get("API_KEY");
            for (int i = 0; i < image.length; i++) {
                String zString = "&z=11";
                String point = "&ll=" + coords[i];
                String uri = "https://static-maps.yandex.ru/v1?size=650,450" + "&apikey=" + apikey + point + zString;
                URL url = new URL(uri);
                image[i] = ImageIO.read(url);
                System.out.println("image loaded: " + i);
            }
            System.out.println("all images loaded");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }

}
