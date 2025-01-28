FROM openjdk:17
WORKDIR /appContainer
COPY ./target/bootrest-book.jar    /appContainer
EXPOSE 8282
CMD ["java","-jar","bootrest-book.jar"]