
package  com.udacity.rwaheng.popularmovies.model.movie;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class SpokenLanguage {

    @SerializedName("iso_639_1")
    private String iso6391;
    private String name;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
