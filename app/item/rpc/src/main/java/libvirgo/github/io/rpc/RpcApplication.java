package libvirgo.github.io.rpc;

import item.Hello;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcApplication.class, args);
        Hello.StreamReq.Builder b = Hello.StreamReq.newBuilder();
        Hello.StreamReq req = b.setName("hello").build();
        System.out.println("protobuf:"+ req.getName());
    }

}
