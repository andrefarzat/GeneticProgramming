package com.andrefarzat.GP;


public class CLI {

    public static void main(String[] args) {
        System.out.println("Starting GP!");

        String[] leftList = {"afoot","catfoot","dogfoot","fanfoot","foody","foolery","foolish","fooster","footage","foothot","footle","footpad","footway","hotfoot","jawfoot","mafoo","nonfood","padfoot","prefool","sfoot","unfool"};
        String[] rightList = {"Atlas","Aymoro","Iberic","Mahran","Ormazd","Silipan","altared","chandoo","crenel","crooked","fardo","folksy","forest","hebamic","idgah","manlike","marly","palazzi","sixfold","tarrock","unfold"};

        GP gp = new GP("test", 0, leftList, rightList);
        gp.run();

        System.out.println("Done!");
    }
}
