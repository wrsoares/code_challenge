package utils;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Utils {

    static Properties properties = new Properties();
    public static String parseJsonFile(String json) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get("src/test/java/resources/"+json+".json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }

    public static String getProperty(String property) {
        InputStream input;
        Properties prop = new Properties();
        try {
            input = new FileInputStream("src/test/java/resources/test.properties");
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop.getProperty(property);
    }

    public static JSONArray extractJsonArray(Response response) {
        JSONArray posts = new JSONArray(response.asPrettyString());
        return posts;
    }

    public static List<Map<String, String>> extractPosts(JSONArray posts) {
        List<Map<String, String>> listPosts = new ArrayList<>();
        for (Object o : posts
        ) {
            JSONObject post = new JSONObject(o.toString());
            Map<String, String> valuePost = new HashMap<>();
            valuePost.put("slug", String.valueOf(post.get("slug")));
            valuePost.put("status", String.valueOf(post.get("status")));
            valuePost.put("publishedAt", String.valueOf(post.get("publishedAt")));
            valuePost.put("updatedAt", String.valueOf(post.get("updatedAt")));
            listPosts.add(valuePost);
        }
        return listPosts;
    }

    public static void writeLog(Response response)
            throws IOException {
        List posts = extractPosts(extractJsonArray(response));
        BufferedWriter writer = new BufferedWriter(new FileWriter("target/test.log"));
        writer.write("| slug | status | publishedAt | updatedAt |");
        writer.newLine();
        writer.write("|:-----------|-----------:|------------:|-------------:|");
        writer.newLine();
        for (Object valuePost: posts
             ) {
            Map<String, String> values = (Map<String, String>) valuePost;

            writer.write("| "+values.get("slug")+" | "+values.get("status")+" | "+values.get("publishedAt")+" |" +
                    " "+values.get("publishedAt")+" |");
            writer.newLine();
        }
        writer.close();
    }
}
