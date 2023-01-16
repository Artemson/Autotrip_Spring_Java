## Описание
Autotrip - это веб-сервис по поиску билетов и выбора направлений.

## Использованные технологии
Java, Maven, Spring Framework, PostgreSQL

## Декомпозиция проекта
1. Личный кабинет
   * Страница клиента
   * Страница менеджера
   * Страница администратора
2. Системные роли
   * Сохранение ролей в базе данных
   * Возможность изменения роли пользователя администратором
3. Хранение данных об турах
   * Хранение в базе данных направления, статус, даты вылета, стоимость.

## Запуск
Скачать утилиту Docker и запустить контейнер:
```docker pull postgres
```
```
docker run --name some-postgres -p 5432:5432 -e POSTGRES_PASSWORD=admin -e POSTGRES_USER=admin -e POSTGRES_DB=application -d postgres
```

## Скриншоты

<table>
    <tr>
        <td>
              <div align="center"><img src="https://github.com/Artemson/Autotrip_Spring_Java/blob/main/src/main/resources/static/img/add_trip.png" width="40%"></div>
        </td>
          </tr>
      <tr>
        <td>
            <div align="center"><img src="https://github.com/Artemson/Autotrip_Spring_Java/blob/main/src/main/resources/static/img/add_trip.png" width="40%"></div>
        </td>
      </tr>
      <tr>
          <td>
            <div align="center"><img src="https://github.com/Artemson/Autotrip_Spring_Java/blob/main/src/main/resources/static/img/add_trip.png" width="40%"></div>
        </td>
    </tr>
    <tr>
          <td>
            <div align="center"><img src="https://github.com/Artemson/Autotrip_Spring_Java/blob/main/src/main/resources/static/img/add_trip.png" width="40%"></div>
        </td>
    </tr>
</table>


## Авторы
Студенты группы ИКПИ-01 Ветлугаев Артём и Бандурин Никита
