# This repo is for the backend system used in Talk-To-ChatGPT mobile app:
## Only two APIs were used:
### Save Question:
Saves the question, answer, date of creation, request as a JSON, response as a JSON, names of voice files (user's question and AI's answer) into MySQL DB.
Saves the question data into a Kafka topic.
Uploads the voice files into Object Storage Server (MinIO).
### Get Questions History:
Retrieve the questions from DB to be visible to the user in a GET request.
## Libraries used:
Spring Web, MySQL Server, Apache Kafka, MinIO, Spring JPA.
