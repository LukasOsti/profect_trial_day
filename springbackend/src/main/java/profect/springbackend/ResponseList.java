package profect.springbackend;

import java.util.List;

public class ResponseList {

    private List<String> list;

    public ResponseList(List<String> list){
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
