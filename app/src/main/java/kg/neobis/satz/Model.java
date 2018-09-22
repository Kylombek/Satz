package kg.neobis.satz;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Model {

    @SerializedName("text")
    public ArrayList<String> list = new ArrayList<>();


    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;


    }
}

