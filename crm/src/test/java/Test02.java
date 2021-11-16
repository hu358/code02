import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test02 {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        // 用户时间
        LocalDateTime expireTimeTemp = LocalDateTime.parse("2021-10-01 22:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(expireTimeTemp);
        //
        if (now.isBefore(expireTimeTemp)){
            System.out.println("hello");
        }
    }
}
