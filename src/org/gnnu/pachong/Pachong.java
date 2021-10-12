package org.gnnu.pachong;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pachong {
    public static void main(String[] args) throws IOException {
        Pachong p=new Pachong();
        Set<StringBuffer> mainData=p.getMainData("https://www.zhipin.com/guangzhou/");
        List<List<String>> person=new LinkedList<>();
        List<List<String>> jobs=new LinkedList<>();
        mainData.stream().forEach(o->{
            try {
                StringBuffer stringBuffer = p.readWebSite(o.substring(0));
              List<String> personList= p.getFilterData(stringBuffer,"<div class=\"name\">(.*)</div>",new LinkedList());
                List<String> jobList= p.getFilterData(stringBuffer,"<div class=\"name\">(.*)</div>",new LinkedList());
                person.add(personList);
                jobs.add(jobList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println(person);
    }
    public Set<StringBuffer> getMainData(String linkUrl){
        Set<StringBuffer> list=null;
        try{
           StringBuffer stringBuffer=readWebSite(linkUrl);
            String regex="<a href=\\\"/*.*/\\\"";
            Pattern pattern= Pattern.compile(regex);
            Matcher matcher=pattern.matcher(stringBuffer);
            list=new HashSet<>();
            while(matcher.find()) {
                list.add(new StringBuffer(matcher.group()));
            }
            list.stream().forEach(o->{
                o.replace(0,9,"");
                o.replace(o.length()-1,o.length(),"");
                o.insert(0,"https://www.zhipin.com");
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private StringBuffer readWebSite(String linkUrl) throws IOException {
        URL url=new URL(linkUrl);
        BufferedReader reader=new BufferedReader(new InputStreamReader(url.openStream()));
        BufferedWriter writer=new BufferedWriter(new FileWriter("index.html"));
        StringBuffer stringBuffer=new StringBuffer();
        String line;
        while((line=reader.readLine())!=null){
            stringBuffer.append(line+"\n");
            writer.write(line);
            writer.newLine();
        }
        reader.close();
        writer.close();
        return stringBuffer;
    }
    private List<String> getFilterData(StringBuffer stringBuffer,String regex,List list){
        Pattern pattern= Pattern.compile(regex);
        Matcher matcher=pattern.matcher(stringBuffer);
        while(matcher.find()) {
            list.add(new StringBuffer(matcher.group()));
        }
        return list;
    }

}
