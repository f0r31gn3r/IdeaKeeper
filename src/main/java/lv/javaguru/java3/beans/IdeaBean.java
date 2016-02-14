package lv.javaguru.java3.beans;

/**
 * Created by Anna on 08.02.2016.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.java3.core.dto.idea.IdeaDTO;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@ManagedBean
@RequestScoped
public class IdeaBean{

    private static String ideaLink = new String("http://localhost:8080/rest/ideas/");

    public IdeaDTO currentIdea = new IdeaDTO();

    public IdeaDTO getCurrentIdea() {
        return currentIdea;
    }

    public void setCurrentIdea(IdeaDTO currentIdea) {
        this.currentIdea = currentIdea;
    }

    public IdeaDTO getIdeaById(Long ideaId, Long userId) {
        if(ideaId > 0){
            String link = ideaLink + "get/"+ String.valueOf(ideaId);

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
            currentIdea.setUserId(userId);
        }
        return currentIdea;
    }

    public String deleteIdeaById(Long ideaId, Long userId) {
        //if deletion performed successfully, return 0
        int result = -1;
        String link = ideaLink + "delete/" + String.valueOf(ideaId);

        HttpClient client = new DefaultHttpClient();
        HttpDelete request = new HttpDelete(link);
        HttpResponse response;

        try {
            response = client.execute(request);
            HttpEntity entity = response.getEntity();
            InputStream instream = entity.getContent();
            String resultToString = convertStreamToString(instream).trim();
            result = Integer.parseInt(resultToString);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        if(result == 0){
            refersh();
            return "editUserProfile.faces?faces-redirect=true&userId=" + String.valueOf(userId);

        } else {
            refersh();
            return null;
        }
    }

    //converts to string response given by rest method
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
        return "editUserProfile.faces?faces-redirect=true&userId=" + String.valueOf(userId);
    }

    private static void refersh(){
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        ViewHandler handler = context.getApplication().getViewHandler();
        UIViewRoot root = handler.createView(context, viewId);
        root.setViewId(viewId);
        context.setViewRoot(root);
    }


}