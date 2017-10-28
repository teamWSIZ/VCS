package zajecia3.service;

import zajecia3.model.News;

public class Wykop {
    public static void main(String[] args) {
        News nowy = new News("zmiany w rządzie", "TBD");
        System.out.println(nowy);

        ApplicationService appService = new ApplicationService();

        System.out.println(appService.getAllNews());
        appService.addNews(new News("XXXXXXXXXXX", "karramba"));
        System.out.println(appService.getAllNews());

    }
}