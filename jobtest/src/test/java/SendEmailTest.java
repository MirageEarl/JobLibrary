import com.tangbo.test.AppApplication;
import com.tangbo.test.serivces.EmailOptionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppApplication.class)
public class SendEmailTest {

    @Resource
    private EmailOptionService emailOptionService;

    @Test
    public void sendEmail() {
        emailOptionService.sendEmailToBirthdayEmployee();
    }
}
