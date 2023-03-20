package com.vattenfall.bookstore.infrastructure.persistence;

import java.util.Optional;

import com.vattenfall.bookstore.domain.Author;
import com.vattenfall.bookstore.domain.AuthorRepository;

class AuthorOrmRepository implements AuthorRepository {

    private final AuthorDao authorDao;

    AuthorOrmRepository(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Optional<Author> findById(int authorId) {
        return authorDao.findByAuthorId(authorId)
                        .map(AuthorEntity::toDomainObject);
    }

    @Override
    public void save(Author author) {
        authorDao.save(new AuthorEntity(
                author.id(),
                author.name(),
                author.surname()
        ));
    }
}