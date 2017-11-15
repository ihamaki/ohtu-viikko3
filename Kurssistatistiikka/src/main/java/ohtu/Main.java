package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "011120775";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/" + studentNr + "/submissions";
        String submissionsResponse = Request.Get(url).execute().returnContent().asString();
        String courseResponse = Request.Get("https://studies.cs.helsinki.fi/ohtustats/courseinfo/").execute().returnContent().asString();
        String statsResponse = Request.Get("https://studies.cs.helsinki.fi/ohtustats/stats").execute().returnContent().asString();

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(submissionsResponse, Submission[].class);
        Course course = mapper.fromJson(courseResponse, Course.class);

        JsonParser parser = new JsonParser();
        JsonObject parsedData = parser.parse(statsResponse).getAsJsonObject();

        printStudentStats(subs, course, studentNr);
        printCourseStats(parsedData);
    }

    public static void printStudentStats(Submission[] subs, Course course, String studentNr) {
        System.out.println("Kurssi: " + course + "\n");

        int hoursTotal = 0;
        int exercisesTotal = 0;

        System.out.println("opiskelijanumero: " + studentNr + "\n");
        for (Submission sub : subs) {
            System.out.println("viikko " + sub.getWeek());
            System.out.print("  tehtyjä tehtäviä yhteensä: " + sub.getExercises().size());
            System.out.print(" (maksimi " + course.getExercises().get(sub.getWeek() - 1) + "), ");
            System.out.print("aikaa kului " + sub.getHours() + " tuntia, ");
            System.out.println("tehdyt tehtävät: " + sub.printExercises());
            hoursTotal += sub.getHours();
            exercisesTotal += sub.getExercises().size();
        }
        System.out.println("\nyhteensä: " + exercisesTotal + " tehtävää, " + hoursTotal + " tuntia");
    }

    public static void printCourseStats(JsonObject parsedData) {
        int totalSubs = 0;
        int totalExercises = 0;

        for (int i = 1; i < parsedData.size(); i++) {
            totalSubs += parsedData.get(i + "").getAsJsonObject().get("students").getAsInt();
            totalExercises += parsedData.get(i + "").getAsJsonObject().get("exercise_total").getAsInt();
        }

        System.out.println("\nKurssilla yhteensä " + totalSubs + " palautusta, palautettuja tehtäviä " + totalExercises + " kpl");
    }
}
