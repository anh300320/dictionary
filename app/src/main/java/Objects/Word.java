package Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Word implements Serializable {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("index")
    @Expose
    private Integer index;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("meaning")
    @Expose
    private List<String> meaning = null;
    @SerializedName("trait")
    @Expose
    private List<String> trait = null;
    @SerializedName("__v")
    @Expose
    private Integer v;

    /*protected Word(Parcel in){
        id = in.readString();
        index = in.readInt();
        key = in.readString();
        type = in.readString();
        in.readStringList(meaning);
        in.readStringList(trait);
        v = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(index);
        dest.writeString(key);
        dest.writeString(type);
        dest.writeStringList(meaning);
        dest.writeStringList(trait);
        dest.writeInt(v);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel source) {
            return new Word(source);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getMeaning() {
        return meaning;
    }

    public void setMeaning(List<String> meaning) {
        this.meaning = meaning;
    }

    public List<String> getTrait() {
        return trait;
    }

    public void setTrait(List<String> trait) {
        this.trait = trait;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
