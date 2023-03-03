package libvirgo.github.io.rpc;

import com.google.protobuf.MessageLite;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class RpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcApplication.class, args);
        ArrayList<MessageLite> list = new ArrayList<>();
    }

}
