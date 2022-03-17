package ru.learnup.vtb.library.hibernate;

import ru.learnup.vtb.library.hibernate.entities.AuthorEntity;
import ru.learnup.vtb.library.hibernate.entities.BookEntity;

public class Main {
    public static void main(String[] args) {
        DbWorker dbWorker = new DbWorker();


        /*AuthorEntity lermontov = dbWorker.getAuthorByName("Михаил Лермонтов");

        dbWorker.saveBook(
                new BookEntity(null,
                        "Герой нашего времени",
                        lermontov));*/

        System.out.println(dbWorker.getAuthorByName("Михаил Лермонтов"));

        /*for (BookEntity allBook : dbWorker.getAllBooks()) {
            System.out.println(allBook);
        }*/
    }
}
