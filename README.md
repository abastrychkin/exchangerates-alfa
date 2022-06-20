# exchangerates-alfa
 Инструкция по запуску

1. Скачайте файл exchangerates-alfa-0.0.1-SNAPSHOT.jar
![изображение](https://user-images.githubusercontent.com/107584204/174689347-9f684b9e-4721-4806-a41c-4ce23d80b793.png)

3. Откройте командную строку
5. Перейдите в каталог, где находится загруженный файл (с помощью команды cd [путь])
6. Выполните команду: java -jar exchangerates-alfa-0.0.1-SNAPSHOT.jar
![изображение](https://user-images.githubusercontent.com/107584204/174689283-87b050fb-2e4f-49db-b6ab-e64b1ea8e697.png)


8. После запуска программы следует открыть браузер. 
9. Работа с сервисом осуществляется с помощью запросов вида:
 http://localhost:8080/{currency}
где {currency} - трёхбуквенная аббревиатура выбранной валюты.
Пример: http://localhost:8080/eur
Сервис вернёт html-страничку с gif-изображением.
![изображение](https://user-images.githubusercontent.com/107584204/174689465-0b525443-df52-4db3-a16a-6a1c1aaba05b.png)


7. Полный список доступных валют можно получить по ссылке: https://docs.openexchangerates.org/docs/supported-currencies
