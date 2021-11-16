import com.bjpowernode.beans.User;
import com.bjpowernode.mapper.UserMapper;
import com.bjpowernode.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//设置Spring测试启动类
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({ "classpath:applicationContext.xml"})
public class Tester {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;

    @Test
    public void fun1(){
        User user = userMapper.getUser("1000","123");
        System.out.println(user);
    }

    @Test
    public void fun2(){
        User user = userService.getUser("1000", "123", "127.0.0.1");
        System.out.println(user);
    }
}