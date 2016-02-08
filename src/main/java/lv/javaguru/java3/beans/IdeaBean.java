package lv.javaguru.java3.beans;

/**
 * Created by Anna on 08.02.2016.
 */

        import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.java3.core.dto.idea.IdeaDTO;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@ManagedBean
@RequestScoped
public class IdeaBean{

    //private static String userLink = new String("http://localhost:8080/rest/users/get/");
    //private static String userIdeasLink = new String("http://localhost:8080/rest/users/get_ideas/");
    private static String ideaLink = new String("http://localhost:8080/rest/ideas/get/");


//    @ManagedProperty(value="#{param.id}")
//    private static Long id;
//
//    public UserDTO userDTO;
//
//    public List<IdeaDTO> userIdeas = new ArrayList<IdeaDTO>();

    public IdeaDTO currentIdea = new IdeaDTO();

    public IdeaDTO getCurrentIdea() {
        return currentIdea;
    }

    public void setCurrentIdea(IdeaDTO currentIdea) {
        this.currentIdea = currentIdea;
    }

//	public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public List<IdeaDTO> getUserIdeas() {
//        return userIdeas;
//    }
//
//    public void setUserIdeas(List<IdeaDTO> userIdeas) {
//        this.userIdeas = userIdeas;
//    }
//
//    public UserDTO getUserDTO() {
//        return userDTO;
//    }
//
//    public void setUserDTO(UserDTO userDTO) {
//        this.userDTO = userDTO;
//    }
//
//    public UserDTO getPersonById(Long id) {
//
//        String link = userLink + String.valueOf(id);
//
//        HttpClient client = new DefaultHttpClient();
//        HttpGet request = new HttpGet(link);
//        HttpResponse response;
//        String result = null;
//        userDTO = null;
//        try {
//            response = client.execute(request);
//            HttpEntity entity = response.getEntity();
//
//            if (entity != null) {
//
//                // A Simple JSON Response Read
//                InputStream instream = entity.getContent();
//                result = convertStreamToString(instream);
//                System.out.println("RESPONSE result: " + result);
//
//                org.json.JSONObject xmlJSONObj = XML.toJSONObject(result);
//                System.out.println("RESPONSE json: " + xmlJSONObj);
//                //userDTO = xmlJSONObj;
//
//                ObjectMapper mapper = new ObjectMapper();
//                String jsonString = xmlJSONObj.toString();
//                jsonString = jsonString.substring(jsonString.indexOf(":")+1, jsonString.length()-1);
//                System.out.println("RESPONSE json string: " + jsonString);
//
//                userDTO = mapper.readValue(jsonString, UserDTO.class);
//
//                instream.close();
//            }
//            // Headers
//            org.apache.http.Header[] headers = response.getAllHeaders();
//            for (int i = 0; i < headers.length; i++) {
//                System.out.println(headers[i]);
//            }
//        } catch (Exception e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//
//        getUserIdeas(userIdeasLink + String.valueOf(id));
//        return userDTO;
//    }

    public IdeaDTO getIdeaById(Long id) {
        if(id > 0){
            String link = ideaLink + String.valueOf(id);

            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(link);
            HttpResponse response;
            String result = null;
            currentIdea = null;
            try {
                response = client.execute(request);
                HttpEntity entity = response.getEntity();

                if (entity != null) {

                    // A Simple JSON Response Read
                    InputStream instream = entity.getContent();
                    result = convertStreamToString(instream);
                    System.out.println("RESPONSE result idea: " + result);

                    System.out.println("RESPONSE json string idea: " + result);

                    ObjectMapper mapper = new ObjectMapper();
                    currentIdea = mapper.readValue(result, IdeaDTO.class);

                    instream.close();
                }
                // Headers
                org.apache.http.Header[] headers = response.getAllHeaders();
                for (int i = 0; i < headers.length; i++) {
                    System.out.println(headers[i]);
                }
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else {
            currentIdea = new IdeaDTO();
            currentIdea.setIdeaId(0L);
        }
        return currentIdea;
    }

//    public void getUserIdeas(String link) {
//
//        HttpClient client = new DefaultHttpClient();
//        HttpGet request = new HttpGet(link);
//        HttpResponse response;
//        String result = null;
//        try {
//            response = client.execute(request);
//            HttpEntity entity = response.getEntity();
//
//            if (entity != null) {
//
//                // A Simple JSON Response Read
//                InputStream instream = entity.getContent();
//                result = convertStreamToString(instream);
//                System.out.println("RESPONSE result: " + result);
//
//                org.json.JSONObject xmlJSONObj = XML.toJSONObject(result);
//                System.out.println("RESPONSE json: " + xmlJSONObj);
//
//                ObjectMapper mapper = new ObjectMapper();
//                String jsonString = xmlJSONObj.toString();
//
//                jsonString = jsonString.substring(jsonString.indexOf("["), jsonString.length()-2);
//                System.out.println("RESPONSE json string: " + jsonString);
//
//                Set<IdeaDTO> userIdeasSET = new HashSet<IdeaDTO>();
//                userIdeasSET = mapper.readValue(jsonString,
//                        TypeFactory.defaultInstance().constructCollectionType(Set.class,
//                                IdeaDTO.class));
//
//                if(userIdeasSET.size() > 0){
//                    for(IdeaDTO i : userIdeasSET){
//                        userIdeas.add(i);
//                    }
//                }
//
//
//                instream.close();
//            }
//            // Headers
//            org.apache.http.Header[] headers = response.getAllHeaders();
//            for (int i = 0; i < headers.length; i++) {
//                System.out.println(headers[i]);
//            }
//        } catch (Exception e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public String editIdea(Long ideaId, Long userId) {
        return "editIdea.faces?ideaId=" + String.valueOf(ideaId) + "&userId=" + String.valueOf(userId);
    }

    public String createIdea(Long userId) {
        return "editIdea.faces?userId=" + String.valueOf(userId);
    }

    public String backToUserProfile(Long userId) {
        return "editUserProfile.faces?userId=" + String.valueOf(userId);
    }
}