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

6) В терминале запустить команду "docker-compose up --build"

7) Запустить веб-сервис командой в терминале "java -jar artifacts/aqa-shop.jar"

8) Перейти в класс BookingTest(QA-diploma/src/test/java/ru/netology/test)

9) Запустить тесты командой Ctrl+Shift+F10


