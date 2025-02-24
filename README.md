Todo List — демонстрационный проект для создания RESTful приложения управления задачами (TODO list) с использованием Spring Boot и PostgreSQL. Проект реализует базовый функционал для создания, получения, обновления и удаления задач, а также включает безопасность с помощью Spring Security и JWT, и автоматическую генерацию API-документации через springdoc-openapi.

        Функциональность
CRUD операции для задач: 
  - Создание новой задачи с указанием необходимых параметров (например, заголовок, описание, срок выполнения).  
  - Получение списка всех задач.  
  - Обновление существующих задач.  
  - Удаление задач.

Безопасность:
  - Защищённые API-эндпоинты с использованием Spring Security.  
  - Аутентификация пользователей с применением JWT.

Документация API: 
  - Автоматически генерируемая документация с помощью springdoc-openapi.


        Технологический стек
- Java 17
- Spring Boot 3.4.2
- Spring Data JPA – для взаимодействия с базой данных.
- Spring Security – для обеспечения безопасности.
- JWT (jjwt) – для реализации аутентификации.
- PostgreSQL – СУБД для хранения данных.
- Lombok – для сокращения шаблонного кода.
- springdoc-openapi-starter-webmvc-ui – для генерации и отображения документации API.

        Установка и запуск
Требования
- Java 17
- Maven (либо можно использовать предоставленный [Maven Wrapper](./mvnw))
- PostgreSQL

Шаг 1. Клонирование репозитория
Откройте терминал и выполните команду:
git clone https://github.com/myktybek-abdikash/todo-list-new.git

cd todo-list-new

Шаг 2. Настройка подключения к базе данных
В файле `src/main/resources/application.properties` укажите параметры подключения к вашей базе данных PostgreSQL. Например:
spring.datasource.url=jdbc:postgresql://localhost:5432/todo_list_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update

Шаг 3. Сборка и запуск приложения
Запустите приложение с помощью Maven Wrapper:
./mvnw spring-boot:run

Или соберите проект и запустите JAR-файл:
./mvnw clean install
java -jar target/todo-list-new-0.0.1-SNAPSHOT.jar

Приложение будет запущено по адресу http://localhost:8080

Документация API
Автоматически сгенерированная документация доступна по адресу:
http://localhost:8080/swagger-ui/index.html

Здесь можно ознакомиться с описанием всех доступных эндпоинтов и протестировать их.

        Автор
Myktybek Abdikash
