package com.json;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;

class BookedSets
{
    private List<String> values;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
    
}
public class JSONServlet extends HttpServlet { 
    private static final long serialVersionUID = 1L;
    // This will store all received articles
    List<Article> articles = new LinkedList<Article>();
 
    /***************************************************
     * URL: /jsonservlet
     * doPost(): receives JSON data, parse it, map it and send back as JSON
     ****************************************************/
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
 
        System.out.println("com.json.JSONServlet.doPost()");
        // 1. get received JSON data from request
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
 
        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();
 
        // 3. Convert received JSON to Article
        System.out.println("com.json.JSONServlet.doPost()");
        System.out.println(json);
        BookedSets obj=mapper.readValue(json,BookedSets.class);
        List<String> values=obj.getValues();
        for(int i=0;i<values.size();i++)
        {
            System.out.println(values.get(i));
        }
//        Article article = mapper.readValue(json, Article.class);
// 
//        // 4. Set response type to JSON
//        response.setContentType("application/json");            
//        
//        // 5. Add article to List<Article>
//        articles.add(article);
//        for(int i=0;i<articles.size();i++)
//        {
//            System.out.println(articles.get(i).getTitle());
//        }
//        // 6. Send List<Article> as JSON to client
//        mapper.writeValue(response.getOutputStream(), articles);
    }
}