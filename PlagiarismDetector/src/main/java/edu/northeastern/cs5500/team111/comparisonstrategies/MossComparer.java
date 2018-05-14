package edu.northeastern.cs5500.team111.comparisonstrategies;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;

import edu.northeastern.cs5500.team111.util.AlgoProperties;
import it.zielke.moji.MossException;
import org.apache.commons.io.FileUtils;
import it.zielke.moji.SocketClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Class to implement the Moss Comparer from Stanford MOSS library
 * which tests the plagiarism score from MOSS website and scrapes the data
 */
public class MossComparer{

    /**
     * compare method locates local python files and sends to the moss server
     * and gets a comparision score
     * @param filePath path of our files to train moss
     * @return a moss score to train out models
     * @throws MossException Connection or internal error from moss system
     * @throws IOException Input output exception in r/w of files
     */
    public Double compare(String filePath) throws MossException, IOException {

        Collection<File> files = FileUtils.listFiles(
                new File(filePath),new String[] { "py" }, true);

        SocketClient socketClient = new SocketClient();

        //set your Moss user ID alt:72750537
        socketClient.setUserID("482356212");

        //set the programming language of all student source codes


            socketClient.setLanguage("python");

        //initialize connection and send parameters
            socketClient.run();



        //upload all source files of students
        for (File f : files) {
                socketClient.uploadFile(f);

        }

        //finished uploading, tell server to check files

            socketClient.sendQuery();

        //get URL with Moss results and do something with it
        String url = socketClient.getResultURL().toString();


        return scrapeMoss(url);

    }

    /**
     * scrapeMoss tries to scrape the data from the moss url
     * @param url the url to connect to with all parameters set
     * @return a score
     */
    private double scrapeMoss(String url) {
        String htmlPage = null;
        try {
            htmlPage = Jsoup.connect(url).get().html();
        } catch (IOException e) {
            PlagiarismdetectorApplication.getLoggerInstance().error(String.valueOf(e));
        }
        
        Document doc = Jsoup.parse(htmlPage, "UTF-8");
        Elements mossResult = doc.getElementsByTag("table");

        if(mossResult.select("tr").size()>1) {

            Element row = mossResult.select("tr").get(1);

            Element col1 = row.select("td").get(0);
            int ind1 = col1.text().indexOf('%');
            String percentage1 = col1.text().substring(ind1 - AlgoProperties.loadInt("MOSS_CONSTANT"), ind1);

            Element col2 = row.select("td").get(1);
            int ind2 = col2.text().indexOf('%');
            String percentage2 = col2.text().substring(ind2 - AlgoProperties.loadInt("MOSS_CONSTANT"), ind2);

            return Math.max(Double.parseDouble(percentage1), Double.parseDouble(percentage2));
        }
        return 0;
    }
}