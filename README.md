# This repo is for the backend system used in Talk-To-ChatGPT mobile app:
## Only two APIs were used:
### Get Voiced Answer:
1) Takes the user's voice record of the question from client device. 
2) #### The Spring-Boot App connects to OpenAI for three steps:
a) Transcription of client's voice records into a text using whisper-1 model. 
b) Chat completion by sending the generated text to OpenAI to get the proper answer as a text using gpt-turbo-3.5 model.
c) Converting answer text into AI-voiced file in mp3 format using tts-1 model and sending the voiced answer as byte[] to the user.
3) Uploading the voice files (Q & A) to object storage server using minIO
4) Saving the question data into both Kafka topic and MySQL DB.
5) Returns the voiced answer to the user.
### Get Questions History:
Retrieve the questions from DB to be visible to the user in a GET request.
## Libraries used:
Spring Web, MySQL Server, Apache Kafka, MinIO, Spring JPA, OkHttp, OpenAI
