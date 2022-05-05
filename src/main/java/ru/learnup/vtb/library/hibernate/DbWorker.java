package ru.learnup.vtb.library.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import ru.learnup.vtb.library.hibernate.entities.AuthorEntity;
import ru.learnup.vtb.library.hibernate.entities.BookEntity;


import javax.persistence.LockModeType;
import java.util.Collection;

public class DbWorker {

    private SessionFactory sessionFactory;

    public DbWorker() {

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure().build();

        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

        this.sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public Collection<BookEntity> getAllBooks() {
        try (final Session session = sessionFactory.openSession()) {
//            session.lock(entity, LockModeType.PESSIMISTIC_WRITE);
            final Query<BookEntity> query = session.createQuery("from BookEntity ", BookEntity.class);
//            query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
            return query.getResultList();
        }
    }

    public AuthorEntity getAuthorByName(String name) {
        try (final Session session = sessionFactory.openSession()) {
            final Query<AuthorEntity> query = session.createQuery("from AuthorEntity  a where a.name = :name", AuthorEntity.class);
            query.setParameter("name", name);
            return query.getSingleResult();

        }
    }

    public boolean saveBook(BookEntity bookEntity) {
        try (final Session session = sessionFactory.openSession()) {
            final Transaction tx = session.beginTransaction();
            session.save(bookEntity);
            tx.commit();
            return true;
        }
    }

    public AuthorEntity saveAuthor(AuthorEntity authorEntity) {
        try (final Session session = sessionFactory.openSession()) {
            final Transaction tx = session.beginTransaction();
            authorEntity.setId((Long) session.save(authorEntity));
            tx.commit();
            return authorEntity;
        }
    }

    public boolean delete(BookEntity bookEntity) {
        try (final Session session = sessionFactory.openSession()) {
            final Query query = session.createQuery("delete BookEntity b where b.id = :id");
            return query.executeUpdate() == 1;
        }
    }
}
