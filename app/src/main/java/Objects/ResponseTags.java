package Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseTags {

    @SerializedName("mes")
    @Expose
    private String mess = "";
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("en_vi")
    @Expose
    private List<String> tags;

    public ResponseTags(String mes, List<String> tags) {
        this.mess = mes;
        this.tags = tags;
    }

    public String getMes() {
        return mess;
    }

    public void setMes(String mes) {
        this.mess = mes;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
