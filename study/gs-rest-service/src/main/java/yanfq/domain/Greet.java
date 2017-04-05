package yanfq.domain;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

/**
 * Created by yanfq on 2017/3/23.
 */
@Component
public class Greet {
    @Id
    private String id;
    private String name;
    private String content;

    public Greet(){}

    public Greet(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString(){
        return "{\"id\":"+id+",\"content\":\""+content+"\"}";
    }
}
