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
Создайте нового пользователя и пустую БД:
```mysql
mysql -u root -p -e 'CREATE DATABASE IF NOT EXISTS officerent;'
mysql -u root -p -e "CREATE USER 'officerent'@'localhost' IDENTIFIED BY 'officerent12345';"
mysql -u root -p -e "GRANT ALL ON officerent.* TO 'officerent'@'localhost';"
```
```
mvn compile
mvn spring-boot:run
```
После запуска проекта выполните следующие SQL скрипты:
```mysql
INSERT INTO role(name) VALUES
                        ('ADMIN_ROLE'),
                        ('MANAGER_ROLE'),
                        ('USER_ROLE');
                        
INSERT INTO office(address, description, image, name, price) VALUES
                        ('address1', 'description1', 'bigoffice.jpg', 'Большой офис', 120000),
                        ('address2', 'description2', 'smalloffice.jpg', 'Маленький офис', 70000),
                        ('address3', 'description3', 'office.jpg', 'Средний офис', 90000),
                        ('address4', 'description4', 'office.jpg', 'name4', 4),
                        ('address5', 'description5', 'office.jpg', 'name5', 5);
```
После создания первого пользователя, предоставьте ему права администратора:
```mysql
UPDATE users_roles SET roles_id = 1 WHERE user_id = 1;
```

## Скриншоты

<table>
    <tr>
        <td>
              <div align="center"><img src="https://github.com/mirokiro5/OfficeRent/blob/main/images/main_page.jpg" width="40%"></div>
        </td>
          </tr>
      <tr>
        <td>
            <div align="center"><img src="https://github.com/mirokiro5/OfficeRent/blob/main/images/details_page.jpg" width="40%"></div>
        </td>
      </tr>
      <tr>
          <td>
            <div align="center"><img src="https://github.com/mirokiro5/OfficeRent/blob/main/images/rent_page.jpg" width="40%"></div>
        </td>
    </tr>
</table>


## Автор
Студент группы ИКПИ-01 Миронов Кирилл
