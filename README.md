# ТЕСТИРОВАНИЕ ВЕБ-СЕРВИСА ПОКУПКИ ТУРА


##ИНСТРУКЦИЯ ДЛЯ ЗАПУСКА АВТО-ТЕСТОВ

На компьютере должен стоять следующее предустановленное ПО:

+ Intellij IDEA Ultimate
+ Docker +Docker compose
+ Git +Git Bash


1) На локальном ПК Создать папку для проекта

2) Нажать правой кнопкой мыши на папку, выбрать пункт "Git Bash Here".

3) Инициировать на папке пустой репозиторий с помощью команды в открывшемся терминале "git init"

4) Скопировать проект с GitHub с помощью команды "git clone https://github.com/LidiiaBogdanova/QA-diploma.git"

5) Открыть проект в  Intellij IDEA Ultimate

6) В терминале запустить команду "docker-compose up --build -d"

7) Запустить веб-сервис командой в терминале 

- для работы с БД MySQL: java  '-Dspring.datasource.url=jdbc:mysql://localhost:3306/touristicBooking' '-Dspring.datasource.username=app' '-Dspring.datasource.password=pass' -jar .\artifacts\aqa-shop.jar


- для работы с БД PostgreSQL java  '-Dspring.datasource.url=jdbc:postgresql://localhost:5432/postgresql' '-Dspring.datasource.username=app' '-Dspring.datasource.password=pass' -jar .\artifacts\aqa-shop.jar


8) В терминале ввести команду  

- для работы с БД postgreSQL: ./gradlew test  '-Ddb.url=jdbc:postgresql://localhost:5432/postgresql' '-Ddb.user=app' '-Ddb.password=pass'

- для работы с БД MySQL: ./gradlew test '-Ddb.url=jdbc:mysql://localhost:3306/touristicBooking' '-Ddb.user=app' '-Ddb.password=pass' 

9) Для генерации отчета Allure ввести в терминале команду ./gradlew allureServe

10) Итоговая отчетность находится в папке [Reports](?)




