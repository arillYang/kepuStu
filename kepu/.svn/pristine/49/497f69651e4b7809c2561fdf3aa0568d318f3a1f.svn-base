package com.kepu.util;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
public class GsonUtil {
	public static Gson getGson(){
		Gson gson = new GsonBuilder()
        .registerTypeAdapter(
            new TypeToken<TreeMap<String, Object>>(){}.getType(), 
            new JsonDeserializer<TreeMap<String, Object>>() {
			@Override
			public TreeMap<String, Object> deserialize(JsonElement json,
					Type arg1, JsonDeserializationContext arg2)
					throws JsonParseException {
				  TreeMap<String, Object> treeMap = new TreeMap<>();
	                JsonObject jsonObject = json.getAsJsonObject();
	                Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
	                for (Map.Entry<String, JsonElement> entry : entrySet) {
	                    treeMap.put(entry.getKey(), entry.getValue());
	                }
	                return treeMap;
			}
        }).create();
		return gson;
	}
}
