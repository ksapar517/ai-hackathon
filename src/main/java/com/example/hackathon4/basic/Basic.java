package com.example.hackathon4.basic;

import com.example.hackathon4.chatGPT.ChatGPT;
import com.example.hackathon4.google.GoogleTranslate;
import com.example.hackathon4.google.Tts;
import com.example.hackathon4.photo.Photo;
import com.example.hackathon4.video.Video;
import org.apache.tomcat.util.json.ParseException;

import java.io.IOException;

public class Basic {
    String url12 = null;
    String answer= null;
    String answerGoogle = null;
    String answerGoogle1 = null;
    String answerGoogle2 = null;

    String question =null;
    GoogleTranslate Translate = new GoogleTranslate();
    ChatGPT ChatGPT = new ChatGPT();
    Tts ttss = new Tts();
    Photo Photo=new Photo();


    Video save=new Video();




    public String basick(String name,String llong,String language,String size) throws IOException, InterruptedException, ParseException {


            answerGoogle = Translate.translate(name, language, "en");
            System.out.println(answerGoogle);

            answerGoogle=name;

            question= " Write information topic about "+answerGoogle+" for "+llong+"-second video";

            answerGoogle1 = ChatGPT.chatGPT(question);

            System.out.println(answerGoogle1);

//
             answerGoogle2=Translate.translate(answerGoogle1,"en",language);
              String aa=null;
             System.out.println(answerGoogle2);
             if(answerGoogle2 == null){
                   System.out.println("_____________________");
                   ttss.tts(answerGoogle1,language);
                   aa=answerGoogle1;
                   }
             else {
                   ttss.tts(answerGoogle2, language);
                   aa=answerGoogle2;
             }

                answer =save.saveVideo(aa,name,llong);
               System.out.println(answer);



             // System.out.println(answerGoogle2);
               return answer;
    }



}
