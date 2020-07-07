/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class DataCollector {
    private ArrayList<Case> cases=new ArrayList<>();
   private DataManager dataManager=new DataManager();
   public void collector() throws Exception
   {
       SSLContext context= SSLContext.getInstance("SSL");
        context.init(null, new TrustManager[]{
         new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] xcs, String string){
                //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void checkServerTrusted(X509Certificate[] xcs, String string){
                 //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                 return new X509Certificate[0];//To change body of generated methods, choose Tools | Templates.
            }
        }
        }, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        Document document=Jsoup.connect("https://www.mohfw.gov.in/").get();
        Element div=document.getElementById("state-data");
        Element table=div.getElementsByTag("table").first();
        Element header=table.getElementsByTag("thead").first();
        Elements columnHeader=header.getElementsByTag("th");
        Element tableBody=table.getElementsByTag("tbody").first();
        Elements rows=tableBody.getElementsByTag("tr");
       
        Platform.runLater(new Runnable() {
           @Override
           public void run() {
               int colCount=6;
               for(int i=0 ;i<35;i++)
            {
            Elements cols=rows.get(i).getElementsByTag("td");
            List<String> colValues=cols.eachText();
            
          
                Case cs=new Case(i+1,
                    colValues.get(1), 
                    Integer.parseInt(colValues.get(2)), 
                    Integer.parseInt(colValues.get(3)), 
                    Integer.parseInt(colValues.get(4)), 
                    Integer.parseInt(colValues.get(5))
            );
            cases.add(cs);
                        
            
            }
               dataManager.addAll(cases);

           }
           
       });
        
   }
}
