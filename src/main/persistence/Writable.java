package persistence;


import org.json.JSONObject;

//Based on JsonSerialisationDemo example
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

