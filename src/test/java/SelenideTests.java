import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTests {

    @Test
    public void openPageWithSelenideTest(){
        Configuration.startMaximized = true;
        open("https://the-internet.herokuapp.com/");
    }


}
