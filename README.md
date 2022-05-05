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

- для работы с БД MySQL: "java -jar artifacts/aqa-shop.jar -P:jdbc.url=jdbc:mysql://localhost:3306/touristicBooking -P:jdbc.user=app -P:jdbc.password=pass" 

- для работы с БД MySQL или "java -jar artifacts/aqa-shop.jar -P:jdbc.url=jdbc:postgresql://localhost:5432/postgresql -P:jdbc.user=app -P:jdbc.password=pass" для работы с БД PostgreSQL


8) В терминале ввести команду  

- для работы с БД postgreSQL: "./gradlew test  -D:jdbc.url=jdbc:postgresql://localhost:5432/postgresql -D:jdbc.user=app -D:jdbc.password=pass"

- для работы с БД MySQL: "./gradlew test -D:jdbc.url=jdbc:mysql://localhost:3306/touristicBooking -D:jdbc.user=app -D:jdbc.password=pass"



