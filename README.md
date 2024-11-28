
## Кофемашина на Java, Spring и PostgreSQL

## Выпоолненные задачи

+ Добавить простейшую страницу регистрации. Пользователь вводит свои логин и пароль и данная информация вносится в базу данных, пользователю присваивается роль пользователя (User) приложения.
+ Добавить простейшую форму аутентификации. Форма создается программно, а не автоматически генерируется Spring.
+ В приложении должен быть предусмотрен пользователь — администратор (Admin) с ролью отличной, от User.
+ Разграничить уровни доступа к страницам приложения. Пользователь (User) имеет доступ только к страницам просмотра всех записей и запросов. Администратор (Admin) имеет возможность добавлять, редактировать и удалять записи.
+ Информация о пользователях и их ролях должна храниться в базе данных. Способ хранения — на усмотрение студента.
+ Предусмотреть возможность выхода из приложения (logout).
+ Продемонстрировать умение настраивать безопасность на уровне представлений. Для этого реализуется приветствие пользователя после его входа и отображение элемента на основе его роли.


### Как запустить

Запустите скрипт ```init.sql``` с помощью следующей команды:

```
psql -U postgres -d postgres -f init.sql
```

В этом скрипте создается и заполняется тестовыми данными таблица coffee_machines

Откройте терминал и перейдите в директорию вашего проекта, затем выполните следующие команды:

```
mvn clean install
mvn spring-boot:run
```