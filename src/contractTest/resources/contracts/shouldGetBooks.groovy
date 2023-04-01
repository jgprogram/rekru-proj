package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return all books in bookstore"
    request {
        method GET()
        url "/books"
    }
    response {
        status 200
        headers {
            contentType "application/json"
        }
        body([[
                      [
                              isbn    : "0-4327-9079-9",
                              title   : "Book One",
                              authorId: 1
                      ],
                      [
                              isbn    : "0-5384-4445-2",
                              title   : "Book Two",
                              authorId: 12
                      ]
              ]])
    }
}
