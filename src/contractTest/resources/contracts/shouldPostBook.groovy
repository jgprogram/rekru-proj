package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return all books in bookstore"
    request {
        method POST()
        url "/books"
        headers {
            contentType "application/json"
        }
        body(
                [
                        isbn    : "0-4327-9079-9",
                        title   : "Book One",
                        authorId: 1
                ]
        )
        bodyMatchers {
            jsonPath('$.isbn', byRegex("([a-zA-Z0-9]-){13}").asString())
            jsonPath('$.title', byRegex(nonEmpty()).asString())
            jsonPath('$.authorId', byRegex(anInteger()).asInteger())
        }
    }
    response {
        status 200
    }
}